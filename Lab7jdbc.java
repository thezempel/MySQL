import java.sql.*;

public class Lab7jdbc {

    private static String hostname = "localhost";
    private	static String port     = "3306";
    private static String database = "employees";
    private static String user     = "root";
    private static String password = "";
    private static String flags = "?noAccessToProcedureBodies=true";

    public static void main(String args[]) throws Exception {

        Class.forName("com.mysql.jdbc.Driver");

        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://"
                    + hostname + "/" + database + flags, user, password);
            int empID = 13527;
            CallableStatement cStmt = conn.prepareCall("{call getSalaryCount(?,?)}");
            cStmt.setInt(1, empID);
            //the second parameter is an OUT,so register it
            cStmt.registerOutParameter(2, Types.INTEGER);
            //Use execute method to run stored procedure.
            System.out.println("Executing stored procedure..." );
            cStmt.execute();
            //Retrieve salaryCount with getInt method
            int salaryCount = cStmt.getInt(2);
            System.out.println("empID:" + empID
                    + " salary count is " + salaryCount);
/*
            //Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            CallableStatement cStmt = conn.prepareCall("{call logProblem(?)}");
            cStmt.setString(1, "my prob from lab 7");

            System.out.println("Executing stored procedure..." );
            cStmt.execute();

 */
            /*
            boolean gotResults = stmt.execute("SELECT * from employees");

            ResultSet rs = stmt.getResultSet(); //get a "Result Set"
            rs.first();						//go to first record in the result set

            while (gotResults) {
                int empId = rs.getInt("emp_no");
                String first = rs.getString("first_name");
                String last = rs.getString("last_name");
                String date = String.valueOf(rs.getDate("hire_date"));
                System.out.println("emp_no: " + empId + " first_name: " + first + " last_name: " + last +
                                " hire_date " + date);
                gotResults = rs.next();
            }
*/
            conn.close();

        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            ex.printStackTrace();
        }
    }
}

