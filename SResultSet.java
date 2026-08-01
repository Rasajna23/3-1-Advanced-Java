import java.sql.*;

public class SResultSet {

public static void main(String[] args) throws Exception {

    String url = "jdbc:mysql://localhost:3306/company";
    String user = "javauser";
    String password = "Java@12345";

    try {

        // Load MySQL JDBC Driver
        Class.forName("com.mysql.cj.jdbc.Driver");

        // Connect to Database
        Connection con =
                DriverManager.getConnection(url, user, password);

        System.out.println("Database Connected Successfully");

        // Create Scrollable, Read-Only ResultSet
        Statement st = con.createStatement(
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY
        );

        // Execute query on Employee table
        ResultSet rs =
                st.executeQuery(
                        "SELECT * FROM employee ORDER BY id"
                );

        // -----------------------------------------
        // Forward Direction
        // -----------------------------------------

        System.out.println("\nRecords in Forward Direction:");
        System.out.println("--------------------------------------");
        System.out.println("ID\tNAME\tSALARY");
        System.out.println("--------------------------------------");

        while (rs.next()) {

            System.out.println(
                    rs.getInt("id") + "\t" +
                    rs.getString("name") + "\t" +
                    rs.getDouble("salary")
            );
        }

        // -----------------------------------------
        // Backward Direction
        // -----------------------------------------

        System.out.println("\nRecords in Backward Direction:");
        System.out.println("--------------------------------------");
        System.out.println("ID\tNAME\tSALARY");
        System.out.println("--------------------------------------");

        while (rs.previous()) {

            System.out.println(
                    rs.getInt("id") + "\t" +
                    rs.getString("name") + "\t" +
                    rs.getDouble("salary")
            );
        }

        // -----------------------------------------
        // First Record
        // -----------------------------------------

        rs.first();

        System.out.println("\nFirst Employee Record:");

        System.out.println(
                "Row " + rs.getRow() + ": " +
                rs.getInt("id") + "\t" +
                rs.getString("name") + "\t" +
                rs.getDouble("salary")
        );

        // -----------------------------------------
        // Last Record
        // -----------------------------------------

        rs.last();

        System.out.println("\nLast Employee Record:");

        System.out.println(
                "Row " + rs.getRow() + ": " +
                rs.getInt("id") + "\t" +
                rs.getString("name") + "\t" +
                rs.getDouble("salary")
        );

        // -----------------------------------------
        // 2nd Record from Last
        // -----------------------------------------

        rs.last();

        rs.relative(-1);

        System.out.println("\n2nd Employee Record from Last:");

        System.out.println(
                "Row " + rs.getRow() + ": " +
                rs.getInt("id") + "\t" +
                rs.getString("name") + "\t" +
                rs.getDouble("salary")
        );

        // -----------------------------------------
        // 2nd Record from Beginning
        // -----------------------------------------

        rs.absolute(2);

        System.out.println("\n2nd Employee Record from Beginning:");

        System.out.println(
                "Row " + rs.getRow() + ": " +
                rs.getInt("id") + "\t" +
                rs.getString("name") + "\t" +
                rs.getDouble("salary")
        );

        // Close resources
        rs.close();
        st.close();
        con.close();

        System.out.println("\nDatabase Connection Closed");

    } catch (Exception e) {

        e.printStackTrace();
    }
}
}
