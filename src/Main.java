import dao.BugDAO;
import dao.UserDAO;
import java.util.Scanner;
import model.Bug;
import model.User;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static UserDAO userDAO = new UserDAO();
    static BugDAO bugDAO = new BugDAO();
    static User loggedInUser = null;

    public static void main(String[] args) {
        loginMenu();
    }

    public static void loginMenu() {
        while(true){
            System.out.println("\n===== Software Bug Tracking System =====");
            System.out.println("1. Login");
            System.out.println("2. Register User");
            System.out.println("3. Exit");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch(choice){
                case 1:
                    login();
                    break;
                case 2:
                    registerUser();
                    break;
                case 3:
                    System.out.println("Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    public static void login() {
        System.out.print("Email: ");
        String email = sc.nextLine();

        System.out.print("Password: ");
        String password = sc.nextLine();

        loggedInUser = userDAO.login(email, password);

        if (loggedInUser != null) {
            System.out.println("Login successful! Welcome " + loggedInUser.getFullName());
            dashboard();
        } else {
            System.out.println("Invalid email or password.");
        }
    }

    public static void registerUser() {
        System.out.print("Full name: ");
        String fullName = sc.nextLine();

        System.out.print("Email: ");
        String email = sc.nextLine();

        System.out.print("Password: ");
        String password = sc.nextLine();

        System.out.print("Role (Admin/Developer/Tester): ");
        String role = sc.nextLine();

        User user = new User(0, fullName, email, password, role);

        if (userDAO.addUser(user)) {
            System.out.println("User registered successfully.");
        } else {
            System.out.println("User registration failed.");
        }
    }

    public static void dashboard() {
        while (true) {
            System.out.println("\n===== Dashboard =====");
            System.out.println("1. View All Users");
            System.out.println("2. Report Bug");
            System.out.println("3. View All Bugs");
            System.out.println("4. Update Bug Status");
            System.out.println("5. Logout");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch(choice){
                case 1:
                    viewAllUsers();
                    break;
                case 2:
                    reportBug();
                    break;
                case 3:
                    viewAllBugs();
                    break;
                case 4:
                    updateBugStatus();
                    break;
                case 5:
                    loggedInUser = null;
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    public static void viewAllUsers() {
        System.out.println("\n--- Users ---");

        for (User user : userDAO.getAllUsers()) {
            System.out.println(user);
        }
    }

    public static void reportBug() {
        System.out.print("Project ID: ");
        int projectId = sc.nextInt();
        sc.nextLine();

        System.out.print("Bug title: ");
        String title = sc.nextLine();

        System.out.print("Bug description: ");
        String description = sc.nextLine();

        System.out.print("Severity (Low/Medium/High/Critical): ");
        String severity = sc.nextLine();

        System.out.print("Assigned To User ID, or 0 if unassigned: ");
        int assignedTo = sc.nextInt();
        sc.nextLine();

        Bug bug = new Bug(
                0,
                projectId,
                title,
                description,
                severity,
                "New",
                loggedInUser.getUserId(),
                assignedTo
        );

        if (bugDAO.addBug(bug)) {
            System.out.println("Bug reported successfully.");
        } else {
            System.out.println("Bug report failed.");
        }
    }

    public static void viewAllBugs() {
        System.out.println("\n--- Bugs ---");

        for (Bug bug : bugDAO.getAllBugs()) {
            System.out.println(bug);
        }
    }

    public static void updateBugStatus() {
        System.out.print("Bug ID: ");
        int bugId = sc.nextInt();
        sc.nextLine();

        System.out.print("New Status (New/Assigned/In Progress/Testing/Resolved/Closed): ");
        String status = sc.nextLine();

        if (bugDAO.updateBugStatus(bugId, status)) {
            System.out.println("Bug status updated.");
        } else {
            System.out.println("Bug status update failed.");
        }
    }
}