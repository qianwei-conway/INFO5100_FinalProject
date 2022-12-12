import java.sql.DriverManager;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

public class Job extends User {
    // initiate some variables related to a job's detail
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

    // initiate some variables related to the filter
    protected String filterCompany;
    protected String filterPosition;
    protected String filterLocation;
    protected Object[] filterInstancy;
    protected Object[] filterReturnOffer;
    protected Object[] filterRemote;

    // want to show the jobs which haven't expired, so initiate a variable referring to today
    private String todayDate;

    // the SQL where clause related to filter
    private String whereClause;

    // the column number of job database
    private int fieldsNum = 17;

    public Job() {
        // make the Boolean value null at first
        instancy = null;
        returnOffer = null;
        remote = null;
        filterInstancy = null;
        filterReturnOffer = null;
        filterRemote = null;

        // assign today date
        todayDate = String.valueOf(LocalDate.now());
    }

    // this function aims at returning uploaded details
    protected ArrayList<ArrayList<String>> edit() {
        ArrayList<ArrayList<String>> waitEditJob = new ArrayList<>();
        try {
            // call Class.forName() to load the driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // project is the name of database
            conn = DriverManager.getConnection("jdbc:mysql://localhost/project?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true", "root", "mysql123");
            stmt = conn.createStatement();
            // from xxx is the table name
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

    // this function aims at returning all jobs that haven't expired
    protected ArrayList<ArrayList<String>> showAllJobs() {
        ArrayList<ArrayList<String>> li = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/project?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true", "root", "mysql123");
            stmt = conn.createStatement();

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

    // this function aims at returning all jobs that a HR has uploaded
    protected ArrayList<ArrayList<String>> showJobsByUser() {
        ArrayList<ArrayList<String>> li = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/project?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true", "root", "mysql123");
            stmt = conn.createStatement();

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

    // this function aims at returning all jobs that meets the filter conditions
    protected ArrayList<ArrayList<String>> filter() {
        // create the where clause
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

        ArrayList<ArrayList<String>> li = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/project?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true", "root", "mysql123");
            stmt = conn.createStatement();

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

    // this function aims at adding a job row into database
    protected boolean addNewJob() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/project?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true", "root", "mysql123");
            stmt = conn.createStatement();

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

    // this function aims at updating a job row in database according to edition
    protected boolean editAJob() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/project?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true", "root", "mysql123");
            stmt = conn.createStatement();

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

    // this function aims at deleting a job row in database
    protected boolean deleteAJob() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/project?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true", "root", "mysql123");
            stmt = conn.createStatement();

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

    // this function aims at checking if all required input areas are complete and SQL successfully execute,
    // and return the status for GUI class
    protected String submit(String action) {
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

    // this function aims at checking if all required text areas are complete
    private boolean checkCpl() {
        return !company.equals("") && !position.equals("") && instancy != null &&
                returnOffer != null && !duty.equals("") && !location.equals("") &&
                remote != null && !applyLink.equals("") && !deadline.equals("");
    }

    // this function aims at reset all the job detail variables after submitting
    // if not do so, then when the user submits a second job, he/she can submit successfully even if some required areas are incomplete
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

    // this function aims at returning a job detail when user click a row in job table
    protected ArrayList<ArrayList<String>> view() {
        ArrayList<ArrayList<String>> li = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/project?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true", "root", "mysql123");
            stmt = conn.createStatement();

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

    // replaceQuotationMarks(), aims at making sure the SQL can execute even if there are quotation marks in inputs.
    private String rplQuo(String str) {
        return str.replace("'","\\\'").replace("\"","\\\"");
    }

    // this function aims at converting ArrayList<ArrayList<String>> type data into String[][] type data
    protected String[][] aList2Arr(ArrayList<ArrayList<String>> data) {
        String[][] tableData = new String[data.size()][];
        int i = 0;
        for (ArrayList<String> al : data) {
            tableData[i++] = al.toArray(new String[al.size()]);
        }

        return tableData;
    }
}
