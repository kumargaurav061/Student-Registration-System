import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends JFrame {
    private JTextField passwordField;

    public LoginFrame() {
        setTitle("Login");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JLabel label = new JLabel("Enter Password:");
        label.setBounds(300, 200, 200, 30);
        add(label);

        passwordField = new JTextField();
        passwordField.setBounds(300, 250, 200, 30);
        add(passwordField);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(350, 300, 100, 30);
        loginButton.addActionListener(new LoginAction());
        add(loginButton);
    }

    private class LoginAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String password = passwordField.getText();
            if ("1234".equals(password)) {
                MainMenuFrame mainMenuFrame = new MainMenuFrame();
                mainMenuFrame.setVisible(true);
                dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Invalid Password");
            }
        }
    }
}
