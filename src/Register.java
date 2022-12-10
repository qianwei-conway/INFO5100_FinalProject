import java.sql.DriverManager;


public class Register extends User {
    private String reenterPassword;

    public Register() {
    }

    public String register() {
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

    public Boolean checkUsername() {
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

    public boolean checkPasswordMatch() {
        if (getPassword().equals(reenterPassword)) {
            return true;
        } else {
            return false;
        }
    }

    public void addNewUser() {
        try {
            // 调用Class.forName()方法加载驱动程序
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("成功加载MySQL驱动！");

            conn = DriverManager.getConnection("jdbc:mysql://localhost/project?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true", "root", "mysql123");//url 账号 密码
            stmt = conn.createStatement(); //创建Statement对象
            System.out.println("成功连接到数据库！");

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

    public void setReenterPassword(String reenterPassword) {
        this.reenterPassword = reenterPassword;
    }

    protected String getReenterPassword() {
        return reenterPassword;
    }
}
