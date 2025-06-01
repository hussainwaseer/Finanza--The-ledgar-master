import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class Accounts extends JFrame{
    public static String newChosenImagePath=Dashboard.adminProfilePicturePath;

    Accounts(){
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
        accountButton.setBackground(Color.LIGHT_GRAY);
        accountButton.setForeground(new Color(11, 181, 31));
        accountButton.setFont(new Font("Roman",Font.BOLD,14));
        accountButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        accountButton.setBounds(25,310,150,70);
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
        accountHeadingText.setText("Account Details");
        accountHeadingText.setForeground(new Color(11, 181, 31));
        accountHeadingText.setFont(new Font("Roman",Font.BOLD,21));
        accountHeadingText.setBounds(30,30,250,40);
        bodyPanel.add(accountHeadingText);

        JPanel editDetailsOfAccountPanel = new JPanel();
        editDetailsOfAccountPanel.setBounds(30,100,320,300);
        editDetailsOfAccountPanel.setLayout(null);
        bodyPanel.add(editDetailsOfAccountPanel);

        JLabel editUsernameText = new JLabel();
        editUsernameText.setForeground(Color.BLACK);
        editUsernameText.setText("Username: ");
        editUsernameText.setFont(new Font("Roman",Font.BOLD,15));
        editUsernameText.setBounds(5,10,100,30);
        editDetailsOfAccountPanel.add(editUsernameText);

        JTextArea editUsernameArea = new JTextArea();
        editUsernameArea.setText(Login.username);
        editUsernameArea.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
        editUsernameArea.setBounds(100,13,100,30);
        editUsernameArea.setFont(new Font("Roman",Font.BOLD,15));
        editUsernameArea.setOpaque(false);
        editUsernameArea.setLineWrap(false);
        editUsernameArea.setWrapStyleWord(false);
        editDetailsOfAccountPanel.add(editUsernameArea);

        JLabel editPasswordText = new JLabel();
        editPasswordText.setText("Password: ");
        editPasswordText.setFont(new Font("Roman", Font.BOLD, 15));
        editPasswordText.setForeground(Color.BLACK);
        editPasswordText.setBounds(5,60,100,30);
        editDetailsOfAccountPanel.add(editPasswordText);

        JPasswordField editPasswordArea = new JPasswordField();
        editPasswordArea.setFont(new Font("Roman",Font.BOLD,15));
        editPasswordArea.setOpaque(false);
        editPasswordArea.setText(Login.password);
        editPasswordArea.setEchoChar('*');
        editPasswordArea.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
        editPasswordArea.setBounds(100,63,100,30);
        editDetailsOfAccountPanel.add(editPasswordArea);

        JLabel editConfomPasswordText = new JLabel();
        editConfomPasswordText.setText("Confirm: ");
        editConfomPasswordText.setForeground(Color.BLACK);
        editConfomPasswordText.setFont(new Font("Roman",Font.BOLD,15));
        editConfomPasswordText.setBounds(10,110,150,30);
        editDetailsOfAccountPanel.add(editConfomPasswordText);

        JPasswordField editConformPasswordArea = new JPasswordField();
        editConformPasswordArea.setText(Login.password);
        editConformPasswordArea.setEchoChar('*');
        editConformPasswordArea.setOpaque(false);
        editConformPasswordArea.setFont(new Font("Roman",Font.BOLD,15));
        editConformPasswordArea.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
        editConformPasswordArea.setBounds(100,110,100,30);
        editDetailsOfAccountPanel.add(editConformPasswordArea);

        JPanel editImagePanel = new JPanel();
        editImagePanel.setLayout(null);
        editImagePanel.setBounds(340,80,300,230);
        bodyPanel.add(editImagePanel);

        JLabel editImageHeadingText = new JLabel();
        editImageHeadingText.setText("Profile Picture");
        editImagePanel.setForeground(Color.BLACK);
        editImageHeadingText.setFont(new Font("Roman",Font.BOLD,15));
        editImageHeadingText.setBounds(25,5,150,30);
        editImagePanel.add(editImageHeadingText);

        ImageIcon editImageIcon;
        editImageIcon = new ImageIcon(Dashboard.adminProfilePicturePath);
        Image editImageIconScaled = editImageIcon.getImage().getScaledInstance(100,100,Image.SCALE_SMOOTH);
        ImageIcon editImageIconScaledIcon = new ImageIcon(editImageIconScaled);
        JLabel editImageIconLabel = new JLabel(editImageIconScaledIcon);
        editImageIconLabel.setBounds(27,35,100,100);
        editImagePanel.add(editImageIconLabel,BorderLayout.CENTER);

        JButton editImageBrowseButton = new JButton();
        editImageBrowseButton.setText("Browse Image");
        editImageBrowseButton.setForeground(Color.BLACK);
        editImageBrowseButton.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
        editImageBrowseButton.setFont(new Font("Roman",Font.BOLD,11));
        editImageBrowseButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        editImageBrowseButton.setBounds(30,140,90,30);
        editImageBrowseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooseImageAction = new JFileChooser();
                chooseImageAction.setDialogTitle("Choose Picture");
                int result = chooseImageAction.showOpenDialog(null);
                if(result == chooseImageAction.APPROVE_OPTION){
                    java.io.File selectedPicture = chooseImageAction.getSelectedFile();
                    newChosenImagePath = selectedPicture.getPath();
                    ImageIcon selectedImageIcon = new ImageIcon(newChosenImagePath);
                    Image scaledImage = selectedImageIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                    ImageIcon finalIcon = new ImageIcon(scaledImage);
                    editImageIconLabel.setIcon(finalIcon);
                }
            }
        });
        editImagePanel.add(editImageBrowseButton);

        JButton saveChangesOfAccountInformation = new JButton();
        saveChangesOfAccountInformation.setText("Save Changes");
        saveChangesOfAccountInformation.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        saveChangesOfAccountInformation.setBorder(BorderFactory.createLineBorder(Color.black,2));
        saveChangesOfAccountInformation.setForeground(Color.WHITE);
        saveChangesOfAccountInformation.setBackground(new Color(113, 250, 57));
        saveChangesOfAccountInformation.setFont(new Font("Roman",Font.BOLD,15));
        saveChangesOfAccountInformation.setBounds(200,190,120,30);
        saveChangesOfAccountInformation.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(new String(editPasswordArea.getPassword()).equals(new String(editConformPasswordArea.getPassword()))){
                    JPasswordField twoStepVerificationField = new JPasswordField();
                    twoStepVerificationField.setEchoChar('*');
                    int twoStepVerificationPassword = JOptionPane.showConfirmDialog(
                            null,
                            twoStepVerificationField,
                            "Enter Password",
                            JOptionPane.OK_CANCEL_OPTION,
                            JOptionPane.INFORMATION_MESSAGE
                    );
                    if(new String(twoStepVerificationField.getPassword()).equals(Login.password)) {
                        Main.changeDetailsOfAccount(newChosenImagePath);
                        boolean done = Main.changeUsernameAndPassword(editUsernameArea.getText(),new String(editPasswordArea.getPassword()));
                        if(done){JOptionPane.showMessageDialog(
                                null,
                                "Changes saved Succesfully",
                                "Information updated",
                                JOptionPane.INFORMATION_MESSAGE
                        );
                        new Accounts();
                        dispose();}
                        else{
                            JOptionPane.showMessageDialog(
                                    null,
                                    "Changes not done due to some error",
                                    "Error occured",
                                    JOptionPane.INFORMATION_MESSAGE
                            );
                        }
                    }
                    else{
                        JOptionPane.showMessageDialog(
                                null,
                                "Password invalid!",
                                "Failed",
                                JOptionPane.INFORMATION_MESSAGE
                        );
                    }

                }
                else{
                    JOptionPane.showMessageDialog(
                            null,
                            "You're password is not matching rewrite your password!",
                            "Incorrect Match",
                            JOptionPane.INFORMATION_MESSAGE
                    );
                }
            }
        });
        editDetailsOfAccountPanel.add(saveChangesOfAccountInformation);

        JPanel deleteCurrentAccountPanel = new JPanel();
        deleteCurrentAccountPanel.setLayout(null);
        deleteCurrentAccountPanel.setBounds(420,470,140,50);
        bodyPanel.add(deleteCurrentAccountPanel);

        JButton deleteCurrentAccountButton = new JButton();
        deleteCurrentAccountButton.setBackground(Color.red);
        deleteCurrentAccountButton.setText("Delete Account");
        deleteCurrentAccountButton.setFont(new Font("Roman",Font.BOLD,13));
        deleteCurrentAccountButton.setBorder(BorderFactory.createLineBorder(Color.black,2));
        deleteCurrentAccountButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        deleteCurrentAccountButton.setForeground(Color.white);
        deleteCurrentAccountButton.setBounds(0,0,140,40);
        deleteCurrentAccountButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
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
                    boolean done = Main.deleteExistingAccount();
                    if(done){
                    JOptionPane.showMessageDialog(
                            null,
                            "Account deleted Succesfully thanks for spending time with us :>",
                            "Deleted",
                            JOptionPane.INFORMATION_MESSAGE
                    );
                    System.exit(0);
                    dispose();}
                    else{
                        JOptionPane.showMessageDialog(
                                null,
                                "Account not deleted there is some error",
                                "Error Occured",
                                JOptionPane.INFORMATION_MESSAGE
                        );
                    }
                }
            }
        });
        deleteCurrentAccountPanel.add(deleteCurrentAccountButton);

        JPanel generateBankStatementPanel = new JPanel();
        generateBankStatementPanel.setLayout(null);
        generateBankStatementPanel.setBounds(40,470,200,60);
        bodyPanel.add(generateBankStatementPanel);

        JButton generateBankStatementButton = new JButton();
        generateBankStatementButton.setText("Generate Statement");
        generateBankStatementButton.setFont(new Font("Roman",Font.BOLD,14));
        generateBankStatementButton.setBackground(new Color(113, 250, 57));
        generateBankStatementButton.setForeground(Color.WHITE);
        generateBankStatementButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        generateBankStatementButton.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
        generateBankStatementButton.setBounds(0,0,180,40);
        generateBankStatementButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                String pathForPdf="";
                String PdfName="finanza Statement.pdf";
                JFileChooser pathChooserForPdf = new JFileChooser();
                pathChooserForPdf.setDialogTitle("Select Location to save Pdf");
                pathChooserForPdf.setSelectedFile(new File(PdfName));

                int userSelection = pathChooserForPdf.showSaveDialog(null);
                if(userSelection == JFileChooser.APPROVE_OPTION){
                    File fileToGetPath = pathChooserForPdf.getSelectedFile();
                    pathForPdf=fileToGetPath.getAbsolutePath();
                }


                JPasswordField getPasswordField = new JPasswordField();
                getPasswordField.setEchoChar('*');
                int inputPasswordArea = JOptionPane.showConfirmDialog(
                        null,
                        getPasswordField,
                        "Enter password to proceed",
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.INFORMATION_MESSAGE
                );
                String filteredPassword = new String(getPasswordField.getPassword());
                if(filteredPassword.equals(Login.password)){
                boolean done=false;
                try{
                    Main.generatePDF(pathForPdf);
                    done=true;
                }
                catch(Exception ef){
                    System.out.println(ef.getMessage());
                }
                if(done){
                    JOptionPane.showMessageDialog(
                            null,
                            "Transaction Statement Generated Sucessfully",
                            "Generated",
                            JOptionPane.INFORMATION_MESSAGE
                    );
                }
                else{
                    JOptionPane.showMessageDialog(
                            null,
                            "Transaction Statement could not be Generated ",
                            "Error occured",
                            JOptionPane.INFORMATION_MESSAGE
                    );
                }
                }
                else{
                    JOptionPane.showMessageDialog(
                            null,
                            "Your entered password is wrong, kindly try again",
                            "Statement can not be generated",
                            JOptionPane.INFORMATION_MESSAGE
                    );
                }
            }
        });
        generateBankStatementPanel.add(generateBankStatementButton);

        setVisible(true);
    }
}