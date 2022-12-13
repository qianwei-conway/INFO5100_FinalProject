import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class User {
    // initiate MySQL connection variables: just for professor and TAs' test
    protected String sqlURL;
    protected String sqlUsername;
    protected String sqlPassword;

    // initiate variables for a user information
    private String identity;
    private String username;
    private String password;

    // initiate variables for database operation
    protected Connection conn;
    protected Statement stmt;

    public User() {
    }

    // this function aims at returning all data that sql clause wants
    protected ArrayList<ArrayList<String>> dbGetData(String sql, int colNum) {
        ArrayList<ArrayList<String>> list = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(sqlURL, sqlUsername, sqlPassword);
            stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery(sql);// create data object

            while (rs.next()) {
                ArrayList<String> li = new ArrayList<>();
                for (int i = 1; i <= colNum; ++i) {
                    li.add(rs.getString(i));
                }
                list.add(li);
            }

            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    // this function aims at returning the password in database for checking account validity
    protected String dbGetPassword(String sql) {
        String psw = "";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(sqlURL, sqlUsername, sqlPassword);
            stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                psw = rs.getString(1);
            }

            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return psw;
    }

    // this function aims at updating the database, related to operations like insert, delete, update...
    protected boolean dbUpdateData(String sql) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(sqlURL, sqlUsername, sqlPassword);
            stmt = conn.createStatement();

            stmt.executeUpdate(sql);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    // Setter and Getter functions
    protected void setIdentity(String identity) {
        this.identity = identity;
    }

    protected void setUsername(String username) {
        this.username = username;
    }

    protected void setPassword(String password) {
        this.password = password;
    }

    protected String getUsername() {
        return username;
    }

    protected String getPassword() {
        return password;
    }

    protected String getIdentity() {
        return identity;
    }
}
