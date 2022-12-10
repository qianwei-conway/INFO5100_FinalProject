import com.nancal.tools.MultiComboBox;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

public class GUI {

    private Login login;
    private Register register;
    private Job job;

    private int frameWidth = 600;
    private int frameHeight = 600;

    private JFrame jfChooseIdentity;

    private JFrame jfLogin;
    private JLabel jlUsername;
    private JTextField jtfUsername;
    private JLabel jlPassword;
    private JPasswordField jpfPassword;

    private JPasswordField jpfConfirm;

    private JFrame jfRegister;

    private JFrame jfStudent;
    private JPanel jpAllJobs;
    JTextField jtfFilterCompany;
    JTextField jtfFilterPosition;
    JTextField jtfFilterLocation;
    MultiComboBox comboxFilterInstancy;
    MultiComboBox comboxFilterReturnOffer;
    MultiComboBox comboxFilterRemote;

    private JFrame jfHR;
    private JFrame jfJobInput;
    private String action;
    JTextArea jtaCompany;
    JTextArea jtaPosition;
    JRadioButton radioBtnIns;
    JRadioButton radioBtnInsN;
    ButtonGroup btngrpInstancy;
    JRadioButton radioBtnRet;
    JRadioButton radioBtnRetN;
    ButtonGroup btngrpReturnOffer;
    JTextArea jtaDuty;
    JTextArea jtaRequirement;
    JTextArea jtaBenefit;
    JTextArea jtaInternTime;
    JTextArea jtaLocation;
    JRadioButton radioBtnRem;
    JRadioButton radioBtnRemN;
    ButtonGroup btngrpRemote;
    JTextArea jtaApplyLink;
    JTextArea jtaApplyInstruction;
    UtilDateModel model;
    JDatePickerImpl datePicker;
    JTextArea jtaOtherInfo;
    JTextArea jtaIntro;

    private JFrame jfStudentJobDetail;
    private JFrame jfHRJobDetail;

    private String[] tableHeader = {"id", "Company", "Position"};

    public GUI() {
        // Make the window look and feel better.
        JFrame.setDefaultLookAndFeelDecorated(true);

        chooseIdentityPage();
    }

    public void chooseIdentityPage() {
        // Display the Choose Identity Page
        jfChooseIdentity = new JFrame("Candy Internship");
        jfChooseIdentity.setSize(frameWidth, frameHeight);
        jfChooseIdentity.setLocationRelativeTo(null);
        jfChooseIdentity.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel jpChooseIdentity = new JPanel();
        jpChooseIdentity.setLayout(null);

        JLabel jlChooseIdentityLabel = new JLabel("You are...");

        // create two radio buttons
        JRadioButton radioBtnStudent = new JRadioButton("Student");
        JRadioButton radioBtnHR = new JRadioButton("Hiring Manager");

        // create button group, add two radio buttons into it
        ButtonGroup btngrpIdentity = new ButtonGroup();
        btngrpIdentity.add(radioBtnStudent);
        btngrpIdentity.add(radioBtnHR);

        jpChooseIdentity.add(jlChooseIdentityLabel);
        jpChooseIdentity.add(radioBtnStudent);
        jpChooseIdentity.add(radioBtnHR);

        jlChooseIdentityLabel.setBounds(200, 150, 200, 50);
        radioBtnStudent.setBounds(200, 200, 200, 50);
        radioBtnHR.setBounds(200, 250, 200, 50);

        ActionListener sliceActionListener = actionEvent -> {
            AbstractButton aBtnIdentity = (AbstractButton) actionEvent.getSource();
            login = new Login();
            login.setIdentity(aBtnIdentity.getText());
            loginPage();
        };
        radioBtnStudent.addActionListener(sliceActionListener);
        radioBtnHR.addActionListener(sliceActionListener);

        jfChooseIdentity.setContentPane(jpChooseIdentity);
        jfChooseIdentity.setVisible(true);
    }

