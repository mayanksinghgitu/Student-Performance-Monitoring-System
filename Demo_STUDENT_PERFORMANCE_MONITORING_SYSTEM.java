import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;
import java.util.Comparator;

class StudentPerformanceSystem{
    private JFrame frame;
    private JTextField nameField, rollField, subjectField, marksField;
    private JTable table;
    private DefaultTableModel tableModel;

    public StudentPerformanceSystem() {
        initialize();
    }
    void initialize() {
        frame = new JFrame("Student Performance Monitoring System");
        frame.setSize(1919, 1006);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

// Create a custom JPanel with a background image
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon backgroundImage = new ImageIcon("network.jpg"); // Replace with your image path
                backgroundImage.getImage().getScaledInstance(234,323,Image.SCALE_DEFAULT);
            }
        };
        backgroundPanel.setLayout(new BorderLayout()); // Set layout for the panel to match the frame

// Add the background panel to the frame
        frame.setContentPane(backgroundPanel);

// Add components to the background panel as needed
        JLabel label = new JLabel("Welcome to the Student Performance Monitoring System!", SwingConstants.CENTER);
        label.setFont(new Font("Century Gothic", Font.BOLD, 24));
        label.setForeground(Color.WHITE);
        backgroundPanel.add(label, BorderLayout.CENTER);

// Display the frame
        frame.setVisible(true);

        // Header Panel
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(44, 62, 80)); // Dark gray-blue
        JLabel titleLabel = new JLabel("Student Performance Monitoring System");
        titleLabel.setFont(new Font("Century Gothic", Font.BOLD, 60));
        titleLabel.setForeground(Color.WHITE); // White text for header
        headerPanel.add(titleLabel);
        frame.add(headerPanel, BorderLayout.NORTH);

//         Form Panel
//         Define the font to be used throughout the form panel
         Font formFont = new Font("Century Gothic", Font.BOLD, 22); // You can use any font style and size you like

        JPanel formPanel = new JPanel(new GridLayout(8, 2, 20, 10)); // Increase rows for better button spacing
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        formPanel.setPreferredSize(new Dimension(400, 600));
        formPanel.setBackground(new Color(250, 243, 148)); // Light yellow background

// Label and Text Field for Name
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setFont(formFont);  // Set font for label
        formPanel.add(nameLabel);
        nameField = new JTextField();
        nameField.setFont(formFont);  // Set font for text field
        nameField.setPreferredSize(new Dimension(250, 20));
        formPanel.add(nameField);

// Label and Text Field for Roll Number
        JLabel rollLabel = new JLabel("Roll Number:");
        rollLabel.setFont(formFont);  // Set font
        formPanel.add(rollLabel);
        rollField = new JTextField();
        rollField.setFont(formFont);  // Set font for text field
        rollField.setPreferredSize(new Dimension(250, 20));
        formPanel.add(rollField);

// Label and Text Field for Subject
        JLabel subjectLabel = new JLabel("Subject:");
        subjectLabel.setFont(formFont);  // Set font for label
        formPanel.add(subjectLabel);
        subjectField = new JTextField();
        subjectField.setFont(formFont);  // Set font for text field
        subjectField.setPreferredSize(new Dimension(250, 20));
        formPanel.add(subjectField);

// Label and Text Field for Marks
        JLabel marksLabel = new JLabel("Marks:");
        marksLabel.setFont(formFont);  // Set font for label
        formPanel.add(marksLabel);
        marksField = new JTextField();
        marksField.setFont(formFont);  // Set font for text field
        marksField.setPreferredSize(new Dimension(250, 20));
        formPanel.add(marksField);

// Add Student Button

        JButton addButton = new JButton("Add Student");
        addButton.setBackground(new Color(52, 152, 219)); // Blue button
        addButton.setForeground(Color.WHITE);
        addButton.setFont(new Font("Century Gothic", Font.BOLD, 20));  // Bold text for better visibility
        addButton.setPreferredSize(new Dimension(200, 20));  // Larger button for easier interaction
        formPanel.add(addButton);
        addButton.addActionListener(this::addStudent);

        JButton toggleButton = new JButton("Show Table");
        toggleButton.setFont(new Font("Century Gothic", Font.BOLD, 20));
        toggleButton.setBackground(new Color(52, 152, 219)); // Blue button
        toggleButton.setForeground(Color.WHITE);
        toggleButton.setPreferredSize(new Dimension(200, 40));
        formPanel.add(toggleButton);

