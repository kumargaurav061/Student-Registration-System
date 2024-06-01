import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ViewStudentsFrame extends JFrame {
    private JTable table;
    private DefaultTableModel model;

    public ViewStudentsFrame() {
        setTitle("View Students");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        model = new DefaultTableModel(new String[]{"SIC", "Name", "Branch", "Edit", "View"}, 0);
        table = new JTable(model);
        table.setBounds(50, 50, 700, 400);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(50, 50, 700, 400);
        add(scrollPane);

        // Set button renderer and editor for Edit and View columns
        TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(3).setCellRenderer(new ButtonRenderer());
        columnModel.getColumn(3).setCellEditor(new ButtonEditor(new JCheckBox(), table));
        columnModel.getColumn(4).setCellRenderer(new ButtonRenderer());
        columnModel.getColumn(4).setCellEditor(new ButtonEditor(new JCheckBox(), table));

        loadStudents();

        JButton backButton = new JButton("Back");
        backButton.setBounds(350, 500, 100, 30);
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

    private void loadStudents() {
        String sql = "SELECT SIC, name, branch FROM students";

        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                String sic = resultSet.getString("SIC");
                String name = resultSet.getString("name");
                String branch = resultSet.getString("branch");
                model.addRow(new Object[]{sic, name, branch, "Edit", "View"});
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading students");
        }
    }
}
