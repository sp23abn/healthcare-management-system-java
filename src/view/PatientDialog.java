package view;

import controller.DataManager;
import model.Patient;
import javax.swing.*;
import java.awt.*;

public class PatientDialog extends JDialog {
    private DataManager dataManager;
    private Patient patient;
    private boolean saved = false;
    
    private JTextField idField, firstNameField, lastNameField, dobField;
    private JTextField nhsField, genderField, phoneField, emailField;
    private JTextField addressField, postcodeField, emergencyNameField, emergencyPhoneField;
    
    public PatientDialog(Frame parent, Patient patient, DataManager dataManager) {
        super(parent, patient == null ? "Add Patient" : "Edit Patient", true);
        this.dataManager = dataManager;
        this.patient = patient;
        
        setSize(500, 600);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());
        
        JPanel formPanel = new JPanel(new GridLayout(13, 2, 5, 5));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        idField = new JTextField();
        firstNameField = new JTextField();
        lastNameField = new JTextField();
        dobField = new JTextField();
        nhsField = new JTextField();
        genderField = new JTextField();
        phoneField = new JTextField();
        emailField = new JTextField();
        addressField = new JTextField();
        postcodeField = new JTextField();
        emergencyNameField = new JTextField();
        emergencyPhoneField = new JTextField();
        
        if (patient != null) {
            idField.setText(patient.getUserId());
            idField.setEditable(false);
            firstNameField.setText(patient.getFirstName());
            lastNameField.setText(patient.getLastName());
            dobField.setText(patient.getDateOfBirth());
            nhsField.setText(patient.getNhsNumber());
            genderField.setText(patient.getGender());
            phoneField.setText(patient.getContactInfo());
            emailField.setText(patient.getEmail());
            addressField.setText(patient.getAddress());
            postcodeField.setText(patient.getPostcode());
            emergencyNameField.setText(patient.getEmergencyContactName());
            emergencyPhoneField.setText(patient.getEmergencyContactPhone());
        }
        
        formPanel.add(new JLabel("Patient ID:"));
        formPanel.add(idField);
        formPanel.add(new JLabel("First Name:"));
        formPanel.add(firstNameField);
        formPanel.add(new JLabel("Last Name:"));
        formPanel.add(lastNameField);
        formPanel.add(new JLabel("Date of Birth (YYYY-MM-DD):"));
        formPanel.add(dobField);
        formPanel.add(new JLabel("NHS Number:"));
        formPanel.add(nhsField);
        formPanel.add(new JLabel("Gender (M/F):"));
        formPanel.add(genderField);
        formPanel.add(new JLabel("Phone:"));
        formPanel.add(phoneField);
        formPanel.add(new JLabel("Email:"));
        formPanel.add(emailField);
        formPanel.add(new JLabel("Address:"));
        formPanel.add(addressField);
        formPanel.add(new JLabel("Postcode:"));
        formPanel.add(postcodeField);
        formPanel.add(new JLabel("Emergency Contact Name:"));
        formPanel.add(emergencyNameField);
        formPanel.add(new JLabel("Emergency Contact Phone:"));
        formPanel.add(emergencyPhoneField);
        
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
        if (patient == null) {
            String id = idField.getText().trim();
            if (id.isEmpty()) {
                id = dataManager.generateNewPatientId();
            }
            patient = new Patient(id, firstNameField.getText(), lastNameField.getText(),
                dobField.getText(), nhsField.getText(), genderField.getText(),
                phoneField.getText(), emailField.getText(), addressField.getText(),
                postcodeField.getText(), emergencyNameField.getText(),
                emergencyPhoneField.getText(), java.time.LocalDate.now().toString(), "S001");
            dataManager.addPatient(patient);
        } else {
            patient.setFirstName(firstNameField.getText());
            patient.setLastName(lastNameField.getText());
            patient.setDateOfBirth(dobField.getText());
            patient.setNhsNumber(nhsField.getText());
            patient.setGender(genderField.getText());
            patient.setContactInfo(phoneField.getText());
            patient.setEmail(emailField.getText());
            patient.setAddress(addressField.getText());
            patient.setPostcode(postcodeField.getText());
            patient.setEmergencyContactName(emergencyNameField.getText());
            patient.setEmergencyContactPhone(emergencyPhoneField.getText());
            dataManager.updatePatient(patient.getUserId(), patient);
        }
        saved = true;
        dispose();
    }
    
    public boolean isSaved() { return saved; }
}
