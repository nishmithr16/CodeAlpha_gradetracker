import javax.swing.*;
import java.awt.*;

/**
 * Standalone application wrapper for the Student Grade Tracker Applet
 * This allows running the applet as a regular Java application
 */
public class StudentGradeTrackerApp {
    public static void main(String[] args) {
        // Set system look and feel for better appearance
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeel());
        } catch (Exception e) {
            // Use default look and feel if system L&F is not available
        }
        
        // Create the main frame
        JFrame frame = new JFrame("Student Grade Tracker");
        
        // Create and initialize the applet
        StudentGradeTrackerApplet applet = new StudentGradeTrackerApplet();
        
        // Configure the frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 500);
        frame.setLocationRelativeTo(null); // Center on screen
        frame.setResizable(true);
        
        // Initialize the applet (this calls the init() method)
        applet.init();
        
        // Add the applet to the frame
        frame.add(applet);
        
        // Make the frame visible
        frame.setVisible(true);
        
        System.out.println("Student Grade Tracker Application Started");
        System.out.println("Close the window to exit the application");
    }
}