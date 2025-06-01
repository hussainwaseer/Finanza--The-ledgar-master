import javax.swing.*;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import com.toedter.calendar.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Vector;

public class Transactions extends JFrame{

    Transactions(){
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
        dashboardButton.setBackground(Color.WHITE);
        dashboardButton.setForeground(new Color(11, 181, 31));
        dashboardButton.setFont(new Font("Roman",Font.BOLD,14));
        dashboardButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        dashboardButton.setBounds(25,130,150,70);
        dashboardButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Dashboard();
                dispose();
            }
        });
        leftSection.add(dashboardButton);


        JButton transactionButton = new JButton();
        transactionButton.setText("Transaction");
        transactionButton.setBackground(Color.LIGHT_GRAY);
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

        JLabel accountHeadingText = new JLabel();
        accountHeadingText.setText("Transactions");
        accountHeadingText.setForeground(new Color(11, 181, 31));
        accountHeadingText.setFont(new Font("Roman",Font.BOLD,21));
        accountHeadingText.setBounds(30,15,250,40);
        bodyPanel.add(accountHeadingText);

        JPanel fieldsForInputTransactionsPanel = new JPanel();
        fieldsForInputTransactionsPanel.setLayout(null);
        fieldsForInputTransactionsPanel.setBounds(10,60,550,130);
        bodyPanel.add(fieldsForInputTransactionsPanel);

        JLabel amountTextLabel = new JLabel();
        amountTextLabel.setText("Amount: ");
        amountTextLabel.setFont(new Font("Roman",Font.BOLD,18));
        amountTextLabel.setForeground(Color.BLACK);
        amountTextLabel.setBounds(30,0,90,30);
        fieldsForInputTransactionsPanel.add(amountTextLabel);

        DecimalFormat decimalAmountFormatterField = new DecimalFormat("#0.0");
        NumberFormatter amountFormatter = new NumberFormatter(decimalAmountFormatterField);
        amountFormatter.setAllowsInvalid(false);
        amountFormatter.setMaximum(10000000.00);
        amountFormatter.setMinimum(0.00);

        JFormattedTextField amountField = new JFormattedTextField(amountFormatter);
        amountField.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        amountField.setOpaque(false);
        amountField.setFont(new Font("Roman",Font.BOLD,13));
        amountField.setBackground(new Color(0, 0, 0, 0));
        amountField.setBounds(120, 5, 120, 20);
        fieldsForInputTransactionsPanel.add(amountField);

        JLabel typeFieldLabel = new JLabel();
        typeFieldLabel.setText("TYPE: ");
        typeFieldLabel.setForeground(Color.BLACK);
        typeFieldLabel.setFont(new Font("Roman",Font.BOLD,18));
        typeFieldLabel.setBounds(310,5,90,20);
        fieldsForInputTransactionsPanel.add(typeFieldLabel);

        String [] optionsForTypeField = {" ","INCOME","EXPENSE"};
        JComboBox<String> dropdownForType = new JComboBox<>(optionsForTypeField);
        dropdownForType.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
        dropdownForType.setBackground(new Color(0,0,0,0));
        dropdownForType.setFont(new Font("Roman",Font.PLAIN,15));
        dropdownForType.setBounds(380,5,120,20);
        fieldsForInputTransactionsPanel.add(dropdownForType);

        JLabel categoriesFieldLabel = new JLabel();
        categoriesFieldLabel.setText("CATEGORY: ");
        categoriesFieldLabel.setFont(new Font("Roman",Font.BOLD,16));
        categoriesFieldLabel.setForeground(Color.BLACK);
        categoriesFieldLabel.setBounds(20,45,120,20);
        fieldsForInputTransactionsPanel.add(categoriesFieldLabel);

        String [] incomeCategories = {" ","Salary", "Freelance", "Investment", "Business", "Other"};
        String [] expenseCategories = {" ","Rent", "Groceries", "Utilities", "Dining", "Healthcare", "Entertainment", "Other"};

        String [] emptyInCategory= {" "};

        JComboBox<String> dropboxForCategories = new JComboBox<>(emptyInCategory);
        dropboxForCategories.setBorder(BorderFactory.createLineBorder(Color.black,2));
        dropboxForCategories.setBackground(new Color(0,0,0,0));
        dropboxForCategories.setFont(new Font("Roman",Font.PLAIN,15));
        dropboxForCategories.setBounds(120,47,120,20);
        fieldsForInputTransactionsPanel.add(dropboxForCategories);
        dropdownForType.addActionListener(e -> {
            String getSelectedItem = (String) dropdownForType.getSelectedItem();
            if("INCOME".equals(getSelectedItem)){
                dropboxForCategories.setModel(new DefaultComboBoxModel<>(incomeCategories));
            }
            else if("EXPENSE".equals(getSelectedItem)){
                dropboxForCategories.setModel(new DefaultComboBoxModel<>(expenseCategories));
            }
            else{
                dropboxForCategories.setModel(new DefaultComboBoxModel<>(emptyInCategory));
            }
        });

        JLabel dateLabel = new JLabel();
        dateLabel.setText("DATE: ");
        dateLabel.setFont(new Font("Roman",Font.BOLD,18));
        dateLabel.setForeground(Color.BLACK);
        dateLabel.setBounds(310,45,120,20);
        fieldsForInputTransactionsPanel.add(dateLabel);

        JDateChooser dateChooser = new JDateChooser();
        dateChooser.setBounds(380,45,120,20);
        fieldsForInputTransactionsPanel.add(dateChooser);

        JButton submitButton = new JButton();
        submitButton.setText("ADD TRANSACTION");
        submitButton.setFont(new Font("Roman",Font.BOLD,13));
        submitButton.setBackground(new Color(113, 250, 57));
        submitButton.setForeground(Color.WHITE);
        submitButton.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
        submitButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        submitButton.setBounds(120,90,140,30);
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Object valueOfAmount = amountField.getValue();
                if (valueOfAmount == null || ((Number) valueOfAmount).doubleValue() == 0) {
                    JOptionPane.showMessageDialog(
                            null,
                            "Amount is empty or zero",
                            "Invalid Amount",
                            JOptionPane.INFORMATION_MESSAGE
                    );
                }
                else{
                    if(dropdownForType.getSelectedItem().equals(" ")){
                        JOptionPane.showMessageDialog(
                                null,
                                "Please select a valid type",
                                "Invalid Type",
                                JOptionPane.INFORMATION_MESSAGE
                        );
                    }
                    else{
                        if(dropboxForCategories.getSelectedItem().equals(" ")){
                            JOptionPane.showMessageDialog(
                                    null,
                                    "Please select a valid Category",
                                    "Invalid Category",
                                    JOptionPane.INFORMATION_MESSAGE
                            );
                        }
                        else{
                            java.util.Date getDate = dateChooser.getDate();

                            if(dateChooser.getDate()==null){
                                JOptionPane.showMessageDialog(
                                        null,
                                        "Data can't be null, Select a date",
                                        "Invalid Date",
                                        JOptionPane.INFORMATION_MESSAGE
                                );
                            }
                            else{
                                JPasswordField twoStepVerification = new JPasswordField();
                                twoStepVerification.setEchoChar('*');
                                int takeInputForTwoStepVerification = JOptionPane.showConfirmDialog(
                                        null,
                                        twoStepVerification,
                                        "Enter password to confirm",
                                        JOptionPane.OK_CANCEL_OPTION,
                                        JOptionPane.PLAIN_MESSAGE
                                );
                                String filteredtwoStepVerification = new String(twoStepVerification.getPassword());
                                if(filteredtwoStepVerification.equals(Login.password)){
                                    String sendDate = " ";
                                    SimpleDateFormat sdf = new SimpleDateFormat();
                                      sendDate=sdf.format(getDate);
                                    if(dropboxForCategories.equals("EXPENSE") && ((Double)valueOfAmount).doubleValue() > Dashboard.adminAccountBalance ){
                                      JOptionPane.showMessageDialog(
                                                null,
                                             "Accout balance is insufficient for this transaction",
                                             "Insufficient Balance",
                                             JOptionPane.INFORMATION_MESSAGE
                                        );
                                 }
                                 else {
                                        double sendAmount = (double) amountField.getValue();
                                     String sendType = (String) dropdownForType.getSelectedItem();
                                     String sendCategory = (String) dropboxForCategories.getSelectedItem();
                                    if (dropdownForType.getSelectedItem().equals("INCOME")) {
                                        boolean done = Main.transactionOfIncome(sendAmount, sendType, sendCategory, sendDate);
                                        if (done) {
                                            JOptionPane.showMessageDialog(
                                                    null,
                                                    "Transaction Successfull thanks for using us :>",
                                                    "Successfull",
                                                    JOptionPane.INFORMATION_MESSAGE
                                            );
                                            new Transactions();
                                            dispose();

                                        } else {
                                            JOptionPane.showMessageDialog(
                                                    null,
                                                    "Transaction unsucessfull due to some error try again",
                                                    "Failed",
                                                    JOptionPane.INFORMATION_MESSAGE
                                            );
                                        }
                                    } else if (dropdownForType.getSelectedItem().equals("EXPENSE")) {
                                        boolean done = Main.transactionOfExpense(sendAmount, sendType, sendCategory, sendDate);
                                        if (done) {
                                            JOptionPane.showMessageDialog(
                                                    null,
                                                    "Transaction sucessfull, thanks for using us:>",
                                                    "Transaction succesfull",
                                                    JOptionPane.INFORMATION_MESSAGE
                                            );
                                            new Transactions();
                                            dispose();
                                        } else {
                                            JOptionPane.showMessageDialog(
                                                    null,
                                                    "Transaction unsucessfull due to some error try again",
                                                    "Failed",
                                                    JOptionPane.INFORMATION_MESSAGE
                                            );
                                        }
                                    }
                                 }
                                }
                                else{
                                    JOptionPane.showMessageDialog(
                                            null,
                                            "Password Wrong, kindly check your password",
                                            "Wrong Password",
                                            JOptionPane.INFORMATION_MESSAGE
                                    );
                                }
                            }
                        }
                    }
                }
            }
        });
        fieldsForInputTransactionsPanel.add(submitButton);

        JButton deleteTransactionButton = new JButton();
        deleteTransactionButton.setText("Delete Transaction");
        deleteTransactionButton.setBackground(Color.red);
        deleteTransactionButton.setForeground(Color.WHITE);
        deleteTransactionButton.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
        deleteTransactionButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        deleteTransactionButton.setFont(new Font("Roman",Font.BOLD,13));
        deleteTransactionButton.setBounds(300,90,140,30);
        deleteTransactionButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String idOfTransaction = JOptionPane.showInputDialog(
                        null,
                        "Enter the ID of transaction which you want to delete",
                        "Delete Transaction",
                        JOptionPane.INFORMATION_MESSAGE
                );
                if(idOfTransaction!=null){
                    JPasswordField twoStepVerification = new JPasswordField();
                    twoStepVerification.setEchoChar('*');
                    int takeInputForTwoStepVerification = JOptionPane.showConfirmDialog(
                            null,
                            twoStepVerification,
                            "Enter password to confirm",
                            JOptionPane.OK_CANCEL_OPTION,
                            JOptionPane.PLAIN_MESSAGE
                    );
                    String filteredTwoStepVerification = new String(twoStepVerification.getPassword());
                    if(filteredTwoStepVerification.equals(Login.password)){
                        boolean done = Main.deleteTransaction(Integer.parseInt(idOfTransaction));
                        if(done){
                            JOptionPane.showMessageDialog(
                                    null,
                                    "Transaction deleted sucessfully :> ",
                                    "Operation Sucessful",
                                    JOptionPane.INFORMATION_MESSAGE
                            );
                            new Transactions();
                            dispose();
                        }
                        else{
                            JOptionPane.showMessageDialog(
                                    null,
                                    " operation failed, kindly check your transaction ID",
                                    "Failed",
                                    JOptionPane.INFORMATION_MESSAGE
                            );
                        }
                    }
                    else{
                        JOptionPane.showMessageDialog(
                                null,
                                "Password is incorrect!",
                                "Invalid password",
                                JOptionPane.INFORMATION_MESSAGE
                        );
                    }
                }
                else{
                    JOptionPane.showMessageDialog(
                            null,
                            "User Id is null",
                            "Invalid",
                            JOptionPane.INFORMATION_MESSAGE
                    );
                }
            }
        });
        fieldsForInputTransactionsPanel.add(deleteTransactionButton);

        JPanel displayAllTransactionsPanel = new JPanel();
        displayAllTransactionsPanel.setLayout(null);
        displayAllTransactionsPanel.setBounds(25,190,550,330);
        bodyPanel.add(displayAllTransactionsPanel);

        Vector<String> transactionColumn = new Vector<>();
        transactionColumn.add("ID");
        transactionColumn.add("Amount");
        transactionColumn.add("Type");
        transactionColumn.add("Category");
        transactionColumn.add("Date");

        Vector<Vector<Object>> transactionData;
        transactionData = Main.displayAllTransactions();

        JTable displayAllTransactionTable = new JTable(transactionData,transactionColumn){
            @Override
            public boolean isCellEditable(int rows,int column){
                return false;
            }
        };
        JScrollPane displayAllTransactionsScrollPane = new JScrollPane(displayAllTransactionTable);
        displayAllTransactionsScrollPane.setBounds(0,0,550,330);
        displayAllTransactionTable.getTableHeader().setFont(new Font("Roman",Font.PLAIN,15));
        displayAllTransactionTable.setFont(new Font("Roman",Font.PLAIN,12));
        displayAllTransactionTable.setShowGrid(true);
        displayAllTransactionTable.setGridColor(Color.BLACK);
        displayAllTransactionTable.getTableHeader().setBackground(new Color(113, 250, 57));
        displayAllTransactionTable.getTableHeader().setForeground(Color.WHITE);
        displayAllTransactionsPanel.add(displayAllTransactionsScrollPane);


        setVisible(true);
    }
}