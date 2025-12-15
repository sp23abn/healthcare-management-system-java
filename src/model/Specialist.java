package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Specialist class representing consultant doctors in hospitals.
 * Extends the abstract Clinician class.
 * 
 * @author Shubhamkumar Prahladbhai Patel
 * @version 1.0
 */
public class Specialist extends Clinician {
    private String specialistId;
    private String hospitalDepartment;
    private List<Patient> referredPatients;
    private List<ClinicalRecord> consultationRecords;
    
    /**
     * Default constructor for Specialist class.
     */
    public Specialist() {
        super();
        this.specialistId = "";
        this.hospitalDepartment = "";
        this.referredPatients = new ArrayList<>();
        this.consultationRecords = new ArrayList<>();
    }
    
    /**
     * Parameterized constructor for Specialist class.
     * 
     * @param clinicianId Unique clinician identifier
     * @param firstName Specialist's first name
     * @param lastName Specialist's last name
     * @param title Professional title
     * @param specialty Medical specialty
     * @param gmcNumber GMC registration number
     * @param contactInfo Phone number
     * @param email Email address
     * @param workplaceId Workplace facility ID
     * @param workplaceType Type of workplace
     * @param employmentStatus Employment status
     * @param startDate Start date
     * @param hospitalDepartment Hospital department
     */
    public Specialist(String clinicianId, String firstName, String lastName, String title,
                      String specialty, String gmcNumber, String contactInfo, String email,
                      String workplaceId, String workplaceType, String employmentStatus,
                      String startDate, String hospitalDepartment) {
        super(clinicianId, firstName, lastName, title, specialty, gmcNumber,
              contactInfo, email, workplaceId, workplaceType, employmentStatus, startDate);
        this.specialistId = clinicianId;
        this.hospitalDepartment = hospitalDepartment;
        this.referredPatients = new ArrayList<>();
        this.consultationRecords = new ArrayList<>();
        this.role = "Specialist";
    }
    
    // Getter and Setter methods
    public String getSpecialistId() { return specialistId; }
    public void setSpecialistId(String id) { this.specialistId = id; }
    public String getHospitalDepartment() { return hospitalDepartment; }
    public void setHospitalDepartment(String dept) { this.hospitalDepartment = dept; }
    public List<Patient> getReferredPatients() { return referredPatients; }
    public List<ClinicalRecord> getConsultationRecords() { return consultationRecords; }
    
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
        consultationRecords.add(record);
        return record;
    }
    
    /**
     * Receives and processes a referral from a GP.
     * 
     * @param referral Referral to process
     * @return true if referral accepted
     */
    public boolean receiveReferral(Referral referral) {
        if (referral != null && referral.getReferredToClinicianId().equals(this.specialistId)) {
            referral.setStatus("In Progress");
            return true;
        }
        return false;
    }
    
    /**
     * Reviews and updates a referral with assessment details.
     * 
     * @param referralId ID of referral to review
     * @param assessment Assessment notes
     * @return true if review successful
     */
    public boolean reviewReferral(String referralId, String assessment) {
        // In actual implementation, this would interact with ReferralManager
        return !referralId.isEmpty() && !assessment.isEmpty();
    }
    
    /**
     * Accepts an appointment for a referred patient.
     * 
     * @param appointment Appointment to accept
     * @return true if appointment accepted
     */
    public boolean acceptAppointment(Appointment appointment) {
        if (appointment != null && appointment.getClinicianId().equals(this.specialistId)) {
            appointment.setStatus("Confirmed");
            return true;
        }
        return false;
    }
    
    /**
     * Adds a referred patient to the specialist's list.
     * 
     * @param patient Patient to add
     */
    public void addReferredPatient(Patient patient) {
        if (patient != null && !referredPatients.contains(patient)) {
            referredPatients.add(patient);
        }
    }
    
    /**
     * Creates a prescription for a patient after consultation.
     * 
     * @param patient Patient receiving prescription
     * @param medication Medication details
     * @return Created prescription
     */
    public Prescription createPrescription(Patient patient, String medication) {
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
     * Returns a string representation of the Specialist.
     * 
     * @return String containing specialist details
     */
    @Override
    public String toString() {
        return "Specialist ID: " + specialistId + ", Name: " + getFullName() + 
               ", Specialty: " + specialty + ", Department: " + hospitalDepartment + 
               ", Referred Patients: " + referredPatients.size();
    }
}
