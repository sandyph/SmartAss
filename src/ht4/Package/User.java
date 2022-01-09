//user class to create user object which will store current user in order to update info or delete account
package ht4.Package;

import java.sql.*;

public class User {
    Connection connection = DBConnection.DBC();
    static int id = 0;
    static String username = "";
    static String password = "";
    static String name = "";
    static String phone = "";
    static String email = "";
    static int age = 0;
    static int heightFT = 0;
    static int heightIN = 0;
    static int currentWeight = 0;
    static int goalWeight = 0;
    static int bmi = 0;

    //User object is created from searching the database entry that matches the given username and password.
    public User(String userName, String pwd) {
        try {
            ResultSet rs = connection.prepareStatement("SELECT * FROM Smartass.dbo.Table_Login WHERE (username = '" + userName + "' AND password = '" + pwd + "')").executeQuery();
            while(rs.next()) {
                id = rs.getInt(1);
                username = userName;
                password = pwd;
                name = rs.getString(4);
                phone = rs.getString(5);
                email = rs.getString(6);
                age = rs.getInt(7);
                heightFT = rs.getInt(8);
                heightIN = rs.getInt(9);
                currentWeight = rs.getInt(10);
                bmi = rs.getInt(11);
                goalWeight = rs.getInt(12);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}