package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Manages credentials from properties file
 * Credentials are stored in src/test/resources/credentials.properties
 * This file is not pushed to GitHub for security
 */
public class CredentialsManager {
    
    private static Properties properties;
    private static final String CREDENTIALS_FILE = "credentials.properties";
    
    static {
        loadCredentials();
    }
    
    /**
     * Loads credentials from properties file
     */
    private static void loadCredentials() {
        properties = new Properties();
        try (InputStream input = CredentialsManager.class
                .getClassLoader()
                .getResourceAsStream(CREDENTIALS_FILE)) {
            
            if (input == null) {
                System.err.println("⚠️  WARNING: credentials.properties file not found!");
                System.err.println("Please create src/test/resources/credentials.properties");
                System.err.println("Use credentials.properties.example as a template");
                return;
            }
            
            properties.load(input);
            System.out.println("✓ Credentials loaded successfully from " + CREDENTIALS_FILE);
            
        } catch (IOException e) {
            System.err.println("Error loading credentials: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * Gets the username from credentials file
     * @return username or null if not found
     */
    public static String getUsername() {
        String username = properties.getProperty("username");
        if (username == null || username.equals("your_email@example.com")) {
            System.err.println("⚠️  WARNING: Please update username in credentials.properties");
        }
        return username;
    }
    
    /**
     * Gets the password from credentials file
     * @return password or null if not found
     */
    public static String getPassword() {
        String password = properties.getProperty("password");
        if (password == null || password.equals("your_password_here")) {
            System.err.println("⚠️  WARNING: Please update password in credentials.properties");
        }
        return password;
    }
    
    /**
     * Gets a custom property from credentials file
     * @param key the property key
     * @return property value or null if not found
     */
    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
    
    /**
     * Gets a custom property with a default value
     * @param key the property key
     * @param defaultValue default value if key not found
     * @return property value or default value
     */
    public static String getProperty(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }
    
    /**
     * Checks if credentials are properly configured
     * @return true if credentials are set and not default values
     */
    public static boolean areCredentialsConfigured() {
        String username = getUsername();
        String password = getPassword();
        
        return username != null && 
               password != null && 
               !username.equals("your_email@example.com") && 
               !password.equals("your_password_here") &&
               !username.isEmpty() &&
               !password.isEmpty();
    }
}

