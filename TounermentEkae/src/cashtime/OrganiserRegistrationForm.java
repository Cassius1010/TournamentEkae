
package cashtime;

import javax.swing.*;
import java.awt.*;

public class OrganiserRegistrationForm {

    public static void registerOrganiser(JFrame parent) {

        JTextField fullNameField = new JTextField(20);
        JTextField emailField = new JTextField(20);
        JTextField phoneField = new JTextField(20);
        JTextField organisationField = new JTextField(20);

        JPasswordField passwordField = new JPasswordField(20);
        JPasswordField confirmPasswordField = new JPasswordField(20);

        JPanel form = new JPanel(new GridLayout(6, 2, 10, 12));

        form.setBorder(
                BorderFactory.createEmptyBorder(15, 15, 15, 15)
        );

        form.add(new JLabel("Full Name:"));
        form.add(fullNameField);

        form.add(new JLabel("Email Address:"));
        form.add(emailField);

        form.add(new JLabel("Phone Number:"));
        form.add(phoneField);

        form.add(new JLabel("Organisation Name:"));
        form.add(organisationField);

        form.add(new JLabel("Password:"));
        form.add(passwordField);

        form.add(new JLabel("Confirm Password:"));
        form.add(confirmPasswordField);

        int result = JOptionPane.showConfirmDialog(
                parent,
                form,
                "TournamentEkae - Organiser Registration",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
        );

        if (result != JOptionPane.OK_OPTION) {
            return;
        }

        String fullName = fullNameField.getText().trim();
        String email = emailField.getText().trim().toLowerCase();
        String phone = phoneField.getText().trim();
        String organisation = organisationField.getText().trim();

        String password = new String(passwordField.getPassword());
        String confirmPassword =
                new String(confirmPasswordField.getPassword());

        if (fullName.isEmpty() || email.isEmpty()
                || phone.isEmpty() || organisation.isEmpty()
                || password.isEmpty() || confirmPassword.isEmpty()) {

            JOptionPane.showMessageDialog(
                    parent,
                    "Please complete all fields.",
                    "Missing Information",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {

            JOptionPane.showMessageDialog(
                    parent,
                    "Please enter a valid email address.",
                    "Invalid Email",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        if (!phone.matches("\\d{10}")) {

            JOptionPane.showMessageDialog(
                    parent,
                    "Enter a valid 10-digit phone number.",
                    "Invalid Phone Number",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        if (password.length() < 8) {

            JOptionPane.showMessageDialog(
                    parent,
                    "Your password must contain at least 8 characters.",
                    "Weak Password",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        if (!password.equals(confirmPassword)) {

            JOptionPane.showMessageDialog(
                    parent,
                    "Your passwords do not match.",
                    "Password Error",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        Organiser organiser = new Organiser(
                0,
                fullName,
                email,
                phone,
                organisation,
                password
        );

        boolean registered =
                OrganiserDAO.registerOrganiser(organiser);

        if (registered) {

            JOptionPane.showMessageDialog(
                    parent,
                    "Your organiser account has been created successfully!\n\n"
                    + "You can now log in using your email and password.",
                    "Registration Successful",
                    JOptionPane.INFORMATION_MESSAGE
            );

        } else {

            JOptionPane.showMessageDialog(
                    parent,
                    "Registration failed.\n"
                    + "This email may already be registered, "
                    + "or a database error occurred.",
                    "Registration Failed",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
}