// Clear Button
        JButton clearButton = new JButton("Clear");
        clearButton.setBackground(new Color(231, 76, 60)); // Red button
        clearButton.setForeground(Color.WHITE);
        clearButton.setFont(new Font("Century Gothic", Font.BOLD, 25));  // Bold text for better visibility
        clearButton.setPreferredSize(new Dimension(200, 20));  // Larger button for easier interaction
        formPanel.add(clearButton);
        clearButton.addActionListener(e -> clearFields());

        //compare BUTTON
        JButton compareButton = new JButton("Compare");
        compareButton.setBackground(new Color(46, 204, 113)); // Green
        compareButton.setForeground(Color.WHITE);
        compareButton.setFont(new Font("Century Gothic", Font.BOLD, 20)); // Make the text bold
        // compareButton.addActionListener(e -> openComparePerformanceDialog());
        formPanel.add(compareButton);
        compareButton.addActionListener(this::comparePerformance);

        // EXIT BUTTON

        JButton exitButton = new JButton("Close");
        exitButton.setBackground(new Color(231, 76, 60));
        exitButton.setForeground(Color.WHITE);
        exitButton.setFont(new Font("Century Gothic", Font.BOLD, 25));
        exitButton.setPreferredSize(new Dimension(200, 20));
        formPanel.add(exitButton);

        JButton aboutButton = new JButton("About App");
        aboutButton.setBackground(new Color(9, 22, 60)); // Green button
        aboutButton.setForeground(Color.WHITE); // White text color
        aboutButton.setFont(new Font("Century Gothic", Font.BOLD, 25));
        aboutButton.setPreferredSize(new Dimension(200, 20));
        formPanel.add(aboutButton);
        aboutButton.setPreferredSize(new Dimension(250, 40));  // Larger button for easier interaction
        aboutButton.setFocusPainted(false); // Remove focus border when clicked
        aboutButton.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add padding for a neat look
        formPanel.add(aboutButton);

// Action Listener for About Button
        aboutButton.addActionListener(e -> showAboutInfo());

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Exit the application
                System.exit(0);
            }
        });

        frame.add(formPanel, BorderLayout.WEST);

        // Make the frame visible
        frame.setVisible(true);

        // Table Panel
        JPanel tablePanel = new JPanel(new BorderLayout());
        tableModel = new DefaultTableModel(new String[]{"Name", "Roll Number", "Subject", "Marks", "Average Marks"}, 0);
        table = new JTable(tableModel);

// Wrap the table in a scroll pane
        JScrollPane tableScroll = new JScrollPane(table);
        tablePanel.add(tableScroll, BorderLayout.CENTER);

// Font for table content
        Font tableFont = new Font("Arial", Font.PLAIN, 24);
        table.setFont(tableFont);

// Bold font for table headers
        Font headerFont = new Font("Arial", Font.BOLD, 22);
        table.getTableHeader().setFont(headerFont);

// Row height and grid lines
        table.setRowHeight(30);
        table.setShowGrid(true);
        table.setGridColor(new Color(0, 0, 0)); // Light gray grid lines

// Header customization
        table.getTableHeader().setBackground(new Color(0, 102, 204)); // Blue background
        table.getTableHeader().setForeground(Color.WHITE); // White text

// Alternate row colors
        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (!isSelected) {
                    c.setBackground(row % 2 == 0 ? new Color(224, 255, 255) : Color.WHITE); // Light cyan for even rows
                }
                return c;
            }
        });

