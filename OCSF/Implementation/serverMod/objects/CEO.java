package objects;

import java.sql.*;

public class CEO extends Lead {

    // constructor sets permissions
    public CEO() {
        permissions = 3;
    }

    public void createOrg(int ID) {
        try (
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/data", "project", "123");
                Statement state = con.createStatement();
        ) {
            // get task and project ID's from database
            String input = String.format("INSERT INTO organisation VALUES ('%s')", Integer.toString(ID));
            state.executeUpdate(input);

            ResultSet rs = state.executeQuery("SELECT LAST_INSERT_ID();");

            input = String.format("INSERT INTO inORG Values (%d, %d);", rs.getInt("organisationID"), ID);
            state.executeUpdate(input);

            // close the connection
            rs.close();
            state.close();
            con.close();
        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
            return;
        }
    }


    public void deleteOrg(int ID) {
        try(
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/data", "project", "123");
                Statement state = con.createStatement();
        ) {
            // deletes project from database
            String input = String.format("DELETE FROM organisation WHERE organisationID = %d;", ID);
            state.executeUpdate(input);

            // close the connection
            state.close();
            con.close();
        } catch(SQLException sqle) {
            System.out.println(sqle.getMessage());
        }
    }
}
