import java.sql.*;

public class DatabaseHelloWorld {
    public static void main(String[] args) {

        // Napajame sa na databazu
        Connection conn;
        String url = "jdbc:mysql://localhost:3306/kniznica_db";
        String username = "root";
        String password = "password";
        System.out.println("Connecting database...");
        try {
            conn = DriverManager.getConnection(url, username, password);
            System.out.println("Database connected!");
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }

        // Vyberame udaje, resp. zaznamy z tabulky knihy
        System.out.println("Výber záznamov z tabuľky knihy");
        try {
            String query = "SELECT * FROM knihy";  // do premennej s nazvom query napiseme SQL  prikaz na vyber udajov z tabulky knihy...
            // * - vypise/reprezentuje vsetky stlpce,tkore vyberame a mozeme sa rozhodnut aj pre konkretne zadanim ich nazvu,napr. id,autor,nazov
            // create the java statement
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            // execute the query, and get a java resultset
            ResultSet rs = preparedStmt.executeQuery(query);
            // iterate through the java resultset
            while (rs.next())
            {
                int id = rs.getInt("id");
                String nazov = rs.getString("nazov");
                String autor = rs.getString("autor");
                // print the results
                System.out.println(nazov);
            }
            preparedStmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}