// Add padding around the table panel
        tablePanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        tablePanel.setBackground(new Color(236, 124, 124)); // Light blue panel background

        // Action Panel
        JPanel actionPanel = new JPanel(new FlowLayout());
        actionPanel.setBackground(new Color(44, 62, 80)); // Dark gray-blue

        JButton deleteButton = new JButton("Delete Selected");
        deleteButton.setBackground(new Color(255, 0, 0)); // Gray-blue
        deleteButton.setForeground(Color.WHITE);
        deleteButton.addActionListener(this::deleteStudent);
        actionPanel.add(deleteButton);

// Assume `tablePanel` is the panel you want to show/hide
        tablePanel.setVisible(true); // Initially hidden

        toggleButton.addActionListener(e -> {
            boolean isVisible = tablePanel.isVisible();
            tablePanel.setVisible(!isVisible); // Toggle visibility
            toggleButton.setText(isVisible ? "Show Table" : "Hide Table"); // Update button text
        });

        JButton topRankersButton = new JButton("Short By Top Rankers");
        topRankersButton.setBackground(new Color(21, 47, 63)); // Orange color
        topRankersButton.setForeground(Color.WHITE);
        // topRankersButton.setFont(new Font("Century Gothic", Font.BOLD, 20));
        topRankersButton.addActionListener(this::sortByTopRankers);
        actionPanel.add(topRankersButton);

        JButton searchButton = new JButton("Search by Roll Number");
        searchButton.setBackground(new Color(14, 2, 233, 216)); // Green
        searchButton.setForeground(Color.WHITE);
        searchButton.addActionListener(this::searchStudent);
        actionPanel.add(searchButton);

        JButton exportButton = new JButton("Export to CSV");
        exportButton.setBackground(new Color(241, 196, 15)); // Yellow
        exportButton.setForeground(Color.BLACK);
        exportButton.addActionListener(e -> exportToCSV());
        actionPanel.add(exportButton);

        JButton calculateButton = new JButton("Calculate Averages");
        calculateButton.setBackground(new Color(155, 89, 182)); // Purple
        calculateButton.setForeground(Color.WHITE);
        calculateButton.addActionListener(this::calculateAverages);
        actionPanel.add(calculateButton);

        JButton viewAverageButton = new JButton("View Average by Roll Number");
        viewAverageButton.setBackground(new Color(39, 174, 96)); // Green
        viewAverageButton.setForeground(Color.WHITE);
        viewAverageButton.addActionListener(this::viewAverageByRollNumber);
        actionPanel.add(viewAverageButton);

        // New button to sort by roll number
        JButton sortButton = new JButton("Sort by Roll Number");
        sortButton.setBackground(new Color(52, 152, 219)); // Blue
        sortButton.setForeground(Color.WHITE);
        sortButton.addActionListener(this::sortByRollNumber);
        actionPanel.add(sortButton);

        // New button to sort by Top Scorer
        JButton topScorerButton = new JButton("Top Score");
        topScorerButton.setBackground(new Color(187, 8, 231)); // Red
        topScorerButton.setForeground(Color.WHITE);
        topScorerButton.addActionListener(this::topScorer);
        actionPanel.add(topScorerButton);

        tablePanel.add(actionPanel, BorderLayout.SOUTH);
        frame.add(tablePanel, BorderLayout.CENTER);

        frame.setVisible(true);
    }

    private void comparePerformance(ActionEvent e) {
        // Create a panel for multiple input fields
        JPanel inputPanel = new JPanel(new GridLayout(4, 2, 10, 10)); // 4 rows, 2 columns, 10px spacing
        inputPanel.setSize(600, 900);
        inputPanel.setBackground(new Color(220, 245, 222)); // Light blue background
        inputPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15)); // Padding around the panel

        // Labels and text fields for the first student
        JLabel label1Name = new JLabel("Enter First Student Name:");
        label1Name.setForeground(new Color(0, 102, 204)); // Blue text
        inputPanel.add(label1Name);
        JTextField name1Field = new JTextField();
        name1Field.setBackground(new Color(224, 255, 255)); // Light cyan background
        inputPanel.add(name1Field);

        JLabel label1Roll = new JLabel("Enter First Student Roll Number:");
        label1Roll.setForeground(new Color(0, 102, 204)); // Blue text
        inputPanel.add(label1Roll);
        JTextField roll1Field = new JTextField();
        roll1Field.setBackground(new Color(224, 255, 255)); // Light cyan background
        inputPanel.add(roll1Field);

        // Labels and text fields for the second student
        JLabel label2Name = new JLabel("Enter Second Student Name:");
        label2Name.setForeground(new Color(243, 4, 4)); // Blue text
        inputPanel.add(label2Name);
        JTextField name2Field = new JTextField();
        name2Field.setBackground(new Color(241, 221, 221)); // Light cyan background
        inputPanel.add(name2Field);

        JLabel label2Roll = new JLabel("Enter Second Student Roll Number:");
        label2Roll.setForeground(new Color(245, 7, 7)); // Blue text
        inputPanel.add(label2Roll);
        JTextField roll2Field = new JTextField();
        roll2Field.setBackground(new Color(255, 224, 224)); // Light cyan background
        inputPanel.add(roll2Field);

        // Show the dialog with the input panel
        int result = JOptionPane.showConfirmDialog(frame, inputPanel, "Enter Details for Both Students", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            String name1 = name1Field.getText().trim();
            String roll1 = roll1Field.getText().trim();
            String name2 = name2Field.getText().trim();
            String roll2 = roll2Field.getText().trim();

            if (name1.isEmpty() || roll1.isEmpty() || name2.isEmpty() || roll2.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Please fill all fields.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Calculate averages for both students
            double avg1 = calculateStudentAverage(roll1);
            double avg2 = calculateStudentAverage(roll2);

            // Check if both students exist in the data
            if (avg1 == -1 || avg2 == -1) {
                JOptionPane.showMessageDialog(frame, "One or both students not found.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                // Show the comparison result
                String message = String.format(
                        "<html><body style='font-size:12px;'>"
                                + "<b>%s</b> (Roll: %s) Average: %.2f<br>"
                                + "<b>%s</b> (Roll: %s) Average: %.2f<br><br>"
                                + "<b>%s</b></body></html>",
                        name1, roll1, avg1, name2, roll2, avg2,
                        (avg1 > avg2 ? name1 + " has a higher average than " + name2 : avg1 < avg2 ? name2 + " has a higher average than " + name1 : "Both students have the same average.")
                );
                JOptionPane.showMessageDialog(frame, message, "Comparison Result", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    private double calculateStudentAverage(String roll) {
        int totalMarks = 0, count = 0;

        for (int i = 0; i < tableModel.getRowCount(); i++) {
            if (tableModel.getValueAt(i, 1).toString().equals(roll)) {
                totalMarks += Integer.parseInt(tableModel.getValueAt(i, 3).toString());
                count++;
            }
        }

        return count > 0 ? (double) totalMarks / count : -1;
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

        try {
            int marksInt = Integer.parseInt(marks);
            tableModel.addRow(new Object[]{name, roll, subject, marksInt, "-"}); // Default "-" for average
            clearFields();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Marks must be a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    void clearFields() {
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


    private void showAboutInfo() {
        JOptionPane.showMessageDialog(frame,
                "Student Performance Monitoring System\n\n" +
                        "Version: 1.0.2025\n" +
                        "Developed by: MAYANK SINGH \n" +//"(B.tech CSE Student at Galgotias University) \n" +
                        "This application allows you to manage and monitor the performance of students.\n" +
                        "You can add Student Details, Calculate Averages,Compare Performance B/t Two Student, Sort Students According to Roll number or Scores, and Export data to CSV.\n" +
                        "Conclusion:\n" +
                        "The Student Performance Monitoring System is a comprehensive and efficient tool for educators to track and manage students' academic performance.\n" +
                        "Its featuresstreamline administrative tasks, allowing for quicker analysis of data, better decision-making, and more effective student performance tracking.\n" +
                        "The system is designed to be scalable, with room for further features and enhancements, such as additional reports and analytics tools.\n",

                "About Application",
                JOptionPane.INFORMATION_MESSAGE);
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

    private void calculateAverages(ActionEvent e) {
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            String roll = tableModel.getValueAt(i, 1).toString();
            int totalMarks = 0, count = 0;

            for (int j = 0; j < tableModel.getRowCount(); j++) {
                if (tableModel.getValueAt(j, 1).toString().equals(roll)) {
                    totalMarks += Integer.parseInt(tableModel.getValueAt(j, 3).toString());
                    count++;
                }
            }

            double average = (double) totalMarks / count;
            tableModel.setValueAt(String.format("%.2f", average), i, 4);
        }
    }

    private void viewAverageByRollNumber(ActionEvent e) {
        String roll = JOptionPane.showInputDialog(frame, "Enter Roll Number to view average:");
        if (roll == null || roll.isEmpty()) return;

        int totalMarks = 0, count = 0;

        for (int i = 0; i < tableModel.getRowCount(); i++) {
            if (tableModel.getValueAt(i, 1).toString().equals(roll)) {
                totalMarks += Integer.parseInt(tableModel.getValueAt(i, 3).toString());
                count++;
            }
        }

        if (count > 0) {
            double average = (double) totalMarks / count;
            JOptionPane.showMessageDialog(frame, "Average marks for roll number " + roll + ": " + String.format("%.2f", average));
        } else {
            JOptionPane.showMessageDialog(frame, "Student not found.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void sortByRollNumber(ActionEvent e) {
        Vector<Vector<Object>> data = new Vector<>();
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            Vector<Object> row = new Vector<>();
            row.add(tableModel.getValueAt(i, 0));
            row.add(tableModel.getValueAt(i, 1));
            row.add(tableModel.getValueAt(i, 2));
            row.add(tableModel.getValueAt(i, 3));
            row.add(tableModel.getValueAt(i, 4));
            data.add(row);
        }

        data.sort(Comparator.comparing(o -> Integer.parseInt(o.get(1).toString())));

        tableModel.setRowCount(0);
        for (Vector<Object> row : data) {
            tableModel.addRow(row);
        }
    }

    private void sortByTopRankers(ActionEvent e) {
        Vector<Vector<Object>> data = new Vector<>();
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            Vector<Object> row = new Vector<>();
            for (int j = 0; j < tableModel.getColumnCount(); j++) {
                row.add(tableModel.getValueAt(i, j));
            }
            data.add(row);
        }

        // Sort data by "Average Marks" (Column 4) in descending order
        data.sort((row1, row2) -> {
            String avg1 = row1.get(4).toString();
            String avg2 = row2.get(4).toString();

            // Handle rows where Average Marks is "-"
            if (avg1.equals("-")) avg1 = "0";
            if (avg2.equals("-")) avg2 = "0";

            double marks1 = Double.parseDouble(avg1);
            double marks2 = Double.parseDouble(avg2);
            return Double.compare(marks2, marks1); // Descending order
        });

        // Clear and update table with sorted data
        tableModel.setRowCount(0);
        for (Vector<Object> row : data) {
            tableModel.addRow(row);
        }
    }

    private void topScorer(ActionEvent e) {
        int topMarks = -1;
        String topStudent = "";
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            int average = Integer.parseInt(tableModel.getValueAt(i, 3).toString());
            if (average > topMarks) {
                topMarks = average;
                topStudent = tableModel.getValueAt(i, 0).toString();
            }
        }

        if (topMarks != -1) {
            JOptionPane.showMessageDialog(frame, "Top Scorer: " + topStudent + " with " + topMarks + " marks");
        } else {
            JOptionPane.showMessageDialog(frame, "No data available.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void exportToCSV() {
        try (FileWriter writer = new FileWriter("students.csv")) {
            for (int i = 0; i < tableModel.getColumnCount(); i++) {
                writer.append(tableModel.getColumnName(i)).append(",");
            }
            writer.append("\n");

            for (int i = 0; i < tableModel.getRowCount(); i++) {
                for (int j = 0; j < tableModel.getColumnCount(); j++) {
                    writer.append(tableModel.getValueAt(i, j).toString()).append(",");
                }
                writer.append("\n");
            }
            JOptionPane.showMessageDialog(frame, "Data exported successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(frame, "Error exporting data.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new StudentPerformanceSystem());

    }
}

