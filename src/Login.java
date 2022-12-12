import java.sql.DriverManager;

// this class will inherit User class
public class Login extends User {

    public Login() {
    }

    // returning a status referring to how login process is doing
    public String login() {
        return checkAccountValidity();
    }

    // return the account validity status
    // if username already exists?
    // if password is incorrect?
    public String checkAccountValidity() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/project?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true", "root", "mysql123");
            stmt = conn.createStatement();

            String sql = String.format("select password from candyUser where username = '%s' AND identity = '%s'", getUsername(), getIdentity());
            String psw = dbGetPassword(sql);

            if (psw.equals("")) {
                return "username not exists";
            } else {
                if (psw.equals(getPassword())) {
                    return "ok";
                } else {
                    return "psw not correct";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
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
}
