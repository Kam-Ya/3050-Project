package main.objects;

import java.io.Serializable;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class User implements Serializable {
    private static final long serialVersionUID=20241130;
    private String name;
    private Role role;
    private String login;
    private String pass;
    private Integer userID;
    public ArrayList<Project> projs = new ArrayList<Project>();

    // constructor
    public User(String name, Role role) {
        this.name = name;
        this.role = role;
    }

    // constructor part 2 electric boogaloo
    public User(Integer ID) {
        this.userID = ID;
    }

    // constructor part 3 yuh huh
    public User(String name, Role role, String username, String password) throws SQLException {
        this.login = username;
        this.pass = password;
        this.name = name;
        this.role = role;
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


    public void addToDatabase() throws SQLException {
        try(
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/data", "project", "123");
                Statement state = con.createStatement();
        ) {
            // adds the actual user information into the database
            String input = String.format("INSERT INTO users (name, role) VALUES('%s',%d);", this.name, this.role.permissions);
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

    public void listProj() {
        try(
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/data", "project", "123");
                Statement state = con.createStatement();
        ) {
            // get the project ID from the database
            String input = String.format("SELECT projectID, name, date, description FROM project LEFT JOIN projassign ON emp = %d", this.userID);
            ResultSet rs = state.executeQuery(input);

            while(rs.next()) {
                SimpleDateFormat form = new SimpleDateFormat("dd/MM/yyyy");

                try {
                    this.projs.add(new Project(rs.getString("name"), form.parse(rs.getString("date")), rs.getString("description"), rs.getInt("projectID")));
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }

            // close the connection
            rs.close();
            state.close();
            con.close();
        } catch(SQLException sqle) {
            return;
        }
    }


    public ArrayList<Project> getProjs() {
        return this.projs;
    }

    public void SetID(Integer ID) {
        this.userID = ID;
    }

    public void createUser() throws SQLException {
        this.addToDatabase();
        Login log = new Login(this.login, this.pass);

        try(
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/data", "project", "123");
                Statement state = con.createStatement();
        ) {
            // adds the actual user information into the database
            ResultSet rs = state.executeQuery("SELECT employeeID FROM users ORDER BY employeeID DESC LIMIT 1");
            rs.next();
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
}