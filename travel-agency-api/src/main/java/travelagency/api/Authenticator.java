package travelagency.api;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The Authenticator class manages the authentication of request senders.
 * It identifies the accessor by the username and password sent via URI tokens.
 *
 * @author I551381
 * @version 1.0
 */
public class Authenticator {

    /**
     * Returns a map of valid user credentials.
     *
     * @return A map containing valid usernames and their corresponding passwords.
     */
    public Map<String, String> getCredentialsMap() {
        Map<String, String> credentials = new HashMap<>();
        credentials.put("demo", "123");
        credentials.put("flo", "password");
        return credentials;
    }

    /**
     * Extracts the username from the given URI using a regex pattern.
     *
     * @param uri The URI containing the server address, request, and tokens (username and password).
     * @return The extracted username as a String or null if not found.
     */
    public String extractUsername(String uri) {
        Pattern pattern = Pattern.compile("username=([^&]+)");
        Matcher matcher = pattern.matcher(uri);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return null;
    }

    /**
     * Extracts the password from the given URI using a regex pattern.
     *
     * @param uri The URI containing the server address, request, and tokens (username and password).
     * @return The extracted password as a String or null if not found.
     */
    public String extractPassword(String uri) {
        Pattern pattern = Pattern.compile("password=([^&]+)");
        Matcher matcher = pattern.matcher(uri);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return null;
    }

    /**
     * Checks if the provided credentials are listed in the credentials map.
     *
     * @param username    The username to check.
     * @param password    The password to check.
     * @return True if the credentials are listed in the credentials map; false otherwise.
     */
    public boolean checkCredentials( String username, String password) {
        return Authenticator.checkCredentials(this.getCredentialsMap(), username, password);
    }

    /**
     * Checks if the provided credentials are listed in the credentials map.
     *
     * @param credentials A map containing valid users and their passwords as key-value pairs.
     * @param username    The username to check.
     * @param password    The password to check.
     * @return True if the credentials are listed in the credentials map; false otherwise.
     */
    public static boolean checkCredentials(Map<String, String> credentials, String username, String password) {
        return credentials.containsKey(username) && credentials.get(username).equals(password);
    }
}
