package main.objects;

import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Base64;

public class Login implements Serializable {
    private static final long serialVersionUID=20241130;
    private final String username;
    private final String password;

    public Login(String user, String pass) {
        this.username = user;
        this.password = pass;
    }

    public int authenticate() {
        try(
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/data", "project", "123");
                Statement state = con.createStatement();
        ) {
            // get sat and hashed password from database
            String input = String.format("SELECT salt FROM login WHERE username = '%s';", this.username);
            ResultSet rs = state.executeQuery(input);

            // rehash the password
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            rs.next();
            byte[] salt = Base64.getDecoder().decode(rs.getString("salt"));


            md.update(salt);
            byte[] hashedPassword = md.digest(this.password.getBytes(StandardCharsets.UTF_8));

            input = String.format("SELECT pass FROM login WHERE username = '%s';", this.username);
            ResultSet pass = state.executeQuery(input);


            // compare to see if the passwords are the same
            pass.next();
            if(Arrays.equals(Base64.getDecoder().decode(pass.getBytes("pass")), hashedPassword)) {
                input = String.format("SELECT user FROM login WHERE username = '%s';", this.username);
                ResultSet token = state.executeQuery(input);
                token.next();
                return token.getInt("user");
            } else {
                return -1;
            }

            // close the connection


        } catch(SQLException | NoSuchAlgorithmException sqle) {
            System.out.println(sqle.getMessage());
            return -1;
        }
    }

    public void register(int ID) {
        try {
            // generate salt
            SecureRandom rand = new SecureRandom();
            byte[] salt = new byte[16];
            rand.nextBytes(salt);

            // create the hashed password
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(salt);
            byte[] hashedPassword = md.digest(this.password.getBytes(StandardCharsets.UTF_8));

            try (
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/data", "project", "123");
                    Statement state = con.createStatement();
            ) {
                // deletes the project from the database
                DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                String input = String.format("INSERT INTO login VALUES ('%s', '%s', '%s', %d);",
                        this.username, Base64.getEncoder().encodeToString(hashedPassword), Base64.getEncoder().encodeToString(salt), ID); // TODO
                state.executeUpdate(input);

                // close connection
                state.close();
                con.close();
            } catch (SQLException sqle) {
                System.out.println(sqle.getMessage());
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
