package cashtime;


import javax.swing.*;
import java.awt.*;

public class HomePage {

    private JFrame frame;

    public HomePage() {

        frame = new JFrame("TournamentEkae");
        frame.setSize(900, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        createHeader();
        createMainContent();
        
        frame.setVisible(true);
    }

    private void createHeader() {

        JPanel header = new JPanel(new GridLayout(2, 1));
        header.setBackground(new Color(20, 90, 50));
        header.setBorder(
            BorderFactory.createEmptyBorder(25, 10, 25, 10)
        );

        JLabel title = new JLabel(
            "TOURNAMENTE KAE",
            JLabel.CENTER
        );

        title.setFont(new Font("Arial", Font.BOLD, 32));
        title.setForeground(Color.WHITE);

        JLabel slogan = new JLabel(
            "Your Kasi. Your Village. Your Tournament.",
            JLabel.CENTER
        );

        slogan.setFont(new Font("Arial", Font.PLAIN, 16));
        slogan.setForeground(Color.WHITE);

        header.add(title);
        header.add(slogan);

        frame.add(header, BorderLayout.NORTH);
    }

    private void createMainContent() {

        JPanel content = new JPanel(new GridLayout(1, 3, 20, 20));

        content.setBackground(new Color(245, 248, 245));
        content.setBorder(
            BorderFactory.createEmptyBorder(60, 35, 60, 35)
        );

        JPanel supporterPanel = createPortal(
            "EXPLORE TOURNAMENTS",
            "Discover football tournaments\nin your kasi and village.",
            "Explore Now"
        );

        JPanel teamPanel = createPortal(
            "TEAM PORTAL",
            "Register your football team\nand affiliate with tournaments.",
            "Register Team"
        );

        JPanel organiserPanel = createPortal(
            "ORGANISER PORTAL",
            "Create tournaments, manage teams\nand publish fixtures.",
            "Organiser Login"
        );

        JButton supporterButton =
            (JButton) supporterPanel.getComponent(2);

        JButton teamButton =
            (JButton) teamPanel.getComponent(2);

        JButton organiserButton =
            (JButton) organiserPanel.getComponent(2);

        supporterButton.addActionListener(e -> exploreTournaments());

        teamButton.addActionListener(e -> openTeamRegistration());

        organiserButton.addActionListener(e -> openOrganiserPortal());

        content.add(supporterPanel);
        content.add(teamPanel);
        content.add(organiserPanel);

        frame.add(content, BorderLayout.CENTER);

        JLabel footer = new JLabel(
            "TournamentEkae | Connecting Football Communities",
            JLabel.CENTER
        );

        footer.setBorder(
            BorderFactory.createEmptyBorder(10, 10, 15, 10)
        );

        frame.add(footer, BorderLayout.SOUTH);
    }

    private JPanel createPortal(
            String heading, String description, String buttonText) {

        JPanel panel = new JPanel(new GridLayout(3, 1, 10, 15));

        panel.setBackground(Color.WHITE);

        panel.setBorder(
            BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(
                    new Color(210, 220, 210)
                ),
                BorderFactory.createEmptyBorder(20, 12, 20, 12)
            )
        );

        JLabel title = new JLabel(heading, JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 17));
        title.setForeground(new Color(20, 90, 50));

        JLabel descriptionLabel = new JLabel(
            "<html><center>" + description.replace("\n", "<br>")
            + "</center></html>",
            JLabel.CENTER
        );

        JButton button = new JButton(buttonText);
        button.setBackground(new Color(20, 90, 50));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);

        panel.add(title);
        panel.add(descriptionLabel);
        panel.add(button);

        return panel;
    }

    private void exploreTournaments() {

        JOptionPane.showMessageDialog(
            frame,
            "There are currently no published tournaments.\n"
            + "Please check again soon.",
            "Explore Tournaments",
            JOptionPane.INFORMATION_MESSAGE
        );
    }

    private void openTeamRegistration() {

        Team team = TeamRegistrationForm.registerTeam(frame);

        if (team != null) {

            JOptionPane.showMessageDialog(
                frame,
                "Team registered successfully!\n\n"
                + "Team: " + team.getTeamName() + "\n"
                + "Captain: " + team.getCaptainName() + "\n"
                + "Location: " + team.getLocation() + "\n"
                + "Province: " + team.getProvince()
            );
        }
    }

    private void openOrganiserPortal() {

        String[] options = {
            "Register as Organiser",
            "Organiser Login",
            "Cancel"
        };

        int choice = JOptionPane.showOptionDialog(
                frame,
                "Welcome to TournamentEkae Organiser Portal",
                "Organiser Portal",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                options,
                options[0]
        );

        if (choice == 0) {

            OrganiserRegistrationForm.registerOrganiser(frame);

        } else if (choice == 1) {

            OrganiserLoginForm.loginOrganiser(frame);
        }
    }
}
