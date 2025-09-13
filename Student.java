/**
 * Student class to represent a student with name and marks
 */
public class Student {
    private String name;
    private int marks;
    
    // Constructor
    public Student(String name, int marks) {
        this.name = name;
        this.marks = marks;
    }
    
    // Getter methods
    public String getName() {
        return name;
    }
    
    public int getMarks() {
        return marks;
    }
    
    // Override toString for easy display
    @Override
    public String toString() {
        return name + " - " + marks + " marks";
    }
}