import controller.DataManager;
import view.MainFrame;
import javax.swing.*;

/**
 * Main Application Entry Point.
 * Healthcare Management System with MVC Architecture.
 * 
 * @author Shubhamkumar Prahladbhai Patel
 * @version 1.0
 */
public class HealthcareManagementSystem {
    public static void main(String[] args) {
        System.out.println("=".repeat(70));
        System.out.println("HEALTHCARE MANAGEMENT SYSTEM");
        System.out.println("Author: Shubhamkumar Prahladbhai Patel");
        System.out.println("Architecture: Model-View-Controller (MVC)");
        System.out.println("Design Pattern: Singleton (ReferralManager)");
        System.out.println("=".repeat(70));
        
        // Initialize MVC components
        DataManager dataManager = new DataManager("data");
        
        // Launch GUI on Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            MainFrame mainFrame = new MainFrame(dataManager);
            mainFrame.setVisible(true);
        });
    }
}
