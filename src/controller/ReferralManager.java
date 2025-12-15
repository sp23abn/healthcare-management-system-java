package controller;

import model.Referral;
import java.util.ArrayList;
import java.util.List;
import java.io.*;

/**
 * Singleton ReferralManager - ensures single instance for referral management.
 * Manages all referrals with centralized control and audit trail.
 * 
 * DESIGN PATTERN: Singleton (Eager Initialization)
 * 
 * @author Shubhamkumar Prahladbhai Patel
 * @version 1.0
 */
public class ReferralManager {
    // Eager initialization - single instance created at class loading
    private static final ReferralManager INSTANCE = new ReferralManager();
    
    private List<Referral> referrals;
    private int referralCounter;
    
    /**
     * Private constructor prevents external instantiation.
     * This ensures only one instance exists (Singleton pattern).
     */
    private ReferralManager() {
        this.referrals = new ArrayList<>();
        this.referralCounter = 1000;
        System.out.println("[SINGLETON] ReferralManager instance created");
    }
    
    /**
     * Get the single instance of ReferralManager.
     * 
     * @return The singleton instance
     */
    public static ReferralManager getInstance() {
        return INSTANCE;
    }
    
    /**
     * Adds a new referral to the system.
     * 
     * @param referral Referral to add
     * @return true if added successfully
     */
    public boolean addReferral(Referral referral) {
        if (referral != null) {
            referrals.add(referral);
            logAction("Added referral: " + referral.getReferralId());
            return true;
        }
        return false;
    }
    
    /**
     * Retrieves all referrals in the system.
     * 
     * @return List of all referrals
     */
    public List<Referral> getAllReferrals() {
        return new ArrayList<>(referrals);
    }
    
    /**
     * Finds a referral by ID.
     * 
     * @param referralId ID to search for
     * @return Referral if found, null otherwise
     */
    public Referral findReferralById(String referralId) {
        for (Referral ref : referrals) {
            if (ref.getReferralId().equals(referralId)) {
                return ref;
            }
        }
        return null;
    }
    
    /**
     * Updates an existing referral.
     * 
     * @param referralId ID of referral to update
     * @param updatedReferral Updated referral data
     * @return true if updated successfully
     */
    public boolean updateReferral(String referralId, Referral updatedReferral) {
        for (int i = 0; i < referrals.size(); i++) {
            if (referrals.get(i).getReferralId().equals(referralId)) {
                referrals.set(i, updatedReferral);
                logAction("Updated referral: " + referralId);
                return true;
            }
        }
        return false;
    }
    
    /**
     * Deletes a referral from the system.
     * 
     * @param referralId ID of referral to delete
     * @return true if deleted successfully
     */
    public boolean deleteReferral(String referralId) {
        for (int i = 0; i < referrals.size(); i++) {
            if (referrals.get(i).getReferralId().equals(referralId)) {
                referrals.remove(i);
                logAction("Deleted referral: " + referralId);
                return true;
            }
        }
        return false;
    }
    
    /**
     * Generates a unique referral ID.
     * 
     * @return New unique referral ID
     */
    public String generateReferralId() {
        return "R" + (++referralCounter);
    }
    
    /**
     * Sends referral (simulates email/EHR update).
     * 
     * @param referral Referral to send
     * @return true if sent successfully
     */
    public boolean sendReferral(Referral referral) {
        if (referral != null) {
            referral.sendReferral();
            saveReferralToFile(referral);
            logAction("Sent referral: " + referral.getReferralId());
            return true;
        }
        return false;
    }
    
    /**
     * Saves referral details to output file (simulates email generation).
     * 
     * @param referral Referral to save
     */
    private void saveReferralToFile(Referral referral) {
        try {
            File outputDir = new File("output");
            if (!outputDir.exists()) {
                outputDir.mkdirs();
            }
            
            String filename = "output/referral_" + referral.getReferralId() + ".txt";
            BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true));
            
            writer.write("=".repeat(60));
            writer.newLine();
            writer.write("REFERRAL NOTIFICATION");
            writer.newLine();
            writer.write("=".repeat(60));
            writer.newLine();
            writer.write("Referral ID: " + referral.getReferralId());
            writer.newLine();
            writer.write("Patient ID: " + referral.getPatientId());
            writer.newLine();
            writer.write("From Clinician: " + referral.getReferringClinicianId());
            writer.newLine();
            writer.write("To Clinician: " + referral.getReferredToClinicianId());
            writer.newLine();
            writer.write("Date: " + referral.getReferralDate());
            writer.newLine();
            writer.write("Urgency: " + referral.getUrgencyLevel());
            writer.newLine();
            writer.write("Reason: " + referral.getReferralReason());
            writer.newLine();
            writer.write("Clinical Summary: " + referral.getClinicalSummary());
            writer.newLine();
            writer.write("Investigations: " + referral.getRequestedInvestigations());
            writer.newLine();
            writer.write("=".repeat(60));
            writer.newLine();
            writer.newLine();
            
            writer.close();
            System.out.println("Referral saved to: " + filename);
        } catch (IOException e) {
            System.err.println("Error saving referral: " + e.getMessage());
        }
    }
    
    /**
     * Logs actions for audit trail.
     * 
     * @param action Action to log
     */
    private void logAction(String action) {
        String timestamp = java.time.LocalDateTime.now().toString();
        System.out.println("[" + timestamp + "] " + action);
    }
    
    /**
     * Gets total number of referrals.
     * 
     * @return Count of referrals
     */
    public int getReferralCount() {
        return referrals.size();
    }
}
