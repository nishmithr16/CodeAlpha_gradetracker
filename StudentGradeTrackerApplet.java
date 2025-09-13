import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * Student Grade Tracker Applet
 * 
 * HTML to run this applet:
 * <html>
 * <body>
 * <applet code="StudentGradeTrackerApplet.class" width="600" height="500">
 * </applet>
 * </body>
 * </html>
 * 
 * To compile and run:
 * 1. javac *.java
 * 2. Create an HTML file with the above applet tag
 * 3. Open the HTML file in a browser with Java plugin support
 */
public class StudentGradeTrackerApplet extends Applet implements ActionListener {
    
    // AWT Components
    private TextField nameField;
    private TextField marksField;
    private Button addButton;
    private Button showReportButton;
    private Button clearButton;
    private TextArea reportArea;
    private Label statusLabel;
    
    // Data storage
    private ArrayList<Student> students;
    
    // Initialize the applet
    public void init() {
        students = new ArrayList<>();
        setupUI();
        setupEventHandlers();
    }
    
    private void setupUI() {
        // Set layout
        setLayout(new BorderLayout());
        setBackground(Color.LIGHT_GRAY);
        
        // Create input panel
        Panel inputPanel = new Panel(new GridLayout(4, 2, 10, 10));
        inputPanel.setBackground(Color.WHITE);
        
        // Add components to input panel
        inputPanel.add(new Label("Student Name:"));
        nameField = new TextField(20);
        inputPanel.add(nameField);
        
        inputPanel.add(new Label("Marks (0-100):"));
        marksField = new TextField(20);
        inputPanel.add(marksField);
        
        // Buttons
        addButton = new Button("Add Student");
        addButton.setBackground(Color.CYAN);
        inputPanel.add(addButton);
        
        showReportButton = new Button("Show Report");
        showReportButton.setBackground(Color.YELLOW);
        inputPanel.add(showReportButton);
        
        clearButton = new Button("Clear All");
        clearButton.setBackground(Color.PINK);
        inputPanel.add(clearButton);
        
        // Status label
        statusLabel = new Label("Ready to add students...");
        statusLabel.setForeground(Color.BLUE);
        inputPanel.add(statusLabel);
        
        // Report area
        reportArea = new TextArea(15, 50);
        reportArea.setEditable(false);
        reportArea.setBackground(Color.WHITE);
        reportArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        
        // Add panels to main layout
        add(inputPanel, BorderLayout.NORTH);
        add(new ScrollPane(reportArea), BorderLayout.CENTER);
        
        // Title
        Label titleLabel = new Label("Student Grade Tracker", Label.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setForeground(Color.DARK_GRAY);
        add(titleLabel, BorderLayout.SOUTH);
    }
    
    private void setupEventHandlers() {
        addButton.addActionListener(this);
        showReportButton.addActionListener(this);
        clearButton.addActionListener(this);
        
        // Add key listeners for Enter key
        nameField.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    marksField.requestFocus();
                }
            }
        });
        
        marksField.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    addStudent();
                }
            }
        });
    }
    
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            addStudent();
        } else if (e.getSource() == showReportButton) {
            showReport();
        } else if (e.getSource() == clearButton) {
            clearAll();
        }
    }
    
    private void addStudent() {
        String name = nameField.getText().trim();
        String marksText = marksField.getText().trim();
        
        // Validate input
        if (name.isEmpty()) {
            statusLabel.setText("Error: Please enter student name");
            statusLabel.setForeground(Color.RED);
            nameField.requestFocus();
            return;
        }
        
        if (marksText.isEmpty()) {
            statusLabel.setText("Error: Please enter marks");
            statusLabel.setForeground(Color.RED);
            marksField.requestFocus();
            return;
        }
        
        try {
            int marks = Integer.parseInt(marksText);
            
            // Validate marks range
            if (marks < 0 || marks > 100) {
                statusLabel.setText("Error: Marks must be between 0 and 100");
                statusLabel.setForeground(Color.RED);
                marksField.selectAll();
                marksField.requestFocus();
                return;
            }
            
            // Add student
            Student student = new Student(name, marks);
            students.add(student);
            
            // Update status
            statusLabel.setText("Student added successfully! Total students: " + students.size());
            statusLabel.setForeground(Color.GREEN);
            
            // Clear input fields
            nameField.setText("");
            marksField.setText("");
            nameField.requestFocus();
            
        } catch (NumberFormatException ex) {
            statusLabel.setText("Error: Invalid marks format. Please enter a number");
            statusLabel.setForeground(Color.RED);
            marksField.selectAll();
            marksField.requestFocus();
        }
    }
    
    private void showReport() {
        if (students.isEmpty()) {
            reportArea.setText("No students added yet. Please add some students first.");
            statusLabel.setText("No data to display");
            statusLabel.setForeground(Color.ORANGE);
            return;
        }
        
        StringBuilder report = new StringBuilder();
        report.append("=".repeat(60)).append("\n");
        report.append("           STUDENT GRADE REPORT\n");
        report.append("=".repeat(60)).append("\n\n");
        
        // Display all students
        report.append("STUDENT LIST:\n");
        report.append("-".repeat(40)).append("\n");
        for (int i = 0; i < students.size(); i++) {
            Student student = students.get(i);
            report.append(String.format("%2d. %-20s %3d marks", 
                         i + 1, student.getName(), student.getMarks()));
            
            // Add grade
            String grade = getGrade(student.getMarks());
            report.append(String.format(" (%s)\n", grade));
        }
        
        // Calculate statistics
        int total = 0;
        int highest = Integer.MIN_VALUE;
        int lowest = Integer.MAX_VALUE;
        String topStudent = "";
        String bottomStudent = "";
        
        for (Student student : students) {
            int marks = student.getMarks();
            total += marks;
            
            if (marks > highest) {
                highest = marks;
                topStudent = student.getName();
            }
            
            if (marks < lowest) {
                lowest = marks;
                bottomStudent = student.getName();
            }
        }
        
        double average = (double) total / students.size();
        
        // Add statistics
        report.append("\n").append("=".repeat(60)).append("\n");
        report.append("STATISTICS:\n");
        report.append("-".repeat(40)).append("\n");
        report.append(String.format("Total Students: %d\n", students.size()));
        report.append(String.format("Average Score: %.2f\n", average));
        report.append(String.format("Highest Score: %d (%s)\n", highest, topStudent));
        report.append(String.format("Lowest Score: %d (%s)\n", lowest, bottomStudent));
        
        // Add grade distribution
        report.append("\nGRADE DISTRIBUTION:\n");
        report.append("-".repeat(40)).append("\n");
        int[] gradeCount = new int[5]; // A, B, C, D, F
        
        for (Student student : students) {
            int marks = student.getMarks();
            if (marks >= 90) gradeCount[0]++;
            else if (marks >= 80) gradeCount[1]++;
            else if (marks >= 70) gradeCount[2]++;
            else if (marks >= 60) gradeCount[3]++;
            else gradeCount[4]++;
        }
        
        String[] grades = {"A (90-100)", "B (80-89)", "C (70-79)", "D (60-69)", "F (0-59)"};
        for (int i = 0; i < grades.length; i++) {
            report.append(String.format("%-12s: %d students\n", grades[i], gradeCount[i]));
        }
        
        report.append("\n").append("=".repeat(60));
        
        reportArea.setText(report.toString());
        statusLabel.setText("Report generated successfully!");
        statusLabel.setForeground(Color.BLUE);
    }
    
    private String getGrade(int marks) {
        if (marks >= 90) return "A";
        else if (marks >= 80) return "B";
        else if (marks >= 70) return "C";
        else if (marks >= 60) return "D";
        else return "F";
    }
    
    private void clearAll() {
        students.clear();
        reportArea.setText("");
        nameField.setText("");
        marksField.setText("");
        statusLabel.setText("All data cleared. Ready to add students...");
        statusLabel.setForeground(Color.BLUE);
        nameField.requestFocus();
    }
}