
package cashtime;

import javax.swing.*;
import java.awt.*;

public class OrganiserLoginForm {

    public static void loginOrganiser(JFrame parent) {

        JTextField emailField = new JTextField(20);
        JPasswordField passwordField = new JPasswordField(20);

        JPanel form = new JPanel(new GridLayout(2, 2, 10, 12));

        form.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        form.add(new JLabel("Email Address:"));
        form.add(emailField);

        form.add(new JLabel("Password:"));
        form.add(passwordField);

        int result = JOptionPane.showConfirmDialog(
                parent,
                form,
                "TournamentEkae - Organiser Login",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
        );

        if (result != JOptionPane.OK_OPTION) {
            return;
        }

        String email = emailField.getText().trim().toLowerCase();
        String password = new String(passwordField.getPassword());

        if (email.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(
                    parent,
                    "Please enter your email address and password."
            );
            return;
        }

        Organiser organiser = OrganiserDAO.loginOrganiser(email, password);

        if (organiser != null) {
            JOptionPane.showMessageDialog(
                    parent,
                    "Welcome, " + organiser.getFullName() + "!"
            );
        } else {
            JOptionPane.showMessageDialog(
                    parent,
                    "Invalid email address or password.",
                    "Login Failed",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
}
