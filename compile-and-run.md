# How to Run the Student Grade Tracker Applet

## Method 1: Using Appletviewer (Recommended)

The `appletviewer` tool comes with the Java Development Kit (JDK) and is the best way to run Java applets.

### Steps:
1. **Compile the Java files:**
   ```bash
   javac *.java
   ```

2. **Run with appletviewer:**
   ```bash
   appletviewer AppletRunner.html
   ```

## Method 2: Convert to Standalone Application

Since applets are deprecated, here's how to convert it to a regular Java application:

### Create a main class wrapper:
```java
import javax.swing.*;
import java.awt.*;

public class StudentGradeTrackerApp {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Student Grade Tracker");
        StudentGradeTrackerApplet applet = new StudentGradeTrackerApplet();
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 500);
        frame.setLocationRelativeTo(null);
        
        applet.init();
        frame.add(applet);
        
        frame.setVisible(true);
    }
}
```

Then compile and run:
```bash
javac *.java
java StudentGradeTrackerApp
```

## Method 3: Online Java Compilers

You can also use online Java compilers like:
- **Replit**: Copy the code and run online
- **CodePen**: For Java applets (limited support)
- **JDoodle**: Online Java compiler

## Method 4: IDE Setup

### IntelliJ IDEA:
1. Create new Java project
2. Copy the `.java` files to `src` folder
3. Right-click on `StudentGradeTrackerApplet.java`
4. Select "Run"

### Eclipse:
1. Create new Java project
2. Copy files to `src` folder
3. Right-click project → Run As → Java Applet

## Troubleshooting

### Common Issues:

1. **"javac not found"**
   - Install JDK (Java Development Kit)
   - Add JDK bin folder to PATH

2. **"appletviewer not found"**
   - Ensure you have JDK (not just JRE)
   - Check PATH includes JDK bin directory

3. **Security warnings**
   - Appletviewer may show security warnings
   - Click "Allow" or "Run" to proceed

4. **Modern browser issues**
   - Most browsers no longer support Java applets
   - Use appletviewer or convert to standalone app

## System Requirements

- **Java JDK 8 or higher** (for appletviewer)
- **Operating System**: Windows, macOS, or Linux
- **Memory**: Minimal requirements (applet is lightweight)

## Quick Test Commands

```bash
# Check if Java is installed
java -version

# Check if javac is available
javac -version

# Check if appletviewer is available
appletviewer -help
```

## Alternative: Modern Java Application

For a more modern approach, consider converting to:
- **JavaFX application** (modern UI toolkit)
- **Swing application** (more widely supported)
- **Web application** using Spring Boot + Thymeleaf