import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;



public class TrackMeet {
    private static String hostname = "localhost";
    private	static String port     = "3306";
    private static String database = "dbzempeltrack";
    private static String user     = "root";
    private static String password = "Waffles*1159";
    private static String flags = "?noAccessToProcedureBodies=true";

    public static int menu() {
        Scanner scan = new Scanner(System.in);
        int choice;
        System.out.println("Please select an option: \n" +
                "1: To add a new Athlete \n" +
                "2: To enter a new result \n" +
                "3: To get the results for an event \n" +
                "4: To score an event\n" +
                "5: To disqualify an athlete for one event \n" +
                "6: To disqualify an athlete for the meet \n" +
                "7: To score the meet\n" +
                "8: TO QUIT \n" +
                "Enter number: ");
        choice = scan.nextInt();

        return choice;
    }

    public static void option(int choice) throws Exception {
        switch (choice) {
            case 1:
                addAthlete();
                break;
            case 2:
                addResult();
                break;
            case 3:
                getResult();
                break;
            case 4:
                scoreEvent();
                break;
            case 5:
                dqEvent();
                break;
            case 6:
                dqMeet();
                break;
            case 7:
                scoreMeet();
                break;
            case 8:
                System.out.println("goodbye");
        }
    }

    public static void addAthlete() throws Exception {
        Scanner scan = new Scanner(System.in);
        String first, last, gender;
        int schoolId;
        int athleteId = 1;

        System.out.println("Enter the competitor's first name: ");
        first = scan.next();
        System.out.println("Enter the competitor's last name: ");
        last = scan.next();
        System.out.println("Enter the competitor's gender (m or f): ");
        gender = scan.next();
        System.out.println("Enter the competitor's school ID: ");
        schoolId = scan.nextInt();

        Class.forName("com.mysql.jdbc.Driver");

        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://"
                    + hostname + "/" + database + flags, user, password);
            CallableStatement cStmt = conn.prepareCall("INSERT INTO athletes values (?, ?, ?, ?, ?)");
            cStmt.setString(2, first);
            cStmt.setString(3, last);
            cStmt.setString(5, gender);
            cStmt.setInt(4, schoolId);
            cStmt.setInt(1, athleteId);
            cStmt.execute();
            athleteId++;

            System.out.println(first + last + "has been added");

        }
        catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            ex.printStackTrace();
        }

    }

    public static void addResult() {

    }

    public static void getResult() {

    }

    public static void scoreEvent() {

    }

    public static void dqEvent() {

    }

    public static void dqMeet() {

    }

    public static void scoreMeet() {

    }

    public static void main(String[] args) throws Exception {
        int choice = menu();
        while (choice != 8) {
            option(choice);
            menu();
        }

    }
}

