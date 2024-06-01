import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddStudentFrame extends JFrame {
    private JTextField sicField, nameField, addressField, genderField, branchField, yearField, birthDateField, sectionField, rollNumberField;

    public AddStudentFrame() {
        setTitle("Add Student");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JLabel label = new JLabel("SIC:");
        label.setBounds(50, 50, 100, 30);
        add(label);
        sicField = new JTextField();
        sicField.setBounds(200, 50, 200, 30);
        add(sicField);

        label = new JLabel("Name:");
        label.setBounds(50, 100, 100, 30);
        add(label);
        nameField = new JTextField();
        nameField.setBounds(200, 100, 200, 30);
        add(nameField);

        label = new JLabel("Address:");
        label.setBounds(50, 150, 100, 30);
        add(label);
        addressField = new JTextField();
        addressField.setBounds(200, 150, 200, 30);
        add(addressField);

        label = new JLabel("Gender:");
        label.setBounds(50, 200, 100, 30);
        add(label);
        genderField = new JTextField();
        genderField.setBounds(200, 200, 200, 30);
        add(genderField);

        label = new JLabel("Branch:");
        label.setBounds(50, 250, 100, 30);
        add(label);
        branchField = new JTextField();
        branchField.setBounds(200, 250, 200, 30);
        add(branchField);

        label = new JLabel("Year of Admission:");
        label.setBounds(50, 300, 150, 30);
        add(label);
        yearField = new JTextField();
        yearField.setBounds(200, 300, 200, 30);
        add(yearField);

        label = new JLabel("Birth Date (YYYY-MM-DD):");
        label.setBounds(50, 350, 200, 30);
        add(label);
        birthDateField = new JTextField();
        birthDateField.setBounds(200, 350, 200, 30);
        add(birthDateField);

        label = new JLabel("Section:");
        label.setBounds(50, 400, 100, 30);
        add(label);
        sectionField = new JTextField();
        sectionField.setBounds(200, 400, 200, 30);
        add(sectionField);

        label = new JLabel("Class Roll Number:");
        label.setBounds(50, 450, 150, 30);
        add(label);
        rollNumberField = new JTextField();
        rollNumberField.setBounds(200, 450, 200, 30);
        add(rollNumberField);

        JButton saveButton = new JButton("Save");
        saveButton.setBounds(200, 500, 100, 30);
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addStudent();
            }
        });
        add(saveButton);

        JButton backButton = new JButton("Back");
        backButton.setBounds(320, 500, 100, 30);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainMenuFrame mainMenuFrame = new MainMenuFrame();
                mainMenuFrame.setVisible(true);
                dispose();
            }
        });
        add(backButton);
    }

    private void addStudent() {
        // Check if any field is empty
        if (sicField.getText().isEmpty() || nameField.getText().isEmpty() || addressField.getText().isEmpty() ||
            genderField.getText().isEmpty() || branchField.getText().isEmpty() || yearField.getText().isEmpty() ||
            birthDateField.getText().isEmpty() || sectionField.getText().isEmpty() || rollNumberField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter all student details");
            return; // Exit the method if any field is empty
        }

        // Proceed to insert data if all fields are filled
        String sql = "INSERT INTO students (SIC, name, address, gender, branch, year_of_admission, birth_date, section, class_roll_number) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, sicField.getText());
            statement.setString(2, nameField.getText());
            statement.setString(3, addressField.getText());
            statement.setString(4, genderField.getText());
            statement.setString(5, branchField.getText());
            statement.setInt(6, Integer.parseInt(yearField.getText()));
            statement.setDate(7, java.sql.Date.valueOf(birthDateField.getText()));
            statement.setString(8, sectionField.getText());
            statement.setInt(9, Integer.parseInt(rollNumberField.getText()));

            int rows = statement.executeUpdate();
            if (rows > 0) {
                JOptionPane.showMessageDialog(this, "Student added successfully");
                clearFields();
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error adding student");
        }
    }

    private void clearFields() {
        sicField.setText("");
        nameField.setText("");
        addressField.setText("");
        genderField.setText("");
        branchField.setText("");
        yearField.setText("");
        birthDateField.setText("");
        sectionField.setText("");
        rollNumberField.setText("");
    }
}
