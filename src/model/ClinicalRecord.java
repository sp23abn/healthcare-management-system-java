package model;

/**
 * ClinicalRecord class representing medical records for patients.
 * 
 * @author Shubhamkumar Prahladbhai Patel
 * @version 1.0
 */
public class ClinicalRecord {
    private String recordId;
    private String patientId;
    private String clinicianId;
    private String diagnosis;
    private String symptoms;
    private String treatment;
    private String createdAt;
    private String notes;
    
    public ClinicalRecord() {
        this.recordId = "";
        this.patientId = "";
        this.clinicianId = "";
        this.diagnosis = "";
        this.symptoms = "";
        this.treatment = "";
        this.createdAt = "";
        this.notes = "";
    }
    
    // Getters and Setters
    public String getRecordId() { return recordId; }
    public void setRecordId(String id) { this.recordId = id; }
    public String getPatientId() { return patientId; }
    public void setPatientId(String id) { this.patientId = id; }
    public String getClinicianId() { return clinicianId; }
    public void setClinicianId(String id) { this.clinicianId = id; }
    public String getDiagnosis() { return diagnosis; }
    public void setDiagnosis(String diagnosis) { this.diagnosis = diagnosis; }
    public String getSymptoms() { return symptoms; }
    public void setSymptoms(String symptoms) { this.symptoms = symptoms; }
    public String getTreatment() { return treatment; }
    public void setTreatment(String treatment) { this.treatment = treatment; }
    public String getCreatedAt() { return createdAt; }
    public void setCreatedAt(String date) { this.createdAt = date; }
    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
    
    public void addEntry(String entry) {
        this.notes += "\n" + entry;
    }
    
    @Override
    public String toString() {
        return "Record ID: " + recordId + ", Patient: " + patientId + 
               ", Diagnosis: " + diagnosis + ", Date: " + createdAt;
    }
}
