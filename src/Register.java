import java.sql.DriverManager;

public class Register extends User {
    // initiate a variable for reenter password
    private String reenterPassword;

    public Register() {
    }

    // this function aims at telling how the register process is doing
    protected String register() {
        Boolean checkUsername = checkUsername();
        // Username is okay
        if (checkUsername == true) {
            if (checkPasswordMatch()) {
                // register it
                addNewUser();
                return "ok";
            } else {
                // return warning that passwords don't match
                return "psw not match";
            }
        } else if (checkUsername == false) {
            // return warning that username has already existed
            return "username exists";
        } else {
            // return warning that some errors oocur
            return "error";
        }
    }

    // this function aims at checking if username is already exist
    private Boolean checkUsername() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(sqlURL, sqlUsername, sqlPassword);
            stmt = conn.createStatement();

            String sql = String.format("select password from candyUser where username = '%s'", getUsername());
            String psw = dbGetPassword(sql);

            if (psw.equals("")) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
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
    }

    // this function aims at checking if two password enterings match
    private boolean checkPasswordMatch() {
        if (getPassword().equals(reenterPassword)) {
            return true;
        } else {
            return false;
        }
    }

    // this function aims at inserting a new user into database
    private void addNewUser() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(sqlURL, sqlUsername, sqlPassword);
            stmt = conn.createStatement();

            String sql = String.format("insert into candyUser (identity,username,password) values('%s','%s','%s')", getIdentity(), getUsername(), getPassword());
            dbUpdateData(sql);
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
    }

    // Setter and Getter functions
    public void setReenterPassword(String reenterPassword) {
        this.reenterPassword = reenterPassword;
    }
}
