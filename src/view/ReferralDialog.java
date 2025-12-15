package view;

import controller.DataManager;
import controller.ReferralManager;
import model.Referral;
import javax.swing.*;
import java.awt.*;

public class ReferralDialog extends JDialog {
    private DataManager dataManager;
    private Referral referral;
    private boolean saved = false;
    private JTextField[] fields = new JTextField[8];
    
    public ReferralDialog(Frame parent, Referral referral, DataManager dataManager) {
        super(parent, "Add Referral", true);
        this.dataManager = dataManager;
        this.referral = referral;
        
        setSize(500, 450);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());
        
        JPanel formPanel = new JPanel(new GridLayout(8, 2, 5, 5));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        String[] labels = {"Patient ID:", "From Clinician ID:", "To Clinician ID:",
            "From Facility:", "To Facility:", "Urgency (Routine/Urgent):", "Reason:", "Summary:"};
        
        for (int i = 0; i < 8; i++) {
            fields[i] = new JTextField();
            formPanel.add(new JLabel(labels[i]));
            formPanel.add(fields[i]);
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
        ReferralManager rm = ReferralManager.getInstance();
        String id = rm.generateReferralId();
        String today = java.time.LocalDate.now().toString();
        
        referral = new Referral();
        referral.setReferralId(id);
        referral.setPatientId(fields[0].getText());
        referral.setReferringClinicianId(fields[1].getText());
        referral.setReferredToClinicianId(fields[2].getText());
        referral.setReferringFacilityId(fields[3].getText());
        referral.setReferredToFacilityId(fields[4].getText());
        referral.setReferralDate(today);
        referral.setUrgencyLevel(fields[5].getText());
        referral.setReferralReason(fields[6].getText());
        referral.setClinicalSummary(fields[7].getText());
        referral.setRequestedInvestigations("");
        referral.setStatus("New");
        referral.setCreatedDate(today);
        referral.setLastUpdated(today);
        
        dataManager.addReferral(referral);
        saved = true;
        dispose();
    }
    
    public boolean isSaved() { return saved; }
}
