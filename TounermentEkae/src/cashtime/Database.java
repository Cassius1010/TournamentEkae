
package cashtime;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {

    private static final String URL = "jdbc:sqlite:TournamentEkae.db";

    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(URL);
    }

    public static void createTables() {

      String organisersTable = """
        CREATE TABLE IF NOT EXISTS organisers (
            organiser_id INTEGER PRIMARY KEY AUTOINCREMENT,
            full_name TEXT NOT NULL,
            email TEXT NOT NULL UNIQUE,
            phone_number TEXT NOT NULL,
            organisation_name TEXT NOT NULL,
            password TEXT NOT NULL,
            date_registered TEXT DEFAULT CURRENT_TIMESTAMP
        )
        """;


        String teamsTable = """
                CREATE TABLE IF NOT EXISTS teams (
                    team_id INTEGER PRIMARY KEY AUTOINCREMENT,
                    team_name TEXT NOT NULL,
                    captain_name TEXT NOT NULL,
                    contact_number TEXT NOT NULL,
                    location TEXT NOT NULL,
                    province TEXT NOT NULL,
                    date_registered TEXT DEFAULT CURRENT_TIMESTAMP
                )
                """;

        String tournamentsTable = """
                CREATE TABLE IF NOT EXISTS tournaments (
                    tournament_id INTEGER PRIMARY KEY AUTOINCREMENT,
                    tournament_name TEXT NOT NULL,
                    description TEXT NOT NULL,
                    venue TEXT NOT NULL,
                    location TEXT NOT NULL,
                    province TEXT NOT NULL,
                    start_date TEXT NOT NULL,
                    end_date TEXT NOT NULL,
                    entry_fee REAL NOT NULL,
                    entry_requirements TEXT NOT NULL,
                    status TEXT DEFAULT 'Pending',
                    date_created TEXT DEFAULT CURRENT_TIMESTAMP
                )
                """;

        try (Connection connection = connect();
             Statement statement = connection.createStatement()) {
        	
        	statement.execute(organisersTable);
            statement.execute(teamsTable);
            statement.execute(tournamentsTable);

            System.out.println("Database tables created successfully.");

        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }
}
