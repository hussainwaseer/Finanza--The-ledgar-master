import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;
public class SplashScreen extends JFrame{
    SplashScreen(){
        setSize(600,400);
        setLocation(350,150);
        setUndecorated(true);

        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(new Color(172, 170, 170));
        mainPanel.setBounds(0,0,600,400);
        mainPanel.setLayout(null);
        add(mainPanel);

        ImageIcon finanzaIcon = new ImageIcon(getClass().getResource("logo.png"));
        JLabel setFinanzaIcon = new JLabel(finanzaIcon);
        setFinanzaIcon.setBounds(100,130,400,150);
        setFinanzaIcon.setBackground(null);
        mainPanel.add(setFinanzaIcon);

        JProgressBar loadingBar = new JProgressBar();
        loadingBar.setMinimum(0);
        loadingBar.setMaximum(100);
        loadingBar.setStringPainted(true);
        loadingBar.setForeground(new Color(11, 181, 31));
        loadingBar.setFont(new Font("Roman",Font.BOLD,15));
        loadingBar.setBounds(100,350,400,25);
        mainPanel.add(loadingBar);

        setVisible(true);

        Timer splashScreenDelayTime = new Timer();
        TimerTask delayTask = new TimerTask() {
            int loadingBarProgress=0;
            @Override
            public void run() {
                loadingBarProgress+=2;
                loadingBar.setValue(loadingBarProgress);
                if(loadingBarProgress>=100){
                    splashScreenDelayTime.cancel();
                    dispose();
                    new Login();
                }
            }
        };
        splashScreenDelayTime.scheduleAtFixedRate(delayTask,0,50);
    }
}