    public void loginPage() {
        jfChooseIdentity.setVisible(false);

        // Display the Choose Identity Page
        jfLogin = new JFrame("Candy Internship");
        jfLogin.setSize(frameWidth, frameHeight);
        jfLogin.setLocationRelativeTo(null);
        jfLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel jpLogin = new JPanel();
        jpLogin.setLayout(null);

        JLabel jlLoginLabel = new JLabel("Please log into your account...");
        jlUsername = new JLabel("Username:");
        jlPassword = new JLabel("Password:");
        jtfUsername = new JTextField(10);
        jpfPassword = new JPasswordField(10);
        JButton jbLogin = new JButton("Login");
        JLabel jlGoRegisterLabel = new JLabel("Don't have an account?");
        JButton jbGoRegister = new JButton("Go To Register");

        jpLogin.add(jlLoginLabel);
        jpLogin.add(jlUsername);
        jpLogin.add(jtfUsername);
        jpLogin.add(jlPassword);
        jpLogin.add(jpfPassword);
        jpLogin.add(jbLogin);
        jpLogin.add(jlGoRegisterLabel);
        jpLogin.add(jbGoRegister);

        jlLoginLabel.setBounds(200, 50, 200, 30);
        jlUsername.setBounds(200, 100, 100, 30);
        jtfUsername.setBounds(300, 100, 100, 30);
        jlPassword.setBounds(200, 150, 100, 30);
        jpfPassword.setBounds(300, 150, 100, 30);
        jbLogin.setBounds(260, 200, 80, 30);
        jlGoRegisterLabel.setBounds(230, 470, 160, 30);
        jbGoRegister.setBounds(220, 500, 160, 30);

        jbLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clickButtonLogin();
            }
        });

        jbGoRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerPage();
            }
        });

        jpfPassword.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                if (e.getKeyChar() == KeyEvent.VK_ENTER) {
                    clickButtonLogin();
                }
            }
        });

        jfLogin.setContentPane(jpLogin);
        jfLogin.setVisible(true);
    }

    private void clickButtonLogin() {
        System.out.println("You clicked the login button.");

        login.setUsername(jtfUsername.getText());
        login.setPassword(String.valueOf(jpfPassword.getPassword()));
        String status = login.login();

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
        jfLogin.setVisible(false);

        // Display the Register Page
        jfRegister = new JFrame("Candy Internship");
        jfRegister.setSize(frameWidth, frameHeight);
        jfRegister.setLocationRelativeTo(null);
        jfRegister.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel jpRegister = new JPanel();
        jpRegister.setLayout(null);

        JLabel jlRegisterLabel = new JLabel("Please register your new account...");
        jlUsername = new JLabel("Username:");
        jlPassword = new JLabel("Password:");
        JLabel jlConfirm = new JLabel("Confirm:");
        jtfUsername = new JTextField(10);
        jpfPassword = new JPasswordField(10);
        jpfConfirm = new JPasswordField();
        JButton jbRegister = new JButton("Register");

        jpRegister.add(jlRegisterLabel);
        jpRegister.add(jlUsername);
        jpRegister.add(jtfUsername);
        jpRegister.add(jlPassword);
        jpRegister.add(jpfPassword);
        jpRegister.add(jlConfirm);
        jpRegister.add(jpfConfirm);
        jpRegister.add(jbRegister);

        jlRegisterLabel.setBounds(200, 50, 200, 30);
        jlUsername.setBounds(200, 100, 100, 30);
        jtfUsername.setBounds(300, 100, 100, 30);
        jlPassword.setBounds(200, 150, 100, 30);
        jpfPassword.setBounds(300, 150, 100, 30);
        jlConfirm.setBounds(200, 200, 100, 30);
        jpfConfirm.setBounds(300, 200, 100, 30);
        jbRegister.setBounds(260, 250, 80, 30);

        jbRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clickButtonRegister();
            }
        });

        jfRegister.setContentPane(jpRegister);
        jfRegister.setVisible(true);
    }

    public void clickButtonRegister() {
        register = new Register();

        register.setIdentity(login.getIdentity());
        register.setUsername(jtfUsername.getText());
        register.setPassword(String.valueOf(jpfPassword.getPassword()));
        register.setReenterPassword(String.valueOf(jpfConfirm.getPassword()));

        String status = register.register();

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
        // Display the Student Page
        jfStudent = new JFrame("Hi, " + login.getUsername());
        jfStudent.setSize(frameWidth, frameHeight);
        jfStudent.setLocationRelativeTo(null);
        jfStudent.setLayout(new GridLayout());
        jfStudent.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JSplitPane jspStudent = new JSplitPane();
        JPanel jpFilter = new JPanel();
        jpAllJobs = new JPanel();
        jpFilter.setLayout(null);
        jpAllJobs.setLayout(null);

        // create filter panel
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

        // create jobs panel
        JScrollPane pane = createJobTable(job.showAllJobs());
        pane.setBounds(20, 20, 560, 320);
        jpAllJobs.add(pane);

        jfStudent.setContentPane(jspStudent);
        jspStudent.setOrientation(JSplitPane.VERTICAL_SPLIT);
        jspStudent.setDividerLocation(220);
        jspStudent.setTopComponent(jpFilter);
        jspStudent.setBottomComponent(jpAllJobs);

        jfStudent.setVisible(true);

        jbFilter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filterJobs();
            }
        });
    }

    public void filterJobs() {
        // empty panel
        jpAllJobs.removeAll();

        // get filter params
        job.filterCompany = jtfFilterCompany.getText();
        job.filterPosition = jtfFilterPosition.getText();
        job.filterLocation = jtfFilterLocation.getText();
        job.filterInstancy = comboxFilterInstancy.getSelectedValues();
        job.filterReturnOffer = comboxFilterReturnOffer.getSelectedValues();
        job.filterRemote = comboxFilterRemote.getSelectedValues();

        // refresh table
        JScrollPane pane = createJobTable(job.filter());
        pane.setBounds(20, 20, 560, 320);
        jpAllJobs.add(pane);
    }

    public void hrPage() {
        // Display the Student Page
        jfHR = new JFrame("Hi, " + login.getUsername());
        jfHR.setSize(frameWidth, frameHeight);
        jfHR.setLocationRelativeTo(null);
        jfHR.setLayout(new GridLayout());
        jfHR.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JSplitPane jspHR = new JSplitPane();
        JPanel jpAdd = new JPanel();
        jpAllJobs = new JPanel();
        jpAdd.setLayout(null);
        jpAllJobs.setLayout(null);

        // create add new job panel
        JButton jbAddNewJob = new JButton("Add A New Job");
        jpAdd.add(jbAddNewJob);
        jbAddNewJob.setBounds(200, 70, 200, 30);

        // create jobs panel
        JLabel jlAllYourJobs = new JLabel("Below are all your uploaded jobs...");
        jpAllJobs.add(jlAllYourJobs);
        jlAllYourJobs.setBounds(190, 10, 220, 30);

        job.setUsername(login.getUsername());
        JScrollPane pane = createJobTable(job.showJobsByUser());
        pane.setBounds(20, 50, 560, 390);
        jpAllJobs.add(pane);

        jfHR.setContentPane(jspHR);
        jspHR.setOrientation(JSplitPane.VERTICAL_SPLIT);
        jspHR.setDividerLocation(120);
        jspHR.setTopComponent(jpAdd);
        jspHR.setBottomComponent(jpAllJobs);

        jfHR.setVisible(true);

        jbAddNewJob.addActionListener(e -> {
            action = "add";
            addAJob();
        });
    }

    public void stuJobDetailPage() {
        jfStudent.setVisible(false);

        jfStudentJobDetail = new JFrame("Hi, " + login.getUsername());
        jfStudentJobDetail.setSize(frameWidth, frameHeight);
        jfStudentJobDetail.setLocationRelativeTo(null);
        jfStudentJobDetail.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel jpStudentJobDetail = new JPanel();
        jpStudentJobDetail.setLayout(null);

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
        JTextArea jtaJobDetail = new JTextArea(detail);

        jtaJobDetail.setBackground(null);

        jpStudentJobDetail.add(jtaJobDetail);

        jtaJobDetail.setBounds(50, 70, 500, 500);

        jfStudentJobDetail.setContentPane(jpStudentJobDetail);
        jfStudentJobDetail.setVisible(true);
    }

    public void hrJobDetailPage() {
        jfHR.setVisible(false);

        jfHRJobDetail = new JFrame("Hi, " + login.getUsername());
        jfHRJobDetail.setSize(frameWidth, frameHeight);
        jfHRJobDetail.setLocationRelativeTo(null);
        jfHRJobDetail.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel jpHRJobDetail = new JPanel();
        jpHRJobDetail.setLayout(null);

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
        JTextArea jtaJobDetail = new JTextArea(detail);
        jtaJobDetail.setBackground(null);

        jpHRJobDetail.add(jtaJobDetail);
        jtaJobDetail.setBounds(50, 70, 500, 430);

        JButton jbEdit = new JButton("Edit");
        JButton jbDelete = new JButton("Delete");
        jpHRJobDetail.add(jbEdit);
        jpHRJobDetail.add(jbDelete);
        jbEdit.setBounds(150, 530, 100, 30);
        jbDelete.setBounds(350, 530, 100, 30);

        jbEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                action = "edit";
                editAJob();
            }
        });

        jbDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Custom button text
                Object[] options = {"Yes", "No",};
                int choice = JOptionPane.showOptionDialog(jfHRJobDetail,
                        "Are you sure to DELETE this job?",
                        "DELETE",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE,
                        null,
                        options,
                        options[1]);

                if (choice == 0) {
                    // delete
                    deleteAJob();
                }
            }
        });

        jfHRJobDetail.setContentPane(jpHRJobDetail);
        jfHRJobDetail.setVisible(true);
    }

    public void editAJob() {
        ArrayList<String> detail = job.edit().get(0);

        jobInputPage();

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
        model.setYear(Integer.parseInt(detail.get(14).substring(0, 4)));
        model.setMonth(Integer.parseInt(detail.get(14).substring(5, 7)));
        model.setDay(Integer.parseInt(detail.get(14).substring(8)));
        model.setSelected(true);
        jtaOtherInfo.setText(detail.get(15));
        jtaIntro.setText(detail.get(16));
    }

    public void addAJob() {
        jobInputPage();
    }

    public void jobInputPage() {
        jfHRJobDetail.setVisible(false);

        jfJobInput = new JFrame("Hi, " + login.getUsername());
        jfJobInput.setSize(frameWidth, frameHeight);
        jfJobInput.setLocationRelativeTo(null);
        jfJobInput.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JSplitPane jsplitpJobInput = new JSplitPane();

        JPanel jpBack = new JPanel();
        jpBack.setLayout(null);

        JPanel jpJobInput = new JPanel();
        JScrollPane jspJobInput = new JScrollPane(jpJobInput, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        jpJobInput.setPreferredSize(new Dimension(frameWidth - 20, 2000));
        jspJobInput.setPreferredSize(new Dimension(frameWidth, frameHeight));

        // create widgets
        JLabel jlCompany = new JLabel("Company:");
        jtaCompany = new JTextArea();
        jtaCompany.setEditable(true);
        jtaCompany.setColumns(20);
        jtaCompany.setRows(2);
        jtaCompany.setLineWrap(false);
        JScrollPane jspCompany = new JScrollPane(jtaCompany);

        JLabel jlPosition = new JLabel("Position:");
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
//        ActionListener sliceActionListenerInstancy = actionEvent -> {
//            aBtnInstancy = (AbstractButton) actionEvent.getSource();
//        };
//        radioBtnIns.addActionListener(sliceActionListenerInstancy);
//        radioBtnInsN.addActionListener(sliceActionListenerInstancy);

        radioBtnRet = new JRadioButton("Has Return Offer");
        radioBtnRetN = new JRadioButton("No Return Offer");
        btngrpReturnOffer = new ButtonGroup();
        btngrpReturnOffer.add(radioBtnRet);
        btngrpReturnOffer.add(radioBtnRetN);
//        ActionListener sliceActionListenerReturnOffer = actionEvent -> {
//            aBtnReturnOffer = (AbstractButton) actionEvent.getSource();
//        };
//        radioBtnRet.addActionListener(sliceActionListenerReturnOffer);
//        radioBtnRetN.addActionListener(sliceActionListenerReturnOffer);

        JLabel jlDuty = new JLabel("Duty:");
        jtaDuty = new JTextArea();
        jtaDuty.setEditable(true); // 设置输入框允许编辑
        jtaDuty.setColumns(20); // 设置输入框的长度为14个字符
        jtaDuty.setRows(8); // 设置输入框的高度为3行字符
        jtaDuty.setLineWrap(false); // 设置每行是否折叠。为true的话，输入字符超过每行宽度就会自动换行
        JScrollPane jspDuty = new JScrollPane(jtaDuty); // 创建一个滚动条

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

        JLabel jlLocation = new JLabel("Location:");
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
//        ActionListener sliceActionListenerRemote = actionEvent -> {
//            aBtnRemote = (AbstractButton) actionEvent.getSource();
//        };
//        radioBtnRem.addActionListener(sliceActionListenerRemote);
//        radioBtnRemN.addActionListener(sliceActionListenerRemote);

        JLabel jlApplyLink = new JLabel("Apply Link:");
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

        JLabel jlDeadline = new JLabel("Deadline:");
        model = new UtilDateModel();
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

        BoxLayout boxlayout = new BoxLayout(jpJobInput, BoxLayout.Y_AXIS);
        jpJobInput.setLayout(boxlayout);
        jpJobInput.setBorder(new EmptyBorder(new Insets(30, 30, 200, 30)));

        // add widgets to panel
        jpJobInput.add(jlCompany);
        jpJobInput.add(jspCompany);
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

        jfJobInput.setContentPane(jsplitpJobInput);
        jsplitpJobInput.setOrientation(JSplitPane.VERTICAL_SPLIT);
        jsplitpJobInput.setDividerLocation(70);
        jsplitpJobInput.setTopComponent(jpBack);
        jsplitpJobInput.setBottomComponent(jspJobInput);

        jfJobInput.setVisible(true);

        jbSubmit.addActionListener(e -> submitAJob());
    }

    private void submitAJob() {
        job.company = jtaCompany.getText();
        job.position = jtaPosition.getText();
        if (radioBtnIns.isSelected() || radioBtnInsN.isSelected()) {
            job.instancy = (radioBtnIns.isSelected()) ? true: false;
        }
        if (radioBtnRet.isSelected() || radioBtnRetN.isSelected()) {
            job.returnOffer = (radioBtnRet.isSelected()) ? true: false;
        }
        job.duty = jtaDuty.getText();
        job.requirement = jtaRequirement.getText();
        job.benefit = jtaBenefit.getText();
        job.internTime = jtaInternTime.getText();
        job.location = jtaLocation.getText();
        if (radioBtnRem.isSelected() || radioBtnRemN.isSelected()) {
            job.remote = (radioBtnRem.isSelected()) ? true: false;
        }
        job.applyLink = jtaApplyLink.getText();
        job.applyInstruction = jtaApplyInstruction.getText();
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
        job.deadline = ft.format(datePicker.getModel().getValue());
        job.otherInfo = jtaOtherInfo.getText();
        job.intro = jtaIntro.getText();

        boolean status = job.submit(action);

        if (status == false) {
            // warning of required text areas not complete jumps out
            JOptionPane.showMessageDialog(jfJobInput, "Some required areas incomplete!", "INCOMPLETE", JOptionPane.WARNING_MESSAGE);
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

    public void deleteAJob() {
        jfHRJobDetail.setVisible(false);

        job.deleteAJob();
        // refresh the table
        jpAllJobs.remove(1);
        JScrollPane pane = createJobTable(job.showJobsByUser());
        pane.setBounds(20, 50, 560, 390);
        jpAllJobs.add(pane);


        jfHR.setVisible(true);
    }

    public JScrollPane createJobTable(ArrayList<ArrayList<String>> data) {
        String[][] tableData = job.aList2Arr(data);

        JTable table = new JTable(tableData, tableHeader);
        JScrollPane pane = new JScrollPane(table);
        renderTable(table);

        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(MouseEvent e) {//仅当鼠标单击时响应
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

        // 设置点击表头自动实现排序
        table.setAutoCreateRowSorter(false);

        // 设置表头文字居中显示
        DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) table.getTableHeader().getDefaultRenderer();
        renderer.setHorizontalAlignment(renderer.CENTER);

        // 设置表格中的数据居中显示
        DefaultTableCellRenderer r = new DefaultTableCellRenderer();
        r.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class, r);
        table.setFocusable(false);
        table.setFont(new Font("Times New Roman", Font.PLAIN, 13));
    }
}

class DateLabelFormatter extends JFormattedTextField.AbstractFormatter {

    private String datePattern = "yyyy-MM-dd";
    private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);

    @Override
    public Object stringToValue(String text) throws ParseException {
        return dateFormatter.parseObject(text);
    }

    @Override
    public String valueToString(Object value) throws ParseException {
        if (value != null) {
            Calendar cal = (Calendar) value;
            return dateFormatter.format(cal.getTime());
        }

        return "";
    }

}