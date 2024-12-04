import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.FileWriter;
import java.io.IOException;

public class StudentPerformanceSystem {
    private JFrame frame;
    private JTextField nameField, rollField, subjectField, marksField;
    private JTable table;
    private DefaultTableModel tableModel;

    public StudentPerformanceSystem() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Student Performance Monitoring System");
        frame.setSize(1920, 1080);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Header Panel
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(44, 62, 80)); // Dark gray-blue
        JLabel titleLabel = new JLabel("Student Performance Monitoring System");
        titleLabel.setFont(new Font("Century Gothic", Font.BOLD, 60));
        titleLabel.setForeground(Color.WHITE); // White text for header
        headerPanel.add(titleLabel);
        frame.add(headerPanel, BorderLayout.NORTH);

        // Form Panel
        JPanel formPanel = new JPanel(new GridLayout(5, 2, 20, 20));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        formPanel.setBackground(new Color(236, 240, 241)); // Light gray

        formPanel.add(new JLabel("Name:"));
        nameField = new JTextField();
        formPanel.add(nameField);

        formPanel.add(new JLabel("Roll Number:"));
        rollField = new JTextField();
        formPanel.add(rollField);

        formPanel.add(new JLabel("Subject:"));
        subjectField = new JTextField();
        formPanel.add(subjectField);

        formPanel.add(new JLabel("Marks:"));
        marksField = new JTextField();
        formPanel.add(marksField);

        JButton addButton = new JButton("Add Student");
        addButton.setBackground(new Color(52, 152, 219)); // Blue
        addButton.setForeground(Color.WHITE);
        formPanel.add(addButton);
        addButton.addActionListener(this::addStudent);

        JButton clearButton = new JButton("Clear");
        clearButton.setBackground(new Color(231, 76, 60)); // Red
        clearButton.setForeground(Color.WHITE);
        formPanel.add(clearButton);
        clearButton.addActionListener(e -> clearFields());

        frame.add(formPanel, BorderLayout.WEST);

        // Table Panel
        JPanel tablePanel = new JPanel(new BorderLayout());
        tableModel = new DefaultTableModel(new String[]{"    Name", "    Roll Number", "    Subject", "    Marks"}, 0);
        table = new JTable(tableModel);










    JScrollPane tableScroll = new JScrollPane(table);
        tablePanel.add(tableScroll, BorderLayout.CENTER);
        Font tableFont = new Font("Arial", Font.PLAIN, 24);
        table.setFont(tableFont);


        Font headerFont = new Font("Arial", Font.BOLD, 22);  // Bold font for headers
        table.getTableHeader().setFont(headerFont);
        table.setFont(tableFont);
        table.setRowHeight(30);


        // Action Panel
        JPanel actionPanel = new JPanel(new FlowLayout());
        actionPanel.setBackground(new Color(44, 62, 80)); // Dark gray-blue
        Font customFont2 = new Font("Arial", Font.BOLD, 55);


        JButton deleteButton = new JButton("Delete Selected");
        deleteButton.setBackground(new Color(52, 73, 94)); // Gray-blue
        deleteButton.setForeground(Color.WHITE);
        deleteButton.addActionListener(this::deleteStudent);
        actionPanel.add(deleteButton);

        JButton searchButton = new JButton("Search by Roll Number");
        searchButton.setBackground(new Color(46, 204, 113)); // Green
        searchButton.setForeground(Color.WHITE);
        searchButton.addActionListener(this::searchStudent);
        actionPanel.add(searchButton);

        JButton exportButton = new JButton("Export to CSV");
        exportButton.setBackground(new Color(241, 196, 15)); // Yellow
        exportButton.setForeground(Color.BLACK);
        exportButton.addActionListener(e -> exportToCSV());
        actionPanel.add(exportButton);
        Font customFont = new Font("Arial", Font.BOLD, 25);
        formPanel.setFont(customFont);


        for (Component component :  formPanel.getComponents()) {
            component.setFont(customFont);
        }

        tablePanel.add(actionPanel, BorderLayout.SOUTH);
        frame.add(tablePanel, BorderLayout.CENTER);

        frame.setVisible(true);
    }

    private void addStudent(ActionEvent e) {
        String name = nameField.getText();
        String roll = rollField.getText();
        String subject = subjectField.getText();
        String marks = marksField.getText();

        if (name.isEmpty() || roll.isEmpty() || subject.isEmpty() || marks.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Please fill all fields.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

//        if (!isRollNumberUnique(roll)) {
//            JOptionPane.showMessageDialog(frame, "Roll number must be unique.", "Error", JOptionPane.ERROR_MESSAGE);
//            return;
//        }

        try {
            int marksInt = Integer.parseInt(marks);
            tableModel.addRow(new Object[]{name, roll, subject, marksInt});
            clearFields();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Marks must be a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean isRollNumberUnique(String roll) {
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            if (tableModel.getValueAt(i, 1).toString().equals(roll)) {
                return false;
            }
        }
        return true;
    }

    private void clearFields() {
        nameField.setText("");
        rollField.setText("");
        subjectField.setText("");
        marksField.setText("");
    }

    private void deleteStudent(ActionEvent e) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            tableModel.removeRow(selectedRow);
        } else {
            JOptionPane.showMessageDialog(frame, "No student selected.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void searchStudent(ActionEvent e) {
        String roll = JOptionPane.showInputDialog(frame, "Enter Roll Number to search:");
        if (roll == null || roll.isEmpty()) return;

        boolean found = false;
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            if (tableModel.getValueAt(i, 1).toString().equals(roll)) {
                table.setRowSelectionInterval(i, i);
                table.scrollRectToVisible(table.getCellRect(i, 0, true)); // Scroll to the row
                found = true;
                break;
            }
        }

        if (!found) {
            JOptionPane.showMessageDialog(frame, "Student not found.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void exportToCSV() {
        try {
            FileWriter writer = new FileWriter("students.csv");
            for (int i = 0; i < tableModel.getRowCount(); i++) {
                for (int j = 0; j < tableModel.getColumnCount(); j++) {
                    writer.write(tableModel.getValueAt(i, j).toString() + ",");
                }
                writer.write("\n");
            }
            writer.close();
            JOptionPane.showMessageDialog(frame, "Data exported successfully.");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(frame, "Error exporting data.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(StudentPerformanceSystem::new);
    }
}
