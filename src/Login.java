import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame {
    public static  String username;
    public static  String password;

    Login() {
        // Frame settings
        setTitle("Finanza - The ledgar Master");
        setSize(600, 400);
        setLocation(350, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setResizable(false);


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
        JPanel loginORsignup = new JPanel();

        loginORsignup.setLayout(null);
        loginORsignup.setBounds(260,200,200,100);
        add(loginORsignup);

        JButton loginButton = new JButton();
        loginButton.setText("Login");
        loginButton.setBounds(55,10,90,30);
        loginButton.setBackground(new Color(93, 237, 64));
        loginButton.setForeground(Color.white);
        loginButton.setFont(new Font("Roman",Font.ITALIC,14));
        loginButton.setBorder(BorderFactory.createLineBorder(Color.black,2));
        loginButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        loginORsignup.add(loginButton);
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String getUsername = usernameArea.getText();
                String getPassword = new String(passwordArea.getPassword());
                if(getUsername.equals(null) || getPassword.equals(null)){
                    JOptionPane.showMessageDialog(
                            null,
                            "Username or Password Field can't be null",
                            "NUll Found!",
                            JOptionPane.INFORMATION_MESSAGE
                    );
                }
                else {
                    Main.checkLoginDetailsFromDatabase(getUsername, getPassword);
                    if (getUsername.equals(username) && getPassword.equals(password)) {
                        JOptionPane.showMessageDialog(
                                null,
                                "Login Sucessfull",
                                "Welcome: " + username,
                                JOptionPane.INFORMATION_MESSAGE
                        );
                        new Dashboard();
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(
                                null,
                                "Login Failed, kindly check your username and password",
                                "Login Failed",
                                JOptionPane.INFORMATION_MESSAGE
                        );
                    }
                }
            }
        });

        JLabel signupQuestion = new JLabel();
        signupQuestion.setText("<html><u>Don't have an Account?</u></html>");
        signupQuestion.setFont(new Font("Roman", Font.ITALIC,11));
        signupQuestion.setForeground(Color.black);
        signupQuestion.setBounds(40,30,150,45);
        loginORsignup.add(signupQuestion);

        JButton signupButton = new JButton();
        signupButton.setText("Create Account");
        signupButton.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        signupButton.setForeground(Color.white);
        signupButton.setBackground(new Color(93, 237, 64));
        loginButton.setFont(new Font("Roman",Font.ITALIC,14));
        signupButton.setBounds(45,65,110,30);
        signupButton.setBorder(BorderFactory.createLineBorder(Color.black,2));
        signupButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        signupButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                new CreateAccount();
                dispose();
            }
        });
        loginORsignup.add(signupButton);

        setVisible(true);
    }

}