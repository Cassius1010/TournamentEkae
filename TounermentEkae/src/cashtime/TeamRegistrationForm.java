
package cashtime;

import javax.swing.*;
import java.awt.*;

public class TeamRegistrationForm {

    public static Team registerTeam(JFrame parent) {

        JTextField teamNameField = new JTextField();
        JTextField captainNameField = new JTextField();
        JTextField contactField = new JTextField();
        JTextField locationField = new JTextField();

        String[] provinces = {
            "Gauteng",
            "Limpopo",
            "Mpumalanga",
            "North West",
            "Free State",
            "KwaZulu-Natal",
            "Eastern Cape",
            "Western Cape",
            "Northern Cape"
        };

        JComboBox<String> provinceBox =
                new JComboBox<>(provinces);

        JPanel form = new JPanel(new GridLayout(5, 2, 10, 10));

        form.add(new JLabel("Team Name:"));
        form.add(teamNameField);

        form.add(new JLabel("Captain Name:"));
        form.add(captainNameField);

        form.add(new JLabel("Contact Number:"));
        form.add(contactField);

        form.add(new JLabel("Kasi / Village:"));
        form.add(locationField);

        form.add(new JLabel("Province:"));
        form.add(provinceBox);

        int result = JOptionPane.showConfirmDialog(
                parent,
                form,
                "Register Your Football Team",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
        );

        if (result != JOptionPane.OK_OPTION) {
            return null;
        }

        String teamName = teamNameField.getText().trim();
        String captain = captainNameField.getText().trim();
        String phone = contactField.getText().trim();
        String location = locationField.getText().trim();
        String province = (String) provinceBox.getSelectedItem();

        if (teamName.isEmpty() || captain.isEmpty()
                || phone.isEmpty() || location.isEmpty()) {

            JOptionPane.showMessageDialog(
                    parent,
                    "Please complete all registration fields."
            );

            return null;
        }

        if (!phone.matches("\\d{10}")) {
            JOptionPane.showMessageDialog(
                    parent,
                    "Enter a valid 10-digit contact number."
            );

            return null;
        }

        return new Team(
                0,
                teamName,
                captain,
                phone,
                location,
                province
        );
    }
}
