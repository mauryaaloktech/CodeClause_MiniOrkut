import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

class User {
    private String username;
    private String password;
    private List<User> friends;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.friends = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public List<User> getFriends() {
        return friends;
    }

    public void addFriend(User friend) {
        friends.add(friend);
    }
}

public class MiniOrkut {
    private static Map<String, User> users = new HashMap<>();
    private static User currentUser = null;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Welcome to MiniOrkut!");

        while (true) {
            System.out.println("\n1. Register");
            System.out.println("2. Log in");
            System.out.println("3. Add friend");
            System.out.println("4. Display friends");
            System.out.println("5. Log out");
            System.out.println("6. Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline left by nextInt()

            switch (choice) {
                case 1:
                    registerUser();
                    break;
                case 2:
                    logIn();
                    break;
                case 3:
                    addFriend();
                    break;
                case 4:
                    displayFriends();
                    break;
                case 5:
                    logOut();
                    break;
                case 6:
                    System.out.println("Goodbye!");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void registerUser() {
        System.out.print("Enter your username: ");
        String username = scanner.nextLine();
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        if (users.containsKey(username)) {
            System.out.println("Username already taken. Please choose a different one.");
        } else {
            User newUser = new User(username, password);
            users.put(username, newUser);
            System.out.println("Registration successful! You can now log in.");
        }
    }

    private static void logIn() {
        if (currentUser != null) {
            System.out.println("You are already logged in. Log out first to log in with a different account.");
            return;
        }

        System.out.print("Enter your username: ");
        String username = scanner.nextLine();
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        User user = users.get(username);
        if (user != null && user.getPassword().equals(password)) {
            currentUser = user;
            System.out.println("Logged in as " + username);
        } else {
            System.out.println("Invalid username or password. Please try again.");
        }
    }

    private static void addFriend() {
        if (currentUser == null) {
            System.out.println("You need to log in first.");
            return;
        }

        System.out.print("Enter the username of your friend: ");
        String friendUsername = scanner.nextLine();

        User friend = users.get(friendUsername);
        if (friend != null) {
            currentUser.addFriend(friend);
            System.out.println(friendUsername + " is now your friend!");
        } else {
            System.out.println("User not found. Please check the username and try again.");
        }
    }

    private static void displayFriends() {
        if (currentUser == null) {
            System.out.println("You need to log in first.");
            return;
        }

        List<User> friends = currentUser.getFriends();
        if (friends.isEmpty()) {
            System.out.println("You currently have no friends.");
        } else {
            System.out.println("Your friends:");
            for (User friend : friends) {
                System.out.println("- " + friend.getUsername());
            }
        }
    }

    private static void logOut() {
        if (currentUser == null) {
            System.out.println("You are not logged in.");
        } else {
            currentUser = null;
            System.out.println("Logged out successfully.");
        }
    }
}