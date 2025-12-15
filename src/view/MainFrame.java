package view;

import controller.DataManager;
import model.*;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

/**
 * MainFrame - Main GUI Window (View in MVC).
 * Provides comprehensive interface for healthcare system.
 * 
 * @author Shubhamkumar Prahladbhai Patel
 * @version 1.0
 */
public class MainFrame extends JFrame {
    private DataManager dataManager;
    private JTabbedPane tabbedPane;
    
    // Table models for displaying data
    private DefaultTableModel patientTableModel;
    private DefaultTableModel appointmentTableModel;
    private DefaultTableModel prescriptionTableModel;
    private DefaultTableModel referralTableModel;
    
    // Tables
    private JTable patientTable;
    private JTable appointmentTable;
    private JTable prescriptionTable;
    private JTable referralTable;
    
    public MainFrame(DataManager dataManager) {
        this.dataManager = dataManager;
        
        setTitle("Healthcare Management System - Shubhamkumar Patel");
        setSize(1200, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        initializeComponents();
        loadData();
    }

    private void autoSave() {
        if (dataManager.saveAllData()) {
            System.out.println("Data auto-saved successfully");
        } else {
            System.err.println("Warning: Auto-save failed");
        }
    }

    private void initializeComponents() {
        // Create tabbed pane
        tabbedPane = new JTabbedPane();
        
        // Add tabs
        tabbedPane.addTab("Patients", createPatientPanel());
        tabbedPane.addTab("Appointments", createAppointmentPanel());
        tabbedPane.addTab("Prescriptions", createPrescriptionPanel());
        tabbedPane.addTab("Referrals", createReferralPanel());
        tabbedPane.addTab("About", createAboutPanel());
        
        add(tabbedPane, BorderLayout.CENTER);
        
        // Menu bar
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem saveItem = new JMenuItem("Save All Data");
        saveItem.addActionListener(e -> saveData());
        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(e -> System.exit(0));
        fileMenu.add(saveItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);
        menuBar.add(fileMenu);
        setJMenuBar(menuBar);
    }
    
    // PATIENT PANEL
    private JPanel createPatientPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        
        // Table
        String[] columns = {"ID", "First Name", "Last Name", "DOB", "NHS Number", "Gender", "Email"};
        patientTableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        patientTable = new JTable(patientTableModel);
        JScrollPane scrollPane = new JScrollPane(patientTable);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton addBtn = new JButton("Add Patient");
        JButton editBtn = new JButton("Edit Patient");
        JButton deleteBtn = new JButton("Delete Patient");
        JButton refreshBtn = new JButton("Refresh");
        
        addBtn.addActionListener(e -> addPatient());
        editBtn.addActionListener(e -> editPatient());
        deleteBtn.addActionListener(e -> deletePatient());
        refreshBtn.addActionListener(e -> loadPatients());
        
        buttonPanel.add(addBtn);
        buttonPanel.add(editBtn);
        buttonPanel.add(deleteBtn);
        buttonPanel.add(refreshBtn);
        panel.add(buttonPanel, BorderLayout.SOUTH);
        
        return panel;
    }
    
    // APPOINTMENT PANEL
    private JPanel createAppointmentPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        
        String[] columns = {"ID", "Patient ID", "Clinician ID", "Date", "Time", "Type", "Status"};
        appointmentTableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        appointmentTable = new JTable(appointmentTableModel);
        JScrollPane scrollPane = new JScrollPane(appointmentTable);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton addBtn = new JButton("Add Appointment");
        JButton editBtn = new JButton("Edit Appointment");
        JButton deleteBtn = new JButton("Delete Appointment");
        JButton refreshBtn = new JButton("Refresh");
        
        addBtn.addActionListener(e -> addAppointment());
        editBtn.addActionListener(e -> editAppointment());
        deleteBtn.addActionListener(e -> deleteAppointment());
        refreshBtn.addActionListener(e -> loadAppointments());
        
        buttonPanel.add(addBtn);
        buttonPanel.add(editBtn);
        buttonPanel.add(deleteBtn);
        buttonPanel.add(refreshBtn);
        panel.add(buttonPanel, BorderLayout.SOUTH);
        
