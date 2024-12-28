package com.movie.operator;

public class UserSession {
    // This variable holds the logged-in user's ID.
    private static int loggedInUserId = 0;  // Default value: no user logged in

    // Get the logged-in user's ID
    public static int getLoggedInUserId() {
        return loggedInUserId;  
    }

    // Set the logged-in user's ID
    public static void setLoggedInUserId(int userId) {
        loggedInUserId = userId;  // Assign the user ID when the user logs in
    }

    // Log out by clearing the logged-in user ID
    public static void logOut() {
        loggedInUserId = 0;  // Set the user ID to 0 (no user logged in)
    }
}