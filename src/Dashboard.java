import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.jfree.chart.plot.PiePlot;

public class Dashboard extends JFrame{
    public static String adminProfilePicturePath;
    public static double adminAccountBalance = 0;
    public static double adminAddedBalance=0;
    public static double adminWithdrawnBalance=0;

    Dashboard(){

        setTitle("Finanza -The ledgar master");
        setSize(800,600);
        setLocation(270,60);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JPanel leftSection = new JPanel();
        leftSection.setBounds(0, 0, 200, 600);
        leftSection.setBackground(new Color(15, 171, 62));
        leftSection.setLayout(null);
        add(leftSection);

        JPanel imagePanel = new JPanel();
        imagePanel.setBounds(25, 10, 150, 100);
        imagePanel.setLayout(new BorderLayout());
        ImageIcon Finanza = new ImageIcon(getClass().getResource("logo.png"));
        Image scaledImage = Finanza.getImage().getScaledInstance(150, 100, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        JLabel imageLabel = new JLabel(scaledIcon);
        imagePanel.add(imageLabel, BorderLayout.CENTER);
        leftSection.add(imagePanel);

        JButton dashboardButton = new JButton();
        dashboardButton.setText("Dashboard");
        dashboardButton.setBackground(Color.LIGHT_GRAY);
        dashboardButton.setForeground(new Color(11, 181, 31));
        dashboardButton.setFont(new Font("Roman",Font.BOLD,14));
        dashboardButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        dashboardButton.setBounds(25,130,150,70);
        leftSection.add(dashboardButton);


        JButton transactionButton = new JButton();
        transactionButton.setText("Transaction");
        transactionButton.setBackground(Color.WHITE);
        transactionButton.setForeground(new Color(11, 181, 31));
        transactionButton.setFont(new Font("Roman",Font.BOLD,14));
        transactionButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        transactionButton.setBounds(25,220,150,70);
        transactionButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Transactions();
                dispose();
            }
        });
        leftSection.add(transactionButton);

        JButton accountButton = new JButton();
        accountButton.setText("Accounts");
        accountButton.setBackground(Color.WHITE);
        accountButton.setForeground(new Color(11, 181, 31));
        accountButton.setFont(new Font("Roman",Font.BOLD,14));
        accountButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        accountButton.setBounds(25,310,150,70);
        accountButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Accounts();
                dispose();
            }
        });
        leftSection.add(accountButton);

        JLabel plusSignAccountButton = new JLabel();
        plusSignAccountButton.setText("+");
        plusSignAccountButton.setFont(new Font("Roman",Font.BOLD,17));
        plusSignAccountButton.setForeground(Color.BLACK);
        plusSignAccountButton.setBounds(35,440,40,70);
        leftSection.add(plusSignAccountButton);

        JButton addAccountButton = new JButton();
        addAccountButton.setText("Add Account");
        addAccountButton.setContentAreaFilled(false);
        addAccountButton.setOpaque(false);
        addAccountButton.setBorderPainted(false);
        addAccountButton.setForeground(Color.WHITE);
        addAccountButton.setFont(new Font("Roman",Font.BOLD,17));
        addAccountButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        addAccountButton.setBounds(35,440,150,70);
        addAccountButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               new CreateAccount();
               dispose();
               JOptionPane.showMessageDialog(
                       null,
                       "Create new Account to add it",
                       "Creating new Account",
                       JOptionPane.INFORMATION_MESSAGE
               );
            }
        });
        leftSection.add(addAccountButton);

        JButton logoutButton = new JButton();
        logoutButton.setText("Logout");
        logoutButton.setBackground(Color.white);
        logoutButton.setForeground(Color.BLACK);
        logoutButton.setFont(new Font("Roman",Font.BOLD,17));
        logoutButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        logoutButton.setBounds(35,510,100,30);
        logoutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int confirmLogoutDialoge = JOptionPane.showConfirmDialog(
                        null,
                        "Do you really want to logout?",
                        "Confirmation",
                        JOptionPane.YES_NO_OPTION
                );
                if(confirmLogoutDialoge==0){
                    JOptionPane.showMessageDialog(
                            null,
                            "Good bye :)",
                            "Logging out",
                            JOptionPane.INFORMATION_MESSAGE
                    );
                    new Login();
                    dispose();
                }
            }
        });
        leftSection.add(logoutButton);

        JPanel upperHeading = new JPanel();
        upperHeading.setBackground(new Color(113, 250, 57));
        upperHeading.setBounds(200,0,600,30);
        upperHeading.setLayout(null);
        add(upperHeading);

        JLabel upperHeadingText = new JLabel();
        upperHeadingText.setText("Finanza -The ledgar master");
        upperHeadingText.setForeground(Color.WHITE);
        upperHeading.setFont(new Font("Roman",Font.BOLD,25));
        upperHeadingText.setBounds(15,5,200,20);
        upperHeading.add(upperHeadingText);

        JPanel bodyPanel = new JPanel();
        bodyPanel.setLayout(null);
        bodyPanel.setBounds(200,30,600,570);
        add(bodyPanel);

        JLabel dashboardHeadingText = new JLabel();
        dashboardHeadingText.setText("Dashboard");
        dashboardHeadingText.setForeground(new Color(11, 181, 31));
        dashboardHeadingText.setFont(new Font("Roman",Font.BOLD,21));
        dashboardHeadingText.setBounds(30,30,150,40);
        bodyPanel.add(dashboardHeadingText);

        ImageIcon adminProfilePicture;
        adminProfilePicture = new ImageIcon(adminProfilePicturePath);
        Image adminProfilePictureScaled = adminProfilePicture.getImage().getScaledInstance(100,100, Image.SCALE_SMOOTH);
        ImageIcon adminProfilePictureScaledIcon = new ImageIcon(adminProfilePictureScaled);
        JLabel adminProfilePictureLabel = new JLabel(adminProfilePictureScaledIcon);
        adminProfilePictureLabel.setBounds(480,20,80,80);
        adminProfilePictureLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK,1));
        bodyPanel.add(adminProfilePictureLabel, BorderLayout.CENTER);

        JLabel welcomeText = new JLabel();
        welcomeText.setText("WELCOME Back");
        welcomeText.setForeground(new Color(11, 181, 31));
        welcomeText.setFont(new Font("Roman",Font.BOLD,17));
        welcomeText.setBounds(320,20,150,30);
        bodyPanel.add(welcomeText);

        JLabel adminName = new JLabel();
        adminName.setText(Login.username);
        adminName.setForeground(Color.BLACK);
        adminName.setFont(new Font("Roman",Font.BOLD,17));
        adminName.setBounds(400,50,80,20);
        bodyPanel.add(adminName);

        JPanel accountBalancePanel = new JPanel();
        accountBalancePanel.setBackground(new Color(113, 250, 57));
        accountBalancePanel.setLayout(null);
        accountBalancePanel.setBounds(30,90,180,130);
        bodyPanel.add(accountBalancePanel);

        JLabel accountBalanceHeading = new JLabel();
        accountBalanceHeading.setText("Account Balance");
        accountBalanceHeading.setForeground(Color.WHITE);
        accountBalanceHeading.setFont(new Font("Roman",Font.BOLD,19));
        accountBalanceHeading.setBounds(10,5,170,20);
        accountBalancePanel.add(accountBalanceHeading);

        JLabel accountBalance = new JLabel();
        accountBalance.setText("$"+String.valueOf(adminAccountBalance));
        accountBalance.setFont(new Font("Roman",Font.BOLD,30));
        accountBalance.setForeground(Color.WHITE);
        accountBalance.setBounds(35 ,45,250,40);
        accountBalancePanel.add(accountBalance);

        JPanel addOrWithdrawMoneyPanel = new JPanel();
        addOrWithdrawMoneyPanel.setLayout(null);
        addOrWithdrawMoneyPanel.setBounds(300,90,150,150);
        bodyPanel.add(addOrWithdrawMoneyPanel);

        JButton addMoneyButton = new JButton();
        addMoneyButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        addMoneyButton.setBackground(new Color(15, 171, 62));
        addMoneyButton.setText("+ Add Money");
        addMoneyButton.setFont(new Font("Roman",Font.BOLD,17));
        addMoneyButton.setBounds(10,10,140,50);
        addMoneyButton.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
        addMoneyButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String  addedAmount = JOptionPane.showInputDialog(
                        null,
                        "how much money you want to add? ",
                        "Add money",
                        JOptionPane.PLAIN_MESSAGE
                );
                if(Double.parseDouble(addedAmount)>50000){
                    JOptionPane.showMessageDialog(
                            null,
                            "Sorry you can enter maximum amount of 50000$",
                            "Error",
                            JOptionPane.INFORMATION_MESSAGE
                    );
                }
                else {
                    boolean done = Main.addBalanceOfUser(Double.parseDouble(addedAmount));
                    boolean saved = Main.addMoneyTransaction(Double.parseDouble(addedAmount));
                    if (done && saved) {

                        new Dashboard();
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(
                                null,
                                "Invalid Amount Request",
                                "Invalid ",
                                JOptionPane.INFORMATION_MESSAGE
                        );
                    }
                }
            }
        });
        addOrWithdrawMoneyPanel.add(addMoneyButton);

        JButton withdrawMoneyButton = new JButton();
        withdrawMoneyButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        withdrawMoneyButton.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
        withdrawMoneyButton.setText("- Withdraw $$");
        withdrawMoneyButton.setBackground(Color.red);
        withdrawMoneyButton.setFont(new Font("Roman",Font.BOLD,16));
        withdrawMoneyButton.setBounds(10,70,140,50);
        withdrawMoneyButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String withdrawAmount = JOptionPane.showInputDialog(
                        null,
                        "How much amount you want to withdraw?",
                        "Withdraw cash",
                        JOptionPane.PLAIN_MESSAGE
                );
                if(Integer.parseInt(withdrawAmount) <= adminAccountBalance && Integer.parseInt(withdrawAmount) > 0) {
                    JPasswordField twoStepVerificationPasswordFIeld = new JPasswordField();
                    twoStepVerificationPasswordFIeld.setEchoChar('*');
                    int twoStepVerification =JOptionPane.showConfirmDialog(
                            null,
                        twoStepVerificationPasswordFIeld,
                        "2 Step Verification",
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.PLAIN_MESSAGE
                    );
                    String getPasswordFromField=new String(twoStepVerificationPasswordFIeld.getPassword());
                    if(getPasswordFromField.equals(Login.password)){
                        Main.cutBalanceOfUser(Double.parseDouble(withdrawAmount));
                        Main.withdrawMoneyTransaction(Double.parseDouble(withdrawAmount));
                        JOptionPane.showMessageDialog(
                                null,
                                "Withdraw Successfull",
                                "Transaction Proceed",
                                JOptionPane.INFORMATION_MESSAGE
                        );

                        new Dashboard();
                        dispose();
                    }
                    else{
                        JOptionPane.showMessageDialog(
                                null,
                                "You Entered Wrong Password",
                                "Wrong Password",
                                JOptionPane.INFORMATION_MESSAGE
                        );
                    }
                }
                else{
                    JOptionPane.showMessageDialog(
                            null,
                            "Insufficient Balance",
                            "Invalid ",
                            JOptionPane.INFORMATION_MESSAGE
                    );
                }
            }
        });
        addOrWithdrawMoneyPanel.add(withdrawMoneyButton);

        // setting pie chart dataset
        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("CASH IN", adminAddedBalance);
        dataset.setValue("CASH OUT", adminWithdrawnBalance);

        // setting the panel frame
        JFreeChart cashFlowChart = ChartFactory.createPieChart(
                "Cash Flow",
                dataset,
                true,
                true,
                false
        );
        cashFlowChart.setBackgroundPaint(new Color(0,0,0,0));

        //Changing the colors of pie chart
        PiePlot piechartPlot = (PiePlot) cashFlowChart.getPlot();
        piechartPlot.setSectionPaint("CASH IN", new Color(15, 171, 62));
        piechartPlot.setSectionPaint("CASH OUT",Color.RED);
        piechartPlot.setBackgroundPaint(new Color(0,0,0,0));
        piechartPlot.setOutlineVisible(false);


        // displaying the pie chart
        ChartPanel cashFlowPieChartPanel= new ChartPanel(cashFlowChart, false);
        cashFlowPieChartPanel.setBackground(new Color(0,0,0,0));
        cashFlowPieChartPanel.setOpaque(false);
        cashFlowPieChartPanel.setBounds(130,240,300,280);
        bodyPanel.add(cashFlowPieChartPanel);


        setVisible(true);
    }

}