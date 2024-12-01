package main.java;

import java.io.Serializable;
import java.sql.*;

public class User implements Serializable {
    private String name;
    private final Role role;
    private Integer userID;

    // constructor
    public User(String name, Role role) {
        this.name = name;
        this.role = role;
    }

    public User(String name, Role role, String username, String password) throws SQLException {
        Login log = new Login(username, password);
        this.name = name;
        this.role = role;
        this.addToDatabase();

        try(
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/data", "project", "123");
                Statement state = con.createStatement();
        ) {
            // adds the actual user information into the database
            ResultSet rs = state.executeQuery("SELECT LAST_INSERT_ID();");
            this.userID = rs.getInt("employeeID");

            log.register(this.userID);

            if (this.role.getClass() == CEO.class) {
                CEO c = new CEO();
                c.createOrg(this.userID);
            }

            // close the connection
            state.close();
            con.close();
        } catch(SQLException sqle) {
            System.out.println(sqle.getMessage());
        }

    }

    // getter for name
    public String getName() {
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    // getter for role
    public String getRole() {
        return this.role.getClass().getSimpleName();
    }

    private int getPermissions() {
        return this.role.permissions;
    }   


    private void addToDatabase() throws SQLException {
        try(
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/data", "project", "123");
                Statement state = con.createStatement();
        ) {
            // adds the actual user information into the database
            String input = String.format("INSERT INTO users VALUES('%s', %d);", this.name, this.role.permissions);
            state.executeUpdate(input);

            // close the connection
            state.close();
            con.close();
        } catch(SQLException sqle) {
            System.out.println(sqle.getMessage());
        }
    }

    public void deleteFromDatabase() {
        try(
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/data", "project", "123");
                Statement state = con.createStatement();
        ) {
            // deletes user from database
            String input = String.format("DELETE FROM users WHERE employeeID = '%s';", this.userID);
            state.executeUpdate(input);

            // close the connection
            state.close();
            con.close();
        } catch(SQLException sqle) {
            System.out.println(sqle.getMessage());
        }
    }

    public void DeleteOrg() {
        try(
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/data", "project", "123");
                Statement state = con.createStatement();
        ) {
            // adds the actual user information into the database
            String input = String.format("SELECT org FROM inORG WHERE employee = %d;", this.userID);
            ResultSet rs = state.executeQuery(input);
            int ID = rs.getInt("employeeID");

            CEO c = new CEO();
            c.deleteOrg(ID);

            // close the connection
            rs.close();
            state.close();
            con.close();
        } catch(SQLException sqle) {
            System.out.println(sqle.getMessage());
        }
    }
}