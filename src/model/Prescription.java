package model;

/**
 * Prescription class representing medical prescriptions.
 * @author Shubhamkumar Prahladbhai Patel
 */
public class Prescription {
    private String prescriptionId;
    private String patientId;
    private String clinicianId;
    private String appointmentId;
    private String prescriptionDate;
    private String medicationName;
    private String dosage;
    private String frequency;
    private int durationDays;
    private int quantity;
    private String instructions;
    private String pharmacyName;
    private String status;
    private String issueDate;
    private String collectionDate;
    
    public Prescription() {
        this.prescriptionId = "";
        this.patientId = "";
        this.clinicianId = "";
        this.appointmentId = "";
        this.prescriptionDate = "";
        this.medicationName = "";
        this.dosage = "";
        this.frequency = "";
        this.durationDays = 0;
        this.quantity = 0;
        this.instructions = "";
        this.pharmacyName = "";
        this.status = "Issued";
        this.issueDate = "";
        this.collectionDate = "";
    }
    
    // Full constructor
    public Prescription(String prescriptionId, String patientId, String clinicianId,
                       String appointmentId, String prescriptionDate, String medicationName,
                       String dosage, String frequency, int durationDays, int quantity,
                       String instructions, String pharmacyName, String status,
                       String issueDate, String collectionDate) {
        this.prescriptionId = prescriptionId;
        this.patientId = patientId;
        this.clinicianId = clinicianId;
        this.appointmentId = appointmentId;
        this.prescriptionDate = prescriptionDate;
        this.medicationName = medicationName;
        this.dosage = dosage;
        this.frequency = frequency;
        this.durationDays = durationDays;
        this.quantity = quantity;
        this.instructions = instructions;
        this.pharmacyName = pharmacyName;
        this.status = status;
        this.issueDate = issueDate;
        this.collectionDate = collectionDate;
    }
    
    // Getters
    public String getPrescriptionId() { return prescriptionId; }
    public String getPatientId() { return patientId; }
    public String getClinicianId() { return clinicianId; }
    public String getAppointmentId() { return appointmentId; }
    public String getPrescriptionDate() { return prescriptionDate; }
    public String getMedicationName() { return medicationName; }
    public String getDosage() { return dosage; }
    public String getFrequency() { return frequency; }
    public int getDurationDays() { return durationDays; }
    public int getQuantity() { return quantity; }
    public String getInstructions() { return instructions; }
    public String getPharmacyName() { return pharmacyName; }
    public String getStatus() { return status; }
    public String getIssueDate() { return issueDate; }
    public String getCollectionDate() { return collectionDate; }
    
    // Setters
    public void setPrescriptionId(String id) { this.prescriptionId = id; }
    public void setPatientId(String id) { this.patientId = id; }
    public void setClinicianId(String id) { this.clinicianId = id; }
    public void setAppointmentId(String id) { this.appointmentId = id; }
    public void setPrescriptionDate(String date) { this.prescriptionDate = date; }
    public void setMedicationName(String name) { this.medicationName = name; }
    public void setDosage(String dosage) { this.dosage = dosage; }
    public void setFrequency(String frequency) { this.frequency = frequency; }
    public void setDurationDays(int days) { this.durationDays = days; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public void setInstructions(String instructions) { this.instructions = instructions; }
    public void setPharmacyName(String name) { this.pharmacyName = name; }
    public void setStatus(String status) { this.status = status; }
    public void setIssueDate(String date) { this.issueDate = date; }
    public void setCollectionDate(String date) { this.collectionDate = date; }
    
    public boolean validate() {
        return !medicationName.isEmpty() && !dosage.isEmpty();
    }
    
    public void sendToPharmacy() {
        this.status = "Sent to Pharmacy";
    }
    
    @Override
    public String toString() {
        return "Prescription ID: " + prescriptionId + ", Patient: " + patientId +
               ", Medication: " + medicationName + ", Dosage: " + dosage +
               ", Status: " + status;
    }
    
    public String toCSV() {
        return prescriptionId + "," + patientId + "," + clinicianId + "," +
               appointmentId + "," + prescriptionDate + "," + medicationName + "," +
               dosage + "," + frequency + "," + durationDays + "," + quantity + "," +
               instructions + "," + pharmacyName + "," + status + "," +
               issueDate + "," + collectionDate;
    }
}
