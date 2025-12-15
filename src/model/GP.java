package model;

import java.util.ArrayList;
import java.util.List;

/**
 * GP (General Practitioner) class representing primary care physicians.
 * Extends the abstract Clinician class.
 * 
 * @author Shubhamkumar Prahladbhai Patel
 * @version 1.0
 */
public class GP extends Clinician {
    private String gpId;
    private String clinicAddress;
    private List<Patient> assignedPatients;
    
    /**
     * Default constructor for GP class.
     */
    public GP() {
        super();
        this.gpId = "";
        this.clinicAddress = "";
        this.assignedPatients = new ArrayList<>();
    }
    
    /**
     * Parameterized constructor for GP class.
     * 
     * @param clinicianId Unique clinician identifier
     * @param firstName GP's first name
     * @param lastName GP's last name
     * @param title Professional title
     * @param specialty Medical specialty
     * @param gmcNumber GMC registration number
     * @param contactInfo Phone number
     * @param email Email address
     * @param workplaceId Workplace facility ID
     * @param workplaceType Type of workplace
     * @param employmentStatus Employment status
     * @param startDate Start date
     * @param clinicAddress Clinic address
     */
    public GP(String clinicianId, String firstName, String lastName, String title,
              String specialty, String gmcNumber, String contactInfo, String email,
              String workplaceId, String workplaceType, String employmentStatus,
              String startDate, String clinicAddress) {
        super(clinicianId, firstName, lastName, title, specialty, gmcNumber,
              contactInfo, email, workplaceId, workplaceType, employmentStatus, startDate);
        this.gpId = clinicianId;
        this.clinicAddress = clinicAddress;
        this.assignedPatients = new ArrayList<>();
        this.role = "GP";
    }
    
    // Getter and Setter methods
    public String getGpId() { return gpId; }
    public void setGpId(String gpId) { this.gpId = gpId; }
    public String getClinicAddress() { return clinicAddress; }
    public void setClinicAddress(String address) { this.clinicAddress = address; }
    public List<Patient> getAssignedPatients() { return assignedPatients; }
    
    /**
     * Creates a clinical record for a patient.
     * Implementation of abstract method from Clinician.
     * 
     * @param patient Patient for whom record is created
     * @return Created clinical record
     */
    @Override
    public ClinicalRecord createRecord(Patient patient) {
        ClinicalRecord record = new ClinicalRecord();
        record.setRecordId("CR" + System.currentTimeMillis());
        record.setPatientId(patient.getUserId());
        record.setClinicianId(this.clinicianId);
        record.setCreatedAt(java.time.LocalDateTime.now().toString());
        return record;
    }
    
    /**
     * Writes a prescription for a patient.
     * 
     * @param patient Patient receiving prescription
     * @param medication Medication details
     * @return Created prescription object
     */
    public Prescription writePrescription(Patient patient, String medication) {
        Prescription prescription = new Prescription();
        prescription.setPrescriptionId("RX" + System.currentTimeMillis());
        prescription.setPatientId(patient.getUserId());
        prescription.setClinicianId(this.clinicianId);
        prescription.setMedicationName(medication);
        prescription.setPrescriptionDate(java.time.LocalDate.now().toString());
        prescription.setStatus("Issued");
        return prescription;
    }
    
    /**
     * Creates a referral to a specialist.
     * 
     * @param patient Patient being referred
     * @param specialist Specialist receiving referral
     * @param reason Reason for referral
     * @return Created referral object
     */
    public Referral createReferral(Patient patient, Specialist specialist, String reason) {
        Referral referral = new Referral();
        referral.setReferralId("R" + System.currentTimeMillis());
        referral.setPatientId(patient.getUserId());
        referral.setReferringClinicianId(this.clinicianId);
        referral.setReferredToClinicianId(specialist.getClinicianId());
        referral.setReferralReason(reason);
        referral.setReferralDate(java.time.LocalDate.now().toString());
        referral.setUrgencyLevel("Routine");
        referral.setStatus("New");
        return referral;
    }
    
    /**
     * Assigns a patient to this GP.
     * 
     * @param patient Patient to assign
     */
    public void assignPatient(Patient patient) {
        if (patient != null && !assignedPatients.contains(patient)) {
            assignedPatients.add(patient);
        }
    }
    
    /**
     * Deletes a referral (cancels it).
     * 
     * @param referralId ID of referral to delete
     * @return true if deletion successful
     */
    public boolean deleteReferral(String referralId) {
        // This would interact with ReferralManager in actual implementation
        return true;
    }
    
    /**
     * Returns a string representation of the GP.
     * 
     * @return String containing GP details
     */
    @Override
    public String toString() {
        return "GP ID: " + gpId + ", Name: " + getFullName() + 
               ", Specialty: " + specialty + ", Clinic: " + clinicAddress + 
               ", Patients: " + assignedPatients.size();
    }
}
