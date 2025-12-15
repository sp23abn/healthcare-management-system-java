package controller;

import model.*;
import util.CSVHandler;
import java.util.*;

/**
 * DataManager - Central data management (Model in MVC).
 * Manages all data collections and business logic.
 * 
 * @author Shubhamkumar Prahladbhai Patel
 * @version 1.0
 */
public class DataManager {
    private List<Patient> patients;
    private List<Appointment> appointments;
    private List<Prescription> prescriptions;
    private CSVHandler csvHandler;
    private ReferralManager referralManager;
    
    public DataManager(String dataDirectory) {
        this.csvHandler = new CSVHandler(dataDirectory);
        this.patients = new ArrayList<>();
        this.appointments = new ArrayList<>();
        this.prescriptions = new ArrayList<>();
        this.referralManager = ReferralManager.getInstance();
        loadAllData();
    }
    
    /**
     * Loads all data from CSV files.
     */
    public void loadAllData() {
        System.out.println("Loading data...");
        patients = csvHandler.loadPatients();
        appointments = csvHandler.loadAppointments();
        prescriptions = csvHandler.loadPrescriptions();
        
        List<Referral> loadedReferrals = csvHandler.loadReferrals();
        for (Referral r : loadedReferrals) {
            referralManager.addReferral(r);
        }
        
        System.out.println("Loaded: " + patients.size() + " patients, " +
                         appointments.size() + " appointments, " +
                         prescriptions.size() + " prescriptions, " +
                         referralManager.getReferralCount() + " referrals");
    }
    
    /**
     * Saves all data to CSV files.
     */
    public boolean saveAllData() {
        boolean success = true;
        success &= csvHandler.savePatients(patients);
        success &= csvHandler.saveAppointments(appointments);
        success &= csvHandler.savePrescriptions(prescriptions);
        success &= csvHandler.saveReferrals(referralManager.getAllReferrals());
        return success;
    }
    
    // Patient operations
    public List<Patient> getAllPatients() { return new ArrayList<>(patients); }
    
    public Patient findPatientById(String id) {
        for (Patient p : patients) {
            if (p.getUserId().equals(id)) return p;
        }
        return null;
    }
    
    public boolean addPatient(Patient patient) {
        if (patient != null) {
            patients.add(patient);
            return true;
        }
        return false;
    }
    
    public boolean updatePatient(String id, Patient updated) {
        for (int i = 0; i < patients.size(); i++) {
            if (patients.get(i).getUserId().equals(id)) {
                patients.set(i, updated);
                return true;
            }
        }
        return false;
    }
    
    public boolean deletePatient(String id) {
        return patients.removeIf(p -> p.getUserId().equals(id));
    }
    
    // Appointment operations
    public List<Appointment> getAllAppointments() { return new ArrayList<>(appointments); }
    
    public Appointment findAppointmentById(String id) {
        for (Appointment a : appointments) {
            if (a.getAppointmentId().equals(id)) return a;
        }
        return null;
    }
    
    public boolean addAppointment(Appointment appointment) {
        if (appointment != null) {
            appointments.add(appointment);
            return true;
        }
        return false;
    }
    
    public boolean updateAppointment(String id, Appointment updated) {
        for (int i = 0; i < appointments.size(); i++) {
            if (appointments.get(i).getAppointmentId().equals(id)) {
                appointments.set(i, updated);
                return true;
            }
        }
        return false;
    }
    
    public boolean deleteAppointment(String id) {
        return appointments.removeIf(a -> a.getAppointmentId().equals(id));
    }
    
    // Prescription operations
    public List<Prescription> getAllPrescriptions() { return new ArrayList<>(prescriptions); }
    
    public Prescription findPrescriptionById(String id) {
        for (Prescription p : prescriptions) {
            if (p.getPrescriptionId().equals(id)) return p;
        }
        return null;
    }
    
    public boolean addPrescription(Prescription prescription) {
        if (prescription != null) {
            prescriptions.add(prescription);
            // Save prescription to output file
            savePrescriptionToFile(prescription);
            return true;
        }
        return false;
    }
    
