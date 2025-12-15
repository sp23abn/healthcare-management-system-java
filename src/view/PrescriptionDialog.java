package view;

import controller.DataManager;
import model.Prescription;
import javax.swing.*;
import java.awt.*;

public class PrescriptionDialog extends JDialog {
    private DataManager dataManager;
    private Prescription prescription;
    private boolean saved = false;
    private JTextField idField;
    private JTextField patientIdField;
    private JTextField clinicianIdField;
    private JTextField medicationField;
    private JTextField dosageField;
    private JTextField frequencyField;
    private JTextField durationField;
    private JTextField quantityField;
    private JTextField instructionsField;
    private JTextField pharmacyField;

    public PrescriptionDialog(Frame parent, Prescription prescription, DataManager dataManager) {
        super(parent, prescription == null ? "Add Prescription" : "Edit Prescription", true);
        this.dataManager = dataManager;
        this.prescription = prescription;

        setSize(500, 500);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());

        JPanel formPanel = new JPanel(new GridLayout(10, 2, 5, 5));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Initialize fields
        idField = new JTextField();
        patientIdField = new JTextField();
        clinicianIdField = new JTextField();
        medicationField = new JTextField();
        dosageField = new JTextField();
        frequencyField = new JTextField();
        durationField = new JTextField();
        quantityField = new JTextField();
        instructionsField = new JTextField();
        pharmacyField = new JTextField();

        // Add labels and fields
        formPanel.add(new JLabel("Prescription ID:"));
        formPanel.add(idField);
        formPanel.add(new JLabel("Patient ID:"));
        formPanel.add(patientIdField);
        formPanel.add(new JLabel("Clinician ID:"));
        formPanel.add(clinicianIdField);
        formPanel.add(new JLabel("Medication:"));
        formPanel.add(medicationField);
        formPanel.add(new JLabel("Dosage:"));
        formPanel.add(dosageField);
        formPanel.add(new JLabel("Frequency:"));
        formPanel.add(frequencyField);
        formPanel.add(new JLabel("Duration (days):"));
        formPanel.add(durationField);
        formPanel.add(new JLabel("Quantity:"));
        formPanel.add(quantityField);
        formPanel.add(new JLabel("Instructions:"));
        formPanel.add(instructionsField);
        formPanel.add(new JLabel("Pharmacy:"));
        formPanel.add(pharmacyField);

        // If editing, populate fields
        if (prescription != null) {
            idField.setText(prescription.getPrescriptionId());
            idField.setEditable(false);
            patientIdField.setText(prescription.getPatientId());
            clinicianIdField.setText(prescription.getClinicianId());
            medicationField.setText(prescription.getMedicationName());
            dosageField.setText(prescription.getDosage());
            frequencyField.setText(prescription.getFrequency());
            durationField.setText(String.valueOf(prescription.getDurationDays()));
            quantityField.setText(String.valueOf(prescription.getQuantity()));
            instructionsField.setText(prescription.getInstructions());
            pharmacyField.setText(prescription.getPharmacyName());
        }

        JPanel buttonPanel = new JPanel();
        JButton saveBtn = new JButton("Save");
        JButton cancelBtn = new JButton("Cancel");
        saveBtn.addActionListener(e -> save());
        cancelBtn.addActionListener(e -> dispose());
        buttonPanel.add(saveBtn);
        buttonPanel.add(cancelBtn);

        add(formPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void save() {
        try {
            String id = idField.getText().trim();
            if (id.isEmpty()) {
                id = dataManager.generateNewPrescriptionId();
            }

            int duration = 7;
            int quantity = 10;
            try {
                duration = Integer.parseInt(durationField.getText().trim());
                quantity = Integer.parseInt(quantityField.getText().trim());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this,
                        "Duration and Quantity must be numbers",
                        "Invalid Input",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            String today = java.time.LocalDate.now().toString();

            if (prescription == null) {
                // Creating new prescription
                prescription = new Prescription(
                        id,
                        patientIdField.getText().trim(),
                        clinicianIdField.getText().trim(),
                        "", // appointmentId
                        today, // prescriptionDate
                        medicationField.getText().trim(),
                        dosageField.getText().trim(),
                        frequencyField.getText().trim(),
                        duration,
                        quantity,
                        instructionsField.getText().trim(),
                        pharmacyField.getText().trim(),
                        "Issued",
                        today, // issueDate
                        "" // collectionDate
                );
                dataManager.addPrescription(prescription);
                JOptionPane.showMessageDialog(this,
                        "Prescription created successfully!",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                // Editing existing prescription
                prescription.setPatientId(patientIdField.getText().trim());
                prescription.setClinicianId(clinicianIdField.getText().trim());
                prescription.setMedicationName(medicationField.getText().trim());
                prescription.setDosage(dosageField.getText().trim());
                prescription.setFrequency(frequencyField.getText().trim());
                prescription.setDurationDays(duration);
                prescription.setQuantity(quantity);
                prescription.setInstructions(instructionsField.getText().trim());
                prescription.setPharmacyName(pharmacyField.getText().trim());

                dataManager.updatePrescription(prescription.getPrescriptionId(), prescription);
                JOptionPane.showMessageDialog(this,
                        "Prescription updated successfully!",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE);
            }

            saved = true;
            dispose();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Error saving prescription: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    public boolean isSaved() {
        return saved;
    }
}