import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EditViewStudentFrame extends JFrame {
    private JTextField sicField, nameField, addressField, genderField, branchField, yearField, birthDateField, sectionField, rollNumberField;
    private boolean isEdit;

    public EditViewStudentFrame(String sic, boolean isEdit) {
        this.isEdit = isEdit;
        setTitle(isEdit ? "Edit Student" : "View Student");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JLabel label = new JLabel("SIC:");
        label.setBounds(50, 50, 100, 30);
        add(label);
        sicField = new JTextField(sic);
        sicField.setBounds(200, 50, 200, 30);
        sicField.setEditable(false);
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

        JButton backButton = new JButton("Back");
        backButton.setBounds(320, 500, 100, 30);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ViewStudentsFrame viewStudentsFrame = new ViewStudentsFrame();
                viewStudentsFrame.setVisible(true);
                dispose();
            }
        });
        add(backButton);

        if (isEdit) {
            JButton saveButton = new JButton("Save");
            saveButton.setBounds(200, 500, 100, 30);
            saveButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    updateStudent(sic);
                }
            });
            add(saveButton);
            loadStudentData(sic);
        } else {
            nameField.setEditable(false);
            addressField.setEditable(false);
            genderField.setEditable(false);
            branchField.setEditable(false);
            yearField.setEditable(false);
            birthDateField.setEditable(false);
            sectionField.setEditable(false);
            rollNumberField.setEditable(false);
            loadStudentData(sic);
        }
    }

    private void loadStudentData(String sic) {
        String sql = "SELECT * FROM students WHERE SIC = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, sic);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                nameField.setText(resultSet.getString("name"));
                addressField.setText(resultSet.getString("address"));
                genderField.setText(resultSet.getString("gender"));
                branchField.setText(resultSet.getString("branch"));
                yearField.setText(String.valueOf(resultSet.getInt("year_of_admission")));
                birthDateField.setText(resultSet.getDate("birth_date").toString());
                sectionField.setText(resultSet.getString("section"));
                rollNumberField.setText(String.valueOf(resultSet.getInt("class_roll_number")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading student data");
        }
    }

    private void updateStudent(String sic) {
        String sql = "UPDATE students SET name = ?, address = ?, gender = ?, branch = ?, year_of_admission = ?, birth_date = ?, section = ?, class_roll_number = ? WHERE SIC = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, nameField.getText());
            statement.setString(2, addressField.getText());
            statement.setString(3, genderField.getText());
            statement.setString(4, branchField.getText());
            statement.setInt(5, Integer.parseInt(yearField.getText()));
            statement.setDate(6, java.sql.Date.valueOf(birthDateField.getText()));
            statement.setString(7, sectionField.getText());
            statement.setInt(8, Integer.parseInt(rollNumberField.getText()));
            statement.setString(9, sic);

            int rows = statement.executeUpdate();
            if (rows > 0) {
                JOptionPane.showMessageDialog(this, "Student updated successfully");
                ViewStudentsFrame viewStudentsFrame = new ViewStudentsFrame();
                viewStudentsFrame.setVisible(true);
                dispose();
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error updating student");
        }
    }
}
