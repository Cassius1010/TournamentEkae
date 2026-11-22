
package cashtime;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.security.SecureRandom;
import java.security.MessageDigest;
import java.security.spec.KeySpec;
import java.util.Base64;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class OrganiserDAO {

    private static final int ITERATIONS = 120000;
    private static final int KEY_LENGTH = 256;

    public static boolean registerOrganiser(Organiser organiser) {

        String sql = "INSERT INTO organisers "
                + "(full_name, email, phone_number, "
                + "organisation_name, password) "
                + "VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = Database.connect();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            String hashedPassword =
                    hashPassword(organiser.getPassword());

            statement.setString(1, organiser.getFullName());
            statement.setString(2, organiser.getEmail());
            statement.setString(3, organiser.getPhoneNumber());
            statement.setString(4, organiser.getOrganisationName());
            statement.setString(5, hashedPassword);

            statement.executeUpdate();

            return true;

        } catch (SQLException e) {

            if (e.getMessage() != null &&
                    e.getMessage().contains("UNIQUE")) {

                System.out.println("Email already registered.");

            } else {

                System.out.println("Registration error: "
                        + e.getMessage());
            }

            return false;

        } catch (Exception e) {

            System.out.println("Password processing error: "
                    + e.getMessage());

            return false;
        }
    }

    public static Organiser loginOrganiser(
            String email, String password) {

        String sql = "SELECT * FROM organisers WHERE email = ?";

        try (Connection connection = Database.connect();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setString(1, email);

            try (ResultSet result = statement.executeQuery()) {

                if (result.next()) {

                    String storedPassword =
                            result.getString("password");

                    if (verifyPassword(password, storedPassword)) {

                        return new Organiser(
                                result.getInt("organiser_id"),
                                result.getString("full_name"),
                                result.getString("email"),
                                result.getString("phone_number"),
                                result.getString("organisation_name"),
                                ""
                        );
                    }
                }
            }

        } catch (SQLException e) {

            System.out.println("Login error: " + e.getMessage());

        } catch (Exception e) {

            System.out.println("Password verification error: "
                    + e.getMessage());
        }

        return null;
    }

    private static String hashPassword(String password)
            throws Exception {

        byte[] salt = new byte[16];
        new SecureRandom().nextBytes(salt);

        PBEKeySpec spec = new PBEKeySpec(
                password.toCharArray(),
                salt,
                ITERATIONS,
                KEY_LENGTH
        );

        byte[] hash = SecretKeyFactory
                .getInstance("PBKDF2WithHmacSHA256")
                .generateSecret(spec)
                .getEncoded();

        spec.clearPassword();

        return Base64.getEncoder().encodeToString(salt)
                + ":"
                + Base64.getEncoder().encodeToString(hash);
    }

    private static boolean verifyPassword(
            String password, String storedPassword)
            throws Exception {

        String[] parts = storedPassword.split(":");

        if (parts.length != 2) {
            return false;
        }

        byte[] salt = Base64.getDecoder().decode(parts[0]);
        byte[] originalHash = Base64.getDecoder().decode(parts[1]);

        PBEKeySpec spec = new PBEKeySpec(
                password.toCharArray(),
                salt,
                ITERATIONS,
                KEY_LENGTH
        );

        byte[] testHash = SecretKeyFactory
                .getInstance("PBKDF2WithHmacSHA256")
                .generateSecret(spec)
                .getEncoded();

        spec.clearPassword();

        return MessageDigest.isEqual(originalHash, testHash);
    }
}