        return panel;
    }
    
    // PRESCRIPTION PANEL
    private JPanel createPrescriptionPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        
        String[] columns = {"ID", "Patient ID", "Clinician ID", "Medication", "Dosage", "Status"};
        prescriptionTableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        prescriptionTable = new JTable(prescriptionTableModel);
        JScrollPane scrollPane = new JScrollPane(prescriptionTable);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton addBtn = new JButton("Add Prescription");
        JButton editBtn = new JButton("Edit Prescription");
        JButton deleteBtn = new JButton("Delete Prescription");
        JButton refreshBtn = new JButton("Refresh");
        
        addBtn.addActionListener(e -> addPrescription());
        editBtn.addActionListener(e -> editPrescription());
        deleteBtn.addActionListener(e -> deletePrescription());
        refreshBtn.addActionListener(e -> loadPrescriptions());
        
        buttonPanel.add(addBtn);
        buttonPanel.add(editBtn);
        buttonPanel.add(deleteBtn);
        buttonPanel.add(refreshBtn);
        panel.add(buttonPanel, BorderLayout.SOUTH);
        
        return panel;
    }
    
    // REFERRAL PANEL
    private JPanel createReferralPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        
        String[] columns = {"ID", "Patient ID", "From Clinician", "To Clinician", "Urgency", "Status"};
        referralTableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        referralTable = new JTable(referralTableModel);
        JScrollPane scrollPane = new JScrollPane(referralTable);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton addBtn = new JButton("Add Referral");
        JButton sendBtn = new JButton("Send Referral");
        JButton refreshBtn = new JButton("Refresh");
        
        addBtn.addActionListener(e -> addReferral());
        sendBtn.addActionListener(e -> sendReferral());
        refreshBtn.addActionListener(e -> loadReferrals());
        
        buttonPanel.add(addBtn);
        buttonPanel.add(sendBtn);
        buttonPanel.add(refreshBtn);
        panel.add(buttonPanel, BorderLayout.SOUTH);
        
        return panel;
    }
    
    // ABOUT PANEL
    private JPanel createAboutPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
        
        JLabel title = new JLabel("Healthcare Management System");
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel author = new JLabel("Author: Shubhamkumar Prahladbhai Patel");
        author.setFont(new Font("Arial", Font.PLAIN, 16));
        author.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel architecture = new JLabel("Architecture: Model-View-Controller (MVC)");
        architecture.setFont(new Font("Arial", Font.PLAIN, 14));
        architecture.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel pattern = new JLabel("Design Pattern: Singleton (ReferralManager)");
        pattern.setFont(new Font("Arial", Font.PLAIN, 14));
        pattern.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel version = new JLabel("Version: 1.0");
        version.setFont(new Font("Arial", Font.PLAIN, 14));
        version.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        panel.add(title);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(author);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(architecture);
        panel.add(Box.createRigidArea(new Dimension(0, 5)));
        panel.add(pattern);
        panel.add(Box.createRigidArea(new Dimension(0, 5)));
        panel.add(version);
        
        return panel;
    }
    
    // LOAD DATA METHODS
    private void loadData() {
        loadPatients();
        loadAppointments();
        loadPrescriptions();
        loadReferrals();
    }
    
    private void loadPatients() {
        patientTableModel.setRowCount(0);
        List<Patient> patients = dataManager.getAllPatients();
        for (Patient p : patients) {
            patientTableModel.addRow(new Object[]{
                p.getUserId(),
                p.getFirstName(),
                p.getLastName(),
                p.getDateOfBirth(),
                p.getNhsNumber(),
                p.getGender(),
                p.getEmail()
            });
        }
    }
    
    private void loadAppointments() {
        appointmentTableModel.setRowCount(0);
        List<Appointment> appointments = dataManager.getAllAppointments();
        for (Appointment a : appointments) {
            appointmentTableModel.addRow(new Object[]{
                a.getAppointmentId(),
                a.getPatientId(),
                a.getClinicianId(),
                a.getAppointmentDate(),
                a.getAppointmentTime(),
                a.getAppointmentType(),
                a.getStatus()
            });
        }
    }
    
    private void loadPrescriptions() {
        prescriptionTableModel.setRowCount(0);
        List<Prescription> prescriptions = dataManager.getAllPrescriptions();
        for (Prescription p : prescriptions) {
            prescriptionTableModel.addRow(new Object[]{
                p.getPrescriptionId(),
                p.getPatientId(),
                p.getClinicianId(),
                p.getMedicationName(),
                p.getDosage(),
                p.getStatus()
            });
        }
    }
    
    private void loadReferrals() {
        referralTableModel.setRowCount(0);
        List<Referral> referrals = dataManager.getAllReferrals();
        for (Referral r : referrals) {
            referralTableModel.addRow(new Object[]{
                r.getReferralId(),
                r.getPatientId(),
                r.getReferringClinicianId(),
                r.getReferredToClinicianId(),
                r.getUrgencyLevel(),
                r.getStatus()
            });
        }
    }


    // PATIENT CRUD
    private void addPatient() {
        PatientDialog dialog = new PatientDialog(this, null, dataManager);
        dialog.setVisible(true);
        if (dialog.isSaved()) {
            loadPatients();
            autoSave();
        }
    }

    private void editPatient() {
        int selectedRow = patientTable.getSelectedRow();
        if (selectedRow >= 0) {
            String id = (String) patientTableModel.getValueAt(selectedRow, 0);
            Patient patient = dataManager.findPatientById(id);
            PatientDialog dialog = new PatientDialog(this, patient, dataManager);
            dialog.setVisible(true);
            if (dialog.isSaved()) {
                loadPatients();
                autoSave();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a patient to edit");
        }
    }

    private void deletePatient() {
        int selectedRow = patientTable.getSelectedRow();
        if (selectedRow >= 0) {
            String id = (String) patientTableModel.getValueAt(selectedRow, 0);
            int confirm = JOptionPane.showConfirmDialog(this,
                    "Are you sure you want to delete this patient?",
                    "Confirm Delete", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                dataManager.deletePatient(id);
                loadPatients();
                autoSave();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a patient to delete");
        }
    }

    // APPOINTMENT CRUD
    private void addAppointment() {
        AppointmentDialog dialog = new AppointmentDialog(this, null, dataManager);
        dialog.setVisible(true);
        if (dialog.isSaved()) {
            loadAppointments();
            autoSave();
        }
    }

    private void editAppointment() {
        int selectedRow = appointmentTable.getSelectedRow();
        if (selectedRow >= 0) {
            String id = (String) appointmentTableModel.getValueAt(selectedRow, 0);
            Appointment appointment = dataManager.findAppointmentById(id);
            AppointmentDialog dialog = new AppointmentDialog(this, appointment, dataManager);
            dialog.setVisible(true);
            if (dialog.isSaved()) {
                loadAppointments();
                autoSave();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select an appointment to edit");
        }
    }

    private void deleteAppointment() {
        int selectedRow = appointmentTable.getSelectedRow();
        if (selectedRow >= 0) {
            String id = (String) appointmentTableModel.getValueAt(selectedRow, 0);
            int confirm = JOptionPane.showConfirmDialog(this,
                    "Are you sure you want to delete this appointment?",
                    "Confirm Delete", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                dataManager.deleteAppointment(id);
                loadAppointments();
                autoSave();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select an appointment to delete");
        }
    }

    // PRESCRIPTION CRUD
    private void addPrescription() {
        PrescriptionDialog dialog = new PrescriptionDialog(this, null, dataManager);
        dialog.setVisible(true);
        if (dialog.isSaved()) {
            loadPrescriptions();
            autoSave();
        }
    }

    private void editPrescription() {
        int selectedRow = prescriptionTable.getSelectedRow();
        if (selectedRow >= 0) {
            String id = (String) prescriptionTableModel.getValueAt(selectedRow, 0);
            Prescription prescription = dataManager.findPrescriptionById(id);
            PrescriptionDialog dialog = new PrescriptionDialog(this, prescription, dataManager);
            dialog.setVisible(true);
            if (dialog.isSaved()) {
                loadPrescriptions();
                autoSave();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a prescription to edit");
        }
    }

    private void deletePrescription() {
        int selectedRow = prescriptionTable.getSelectedRow();
        if (selectedRow >= 0) {
            String id = (String) prescriptionTableModel.getValueAt(selectedRow, 0);
            int confirm = JOptionPane.showConfirmDialog(this,
                    "Are you sure you want to delete this prescription?",
                    "Confirm Delete", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                dataManager.deletePrescription(id);
                loadPrescriptions();
                autoSave();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a prescription to delete");
        }
    }

    // REFERRAL CRUD
    private void addReferral() {
        ReferralDialog dialog = new ReferralDialog(this, null, dataManager);
        dialog.setVisible(true);
        if (dialog.isSaved()) {
            loadReferrals();
            autoSave();
        }
    }
    
    private void sendReferral() {
        int selectedRow = referralTable.getSelectedRow();
        if (selectedRow >= 0) {
            String id = (String) referralTableModel.getValueAt(selectedRow, 0);
            Referral referral = dataManager.getAllReferrals().stream()
                .filter(r -> r.getReferralId().equals(id))
                .findFirst().orElse(null);
            
            if (referral != null) {
                dataManager.sendReferral(referral);
                JOptionPane.showMessageDialog(this, 
                    "Referral sent successfully! Check output folder for details.");
                loadReferrals();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a referral to send");
        }
    }
    
    private void saveData() {
        if (dataManager.saveAllData()) {
            JOptionPane.showMessageDialog(this, "Data saved successfully!");
        } else {
            JOptionPane.showMessageDialog(this, "Error saving data!");
        }
    }
}
