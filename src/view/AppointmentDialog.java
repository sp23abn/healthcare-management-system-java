package view;

import controller.DataManager;
import model.Appointment;
import javax.swing.*;
import java.awt.*;

public class AppointmentDialog extends JDialog {
    private DataManager dataManager;
    private Appointment appointment;
    private boolean saved = false;
    private JTextField[] fields = new JTextField[10];
    
    public AppointmentDialog(Frame parent, Appointment appointment, DataManager dataManager) {
        super(parent, appointment == null ? "Add Appointment" : "Edit Appointment", true);
        this.dataManager = dataManager;
        this.appointment = appointment;
        
        setSize(500, 500);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());
        
        JPanel formPanel = new JPanel(new GridLayout(10, 2, 5, 5));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        String[] labels = {"ID:", "Patient ID:", "Clinician ID:", "Facility ID:",
            "Date (YYYY-MM-DD):", "Time (HH:MM):", "Duration (mins):", "Type:", "Status:", "Reason:"};
        
        for (int i = 0; i < 10; i++) {
            fields[i] = new JTextField();
            formPanel.add(new JLabel(labels[i]));
            formPanel.add(fields[i]);
        }
        
        if (appointment != null) {
            fields[0].setText(appointment.getAppointmentId());
            fields[0].setEditable(false);
            fields[1].setText(appointment.getPatientId());
            fields[2].setText(appointment.getClinicianId());
            fields[3].setText(appointment.getFacilityId());
            fields[4].setText(appointment.getAppointmentDate());
            fields[5].setText(appointment.getAppointmentTime());
            fields[6].setText(String.valueOf(appointment.getDurationMinutes()));
            fields[7].setText(appointment.getAppointmentType());
            fields[8].setText(appointment.getStatus());
            fields[9].setText(appointment.getReasonForVisit());
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
        String id = fields[0].getText().trim();
        if (id.isEmpty()) id = dataManager.generateNewAppointmentId();
        
        int duration = 15;
        try { duration = Integer.parseInt(fields[6].getText()); } catch (Exception e) {}
        
        String today = java.time.LocalDate.now().toString();
        
        if (appointment == null) {
            appointment = new Appointment(id, fields[1].getText(), fields[2].getText(),
                fields[3].getText(), fields[4].getText(), fields[5].getText(),
                duration, fields[7].getText(), fields[8].getText(), fields[9].getText(),
                "", today, today);
            dataManager.addAppointment(appointment);
        } else {
            appointment.setPatientId(fields[1].getText());
            appointment.setClinicianId(fields[2].getText());
            appointment.setFacilityId(fields[3].getText());
            appointment.setAppointmentDate(fields[4].getText());
            appointment.setAppointmentTime(fields[5].getText());
            appointment.setDurationMinutes(duration);
            appointment.setAppointmentType(fields[7].getText());
            appointment.setStatus(fields[8].getText());
            appointment.setReasonForVisit(fields[9].getText());
            dataManager.updateAppointment(appointment.getAppointmentId(), appointment);
        }
        saved = true;
        dispose();
    }
    
    public boolean isSaved() { return saved; }
}
