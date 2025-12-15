package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Nurse class representing nursing staff in healthcare facilities.
 * Extends the abstract Clinician class.
 * 
 * @author Shubhamkumar Prahladbhai Patel
 * @version 1.0
 */
public class Nurse extends Clinician {
    private String nurseId;
    private String shiftSchedule;
    private String assignedDoctor;
    private List<Patient> assignedPatients;
    
    /**
     * Default constructor for Nurse class.
     */
    public Nurse() {
        super();
        this.nurseId = "";
        this.shiftSchedule = "";
        this.assignedDoctor = "";
        this.assignedPatients = new ArrayList<>();
    }
    
    /**
     * Parameterized constructor for Nurse class.
     * 
     * @param clinicianId Unique clinician identifier
     * @param firstName Nurse's first name
     * @param lastName Nurse's last name
     * @param title Professional title (Sister, Nurse, etc.)
     * @param specialty Nursing specialty
     * @param gmcNumber NMC/GMC registration number
     * @param contactInfo Phone number
     * @param email Email address
     * @param workplaceId Workplace facility ID
     * @param workplaceType Type of workplace
     * @param employmentStatus Employment status
     * @param startDate Start date
     * @param shiftSchedule Shift schedule details
     * @param assignedDoctor GP assigned to
     */
    public Nurse(String clinicianId, String firstName, String lastName, String title,
                 String specialty, String gmcNumber, String contactInfo, String email,
                 String workplaceId, String workplaceType, String employmentStatus,
                 String startDate, String shiftSchedule, String assignedDoctor) {
        super(clinicianId, firstName, lastName, title, specialty, gmcNumber,
              contactInfo, email, workplaceId, workplaceType, employmentStatus, startDate);
        this.nurseId = clinicianId;
        this.shiftSchedule = shiftSchedule;
        this.assignedDoctor = assignedDoctor;
        this.assignedPatients = new ArrayList<>();
        this.role = "Nurse";
    }
    
    // Getter and Setter methods
    public String getNurseId() { return nurseId; }
    public void setNurseId(String id) { this.nurseId = id; }
    public String getShiftSchedule() { return shiftSchedule; }
    public void setShiftSchedule(String schedule) { this.shiftSchedule = schedule; }
    public String getAssignedDoctor() { return assignedDoctor; }
    public void setAssignedDoctor(String doctor) { this.assignedDoctor = doctor; }
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
     * Updates clinical observations for a patient.
     * 
     * @param patient Patient being assessed
     * @param observations Clinical observations
     * @return Updated clinical record
     */
    public ClinicalRecord updateClinicalObservations(Patient patient, String observations) {
        ClinicalRecord record = createRecord(patient);
        record.setNotes(observations);
        patient.addClinicalRecord(record);
        return record;
    }
    
    /**
     * Assists in clinical documentation.
     * 
     * @param patient Patient being documented
     * @param documentationType Type of documentation
     * @return Documentation string
     */
    public String assistClinicalDocumentation(Patient patient, String documentationType) {
        String documentation = "Clinical Documentation by " + getFullName() + "\n";
        documentation += "Patient: " + patient.getFullName() + "\n";
        documentation += "Type: " + documentationType + "\n";
        documentation += "Date: " + java.time.LocalDateTime.now() + "\n";
        return documentation;
    }
    
    /**
     * Assigns a patient to this nurse for care.
     * 
     * @param patient Patient to assign
     */
    public void assignPatient(Patient patient) {
        if (patient != null && !assignedPatients.contains(patient)) {
            assignedPatients.add(patient);
        }
    }
    
    /**
     * Records vital signs for a patient.
     * 
     * @param patient Patient being assessed
     * @param vitalSigns Vital signs measurements
     * @return true if recording successful
     */
    public boolean recordVitalSigns(Patient patient, String vitalSigns) {
        ClinicalRecord record = createRecord(patient);
        record.setSymptoms("Vital Signs: " + vitalSigns);
        patient.addClinicalRecord(record);
        return true;
    }
    
    /**
     * Returns a string representation of the Nurse.
     * 
     * @return String containing nurse details
     */
    @Override
    public String toString() {
        return "Nurse ID: " + nurseId + ", Name: " + getFullName() + 
               ", Specialty: " + specialty + ", Shift: " + shiftSchedule + 
               ", Assigned Patients: " + assignedPatients.size();
    }
}
