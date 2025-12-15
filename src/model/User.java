package model;

/**
 * Abstract base class representing a user in the healthcare system.
 * This class encapsulates common attributes shared by all user types.
 * 
 * @author Shubhamkumar Prahladbhai Patel
 * @version 1.0
 */
public abstract class User {
    // Protected attributes accessible by subclasses
    protected String userId;
    protected String username;
    protected String password;
    protected String firstName;
    protected String lastName;
    protected String contactInfo;
    protected String role;
    protected String email;
    
    /**
     * Default constructor for User class.
     */
    public User() {
        this.userId = "";
        this.username = "";
        this.password = "";
        this.firstName = "";
        this.lastName = "";
        this.contactInfo = "";
        this.role = "";
        this.email = "";
    }
    
    /**
     * Parameterized constructor for User class.
     * 
     * @param userId Unique identifier for the user
     * @param username Login username
     * @param password Login password
     * @param firstName User's first name
     * @param lastName User's last name
     * @param contactInfo Contact phone number
     * @param role User's role in the system
     * @param email User's email address
     */
    public User(String userId, String username, String password, String firstName, 
                String lastName, String contactInfo, String role, String email) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.contactInfo = contactInfo;
        this.role = role;
        this.email = email;
    }
    
    // Getter methods
    public String getUserId() { return userId; }
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getContactInfo() { return contactInfo; }
    public String getRole() { return role; }
    public String getEmail() { return email; }
    
    // Setter methods
    public void setUserId(String userId) { this.userId = userId; }
    public void setUsername(String username) { this.username = username; }
    public void setPassword(String password) { this.password = password; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public void setContactInfo(String contactInfo) { this.contactInfo = contactInfo; }
    public void setRole(String role) { this.role = role; }
    public void setEmail(String email) { this.email = email; }
    
    /**
     * Gets the full name of the user.
     * 
     * @return Full name combining first and last name
     */
    public String getFullName() {
        return firstName + " " + lastName;
    }
    
    /**
     * Abstract method to authenticate user credentials.
     * Must be implemented by subclasses.
     * 
     * @return true if authentication successful, false otherwise
     */
    public abstract boolean authenticate();
    
    /**
     * Returns a string representation of the User object.
     * 
     * @return String containing user details
     */
    @Override
    public String toString() {
        return "User ID: " + userId + ", Name: " + getFullName() + 
               ", Role: " + role + ", Email: " + email;
    }
}
