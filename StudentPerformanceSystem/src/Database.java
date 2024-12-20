import java.util.HashMap;

public class Database {
    private static HashMap<String, String> users = new HashMap<>();

    public static void registerUser(String email, String password, String name) {
        users.put(email, password);
    }

    public static boolean validateUser(String email, String password) {
        return users.containsKey(email) && users.get(email).equals(password);
    }
}
