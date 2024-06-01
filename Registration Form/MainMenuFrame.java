import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenuFrame extends JFrame {

    public MainMenuFrame() {
        setTitle("Main Menu");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JButton addStudentButton = new JButton("Add Student");
        addStudentButton.setBounds(300, 200, 200, 50);
        addStudentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddStudentFrame addStudentFrame = new AddStudentFrame();
                addStudentFrame.setVisible(true);
                dispose();
            }
        });
        add(addStudentButton);

        JButton viewStudentsButton = new JButton("View Students");
        viewStudentsButton.setBounds(300, 300, 200, 50);
        viewStudentsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ViewStudentsFrame viewStudentsFrame = new ViewStudentsFrame();
                viewStudentsFrame.setVisible(true);
                dispose();
            }
        });
        add(viewStudentsButton);
    }

    public static void main(String[] args) {
        MainMenuFrame mainMenuFrame = new MainMenuFrame();
        mainMenuFrame.setVisible(true);
    }
}
