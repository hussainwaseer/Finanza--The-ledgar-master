import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateAccount extends JFrame {

    CreateAccount() {
        // Frame settings
        setTitle("Finanza - The ledgar Master");
        setSize(600, 400);
        setLocation(350, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setResizable(false);

        // Left section (green in color)
        JPanel leftSection = new JPanel();
        leftSection.setBounds(0, 0, 150, 400);
        leftSection.setBackground(new Color(15, 171, 62));
        add(leftSection);

        // Adding the Image
        JPanel imagePanel = new JPanel();
        imagePanel.setBounds(280, 30, 170, 50);
        imagePanel.setLayout(new BorderLayout());
        ImageIcon Finanza = new ImageIcon(getClass().getResource("logo.png"));
        Image scaledImage = Finanza.getImage().getScaledInstance(200, 150, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        JLabel imageLabel = new JLabel(scaledIcon);
        imagePanel.add(imageLabel, BorderLayout.CENTER);
        add(imagePanel);

        // Adding the texfield areas
        JPanel usernameANDpassword = new JPanel();
        usernameANDpassword.setBounds(260,100,240,100);
        usernameANDpassword.setLayout(null);
        add(usernameANDpassword);

        JLabel usernametext = new JLabel();
        usernametext.setText("Enter username: ");
        usernametext.setForeground(Color.BLACK);
        usernametext.setBounds(10,0,120,20);
        usernameANDpassword.add(usernametext);

        JTextField usernameArea = new JTextField();
        usernameArea.setForeground(Color.BLACK);
        usernameArea.setBounds(30,20,150,20);
        usernameArea.setBorder(BorderFactory.createLineBorder(Color.black));
        usernameArea.setAutoscrolls(true);
        usernameANDpassword.add(usernameArea);

        JLabel passwordText = new JLabel();
        passwordText.setText("Enter password: ");
        passwordText.setForeground(Color.BLACK);
        passwordText.setBounds(10,50,120,20);
        usernameANDpassword.add(passwordText);

        JPasswordField passwordArea = new JPasswordField();
        passwordArea.setForeground(Color.BLACK);
        passwordArea.setBounds(30,70,150,20);
        passwordArea.setBorder(BorderFactory.createLineBorder(Color.black));
        passwordArea.setEchoChar('*');
        usernameANDpassword.add(passwordArea);

        // Login or Signup Panel
        JPanel Signup = new JPanel();
        Signup.setLayout(null);
        Signup.setBounds(260,200,200,100);
        add(Signup);

        JButton SignupButton = new JButton();
        SignupButton.setText("Create Account");
        SignupButton.setBounds(45,10,120,30);
        SignupButton.setBackground(new Color(93, 237, 64));
        SignupButton.setForeground(Color.white);
        SignupButton.setFont(new Font("Roman",Font.ITALIC,14));
        SignupButton.setBorder(BorderFactory.createLineBorder(Color.black,2));
        SignupButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        SignupButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String getpass=new String(passwordArea.getPassword());
                boolean accountCreatedSuccesfully = Main.createAccount(usernameArea.getText(),getpass);
                if(accountCreatedSuccesfully) {
                    JOptionPane.showMessageDialog(
                            null,
                            "Account Created Successfully, Login Now ",
                            "Sucessfull",
                            JOptionPane.INFORMATION_MESSAGE
                    );
                    new Login();
                    dispose();
                }
                else{
                    JOptionPane.showMessageDialog(
                            null,
                            "Account not Created Error Occured",
                            "Error",
                            JOptionPane.INFORMATION_MESSAGE
                    );
                }

            }
        });
        Signup.add(SignupButton);

        JLabel haveAccountQuestion = new JLabel();
        haveAccountQuestion.setText("<html><u>Already have an Account?</u></html>");
        haveAccountQuestion.setFont(new Font("Roman", Font.ITALIC,11));
        haveAccountQuestion.setForeground(Color.black);
        haveAccountQuestion.setBounds(45,40,150,30);
        Signup.add(haveAccountQuestion);

        JButton loginButton = new JButton();
        loginButton.setText("Login");
        loginButton.setBounds(60,70,90,30);
        loginButton.setBackground(new Color(93, 237, 64));
        loginButton.setForeground(Color.white);
        loginButton.setFont(new Font("Roman",Font.ITALIC,14));
        loginButton.setBorder(BorderFactory.createLineBorder(Color.black,2));
        loginButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        Signup.add(loginButton);
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                    new Login();
                    dispose();
                }
            });

        setVisible(true);
    }

}