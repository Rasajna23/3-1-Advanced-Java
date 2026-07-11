import java.sql.*;
import java.util.Scanner;

public class EmployeeManagement {

    static final String URL = "jdbc:mysql://localhost:3306/company";
    static final String USER = "javauser";
    static final String PASSWORD = "Java@12345";

    public static void main(String[] args) {

        Connection con = null;
        Statement st = null;
        Scanner sc = new Scanner(System.in);

        try {

            // Connect to MySQL
            con = DriverManager.getConnection(URL, USER, PASSWORD);

            // Create Statement object
            st = con.createStatement();

           System.out.println("Connected Successfully!");

while(true){

    System.out.println("\n===== Employee Management =====");

    System.out.println("1. Create Table");
    System.out.println("2. Insert");
    System.out.println("3. Display");
    System.out.println("4. Update");
    System.out.println("5. Delete");
    System.out.println("6. Exit");

    System.out.print("Enter Choice : ");

    int choice = sc.nextInt();

    switch(choice){

        case 1:

    String createTable =
        "CREATE TABLE IF NOT EXISTS employee(" +
        "id INT PRIMARY KEY," +
        "name VARCHAR(50)," +
        "salary DOUBLE)";

    st.executeUpdate(createTable);

    System.out.println("Table Created Successfully");

    break;
        case 2:

    System.out.print("Enter ID : ");
    int id = sc.nextInt();

    sc.nextLine();

    System.out.print("Enter Name : ");
    String name = sc.nextLine();

    System.out.print("Enter Salary : ");
    double salary = sc.nextDouble();

    String insert =
        "INSERT INTO employee VALUES(?,?,?)";

    PreparedStatement ps =
        con.prepareStatement(insert);

    ps.setInt(1,id);
    ps.setString(2,name);
    ps.setDouble(3,salary);

    ps.executeUpdate();

    System.out.println("Record Inserted Successfully");

    break;
        case 3:

    ResultSet rs =
        st.executeQuery("SELECT * FROM employee");

    System.out.println();

    System.out.println("ID\tNAME\tSALARY");

    while(rs.next()){

        System.out.println(
            rs.getInt("id") + "\t" +
            rs.getString("name") + "\t" +
            rs.getDouble("salary")
        );

    }

    rs.close();

    break;
        case 4:

    System.out.print("Enter Employee ID: ");
    id = sc.nextInt();

    System.out.print("Enter New Salary: ");
    salary = sc.nextDouble();

    String update =
        "UPDATE employee SET salary=? WHERE id=?";

    PreparedStatement ps2 =
        con.prepareStatement(update);

    ps2.setDouble(1, salary);
    ps2.setInt(2, id);

    count = ps2.executeUpdate();

    if(count > 0)
        System.out.println("Record Updated Successfully");
    else
        System.out.println("Employee Not Found");

    ps2.close();

    break;
        case 5:

    System.out.print("Enter Employee ID: ");
    id = sc.nextInt();

    String delete =
        "DELETE FROM employee WHERE id=?";

    PreparedStatement ps3 =
        con.prepareStatement(delete);

    ps3.setInt(1, id);

    count = ps3.executeUpdate();

    if(count > 0)
        System.out.println("Record Deleted Successfully");
    else
        System.out.println("Employee Not Found");

    ps3.close();

    break;
        case 6:

            con.close();
            sc.close();

            System.out.println("Application Closed");

            System.exit(0);

        default:
            System.out.println("Invalid Choice");
    }

}
        } catch(Exception e) {
            e.printStackTrace();
        }

    }
}
