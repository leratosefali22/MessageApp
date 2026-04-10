import java.util.regex.Pattern;

/**
 * Register class handles user registration with validation for username,
 * password, and South African cell phone numbers.
 * 
 * Reference for regex pattern:
 * South African cell phone validation pattern adapted from:
 * https://regex101.com/library/nL8wR7?orderBy=RE&search=south%20africa%20phone
 * Pattern ensures: +27 followed by 9 digits (total 12 characters including +)
 */
public class Register {
    private String username;
    private String password;
    private String cellPhoneNumber;
    private String firstName;
    private String lastName;
    
    // Constructor
    public Register() {
        this.username = "";
        this.password = "";
        this.cellPhoneNumber = "";
        this.firstName = "";
        this.lastName = "";
    }
    
    /**
     * Checks if username contains an underscore and is no more than 5 characters long
     * @param username The username to validate
     * @return true if valid, false otherwise
     */
    public boolean checkUserName(String username) {
        return username.contains("_") && username.length() <= 5;
    }
    
    /**
     * Checks if password meets complexity requirements:
     * - At least 8 characters long
     * - Contains a capital letter
     * - Contains a number
     * - Contains a special character
     * @param password The password to validate
     * @return true if valid, false otherwise
     */
    public boolean checkPasswordComplexity(String password) {
        boolean hasMinLength = password.length() >= 8;
        boolean hasCapital = !password.equals(password.toLowerCase());
        boolean hasNumber = password.matches(".*\\d.*");
        boolean hasSpecial = !password.matches("[A-Za-z0-9]*");
        
        return hasMinLength && hasCapital && hasNumber && hasSpecial;
    }
    
    /**
     * Validates South African cell phone number using regex
     * Reference: Regex pattern for South African mobile numbers
     * Pattern ensures: +27 followed by 9 digits (total 12 characters with +)
     * @param phoneNumber The cell phone number to validate
     * @return true if valid, false otherwise
     */
    public boolean checkCellPhoneNumber(String phoneNumber) {
        // SA cell phone regex: +27 followed by 9 digits (starting with 6,7,8)
        // Total length should be 12 characters including the '+'
        String saPhoneRegex = "^\\+27[6-8][0-9]{8}$";
        return Pattern.matches(saPhoneRegex, phoneNumber);
    }
    
    /**
     * Returns registration messages based on validation results
     * @param username The username to check
     * @param password The password to check
     * @param phoneNumber The phone number to check
     * @return Registration status message
     */
    public String registerUser(String username, String password, String phoneNumber) {
        boolean isUsernameValid = checkUserName(username);
        boolean isPasswordValid = checkPasswordComplexity(password);
        boolean isPhoneValid = checkCellPhoneNumber(phoneNumber);
        
        if (!isUsernameValid) {
            return "Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.";
        }
        
        if (!isPasswordValid) {
            return "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
        }
        
        if (!isPhoneValid) {
            return "Cell phone number incorrectly formatted or does not contain international code.";
        }
        
        // All validations passed
        this.username = username;
        this.password = password;
        this.cellPhoneNumber = phoneNumber;
        
        return "Username successfully captured.\nPassword successfully captured.\nCell phone number successfully added.\nRegistration successful!";
    }
    
    /**
     * Sets the user's first name
     * @param firstName User's first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    /**
     * Sets the user's last name
     * @param lastName User's last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    // Getters for login verification
    public String getUsername() {
        return username;
    }
    
    public String getPassword() {
        return password;
    }
    
    public String getFirstName() {
        return firstName;
    }
    
    public String getLastName() {
        return lastName;
    }
    
    public String getCellPhoneNumber() {
        return cellPhoneNumber;
    }
}