import java.sql.*;

class UResultSet {
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

        System.out.println(
                "Database Connected Successfully");

        // Create Scrollable and Updatable Statement
        Statement st = con.createStatement(
                ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_UPDATABLE
        );

        // Fetch Employee Records
        ResultSet rs =
                st.executeQuery(
                        "SELECT id, name, salary FROM employee"
                );

        // --------------------------------
        // Display Original Records
        // --------------------------------

        System.out.println("\nOriginal Employee Records:");
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

        // --------------------------------
        // DELETE Last Employee
        // --------------------------------

        rs.last();

        int deletedId = rs.getInt("id");
        String deletedName = rs.getString("name");

        rs.deleteRow();

        System.out.println(
                "\nLast employee record deleted successfully.");

        System.out.println(
                "Deleted Employee: " +
                deletedId + " - " + deletedName);

        // --------------------------------
        // INSERT New Employee
        // --------------------------------

        rs.moveToInsertRow();

        rs.updateInt("id", 106);
        rs.updateString("name", "John Doe");
        rs.updateDouble("salary", 55000);

        rs.insertRow();

        System.out.println(
                "New employee record inserted successfully.");

        // --------------------------------
        // Display Final Records
        // --------------------------------

        System.out.println("\nFinal Employee Records:");
        System.out.println("--------------------------------------");
        System.out.println("ID\tNAME\tSALARY");
        System.out.println("--------------------------------------");

        rs.beforeFirst();

        while (rs.next()) {

            System.out.println(
                    rs.getInt("id") + "\t" +
                    rs.getString("name") + "\t" +
                    rs.getDouble("salary")
            );
        }

        // Close resources
        rs.close();
        st.close();
        con.close();

        System.out.println(
                "\nDatabase Connection Closed");

    } catch (Exception e) {

        e.printStackTrace();
    }
}
}
