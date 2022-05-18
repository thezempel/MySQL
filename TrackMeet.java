import java.sql.*;
import java.util.Scanner;



public class TrackMeet {
    private static String hostname = "localhost";
    private	static String port     = "3306";
    private static String database = "dbzempeltrack";
    private static String user     = "root";
    private static String password = "";
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
                break;
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

    public static void addResult() throws Exception {
        Scanner scan = new Scanner(System.in);
        Class.forName("com.mysql.jdbc.Driver");
        int compId, eventId, result;
        System.out.println("Enter the competitor's number: ");
        compId = scan.nextInt();
        System.out.println("Enter the event's ID: ");
        eventId = scan.nextInt();
        System.out.println("Enter the competitor's result: ");
        result = scan.nextInt();
        Class.forName("com.mysql.jdbc.Driver");

        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://"
                    + hostname + "/" + database + flags, user, password);
            CallableStatement cStmt = conn.prepareCall("INSERT INTO results values (?, ?, ?)");
            cStmt.setInt(1, compId);
            cStmt.setInt(2, eventId);
            cStmt.setInt(3, result);
            cStmt.execute();

            System.out.println("Result has been added");

        }
        catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            ex.printStackTrace();
        }

    }

    public static void getResult() throws Exception{

        Class.forName("com.mysql.jdbc.Driver");

        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://"
                    + hostname + "/" + database + flags, user, password);

            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            boolean gotResults = stmt.execute("SELECT * from events");

            ResultSet rs = stmt.getResultSet(); //get a "Result Set"
            rs.first();                        //go to first record in the result set

            while (gotResults) {
                int compId = rs.getInt("athleteId");
                String event = rs.getString("eventName");
                String score = rs.getString("score");
                String place = String.valueOf(rs.getDate("place"));
                boolean dq = rs.getBoolean("dq");
                System.out.println("| place | athlete |  event  | score |");
                System.out.println("====================================");
                System.out.println(place + "      " + compId + "        " + event + "  " + score + "     " + dq);
                gotResults = rs.next();
            }

        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            ex.printStackTrace();
        }
    }

    public static void scoreEvent() throws Exception{
        Scanner scan = new Scanner (System.in);
        Class.forName("com.mysql.jdbc.Driver");
        int eventId, place, compId, i = 1, score = 10;
        System.out.println("Enter event ID: ");
        eventId = scan.nextInt();

        while (i <= 6) {
            System.out.println("Enter competitors ID for each Place");
            if (i == 1)
                System.out.println(i + "st Place: ");
            else if (i == 2)
                System.out.println(i + "nd Place: ");
            else if (i == 3)
                System.out.println(i + "rd Place: ");
            else if (i == 4|| i == 5 || i == 6)
                System.out.println(i + "th Place: ");

            compId = scan.nextInt();
            place = i;
            try {
                Connection conn = DriverManager.getConnection("jdbc:mysql://"
                        + hostname + "/" + database + flags, user, password);
                CallableStatement cStmt = conn.prepareCall("UPDATE events SET " +
                                                            "athleteId = (?), place = (?), score = (?)" +
                                                            " WHERE eventId = (?)");
                cStmt.setInt(1, compId);
                cStmt.setInt(2, place);
                cStmt.setInt(3, score);
                cStmt.setInt(4, eventId);
                cStmt.execute();



            }
            catch (SQLException ex) {
                System.out.println("SQLException: " + ex.getMessage());
                System.out.println("SQLState: " + ex.getSQLState());
                System.out.println("VendorError: " + ex.getErrorCode());
                ex.printStackTrace();
            }
            i++;
            score -=2;
            if (score == 0) score = 1;
        }
        System.out.println("Event has been scored \n");

    }

    public static void dqEvent() throws Exception{
        Scanner scan = new Scanner(System.in);
        Class.forName("com.mysql.jdbc.Driver");
        int compId;
        System.out.println("Enter competitor ID: ");
        compId = scan.nextInt();
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://"
                    + hostname + "/" + database + flags, user, password);
            CallableStatement cStmt = conn.prepareCall("INSERT INTO results (athleteId, dq) values (?, ?)");
            cStmt.setInt(1, compId);
            cStmt.setBoolean(2, true);
            cStmt.execute();

            System.out.println("Competitor has been disqualified");

        }
        catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            ex.printStackTrace();
        }
    }

    public static void dqMeet() {
        Scanner scan = new Scanner(System.in);
        int compId;
        System.out.println("Enter competitor ID: ");
        compId = scan.nextInt();
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://"
                    + hostname + "/" + database + flags, user, password);
            CallableStatement cStmt = conn.prepareCall("DELETE FROM events WHERE athleteId = (?)");
            cStmt.setInt(1, compId);
            cStmt.execute();



        }
        catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            ex.printStackTrace();
        }
        System.out.println("Competitor has been removed from the meet");
    }

    public static void scoreMeet() throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        int i = 1;
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://"
                    + hostname + "/" + database + flags, user, password);

            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            boolean gotResults = stmt.execute("SELECT schoolName, score FROM school ORDER BY score DESC");

            ResultSet rs = stmt.getResultSet(); //get a "Result Set"
            rs.first();                        //go to first record in the result set

            while (i <= 3) {
                int score = rs.getInt("score");
                String schoolName = rs.getString("schoolName");
                boolean dq = rs.getBoolean("dq");
                System.out.println("| place |   school   |  score  |");
                System.out.println("================================");
                System.out.println(i + "      " + schoolName  + score);
                i++;
            }


        }
        catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        int choice = menu();
        while (choice != 8) {
            option(choice);
            menu();
        }

    }
}

