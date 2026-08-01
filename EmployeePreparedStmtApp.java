
import java.sql.*;

public class EmployeePreparedStmtApp {

public static void main(String[] args) {

    String url = "jdbc:mysql://localhost:3306/company";
    String user = "javauser";
    String password = "Java@12345";

    try {

        // Load MySQL JDBC Driver
        Class.forName("com.mysql.cj.jdbc.Driver");

        // Connect to Database
        Connection con =
                DriverManager.getConnection(url, user, password);

        System.out.println("Connected Successfully!");

        // Create Employee Table
        String createTable =
                "CREATE TABLE IF NOT EXISTS employee (" +
                "id INT PRIMARY KEY, " +
                "name VARCHAR(50), " +
                "salary DOUBLE)";

        con.createStatement().executeUpdate(createTable);

        System.out.println("Table created successfully.");

        // Create Statement
        Statement stmt = con.createStatement();

        // Clear old records
        // This prevents duplicate primary key errors
        stmt.executeUpdate("DELETE FROM employee");

        // Insert Initial Records
        stmt.executeUpdate(
                "INSERT INTO employee VALUES " +
                "(101, 'Ravi', 35000)");

        stmt.executeUpdate(
                "INSERT INTO employee VALUES " +
                "(102, 'Sita', 42000)");

        stmt.executeUpdate(
                "INSERT INTO employee VALUES " +
                "(103, 'Kiran', 50000)");

        System.out.println("Initial records inserted.");

        // Display Initial Records
        System.out.println("\nInitial Employee Records:");

        displayRecords(con);

        // Insert Two New Records Using PreparedStatement

        String insertSQL =
                "INSERT INTO employee (id, name, salary) " +
                "VALUES (?, ?, ?)";

        PreparedStatement insertStmt =
                con.prepareStatement(insertSQL);

        // Insert Meena
        insertStmt.setInt(1, 104);
        insertStmt.setString(2, "Meena");
        insertStmt.setDouble(3, 45000);

        insertStmt.executeUpdate();

        // Insert Ramesh
        insertStmt.setInt(1, 105);
        insertStmt.setString(2, "Ramesh");
        insertStmt.setDouble(3, 55000);

        insertStmt.executeUpdate();

        System.out.println(
                "Two new employee records inserted.");

        // Update Employee Record

        String updateSQL =
                "UPDATE employee SET salary = ? WHERE id = ?";

        PreparedStatement updateStmt =
                con.prepareStatement(updateSQL);

        updateStmt.setDouble(1, 60000);
        updateStmt.setInt(2, 102);

        int updateCount =
                updateStmt.executeUpdate();

        if (updateCount > 0) {
            System.out.println(
                    "One employee record updated.");
        } else {
            System.out.println(
                    "Employee not found.");
        }

        // Delete Employee Record

        String deleteSQL =
                "DELETE FROM employee WHERE id = ?";

        PreparedStatement deleteStmt =
                con.prepareStatement(deleteSQL);

        deleteStmt.setInt(1, 103);

        int deleteCount =
                deleteStmt.executeUpdate();

        if (deleteCount > 0) {
            System.out.println(
                    "One employee record deleted.");
        } else {
            System.out.println(
                    "Employee not found.");
        }

        // Display Final Records

        System.out.println("\nFinal Employee Records:");

        displayRecords(con);

        // Close Resources

        deleteStmt.close();
        updateStmt.close();
        insertStmt.close();
        stmt.close();
        con.close();

        System.out.println(
                "\nDatabase connection closed.");

    } catch (Exception e) {

        e.printStackTrace();
    }
}

// Method to Display Employee Records
public static void displayRecords(Connection con)
        throws SQLException {

    String selectSQL =
            "SELECT * FROM employee";

    PreparedStatement selectStmt =
            con.prepareStatement(selectSQL);

    ResultSet rs =
            selectStmt.executeQuery();

    System.out.println("--------------------------------------");
    System.out.println("ID\tNAME\tSALARY");
    System.out.println("--------------------------------------");

    while (rs.next()) {

        int id =
                rs.getInt("id");

        String name =
                rs.getString("name");

        double salary =
                rs.getDouble("salary");

        System.out.println(
                id + "\t" +
                name + "\t" +
                salary);
    }

    rs.close();
    selectStmt.close();
}
}
