import com.nancal.tools.MultiComboBox;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

public class GUI {
    // initiate three instance of Login, Register, Job class
    private Login login;
    private Register register;
    private Job job;

    // fixed size of frame
    private int frameWidth = 600;
    private int frameHeight = 600;

    // Only put the reused variables at below,
    // some others like labels, buttons that are only used in only one page will not initiate here
    // initiate the variables in Choose Identity Page which need to use repetitively
    private JFrame jfChooseIdentity;

    // initiate the variables in Log In Page which need to use repetitively
    private JFrame jfLogin;
    private JLabel jlUsername;
    private JTextField jtfUsername;
    private JLabel jlPassword;
    private JPasswordField jpfPassword;

    // initiate the variables in Register Page which need to use repetitively
    private JFrame jfRegister;
    private JPasswordField jpfConfirm;

    // initiate the variables in Student Page which need to use repetitively
    private JFrame jfStudent;
    private JPanel jpAllJobs;
    private JTextField jtfFilterCompany;
    private JTextField jtfFilterPosition;
    private JTextField jtfFilterLocation;
    private MultiComboBox comboxFilterInstancy;
    private MultiComboBox comboxFilterReturnOffer;
    private MultiComboBox comboxFilterRemote;

    // initiate the variables in Student Job Detail Page which need to use repetitively
    private JFrame jfStudentJobDetail;

    // initiate the variables in HR Page which need to use repetitively
    private JFrame jfHR;

    // initiate the variables in HR Job Detail Page which need to use repetitively
    private JFrame jfHRJobDetail;

    // initiate the variables in HR Job Input Page which need to use repetitively
    private JFrame jfJobInput;
    private String action;
    private JTextArea jtaCompany;
    private JTextArea jtaPosition;
    private JRadioButton radioBtnIns;
    private JRadioButton radioBtnInsN;
    private ButtonGroup btngrpInstancy;
    private JRadioButton radioBtnRet;
    private JRadioButton radioBtnRetN;
    private ButtonGroup btngrpReturnOffer;
    private JTextArea jtaDuty;
    private JTextArea jtaRequirement;
    private JTextArea jtaBenefit;
    private JTextArea jtaInternTime;
    private JTextArea jtaLocation;
    private JRadioButton radioBtnRem;
    private JRadioButton radioBtnRemN;
    private ButtonGroup btngrpRemote;
    private JTextArea jtaApplyLink;
    private JTextArea jtaApplyInstruction;
    private UtilDateModel model;
    private JDatePickerImpl datePicker;
    private JTextArea jtaOtherInfo;
    private JTextArea jtaIntro;

    // initiate the table header of tables in HR Page and Student Page
    private String[] tableHeader = {"id", "Company", "Position"};

    public GUI() {
        // Make the window look and feel better.
        JFrame.setDefaultLookAndFeelDecorated(true);

        // First display the Choose Identity Page,
        // this page is the initial page of all time, if log out then this page will display.
        chooseIdentityPage();
    }

    public void chooseIdentityPage() {
        // Choose Identity Page frame
        jfChooseIdentity = new JFrame("Candy Internship");
        jfChooseIdentity.setSize(frameWidth, frameHeight);
        jfChooseIdentity.setLocationRelativeTo(null);
        jfChooseIdentity.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Choose Identity Page panel to put widgets in
        JPanel jpChooseIdentity = new JPanel();
        jpChooseIdentity.setLayout(null);

        // create and place some widgets
        JLabel jlChooseIdentityLabel = new JLabel("You are...");
        // create two radio buttons
        JRadioButton radioBtnStudent = new JRadioButton("Student");
        JRadioButton radioBtnHR = new JRadioButton("Hiring Manager");
        // create button group, add two radio buttons into it
        // To make sure that only ONE button can be chosen at one time
        ButtonGroup btngrpIdentity = new ButtonGroup();
        btngrpIdentity.add(radioBtnStudent);
        btngrpIdentity.add(radioBtnHR);

        jpChooseIdentity.add(jlChooseIdentityLabel);
        jpChooseIdentity.add(radioBtnStudent);
        jpChooseIdentity.add(radioBtnHR);

        jlChooseIdentityLabel.setBounds(200, 150, 200, 50);
        radioBtnStudent.setBounds(200, 200, 200, 50);
        radioBtnHR.setBounds(200, 250, 200, 50);

        // display the Choose Identity Page frame
        jfChooseIdentity.setContentPane(jpChooseIdentity);
        jfChooseIdentity.setVisible(true);

        // Below are action listeners
        // An action listener that receives what user choose
        ActionListener sliceActionListener = actionEvent -> {
            // create an abstract button to get the text that user chooses
            AbstractButton aBtnIdentity = (AbstractButton) actionEvent.getSource();

            // After user chooses, then immediately jump to Log In Page
            login = new Login();
            login.setIdentity(aBtnIdentity.getText());
            loginPage();
        };
        radioBtnStudent.addActionListener(sliceActionListener);
        radioBtnHR.addActionListener(sliceActionListener);
    }

