/*This is the class where we will run most of our queries*/
package ht4.Package;

import java.sql.*;

public class DBQueries {
    //Creating user query
    public void creatingUser(String userN, String pwd, Connection conn) {
        try {
            Statement s = conn.createStatement();
            s.executeUpdate("INSERT INTO Smartass.dbo.Table_Login (username, password) VALUES ('" + userN + "','" + pwd + "')");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    //Updating user query
    public boolean updatingUserInfo(Connection conn, String name, String phone, String email, int age, int heightFT, int heightIN, int cWeight, int bmi, int gWeight) {
        try {
            Statement s = conn.createStatement();
            s.executeUpdate("UPDATE Smartass.dbo.Table_Login SET name = '" + name + "', phone = '" + phone + "', email = '" + email + "', age = " + age + ", [height ft] = " + heightFT + ", [height in] = " + heightIN + ", [Current weight] = '" + cWeight + "', bmi = '" + bmi + "', [Goal weight] = '" + gWeight + "' WHERE id = '" + User.id + "'");
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }
    //Selecing user query
    public boolean selectingUserLogin(String userN, String pwd, Connection conn) {
        try {
            PreparedStatement st = conn.prepareStatement("SELECT * FROM Smartass.dbo.Table_Login WHERE (username = '" + userN + "' AND password = '" + pwd + "')");
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    //deletes the query row from database given the user ID.
    public boolean deleteQuery(int userID, Connection conn) {
        int numDeleted = 0;
        try {
            Statement st = conn.createStatement();
            numDeleted = st.executeUpdate("DELETE FROM Smartass.dbo.Table_Login WHERE (id = '" + userID + "')");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return numDeleted != 0;
    }
}
