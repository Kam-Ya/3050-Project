public class User {
    private String name;
    private final Role role;
    // SESSION TOKEN TODO
    private Integer userID;

    // constructor
    public User(String name, Role role) {
        this.name = name;
        this.role = role;

        // TODO?
    }

    // getter for name
    public String getName() {
        return this.name;
    }

    // getter for role
    public String getRole() {
        return this.role.getClass().getSimpleName();
    }

    private int getPermissions() {
        return role.permissions;
    }

    public boolean validateSession() {
        // TODO
        return true;
    }
}