    public void loginPage() {
        // First remove Choose Identity Page
        jfChooseIdentity.setVisible(false);

        // Log In Page frame
        jfLogin = new JFrame("Candy Internship");
        jfLogin.setSize(frameWidth, frameHeight);
        jfLogin.setLocationRelativeTo(null);
        jfLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Log In Page panel to put widgets in
        JPanel jpLogin = new JPanel();
        jpLogin.setLayout(null);

        // create and place some widgets
        JButton jbBack = new JButton("Back");
        JLabel jlLoginLabel = new JLabel("Please log into your account...");
        jlUsername = new JLabel("Username:");
        jlPassword = new JLabel("Password:");
        jtfUsername = new JTextField(10);
        jpfPassword = new JPasswordField(10);
        JButton jbLogin = new JButton("Login");
        JLabel jlGoRegisterLabel = new JLabel("Don't have an account?");
        JButton jbGoRegister = new JButton("Go To Register");

        jpLogin.add(jbBack);
        jpLogin.add(jlLoginLabel);
        jpLogin.add(jlUsername);
        jpLogin.add(jtfUsername);
        jpLogin.add(jlPassword);
        jpLogin.add(jpfPassword);
        jpLogin.add(jbLogin);
        jpLogin.add(jlGoRegisterLabel);
        jpLogin.add(jbGoRegister);

        jbBack.setBounds(20, 20, 100, 30);
        jlLoginLabel.setBounds(200, 50, 200, 30);
        jlUsername.setBounds(200, 100, 100, 30);
        jtfUsername.setBounds(300, 100, 100, 30);
        jlPassword.setBounds(200, 150, 100, 30);
        jpfPassword.setBounds(300, 150, 100, 30);
        jbLogin.setBounds(260, 200, 80, 30);
        jlGoRegisterLabel.setBounds(230, 470, 160, 30);
        jbGoRegister.setBounds(220, 500, 160, 30);

        // display Log In Page frame
        jfLogin.setContentPane(jpLogin);
        jfLogin.setVisible(true);

        // Below are action listeners
        // When click Back button, the go back to Choose Identity PAge
        jbBack.addActionListener(e -> {
            jfChooseIdentity.setVisible(true);
            jfLogin.setVisible(false);
        });

        // When click Login button, then call clickButtonLogin() function
        jbLogin.addActionListener(e -> clickButtonLogin());

        // When click GoRegister button, then call registerPage() function
        jbGoRegister.addActionListener(e -> registerPage());

        // user can hit enter to log in, not have to click Login button
        jpfPassword.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                if (e.getKeyChar() == KeyEvent.VK_ENTER) {
                    clickButtonLogin();
                }
            }
        });
    }

    // This function aims at checking account validity.
    private void clickButtonLogin() {
        // assign what the user inputs to login object
        login.setUsername(jtfUsername.getText());
        login.setPassword(String.valueOf(jpfPassword.getPassword()));
        // Get the status of login() function returns, for the next steps
        String status = login.login();

        // If status is an error the jump out the warning window, if not then go to user's page.
        if (status.equals("ok")) {
            // Go to identity's page
            job = new Job();
            jfLogin.setVisible(false);
            if (login.getIdentity().equals("Student")) {
                // Go to student's page
                studentPage();
            } else {
                // Go to the HR's page
                hrPage();
            }
        } else if (status.equals("psw not correct")) {
            // warning of psw not correct jumps out
            JOptionPane.showMessageDialog(jfLogin, "Password is incorrect!", "INCORRECT", JOptionPane.WARNING_MESSAGE);
        } else if (status.equals("username not exists")) {
            // warning of username not exists jumps out
            JOptionPane.showMessageDialog(jfLogin, "Username not exists!", "NOT EXIST", JOptionPane.WARNING_MESSAGE);
        } else {
            // warning of error jumps out
            JOptionPane.showMessageDialog(jfLogin, "Something goes error!", "ERROR", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void registerPage() {
        // First remove the Log In Page frame
        jfLogin.setVisible(false);

        // Register Page frame
        jfRegister = new JFrame("Candy Internship");
        jfRegister.setSize(frameWidth, frameHeight);
        jfRegister.setLocationRelativeTo(null);
        jfRegister.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Register Page panel to put widgets in
        JPanel jpRegister = new JPanel();
        jpRegister.setLayout(null);

        // create and place some widgets
        JButton jbBack = new JButton("Back");
        JLabel jlRegisterLabel = new JLabel("Please register your new account...");
        jlUsername = new JLabel("Username:");
        jlPassword = new JLabel("Password:");
        JLabel jlConfirm = new JLabel("Confirm:");
        jtfUsername = new JTextField(10);
        jpfPassword = new JPasswordField(10);
        jpfConfirm = new JPasswordField();
        JButton jbRegister = new JButton("Register");

        jpRegister.add(jbBack);
        jpRegister.add(jlRegisterLabel);
        jpRegister.add(jlUsername);
        jpRegister.add(jtfUsername);
        jpRegister.add(jlPassword);
        jpRegister.add(jpfPassword);
        jpRegister.add(jlConfirm);
        jpRegister.add(jpfConfirm);
        jpRegister.add(jbRegister);

        jbBack.setBounds(20, 20, 100, 30);
        jlRegisterLabel.setBounds(190, 50, 220, 30);
        jlUsername.setBounds(200, 100, 100, 30);
        jtfUsername.setBounds(300, 100, 100, 30);
        jlPassword.setBounds(200, 150, 100, 30);
        jpfPassword.setBounds(300, 150, 100, 30);
        jlConfirm.setBounds(200, 200, 100, 30);
        jpfConfirm.setBounds(300, 200, 100, 30);
        jbRegister.setBounds(260, 250, 80, 30);

        // display the Register Page frame
        jfRegister.setContentPane(jpRegister);
        jfRegister.setVisible(true);

        // Below are action listeners
        // click Back button then go back to Log In Page
        jbBack.addActionListener(e -> {
            jfRegister.setVisible(false);
            jfLogin.setVisible(true);
        });

        // click Register button then call clickButtonRegister() function
        jbRegister.addActionListener(e -> clickButtonRegister());
    }

    // This function aims at checking new account validity.
    public void clickButtonRegister() {
        // initiate and assign values to object register
        register = new Register();
        register.setIdentity(login.getIdentity());
        register.setUsername(jtfUsername.getText());
        register.setPassword(String.valueOf(jpfPassword.getPassword()));
        register.setReenterPassword(String.valueOf(jpfConfirm.getPassword()));
        // Get the status of register() function returns, for the next steps
        String status = register.register();

        // If status is an error the jump out the warning window, if not then go to login page.
        if (status.equals("ok")) {
            // inputs are valid, go to login page
            jfRegister.setVisible(false);
            jfLogin.setVisible(true);

            // set the login page inputs the same as what inputted in the register page
            jtfUsername.setText(register.getUsername());
        } else if (status.equals("psw not match")) {
            // warning about password not match jumps out
            JOptionPane.showMessageDialog(jfRegister, "Passwords don't match!", "NOT MATCH", JOptionPane.WARNING_MESSAGE);
        } else if (status.equals("username exists")) {
            // warning about username already exists jumps out
            JOptionPane.showMessageDialog(jfRegister, "Username already exists!", "EXIST", JOptionPane.WARNING_MESSAGE);
        } else {
            // warning about some errors occur jumps out
            JOptionPane.showMessageDialog(jfRegister, "Something goes error!", "ERROR", JOptionPane.WARNING_MESSAGE);
        }
    }

    public void studentPage() {
        // Student Page frame
        jfStudent = new JFrame("Hi, " + login.getUsername());
        jfStudent.setSize(frameWidth, frameHeight);
        jfStudent.setLocationRelativeTo(null);
        // Set the layout pattern to Grid Layout.
        jfStudent.setLayout(new GridLayout());
        jfStudent.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // This frame is divided into two parts (Filter panel and AllJobs panel),
        // so use a split pane to put panels in.
        JSplitPane jspStudent = new JSplitPane();
        JPanel jpFilter = new JPanel();
        jpAllJobs = new JPanel();
        jpFilter.setLayout(null);
        jpAllJobs.setLayout(null);

        // create and place Filter panel's widgets
        JButton jbLogout = new JButton("Log Out");

        JLabel jlFilterCompany = new JLabel("Company:");
        JLabel jlFilterPosition = new JLabel("Position:");
        JLabel jlFilterLocation = new JLabel("Location:");

        jtfFilterCompany = new JTextField(20);
        jtfFilterPosition = new JTextField(20);
        jtfFilterLocation = new JTextField(20);

        JLabel jlFilterInstancy = new JLabel("Instancy:");
        JLabel jlFilterReturnOffer = new JLabel("Return:");
        JLabel jlFilterRemote = new JLabel("Remote:");

        Object[] valuesFilterInstancy = new Object[]{"All", "Instant", "Not Instant"};
        comboxFilterInstancy = new MultiComboBox(valuesFilterInstancy);

        Object[] valuesFilterReturn = new Object[]{"All", "Has Return Offer", "No Return Offer"};
        comboxFilterReturnOffer = new MultiComboBox(valuesFilterReturn);

        Object[] valuesFilterRemote = new Object[]{"All", "Can Remote", "Cannot Remote"};
        comboxFilterRemote = new MultiComboBox(valuesFilterRemote);

        JButton jbFilter = new JButton("Filter");

        jpFilter.add(jbLogout);
        jpFilter.add(jlFilterCompany);
        jpFilter.add(jlFilterPosition);
        jpFilter.add(jlFilterLocation);
        jpFilter.add(jlFilterInstancy);
        jpFilter.add(jlFilterReturnOffer);
        jpFilter.add(jlFilterRemote);
        jpFilter.add(jtfFilterCompany);
        jpFilter.add(jtfFilterPosition);
        jpFilter.add(jtfFilterLocation);
        jpFilter.add(comboxFilterInstancy);
        jpFilter.add(comboxFilterReturnOffer);
        jpFilter.add(comboxFilterRemote);
        jpFilter.add(jbFilter);

        jbLogout.setBounds(480, 20, 100, 30);

        jlFilterCompany.setBounds(20, 70, 80, 30);
        jtfFilterCompany.setBounds(100, 70, 100, 30);
        jlFilterPosition.setBounds(210, 70, 80, 30);
        jtfFilterPosition.setBounds(290, 70, 100, 30);
        jlFilterLocation.setBounds(400, 70, 80, 30);
        jtfFilterLocation.setBounds(480, 70, 100, 30);

        jlFilterInstancy.setBounds(20, 120, 80, 30);
        comboxFilterInstancy.setBounds(100, 120, 100, 30);
        jlFilterReturnOffer.setBounds(210, 120, 80, 30);
        comboxFilterReturnOffer.setBounds(290, 120, 100, 30);
        jlFilterRemote.setBounds(400, 120, 80, 30);
        comboxFilterRemote.setBounds(480, 120, 100, 30);

        jbFilter.setBounds(250, 170, 100, 30);

        // create and place AllJobs panel's widgets
        // job.showAllJobs() function is to get all valid (haven't expired) jobs from database
        // createJobTable is to create a scrollable panel to place all jobs.
        JScrollPane pane = createJobTable(job.showAllJobs());
        pane.setBounds(20, 20, 560, 320);
        jpAllJobs.add(pane);

        // make Student Page frame into two parts and display
        jfStudent.setContentPane(jspStudent);
        jspStudent.setOrientation(JSplitPane.VERTICAL_SPLIT);
        jspStudent.setDividerLocation(220);
        jspStudent.setTopComponent(jpFilter);
        jspStudent.setBottomComponent(jpAllJobs);
        jfStudent.setVisible(true);

        // Below are action listeners
        // if click Logout button then call logout() function
        jbLogout.addActionListener(e -> logout(jfStudent));

        // if click Filter button then call filterJobs() function
        jbFilter.addActionListener(e -> filterJobs());
    }

    // This function aims at refresh the job table after clicking the Filter button
    public void filterJobs() {
        // empty panel
        jpAllJobs.removeAll();

        // get filter parameters
        job.filterCompany = jtfFilterCompany.getText();
        job.filterPosition = jtfFilterPosition.getText();
        job.filterLocation = jtfFilterLocation.getText();
        job.filterInstancy = comboxFilterInstancy.getSelectedValues();
        job.filterReturnOffer = comboxFilterReturnOffer.getSelectedValues();
        job.filterRemote = comboxFilterRemote.getSelectedValues();

        // refresh table
        // job.filter() function is to return all the jobs according to user's preferences
        JScrollPane pane = createJobTable(job.filter());
        pane.setBounds(20, 20, 560, 320);
        jpAllJobs.add(pane);
    }

    public void hrPage() {
        // Student Page frame
        jfHR = new JFrame("Hi, " + login.getUsername());
        jfHR.setSize(frameWidth, frameHeight);
        jfHR.setLocationRelativeTo(null);
        jfHR.setLayout(new GridLayout());
        jfHR.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // This frame is divided into two parts (Top panel and AllJobs panel),
        // so use a split pane to put panels in
        JSplitPane jspHR = new JSplitPane();
        JPanel jpTop = new JPanel();
        jpAllJobs = new JPanel();
        jpTop.setLayout(null);
        jpAllJobs.setLayout(null);

        // create the top panel and put widgets in (Add button and Logout button)
        JButton jbLogout = new JButton("Log Out");
        JButton jbAddNewJob = new JButton("Add A New Job");
        jpTop.add(jbLogout);
        jpTop.add(jbAddNewJob);
        jbLogout.setBounds(480, 20, 100, 30);
        jbAddNewJob.setBounds(200, 70, 200, 30);

        // create jobs panel and put widgets in
        JLabel jlAllYourJobs = new JLabel("Below are all your uploaded jobs...");
        jpAllJobs.add(jlAllYourJobs);
        jlAllYourJobs.setBounds(190, 10, 220, 30);

        job.setUsername(login.getUsername());
        // job.showJobsByUser() function aims at returning all jobs uploaded by this HR
        JScrollPane pane = createJobTable(job.showJobsByUser());
        pane.setBounds(20, 50, 560, 390);
        jpAllJobs.add(pane);

        // make HR Page frame into two parts and display
        jfHR.setContentPane(jspHR);
        jspHR.setOrientation(JSplitPane.VERTICAL_SPLIT);
        jspHR.setDividerLocation(120);
        jspHR.setTopComponent(jpTop);
        jspHR.setBottomComponent(jpAllJobs);
        jfHR.setVisible(true);

        // Below are action listeners
        // if clisk Logout button then call logout() function
        jbLogout.addActionListener(e -> logout(jfHR));

        // if click AddNewJob button then call addAJob() function and set the action variable to add,
        // because the page of "add a job" and "edit a job" is different
        jbAddNewJob.addActionListener(e -> {
            action = "add";
            addAJob();
        });
    }

    public void stuJobDetailPage() {
        // First remove the Student Page
        jfStudent.setVisible(false);

        // Student Job Detail Page frame
        jfStudentJobDetail = new JFrame("Hi, " + login.getUsername());
        jfStudentJobDetail.setSize(frameWidth, frameHeight);
        jfStudentJobDetail.setLocationRelativeTo(null);
        jfStudentJobDetail.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // This frame is divided into two parts (Top panel and StudentJobDetail panel),
        // so use a split pane to put panels in
        JSplitPane jsplitpStudent = new JSplitPane();

        // create top panel and put widgets in (back, logout)
        JPanel jpTop = new JPanel();
        jpTop.setLayout(null);
        JButton jbBack = new JButton("Back");
        JButton jbLogout = new JButton("Log Out");
        jpTop.add(jbBack);
        jpTop.add(jbLogout);
        jbBack.setBounds(20, 20, 100, 30);
        jbLogout.setBounds(480, 20, 100, 30);

        // create job detail panel and put widgets in (scrollable text area)
        ArrayList<String> jobDetail = job.view().get(0);
        String detail = String.format("Company: %s\nPosition: %s\n%s\n%s\n%s\n\n" +
                        "Duties:\n%s\n\nRequirements:\n%s\n\nBenefits:\n%s\n\n" +
                        "Intern Time: %s\nLocation(s): %s\n\n" +
                        "Apply Link: %s\nApply Instructions:\n%s\n\n" +
                        "Deadline: %s\n\nOther Information:\n%s\n\nIntroduction of Company:\n%s",
                jobDetail.get(2), jobDetail.get(3),
                (jobDetail.get(4).equals("0")) ? "Not Instant" : "Instant",
                (jobDetail.get(5).equals("0")) ? "No Return Offer" : "Has Return Offer",
                (jobDetail.get(11).equals("0")) ? "Cannot Remote" : "Can Remote",
                jobDetail.get(6), jobDetail.get(7), jobDetail.get(8),
                jobDetail.get(9), jobDetail.get(10),
                jobDetail.get(12), jobDetail.get(13),
                jobDetail.get(14), jobDetail.get(15), jobDetail.get(16));
        JTextArea jtaStudentJobDetail = new JTextArea(detail);
        jtaStudentJobDetail.setEditable(false);
        jtaStudentJobDetail.setColumns(20);
        jtaStudentJobDetail.setRows(100);
        jtaStudentJobDetail.setLineWrap(false);
        JScrollPane jspStudentJobDetail = new JScrollPane(jtaStudentJobDetail);
        jtaStudentJobDetail.setBackground(null);

        // make Student Job Detail Page frame into two parts and display
        jfStudentJobDetail.setContentPane(jsplitpStudent);
        jsplitpStudent.setOrientation(JSplitPane.VERTICAL_SPLIT);
        jsplitpStudent.setDividerLocation(70);
        jsplitpStudent.setTopComponent(jpTop);
        jsplitpStudent.setBottomComponent(jspStudentJobDetail);
        jfStudentJobDetail.setVisible(true);

        // Below are action listeners
        jbBack.addActionListener(e -> {
            jfStudent.setVisible(true);
            jfStudentJobDetail.setVisible(false);
        });

        jbLogout.addActionListener(e -> logout(jfStudentJobDetail));
    }

    public void hrJobDetailPage() {
        // First remove HR Page
        jfHR.setVisible(false);

        // HR Job Detail Page frame
        jfHRJobDetail = new JFrame("Hi, " + login.getUsername());
        jfHRJobDetail.setSize(frameWidth, frameHeight);
        jfHRJobDetail.setLocationRelativeTo(null);
        jfHRJobDetail.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // This frame is divided into three parts (Top panel, JobDetail panel and Operation panel),
        // so use TWO split pane to put panels in
        JSplitPane jsplitpHR = new JSplitPane();
        JSplitPane jsplitpHRJob = new JSplitPane();

        // create top panel (back, logout)
        JPanel jpTop = new JPanel();
        jpTop.setLayout(null);
        JButton jbBack = new JButton("Back");
        JButton jbLogout = new JButton("Log Out");
        jpTop.add(jbBack);
        jpTop.add(jbLogout);
        jbBack.setBounds(20, 20, 100, 30);
        jbLogout.setBounds(480, 20, 100, 30);

        // create center panel (detail)
        ArrayList<String> jobDetail = job.view().get(0);
        String detail = String.format("Company: %s\nPosition: %s\n%s\n%s\n%s\n\n" +
                        "Duties:\n%s\n\nRequirements:\n%s\n\nBenefits:\n%s\n\n" +
                        "Intern Time: %s\nLocation(s): %s\n\n" +
                        "Apply Link: %s\nApply Instructions:\n%s\n\n" +
                        "Deadline: %s\n\nOther Information:\n%s\n\nIntroduction of Company:\n%s",
                jobDetail.get(2), jobDetail.get(3),
                (jobDetail.get(4).equals("0")) ? "Not Instant" : "Instant",
                (jobDetail.get(5).equals("0")) ? "No Return Offer" : "Has Return Offer",
                (jobDetail.get(11).equals("0")) ? "Cannot Remote" : "Can Remote",
                jobDetail.get(6), jobDetail.get(7), jobDetail.get(8),
                jobDetail.get(9), jobDetail.get(10),
                jobDetail.get(12), jobDetail.get(13),
                jobDetail.get(14), jobDetail.get(15), jobDetail.get(16));
        JTextArea jtaHRJobDetail = new JTextArea(detail);
        jtaHRJobDetail.setEditable(false);
        jtaHRJobDetail.setColumns(20);
        jtaHRJobDetail.setRows(100);
        jtaHRJobDetail.setLineWrap(false);
        JScrollPane jspHRJobDetail = new JScrollPane(jtaHRJobDetail);
        jtaHRJobDetail.setBackground(null);

        // create bottom panel (edit, delete)
        JPanel jpOperation = new JPanel();
        jpOperation.setLayout(null);
        JButton jbEdit = new JButton("Edit");
        JButton jbDelete = new JButton("Delete");
        jpOperation.add(jbEdit);
        jpOperation.add(jbDelete);
        jbEdit.setBounds(150, 20, 100, 30);
        jbDelete.setBounds(350, 20, 100, 30);

        // make HR Job Detail Page frame into THREE parts and display
        jsplitpHR.setOrientation(JSplitPane.VERTICAL_SPLIT);
        jsplitpHR.setDividerLocation(70);
        jsplitpHR.setTopComponent(jpTop);
        jsplitpHR.setBottomComponent(jsplitpHRJob);

        jsplitpHRJob.setOrientation(JSplitPane.VERTICAL_SPLIT);
        jsplitpHRJob.setDividerLocation(400);
        jsplitpHRJob.setTopComponent(jspHRJobDetail);
        jsplitpHRJob.setBottomComponent(jpOperation);

        jfHRJobDetail.setContentPane(jsplitpHR);
        jfHRJobDetail.setVisible(true);

        // Below are action listeners
        // if click Edit button then call editAJob() function and set the action variable to edit,
        // because the page of "add a job" and "edit a job" is different
        jbEdit.addActionListener(e -> {
            action = "edit";
            editAJob();
        });

        // if click Delete button then jumps out the warning window to let user make sure
        jbDelete.addActionListener(e -> {
            //Custom button text
            Object[] options = {"Yes", "No",};
            int choice = JOptionPane.showOptionDialog(jfHRJobDetail,
                    "Are you sure to DELETE this job?",
                    "DELETE",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE,
                    null,
                    options,
                    // default option is NO
                    options[1]);

            if (choice == 0) {
                // delete
                deleteAJob();
            }
        });

        jbBack.addActionListener(e -> {
            jfHR.setVisible(true);
            jfHRJobDetail.setVisible(false);
        });

        jbLogout.addActionListener(e -> logout(jfHRJobDetail));
    }

    public void jobInputPage() {
        // First remove the HR Page and HR Job Detail Page
        jfHR.setVisible(false);
        jfHRJobDetail.setVisible(false);

        // Job Input Page frame
        jfJobInput = new JFrame("Hi, " + login.getUsername());
        jfJobInput.setSize(frameWidth, frameHeight);
        jfJobInput.setLocationRelativeTo(null);
        jfJobInput.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // This frame is divided into two parts (Back panel and JobInput panel),
        // so use a split pane to put panels in
        JSplitPane jsplitpJobInput = new JSplitPane();

        // create top panel (back button)
        JPanel jpBack = new JPanel();
        jpBack.setLayout(null);
        JButton jbBack = new JButton("Back");
        jpBack.add(jbBack);
        jbBack.setBounds(20, 20, 100, 30);

        // create bottom panel
        // set layout pattern to BoxLayout
        JPanel jpJobInput = new JPanel();
        BoxLayout boxlayout = new BoxLayout(jpJobInput, BoxLayout.Y_AXIS);
        jpJobInput.setLayout(boxlayout);
        jpJobInput.setBorder(new EmptyBorder(new Insets(30, 30, 200, 30)));

        // the Job Input panel needs scrollbar and vertical scrollbar always shows,
        // horizontal scrollbar only shows when needed.
        JScrollPane jspJobInput = new JScrollPane(jpJobInput, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        // Have to set preferred size to make sure that Job Input panel take fixed space,
        // but in that space, the content can be scrolled.
        // jpJobInput's width is a bit smaller than frame's, because the scrollbar takes a few spaces
        jpJobInput.setPreferredSize(new Dimension(frameWidth - 20, 2000));
        jspJobInput.setPreferredSize(new Dimension(frameWidth, frameHeight));

        // create widgets
        JLabel jlCompany = new JLabel("*Company:");
        jtaCompany = new JTextArea();
        jtaCompany.setEditable(true);
        jtaCompany.setColumns(20);
        jtaCompany.setRows(2);
        jtaCompany.setLineWrap(false);
        JScrollPane jspCompany = new JScrollPane(jtaCompany);

        JLabel jlPosition = new JLabel("*Position:");
        jtaPosition = new JTextArea();
        jtaPosition.setEditable(true);
        jtaPosition.setColumns(20);
        jtaPosition.setRows(2);
        jtaPosition.setLineWrap(false);
        JScrollPane jspPosition = new JScrollPane(jtaPosition);

        radioBtnIns = new JRadioButton("Instant");
        radioBtnInsN = new JRadioButton("Not Instant");
        btngrpInstancy = new ButtonGroup();
        btngrpInstancy.add(radioBtnIns);
        btngrpInstancy.add(radioBtnInsN);

        radioBtnRet = new JRadioButton("Has Return Offer");
        radioBtnRetN = new JRadioButton("No Return Offer");
        btngrpReturnOffer = new ButtonGroup();
        btngrpReturnOffer.add(radioBtnRet);
        btngrpReturnOffer.add(radioBtnRetN);

        JLabel jlDuty = new JLabel("*Duty:");
        jtaDuty = new JTextArea();
        jtaDuty.setEditable(true); // make the text area editable
        jtaDuty.setColumns(20);
        jtaDuty.setRows(8); // shows 8 rows' height but it can be more because the text area is attached to a scroll panel
        jtaDuty.setLineWrap(false); // NOT automatically wrap line
        JScrollPane jspDuty = new JScrollPane(jtaDuty); // attach the text area to a scrollable panel

        JLabel jlRequirement = new JLabel("Requirement:");
        jtaRequirement = new JTextArea();
        jtaRequirement.setEditable(true);
        jtaRequirement.setColumns(20);
        jtaRequirement.setRows(8);
        jtaRequirement.setLineWrap(false);
        JScrollPane jspRequirement = new JScrollPane(jtaRequirement);

        JLabel jlBenefit = new JLabel("Benefit:");
        jtaBenefit = new JTextArea();
        jtaBenefit.setEditable(true);
        jtaBenefit.setColumns(20);
        jtaBenefit.setRows(8);
        jtaBenefit.setLineWrap(false);
        JScrollPane jspBenefit = new JScrollPane(jtaBenefit);

        JLabel jlInternTime = new JLabel("Intern Time:");
        jtaInternTime = new JTextArea();
        jtaInternTime.setEditable(true);
        jtaInternTime.setColumns(20);
        jtaInternTime.setRows(3);
        jtaInternTime.setLineWrap(false);
        JScrollPane jspInternTime = new JScrollPane(jtaInternTime);

        JLabel jlLocation = new JLabel("*Location:");
        jtaLocation = new JTextArea();
        jtaLocation.setEditable(true);
        jtaLocation.setColumns(20);
        jtaLocation.setRows(3);
        jtaLocation.setLineWrap(false);
        JScrollPane jspLocation = new JScrollPane(jtaLocation);

        radioBtnRem = new JRadioButton("Can Remote");
        radioBtnRemN = new JRadioButton("Cannot Remote");
        btngrpRemote = new ButtonGroup();
        btngrpRemote.add(radioBtnRem);
        btngrpRemote.add(radioBtnRemN);

        JLabel jlApplyLink = new JLabel("*Apply Link:");
        jtaApplyLink = new JTextArea();
        jtaApplyLink.setEditable(true);
        jtaApplyLink.setColumns(20);
        jtaApplyLink.setRows(5);
        jtaApplyLink.setLineWrap(false);
        JScrollPane jspApplyLink = new JScrollPane(jtaApplyLink);

        JLabel jlApplyInstruction = new JLabel("Apply Instruction:");
        jtaApplyInstruction = new JTextArea();
        jtaApplyInstruction.setEditable(true);
        jtaApplyInstruction.setColumns(20);
        jtaApplyInstruction.setRows(5);
        jtaApplyInstruction.setLineWrap(false);
        JScrollPane jspApplyInstruction = new JScrollPane(jtaApplyInstruction);

        JLabel jlDeadline = new JLabel("*Deadline:");
        // default date is today
        model = new UtilDateModel();
        model.setDate(LocalDate.now().getYear(), LocalDate.now().getMonthValue()-1, LocalDate.now().getDayOfMonth());
        model.setSelected(true);
        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
        datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());

        JLabel jlOtherInfo = new JLabel("Other Information:");
        jtaOtherInfo = new JTextArea();
        jtaOtherInfo.setEditable(true);
        jtaOtherInfo.setColumns(20);
        jtaOtherInfo.setRows(8);
        jtaOtherInfo.setLineWrap(false);
        JScrollPane jspOtherInfo = new JScrollPane(jtaOtherInfo);

        JLabel jlIntro = new JLabel("Introduction of Company:");
        jtaIntro = new JTextArea();
        jtaIntro.setEditable(true);
        jtaIntro.setColumns(20);
        jtaIntro.setRows(8);
        jtaIntro.setLineWrap(false);
        JScrollPane jspIntro = new JScrollPane(jtaIntro);

        JButton jbSubmit = new JButton("Submit");

        // add widgets to panel
        jpJobInput.add(jlCompany);
        jpJobInput.add(jspCompany);
        // between company part and position part, insert some blank spaces
        jpJobInput.add(Box.createRigidArea(new Dimension(0, 20)));
        jpJobInput.add(jlPosition);
        jpJobInput.add(jspPosition);
        jpJobInput.add(Box.createRigidArea(new Dimension(0, 20)));
        jpJobInput.add(radioBtnIns);
        jpJobInput.add(radioBtnInsN);
        jpJobInput.add(Box.createRigidArea(new Dimension(0, 20)));
        jpJobInput.add(radioBtnRet);
        jpJobInput.add(radioBtnRetN);
        jpJobInput.add(Box.createRigidArea(new Dimension(0, 20)));
        jpJobInput.add(jlDuty);
        jpJobInput.add(jspDuty);
        jpJobInput.add(Box.createRigidArea(new Dimension(0, 20)));
        jpJobInput.add(jlRequirement);
        jpJobInput.add(jspRequirement);
        jpJobInput.add(Box.createRigidArea(new Dimension(0, 20)));
        jpJobInput.add(jlBenefit);
        jpJobInput.add(jspBenefit);
        jpJobInput.add(Box.createRigidArea(new Dimension(0, 20)));
        jpJobInput.add(jlInternTime);
        jpJobInput.add(jspInternTime);
        jpJobInput.add(Box.createRigidArea(new Dimension(0, 20)));
        jpJobInput.add(jlLocation);
        jpJobInput.add(jspLocation);
        jpJobInput.add(Box.createRigidArea(new Dimension(0, 20)));
        jpJobInput.add(radioBtnRem);
        jpJobInput.add(radioBtnRemN);
        jpJobInput.add(Box.createRigidArea(new Dimension(0, 20)));
        jpJobInput.add(jlApplyLink);
        jpJobInput.add(jspApplyLink);
        jpJobInput.add(Box.createRigidArea(new Dimension(0, 20)));
        jpJobInput.add(jlApplyInstruction);
        jpJobInput.add(jspApplyInstruction);
        jpJobInput.add(Box.createRigidArea(new Dimension(0, 20)));
        jpJobInput.add(jlDeadline);
        jpJobInput.add(datePicker);
        jpJobInput.add(Box.createRigidArea(new Dimension(0, 20)));
        jpJobInput.add(jlOtherInfo);
        jpJobInput.add(jspOtherInfo);
        jpJobInput.add(Box.createRigidArea(new Dimension(0, 20)));
        jpJobInput.add(jlIntro);
        jpJobInput.add(jspIntro);
        jpJobInput.add(Box.createRigidArea(new Dimension(0, 50)));
        jpJobInput.add(jbSubmit);

        // make Job Input Page frame into two parts and display
        jfJobInput.setContentPane(jsplitpJobInput);
        jsplitpJobInput.setOrientation(JSplitPane.VERTICAL_SPLIT);
        jsplitpJobInput.setDividerLocation(70);
        jsplitpJobInput.setTopComponent(jpBack);
        jsplitpJobInput.setBottomComponent(jspJobInput);
        jfJobInput.setVisible(true);

        // Below are action listeners
        // if click submit button, call submitAJob() function
        jbSubmit.addActionListener(e -> submitAJob());

        jbBack.addActionListener(e -> {
            //Custom button text
            Object[] options = {"Yes", "No",};
            int choice = JOptionPane.showOptionDialog(jfJobInput,
                    "You haven't save this editing. Are you sure to exit?",
                    "NOT SAVE",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE,
                    null,
                    options,
                    options[1]);

            if (choice == 0) {
                // back
                jfJobInput.setVisible(false);
                jfHR.setVisible(true);
            }
        });
    }

    public void addAJob() {
        jobInputPage();
    }

    public void editAJob() {
        // job.edit() function aims at returning the job detail that the user uploaded before
        ArrayList<String> detail = job.edit().get(0);

        // display the Job Input Page
        jobInputPage();

        // Set all the input areas' value to what the user uploaded before
        jtaCompany.setText(detail.get(2));
        jtaPosition.setText(detail.get(3));
        btngrpInstancy.setSelected(detail.get(4).equals("1") ? radioBtnIns.getModel() : radioBtnInsN.getModel(), true);
        btngrpReturnOffer.setSelected(detail.get(5).equals("1") ? radioBtnRet.getModel() : radioBtnRetN.getModel(), true);
        jtaDuty.setText(detail.get(6));
        jtaRequirement.setText(detail.get(7));
        jtaBenefit.setText(detail.get(8));
        jtaInternTime.setText(detail.get(9));
        jtaLocation.setText(detail.get(10));
        btngrpRemote.setSelected(detail.get(11).equals("1") ? radioBtnRem.getModel() : radioBtnRemN.getModel(), true);
        jtaApplyLink.setText(detail.get(12));
        jtaApplyInstruction.setText(detail.get(13));
        // make the date picker show the date that the user chose before
        model.setYear(Integer.parseInt(detail.get(14).substring(0, 4)));
        model.setMonth(Integer.parseInt(detail.get(14).substring(5, 7)) - 1);
        model.setDay(Integer.parseInt(detail.get(14).substring(8)));
        model.setSelected(true);
        jtaOtherInfo.setText(detail.get(15));
        jtaIntro.setText(detail.get(16));
    }

    // this function aims at get all input areas' value and check if complete
    private void submitAJob() {
        // Get all input areas' values
        job.company = jtaCompany.getText();
        job.position = jtaPosition.getText();
        if (radioBtnIns.isSelected() || radioBtnInsN.isSelected()) {
            job.instancy = (radioBtnIns.isSelected()) ? true : false;
        }
        if (radioBtnRet.isSelected() || radioBtnRetN.isSelected()) {
            job.returnOffer = (radioBtnRet.isSelected()) ? true : false;
        }
        job.duty = jtaDuty.getText();
        job.requirement = jtaRequirement.getText();
        job.benefit = jtaBenefit.getText();
        job.internTime = jtaInternTime.getText();
        job.location = jtaLocation.getText();
        if (radioBtnRem.isSelected() || radioBtnRemN.isSelected()) {
            job.remote = (radioBtnRem.isSelected()) ? true : false;
        }
        job.applyLink = jtaApplyLink.getText();
        job.applyInstruction = jtaApplyInstruction.getText();
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
        job.deadline = ft.format(datePicker.getModel().getValue());
        job.otherInfo = jtaOtherInfo.getText();
        job.intro = jtaIntro.getText();

        // job.submit() aims at returning the submit result,
        // is it successfully uploaded to database?
        // are there any required areas that have nothing input?
        String status = job.submit(action);

        // if all is done, then go back to HR Page and refresh the job table
        if (status.equals("incomplete")) {
            // warning of required text areas not complete jumps out
            JOptionPane.showMessageDialog(jfJobInput, "*'s and radio buttons are required!", "INCOMPLETE", JOptionPane.WARNING_MESSAGE);
        } else if (status.equals("SQL error")) {
            // warning of input error jumps out
            JOptionPane.showMessageDialog(jfJobInput, "Do not include quotation marks in your input!", "ERROR", JOptionPane.WARNING_MESSAGE);
        } else {
            jfJobInput.setVisible(false);

            // refresh the table
            jpAllJobs.remove(1);
            JScrollPane pane = createJobTable(job.showJobsByUser());
            pane.setBounds(20, 50, 560, 390);
            jpAllJobs.add(pane);

            jfHR.setVisible(true);
        }
    }

    // this function aims at delete a job from database and go back to HR Page and refresh the job table
    public void deleteAJob() {
        // First remove Job Input Page
        jfHRJobDetail.setVisible(false);

        // call job.deleteAJob() function to delete a job from database
        job.deleteAJob();

        // refresh the table
        jpAllJobs.remove(1);
        JScrollPane pane = createJobTable(job.showJobsByUser());
        pane.setBounds(20, 50, 560, 390);
        jpAllJobs.add(pane);

        // make HR Page show again
        jfHR.setVisible(true);
    }

    // this function aims at using returning value to create a JScrollPane
    public JScrollPane createJobTable(ArrayList<ArrayList<String>> data) {
        // convert the list value to array
        String[][] tableData = job.aList2Arr(data);

        // create a table to display all the jobs
        JTable table = new JTable(tableData, tableHeader);
        JScrollPane pane = new JScrollPane(table);

        // make the table in a preferred pattern
        renderTable(table);

        // when click a row, get that row's job id and go to the Job Detail Page
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                job.id = Integer.valueOf(table.getValueAt(table.getSelectedRow(), 0).toString());
                if (login.getIdentity().equals("Student")) {
                    stuJobDetailPage();
                } else {
                    hrJobDetailPage();
                }
            }
        });

        return pane;
    }

    private void renderTable(JTable table) {
        table.setRowHeight(30);
        // inorder to let the scrollbar make sense, use this line so the table's automatically resizing won't happen
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        TableColumn tc = table.getColumnModel().getColumn(0);
        tc.setMaxWidth(0);
        tc.setPreferredWidth(0);
        tc.setMinWidth(0);
        tc.setWidth(0);
        table.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(0);
        table.getTableHeader().getColumnModel().getColumn(0).setMinWidth(0);

        tc = table.getColumnModel().getColumn(1);
        tc.setMaxWidth(275);
        tc.setPreferredWidth(275);
        tc.setMinWidth(275);
        tc.setWidth(275);
        table.getTableHeader().getColumnModel().getColumn(1).setMaxWidth(275);
        table.getTableHeader().getColumnModel().getColumn(1).setMinWidth(275);

        tc = table.getColumnModel().getColumn(2);
        tc.setMaxWidth(275);
        tc.setPreferredWidth(275);
        tc.setMinWidth(275);
        tc.setWidth(275);
        table.getTableHeader().getColumnModel().getColumn(2).setMaxWidth(275);
        table.getTableHeader().getColumnModel().getColumn(2).setMinWidth(275);

        // click the table header and content will automatically sort
        table.setAutoCreateRowSorter(true);

        // table header's content shows at center
        DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) table.getTableHeader().getDefaultRenderer();
        renderer.setHorizontalAlignment(renderer.CENTER);

        // table rows' content shows at center and set the font
        DefaultTableCellRenderer r = new DefaultTableCellRenderer();
        r.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class, r);
        table.setFocusable(false);
        table.setFont(new Font("Times New Roman", Font.PLAIN, 13));
    }

    // this function aims at let user make sure when clicking Logout button
    private void logout(JFrame jf) {
        // jump out the warning window
        // Custom button text
        Object[] options = {"Yes", "No",};
        int choice = JOptionPane.showOptionDialog(jf,
                "Are you sure to log out?",
                "LOGOUT",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE,
                null,
                options,
                options[1]);

        if (choice == 0) {
            // log out
            jf.setVisible(false);
            jfChooseIdentity.setVisible(true);
            jtfUsername.setText("");
            jpfPassword.setText("");
        }
    }
}