package Server.src;

import java.sql.*;

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
        return this.role.permissions;
    }   

    public boolean validateSession() {
        // TODO
        return true;
    }

    private void addToDatabase() throws SQLException {
        try(
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/data", "project", "123");
                Statement state = con.createStatement();
        ) {
            // adds the actual user information into the database
            String input = String.format("INSERT INTO users VALUES('%s', '%d');", this.name, this.role.permissions);
            state.executeUpdate(input);
        } catch(SQLException sqle) {
            // TODO
        }
    }
}