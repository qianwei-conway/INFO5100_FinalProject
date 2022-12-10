import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;

public class Login extends User {

    public Login() {
    }

    public String login() {
        return checkAccountValidity();
    }

    public String checkAccountValidity() {
        try {
            // 调用Class.forName()方法加载驱动程序
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("成功加载MySQL驱动！");

            conn = DriverManager.getConnection("jdbc:mysql://localhost/project?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true", "root", "mysql123");//url 账号 密码
            stmt = conn.createStatement(); //创建Statement对象
            System.out.println("成功连接到数据库！");

            String sql = String.format("select password from candyUser where username = '%s'", getUsername());
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
