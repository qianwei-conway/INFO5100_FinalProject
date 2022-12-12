import java.sql.*;
import java.util.ArrayList;

public class User {
    private String identity;
    private String username;
    private String password;

    protected Connection conn;
    protected Statement stmt;

    public User() {
    }

    protected ArrayList<ArrayList<String>> dbGetData(String sql, int colNum) {
        ArrayList<ArrayList<String>> list = new ArrayList<>();
        try {
            // 调用Class.forName()方法加载驱动程序
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("成功加载MySQL驱动！");

            conn = DriverManager.getConnection("jdbc:mysql://localhost/project?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true", "root", "mysql123");//url 账号 密码
            stmt = conn.createStatement(); //创建Statement对象
            System.out.println("成功连接到数据库！");

            ResultSet rs = stmt.executeQuery(sql);//创建数据对象

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

    protected String dbGetPassword(String sql) {
        String psw = "";
        try {
            // 调用Class.forName()方法加载驱动程序
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("成功加载MySQL驱动！");

            conn = DriverManager.getConnection("jdbc:mysql://localhost/project?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true", "root", "mysql123");//url 账号 密码
            stmt = conn.createStatement(); //创建Statement对象
            System.out.println("成功连接到数据库！");

            ResultSet rs = stmt.executeQuery(sql);//创建数据对象

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

    protected boolean dbUpdateData(String sql) {
        try {
            // 调用Class.forName()方法加载驱动程序
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("成功加载MySQL驱动！");

            conn = DriverManager.getConnection("jdbc:mysql://localhost/project?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true", "root", "mysql123");//url 账号 密码
            stmt = conn.createStatement(); //创建Statement对象
            System.out.println("成功连接到数据库！");

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
