import java.util.Scanner;

/**
 * ChatApp is the main class that handles user interaction for
 * registration and login without using a menu system.
 */
public class IMessage {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Register register = new Register();
        
        System.out.println("=" .repeat(50));
        System.out.println("WELCOME TO THE CHAT APPLICATION");
        System.out.println("=" .repeat(50));
        
        // Part 1: Registration Process
        System.out.println("\n--- REGISTRATION ---");
        
        // Get user's first name
        System.out.print("Enter your first name: ");
        String firstName = scanner.nextLine();
        register.setFirstName(firstName);
        
        // Get user's last name
        System.out.print("Enter your last name: ");
        String lastName = scanner.nextLine();
        register.setLastName(lastName);
        
        // Get username with validation
        String username = "";
        boolean validUsername = false;
        while (!validUsername) {
            System.out.print("\nCreate username (must contain '_' and be ≤5 chars): ");
            username = scanner.nextLine();
            
            if (register.checkUserName(username)) {
                System.out.println("✓ Username successfully captured.");
                validUsername = true;
            } else {
                System.out.println("✗ Username does not exist; please ensure that your username contains an underscore and does not exceed 5 characters in length.");
            }
        }
        
        // Get password with validation
        String password = "";
        boolean validPassword = false;
        while (!validPassword) {
            System.out.println("\nPassword requirements:");
            System.out.println("  - At least 8 characters long");
            System.out.println("  - Contains a capital letter");
            System.out.println("  - Contains a number");
            System.out.println("  - Contains a special character");
            System.out.print("Create password: ");
            password = scanner.nextLine();
            
            if (register.checkPasswordComplexity(password)) {
                System.out.println("✓ Password successfully.");
                validPassword = true;
            } else {
                System.out.println("✗ Password is incorrect,try again; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.");
            }
        }
        
        // Get cell phone number with validation
        String phoneNumber = "";
        boolean validPhone = false;
        while (!validPhone) {
            System.out.print("\nEnter South African cell phone number (format: +27XXXXXXXXX): ");
            phoneNumber = scanner.nextLine();
            
            if (register.checkCellPhoneNumber(phoneNumber)) {
                System.out.println("✓ Cell phone number successfully added.");
                validPhone = true;
            } else {
                System.out.println("✗ Cell phone number incorrectly formatted or does not contain international code.");
                System.out.println("   Example: +27721234567");
            }
        }
        
        // Complete registration
        System.out.println("\n--- REGISTRATION RESULT ---");
        String registrationResult = register.registerUser(username, password, phoneNumber);
        System.out.println(registrationResult);
        
        // Check if registration was successful
        if (!registrationResult.contains("successful")) {
            System.out.println("\nRegistration failed. Please restart the application.");
            scanner.close();
            return;
        }
        
        System.out.println("\n✓ Registration successfully!");
        
        // Part 2: Login Process
        System.out.println("\n--- LOGIN ---");
        System.out.println("Please log in with your information.");
        
        Login login = new Login(register);
        boolean loggedIn = false;
        int attempts = 0;
        
        while (!loggedIn && attempts < 3) {
            System.out.print("\nEnter username: ");
            String loginUsername = scanner.nextLine();
            
            System.out.print("Enter password: ");
            String loginPassword = scanner.nextLine();
            
            String loginStatus = login.returnLoginStatus(loginUsername, loginPassword);
            System.out.println(loginStatus);
            
            if (loginStatus.contains("Welcome")) {
                loggedIn = true;
                System.out.println("\n✓ Login succssfull!");
                System.out.println("Welcome to the Chat Application, " + firstName + "!");
            } else {
                attempts++;
                if (attempts < 3) {
                    System.out.println("Attempts remaining: " + (3 - attempts));
                }
            }
        }
        
        if (!loggedIn) {
            System.out.println("\nToo many failed login attempts. Restart .");
        } else {
            System.out.println("\n" + "=" .repeat(50));
            System.out.println("Welcome '_' !!");
            System.out.println("=" .repeat(50));
        }
        
        scanner.close();
    }
}