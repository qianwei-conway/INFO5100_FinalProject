import java.sql.DriverManager;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;


public class Job extends User {
    protected int id;
    protected String company;
    protected String position;
    protected Boolean instancy;
    protected Boolean returnOffer;
    protected String duty;
    protected String requirement;
    protected String benefit;
    protected String internTime;
    protected String location;
    protected Boolean remote;
    protected String applyLink;
    protected String applyInstruction;
    protected String deadline;
    protected String otherInfo;
    protected String intro;

    protected String filterCompany;
    protected String filterPosition;
    protected String filterLocation;
    protected Object[] filterInstancy;
    protected Object[] filterReturnOffer;
    protected Object[] filterRemote;

    protected String todayDate;
    protected String whereClause;

    protected int fieldsNum = 17;
    protected ResultSet rs;

    public Job() {
        instancy = null;
        returnOffer = null;
        remote = null;

        filterInstancy = null;
        filterReturnOffer = null;
        filterRemote = null;

        todayDate = String.valueOf(LocalDate.now());
        System.out.println(todayDate);
    }

    public ArrayList<ArrayList<String>> edit() {
        ArrayList<ArrayList<String>> waitEditJob = new ArrayList<>();
        try {
            // 调用Class.forName()方法加载驱动程序
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("成功加载MySQL驱动！");

            conn = DriverManager.getConnection("jdbc:mysql://localhost/project?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true", "root", "mysql123");//url 账号 密码
            stmt = conn.createStatement(); //创建Statement对象
            System.out.println("成功连接到数据库！");

            String sql = String.format("select * from candyJob where id = %d", id);
            waitEditJob = dbGetData(sql, fieldsNum);

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
        return waitEditJob;
    }

    public ArrayList<ArrayList<String>> showAllJobs() {
        ArrayList<ArrayList<String>> li = new ArrayList<>();
        try {
            // 调用Class.forName()方法加载驱动程序
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("成功加载MySQL驱动！");

            conn = DriverManager.getConnection("jdbc:mysql://localhost/project?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true", "root", "mysql123");//url 账号 密码
            stmt = conn.createStatement(); //创建Statement对象
            System.out.println("成功连接到数据库！");

            String sql = String.format("select id,company,position from candyJob where deadline >= '%s'", todayDate);
            li = dbGetData(sql, 3);
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
        return li;
    }

    public ArrayList<ArrayList<String>> showJobsByUser() {
        ArrayList<ArrayList<String>> li = new ArrayList<>();
        try {
            // 调用Class.forName()方法加载驱动程序
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("成功加载MySQL驱动！");

            conn = DriverManager.getConnection("jdbc:mysql://localhost/project?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true", "root", "mysql123");//url 账号 密码
            stmt = conn.createStatement(); //创建Statement对象
            System.out.println("成功连接到数据库！");

            String sql = String.format("select id,company,position from candyJob where username = '%s'", getUsername());
            li = dbGetData(sql, 3);
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
        return li;
    }

    public ArrayList<ArrayList<String>> filter() {
        whereClause = String.format("where deadline >= '%s' AND ", todayDate);
        whereClause += (!filterCompany.equals("")) ? String.format("locate('%s',company) > 0 AND ", filterCompany) : "";
        whereClause += (!filterPosition.equals("")) ? String.format("locate('%s',position) > 0 AND ", filterPosition) : "";
        whereClause += (!filterLocation.equals("")) ? String.format("locate('%s',location) > 0 AND ", filterLocation) : "";

        if (Arrays.stream(filterInstancy).count() == 1) {
            String s = Arrays.stream(filterInstancy).findFirst().get().equals("Instant") ? "1" : "0";
            whereClause += "instancy = " + s + " AND ";
        }

        if (Arrays.stream(filterReturnOffer).count() == 1) {
            String s = Arrays.stream(filterReturnOffer).findFirst().get().equals("Has Return Offer") ? "1" : "0";
            whereClause += "returnOffer = " + s + " AND ";
        }

        if (Arrays.stream(filterRemote).count() == 1) {
            String s = Arrays.stream(filterRemote).findFirst().get().equals("Can Remote") ? "1" : "0";
            whereClause += "remote = " + s;
        }

        if (whereClause.endsWith(" AND ")) {
            whereClause = whereClause.substring(0, whereClause.length() - 5);
        }

        System.out.println(whereClause);

        ArrayList<ArrayList<String>> li = new ArrayList<>();
        try {
            // 调用Class.forName()方法加载驱动程序
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("成功加载MySQL驱动！");

            conn = DriverManager.getConnection("jdbc:mysql://localhost/project?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true", "root", "mysql123");//url 账号 密码
            stmt = conn.createStatement(); //创建Statement对象
            System.out.println("成功连接到数据库！");

            String sql = String.format("select id,company,position from candyJob %s", whereClause);
            li = dbGetData(sql, 3);
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

        return li;
    }

    public boolean addNewJob() {
        try {
            // 调用Class.forName()方法加载驱动程序
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("成功加载MySQL驱动！");

            conn = DriverManager.getConnection("jdbc:mysql://localhost/project?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true", "root", "mysql123");//url 账号 密码
            stmt = conn.createStatement(); //创建Statement对象
            System.out.println("成功连接到数据库！");

            String sql = String.format("insert into candyJob (username,company,position,instancy," +
                            "returnOffer,duty,requirement,benefit,internTime," +
                            "location,remote,applyLink," +
                            "applyInstruction,deadline,otherInfo,intro) " +
                            "values('%s','%s','%s',%b," +
                            "%b,'%s','%s','%s','%s'," +
                            "'%s',%b,'%s'," +
                            "'%s','%s','%s','%s')",
                    getUsername(), rplQuo(company), rplQuo(position), instancy,
                    returnOffer, rplQuo(duty), rplQuo(requirement), rplQuo(benefit), rplQuo(internTime),
                    rplQuo(location), remote, rplQuo(applyLink),
                    rplQuo(applyInstruction), deadline, rplQuo(otherInfo), rplQuo(intro));
            dbUpdateData(sql);

            emptyJobFields();

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

    public boolean editAJob() {
        try {
            // 调用Class.forName()方法加载驱动程序
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("成功加载MySQL驱动！");

            conn = DriverManager.getConnection("jdbc:mysql://localhost/project?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true", "root", "mysql123");//url 账号 密码
            stmt = conn.createStatement(); //创建Statement对象
            System.out.println("成功连接到数据库！");

            String sql = String.format("UPDATE candyJob set company='%s',position='%s',instancy=%d," +
                            "returnOffer=%d,duty='%s',requirement='%s',benefit='%s',internTime='%s'," +
                            "location='%s',remote=%d,applyLink='%s',applyInstruction='%s'," +
                            "deadline='%s',otherInfo='%s',intro='%s' WHERE id = %d",
                    rplQuo(company), rplQuo(position), (instancy) ? 1 : 0,
                    (returnOffer) ? 1 : 0, rplQuo(duty), rplQuo(requirement), rplQuo(benefit), rplQuo(internTime),
                    rplQuo(location), (remote) ? 1 : 0, rplQuo(applyLink), rplQuo(applyInstruction),
                    deadline, rplQuo(otherInfo), rplQuo(intro), id);
            dbUpdateData(sql);

            emptyJobFields();

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

    public boolean deleteAJob() {
        try {
            // 调用Class.forName()方法加载驱动程序
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("成功加载MySQL驱动！");

            conn = DriverManager.getConnection("jdbc:mysql://localhost/project?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true", "root", "mysql123");//url 账号 密码
            stmt = conn.createStatement(); //创建Statement对象
            System.out.println("成功连接到数据库！");

            String sql = String.format("delete from candyJob where id = %d", id);
            dbUpdateData(sql);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
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

    public String submit(String action) {
        if (checkCpl()) {
            if (action.equals("add")) {
                boolean status = addNewJob();
                if (status == false) {
                    return "SQL error";
                }
            } else {
                boolean status = editAJob();
                if (status == false) {
                    return "SQL error";
                }
            }
            return "ok";
        } else {
            return "incomplete";
        }
    }

    public boolean checkCpl() {
        return !company.equals("") && !position.equals("") && instancy != null &&
                returnOffer != null && !duty.equals("") && !location.equals("") &&
                remote != null && !applyLink.equals("") && !deadline.equals("");
    }

    private void emptyJobFields() {
        company = "";
        position = "";
        instancy = null;
        returnOffer = null;
        duty = "";
        requirement = "";
        benefit = "";
        internTime = "";
        location = "";
        remote = null;
        applyLink = "";
        applyInstruction = "";
        deadline = "";
        otherInfo = "";
        intro = "";
    }

    public ArrayList<ArrayList<String>> view() {
        ArrayList<ArrayList<String>> li = new ArrayList<>();
        try {
            // 调用Class.forName()方法加载驱动程序
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("成功加载MySQL驱动！");

            conn = DriverManager.getConnection("jdbc:mysql://localhost/project?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true", "root", "mysql123");//url 账号 密码
            stmt = conn.createStatement(); //创建Statement对象
            System.out.println("成功连接到数据库！");

            String sql = String.format("select * from candyJob where id = %d", id);
            li = dbGetData(sql, fieldsNum);
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
        return li;
    }

    // replaceQuotationMarks()
    private String rplQuo(String str) {
        return str.replace("'","\\\'").replace("\"","\\\"");
    }

    public String[][] aList2Arr(ArrayList<ArrayList<String>> data) {
        String[][] tableData = new String[data.size()][];
        int i = 0;
        for (ArrayList<String> al : data) {
            tableData[i++] = al.toArray(new String[al.size()]);
        }

        return tableData;
    }
}
