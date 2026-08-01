import java.sql.*;

public class JDBCStoredProcDemo {
public static void main(String[] args) {

    String url = "jdbc:mysql://localhost:3306/company";
    String user = "javauser";
    String password = "Java@12345";

    try {

        // Load MySQL JDBC Driver
        Class.forName("com.mysql.cj.jdbc.Driver");

        // Connect to Database
        Connection conn =
                DriverManager.getConnection(url, user, password);

        System.out.println(
                "Database Connected Successfully");

        // Create Employee Table
        Statement stmt = conn.createStatement();

        String createTable =
                "CREATE TABLE IF NOT EXISTS employee (" +
                "id INT PRIMARY KEY, " +
                "name VARCHAR(50), " +
                "salary DOUBLE)";

        stmt.executeUpdate(createTable);

        System.out.println(
                "Employee Table Ready");

        /*
         * Call Stored Procedure to Insert Employee
         *
         * Procedure:
         * insert_employee(id, name, salary)
         */

        CallableStatement insertStmt =
                conn.prepareCall(
                        "{call insert_employee(?, ?, ?)}");

        insertStmt.setInt(1, 106);
        insertStmt.setString(2, "John Doe");
        insertStmt.setDouble(3, 55000);

        insertStmt.execute();

        System.out.println(
                "Employee Inserted Successfully");

        /*
         * Call Stored Procedure to Get Employee Salary
         *
         * Procedure:
         * get_salary_by_id(id, OUT salary)
         */

        CallableStatement salaryStmt =
                conn.prepareCall(
                        "{call get_salary_by_id(?, ?)}");

        salaryStmt.setInt(1, 106);

        salaryStmt.registerOutParameter(
                2, Types.DECIMAL);

        salaryStmt.execute();

        double salary =
                salaryStmt.getDouble(2);

        System.out.println(
                "Employee Salary = " + salary);

        // Close Resources
        insertStmt.close();
        salaryStmt.close();
        stmt.close();
        conn.close();

        System.out.println(
                "Database Connection Closed");

    } catch (Exception e) {

        e.printStackTrace();
    }
}
}
