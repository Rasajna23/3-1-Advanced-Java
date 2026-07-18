import java.sql.*;

public class StudentJDBCApp {

    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/company";
        String user = "javauser";
        String password = "Java@12345";

        try {

            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish Connection
            Connection con = DriverManager.getConnection(url, user, password);
            Statement stmt = con.createStatement();

            System.out.println("Connected Successfully!");

            // Create Employee Table
            String createTable =
                "CREATE TABLE IF NOT EXISTS employee(" +
                "id INT PRIMARY KEY," +
                "name VARCHAR(50)," +
                "salary DOUBLE)";

            stmt.executeUpdate(createTable);
            System.out.println("Employee table created successfully.");

            // Clear old records (optional)
            stmt.executeUpdate("DELETE FROM employee");

            // Insert Records
            stmt.executeUpdate("INSERT INTO employee VALUES (101,'Ravi',35000)");
            stmt.executeUpdate("INSERT INTO employee VALUES (102,'Sita',42000)");
            stmt.executeUpdate("INSERT INTO employee VALUES (103,'Kiran',50000)");

            System.out.println("Initial records inserted.");

            // Display Records
            System.out.println("\nInitial Records:");
            displayRecords(stmt);

            // Insert Two New Records
            stmt.executeUpdate("INSERT INTO employee VALUES (104,'Meena',45000)");
            stmt.executeUpdate("INSERT INTO employee VALUES (105,'Ramesh',55000)");

            System.out.println("\nTwo new records inserted.");

            // Update Record
            stmt.executeUpdate("UPDATE employee SET salary=60000 WHERE id=102");
            System.out.println("One record updated.");

            // Delete Record
            stmt.executeUpdate("DELETE FROM employee WHERE id=103");
            System.out.println("One record deleted.");

            // Display Updated Records
            System.out.println("\nFinal Records:");
            displayRecords(stmt);

            stmt.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Method to Display Employee Records
    public static void displayRecords(Statement stmt) throws SQLException {

        ResultSet rs = stmt.executeQuery("SELECT * FROM employee");

        System.out.println("--------------------------------------");
        System.out.println("ID\tNAME\tSALARY");
        System.out.println("--------------------------------------");

        while (rs.next()) {

            int id = rs.getInt("id");
            String name = rs.getString("name");
            double salary = rs.getDouble("salary");

            System.out.println(id + "\t" + name + "\t" + salary);
        }

        rs.close();
    }
}