    public boolean updatePrescription(String id, Prescription updated) {
        for (int i = 0; i < prescriptions.size(); i++) {
            if (prescriptions.get(i).getPrescriptionId().equals(id)) {
                prescriptions.set(i, updated);
                return true;
            }
        }
        return false;
    }
    
    public boolean deletePrescription(String id) {
        return prescriptions.removeIf(p -> p.getPrescriptionId().equals(id));
    }
    
    /**
     * Saves prescription to output text file.
     */
    private void savePrescriptionToFile(Prescription prescription) {
        try {
            java.io.File outputDir = new java.io.File("output");
            if (!outputDir.exists()) outputDir.mkdirs();
            
            String filename = "output/prescription_" + prescription.getPrescriptionId() + ".txt";
            java.io.BufferedWriter writer = new java.io.BufferedWriter(new java.io.FileWriter(filename));
            
            writer.write("=".repeat(60));
            writer.newLine();
            writer.write("PRESCRIPTION");
            writer.newLine();
            writer.write("=".repeat(60));
            writer.newLine();
            writer.write("Prescription ID: " + prescription.getPrescriptionId());
            writer.newLine();
            writer.write("Patient ID: " + prescription.getPatientId());
            writer.newLine();
            writer.write("Clinician ID: " + prescription.getClinicianId());
            writer.newLine();
            writer.write("Date: " + prescription.getPrescriptionDate());
            writer.newLine();
            writer.write("Medication: " + prescription.getMedicationName());
            writer.newLine();
            writer.write("Dosage: " + prescription.getDosage());
            writer.newLine();
            writer.write("Frequency: " + prescription.getFrequency());
            writer.newLine();
            writer.write("Duration: " + prescription.getDurationDays() + " days");
            writer.newLine();
            writer.write("Quantity: " + prescription.getQuantity());
            writer.newLine();
            writer.write("Instructions: " + prescription.getInstructions());
            writer.newLine();
            writer.write("Pharmacy: " + prescription.getPharmacyName());
            writer.newLine();
            writer.write("Status: " + prescription.getStatus());
            writer.newLine();
            writer.write("=".repeat(60));
            writer.newLine();
            
            writer.close();
            System.out.println("Prescription saved to: " + filename);
        } catch (java.io.IOException e) {
            System.err.println("Error saving prescription: " + e.getMessage());
        }
    }
    
    // Referral operations (delegates to ReferralManager)
    public List<Referral> getAllReferrals() {
        return referralManager.getAllReferrals();
    }
    
    public boolean addReferral(Referral referral) {
        return referralManager.addReferral(referral);
    }
    
    public boolean sendReferral(Referral referral) {
        return referralManager.sendReferral(referral);
    }
    
    public String generateNewPatientId() {
        int maxNum = 0;
        for (Patient p : patients) {
            String id = p.getUserId();
            if (id.startsWith("P")) {
                try {
                    int num = Integer.parseInt(id.substring(1));
                    if (num > maxNum) maxNum = num;
                } catch (NumberFormatException e) {}
            }
        }
        return String.format("P%03d", maxNum + 1);
    }
    
    public String generateNewAppointmentId() {
        int maxNum = 0;
        for (Appointment a : appointments) {
            String id = a.getAppointmentId();
            if (id.startsWith("A")) {
                try {
                    int num = Integer.parseInt(id.substring(1));
                    if (num > maxNum) maxNum = num;
                } catch (NumberFormatException e) {}
            }
        }
        return String.format("A%03d", maxNum + 1);
    }
    
    public String generateNewPrescriptionId() {
        int maxNum = 0;
        for (Prescription p : prescriptions) {
            String id = p.getPrescriptionId();
            if (id.startsWith("RX")) {
                try {
                    int num = Integer.parseInt(id.substring(2));
                    if (num > maxNum) maxNum = num;
                } catch (NumberFormatException e) {}
            }
        }
        return String.format("RX%03d", maxNum + 1);
    }
}
