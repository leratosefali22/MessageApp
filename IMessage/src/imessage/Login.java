/**
 * Login class handles user authentication by verifying credentials
 * against stored registration information.
 */
public class Login {
    private Register userRegistration;
    
    // Constructor
    public Login(Register registration) {
        this.userRegistration = registration;
    }
    
    /**
     * Verifies that login details match stored registration details
     * @param enteredUsername Username entered during login
     * @param enteredPassword Password entered during login
     * @return true if credentials match, false otherwise
     */
    public boolean loginUser(String enteredUsername, String enteredPassword) {
        if (userRegistration.getUsername() == null || userRegistration.getPassword() == null) {
            return false;
        }
        
        return userRegistration.getUsername().equals(enteredUsername) && 
               userRegistration.getPassword().equals(enteredPassword);
    }
    
    /**
     * Returns appropriate login status message
     * @param enteredUsername Username entered during login
     * @param enteredPassword Password entered during login
     * @return Success or failure message
     */
    public String returnLoginStatus(String enteredUsername, String enteredPassword) {
        if (loginUser(enteredUsername, enteredPassword)) {
            return "Welcome " + userRegistration.getFirstName() + ", " + 
                   userRegistration.getLastName() + " it is great to see you again.";
        } else {
            return "Username or password incorrect, please try again.";
        }
    }
}