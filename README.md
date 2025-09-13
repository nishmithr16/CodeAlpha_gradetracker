# Student Grade Tracker Applet

A Java applet application for tracking student grades using AWT components.

## Features

- **Student Management**: Add students with names and marks (0-100)
- **Input Validation**: Prevents invalid data entry
- **Grade Calculation**: Automatic grade assignment (A, B, C, D, F)
- **Comprehensive Reports**: Shows all students, statistics, and grade distribution
- **Clean UI**: Professional AWT-based interface with proper layouts

## Components

### Student Class
- Private fields: name (String), marks (int)
- Constructor and getter methods
- String representation for easy display

### Main Applet Features
- TextField for student name entry
- TextField for marks entry (with validation)
- "Add Student" button to store data
- "Show Report" button for comprehensive reporting
- "Clear All" button to reset data
- TextArea for displaying detailed reports
- Status label for user feedback

### Report Features
- Complete student list with grades
- Average, highest, and lowest scores
- Top and bottom performing students
- Grade distribution statistics
- Professional formatting

## How to Compile and Run

### Method 1: Command Line
```bash
# Compile the Java files
javac *.java

# Open AppletRunner.html in a browser with Java plugin support
# Note: Modern browsers have deprecated Java applets
```

### Method 2: Appletviewer (if available)
```bash
# Compile first
javac *.java

# Run with appletviewer
appletviewer AppletRunner.html
```

## Usage Instructions

1. **Adding Students**:
   - Enter student name in the first field
   - Enter marks (0-100) in the second field
   - Click "Add Student" or press Enter
   - Status will confirm successful addition

2. **Viewing Reports**:
   - Click "Show Report" to generate comprehensive report
   - Report includes individual grades and class statistics

3. **Clearing Data**:
   - Click "Clear All" to reset all student data

## Input Validation

- Student name cannot be empty
- Marks must be numeric
- Marks must be between 0-100
- Clear error messages for invalid inputs

## Grade Scale

- A: 90-100 marks
- B: 80-89 marks  
- C: 70-79 marks
- D: 60-69 marks
- F: 0-59 marks

## Layout Design

The applet uses a clean BorderLayout with:
- Input panel at the top (GridLayout)
- Report area in the center (ScrollPane with TextArea)
- Title at the bottom
- Proper color scheme and spacing

## Note

Java applets are deprecated technology. This program is designed for educational purposes to demonstrate:
- AWT component usage
- Event handling in Java
- Object-oriented design principles
- Input validation techniques
- Data structure management (ArrayList)

For modern applications, consider using JavaFX, Swing, or web technologies instead.