package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Patient class representing a patient in the healthcare system.
 * Extends the User abstract class and contains patient-specific attributes.
 * 
 * @author Shubhamkumar Prahladbhai Patel
 * @version 1.0
 */
public class Patient extends User {
    private String nhsNumber;
    private String dateOfBirth;
    private String address;
    private String postcode;
    private String gender;
    private String emergencyContactName;
    private String emergencyContactPhone;
    private String registrationDate;
    private String gpSurgeryId;
    
    // Aggregation - Patient has many appointments and clinical records
    private List<Appointment> appointments;
    private List<ClinicalRecord> clinicalRecords;
    
    /**
     * Default constructor for Patient class.
     * Initializes empty lists for appointments and clinical records.
     */
    public Patient() {
        super();
        this.nhsNumber = "";
        this.dateOfBirth = "";
        this.address = "";
        this.postcode = "";
        this.gender = "";
        this.emergencyContactName = "";
        this.emergencyContactPhone = "";
        this.registrationDate = "";
        this.gpSurgeryId = "";
        this.appointments = new ArrayList<>();
        this.clinicalRecords = new ArrayList<>();
    }
    
    /**
     * Parameterized constructor for Patient class.
     * 
     * @param patientId Unique patient identifier
     * @param firstName Patient's first name
     * @param lastName Patient's last name
     * @param dateOfBirth Patient's date of birth
     * @param nhsNumber NHS number
     * @param gender Patient's gender
     * @param contactInfo Patient's phone number
     * @param email Patient's email address
     * @param address Patient's residential address
     * @param postcode Postal code
     * @param emergencyContactName Name of emergency contact
     * @param emergencyContactPhone Phone number of emergency contact
     * @param registrationDate Date of registration
     * @param gpSurgeryId ID of patient's GP surgery
     */
    public Patient(String patientId, String firstName, String lastName, String dateOfBirth,
                   String nhsNumber, String gender, String contactInfo, String email,
                   String address, String postcode, String emergencyContactName,
                   String emergencyContactPhone, String registrationDate, String gpSurgeryId) {
        super(patientId, "", "", firstName, lastName, contactInfo, "Patient", email);
        this.nhsNumber = nhsNumber;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.postcode = postcode;
        this.gender = gender;
        this.emergencyContactName = emergencyContactName;
        this.emergencyContactPhone = emergencyContactPhone;
        this.registrationDate = registrationDate;
        this.gpSurgeryId = gpSurgeryId;
        this.appointments = new ArrayList<>();
        this.clinicalRecords = new ArrayList<>();
    }
    
    // Getter methods
    public String getNhsNumber() { return nhsNumber; }
    public String getDateOfBirth() { return dateOfBirth; }
    public String getAddress() { return address; }
    public String getPostcode() { return postcode; }
    public String getGender() { return gender; }
    public String getEmergencyContactName() { return emergencyContactName; }
    public String getEmergencyContactPhone() { return emergencyContactPhone; }
    public String getRegistrationDate() { return registrationDate; }
    public String getGpSurgeryId() { return gpSurgeryId; }
    public List<Appointment> getAppointments() { return appointments; }
    public List<ClinicalRecord> getClinicalRecords() { return clinicalRecords; }
    
    // Setter methods
    public void setNhsNumber(String nhsNumber) { this.nhsNumber = nhsNumber; }
    public void setDateOfBirth(String dateOfBirth) { this.dateOfBirth = dateOfBirth; }
    public void setAddress(String address) { this.address = address; }
    public void setPostcode(String postcode) { this.postcode = postcode; }
    public void setGender(String gender) { this.gender = gender; }
    public void setEmergencyContactName(String name) { this.emergencyContactName = name; }
    public void setEmergencyContactPhone(String phone) { this.emergencyContactPhone = phone; }
    public void setRegistrationDate(String date) { this.registrationDate = date; }
    public void setGpSurgeryId(String id) { this.gpSurgeryId = id; }
    
    /**
     * Books a new appointment for the patient.
     * 
     * @param appointment The appointment to be booked
     * @return true if booking successful
     */
    public boolean bookAppointment(Appointment appointment) {
        if (appointment != null) {
            appointments.add(appointment);
            return true;
        }
        return false;
    }
    
    /**
     * Modifies an existing appointment.
     * 
     * @param appointmentId ID of appointment to modify
     * @param newAppointment Updated appointment details
     * @return true if modification successful
     */
    public boolean modifyAppointment(String appointmentId, Appointment newAppointment) {
        for (int i = 0; i < appointments.size(); i++) {
            if (appointments.get(i).getAppointmentId().equals(appointmentId)) {
                appointments.set(i, newAppointment);
                return true;
            }
        }
        return false;
    }
    
    /**
     * Cancels an existing appointment.
     * 
     * @param appointmentId ID of appointment to cancel
     * @return true if cancellation successful
     */
    public boolean cancelAppointment(String appointmentId) {
        for (int i = 0; i < appointments.size(); i++) {
            if (appointments.get(i).getAppointmentId().equals(appointmentId)) {
                appointments.get(i).setStatus("Cancelled");
                return true;
            }
        }
        return false;
    }
    
    /**
     * Retrieves all clinical records for the patient.
     * 
     * @return List of clinical records
     */
    public List<ClinicalRecord> viewRecords() {
        return new ArrayList<>(clinicalRecords);
    }
    
    /**
     * Adds a clinical record to the patient's records.
     * 
     * @param record Clinical record to add
     */
    public void addClinicalRecord(ClinicalRecord record) {
        if (record != null) {
            clinicalRecords.add(record);
        }
    }
    
    /**
     * Authenticates the patient (placeholder implementation).
     * 
     * @return true if authenticated
     */
    @Override
    public boolean authenticate() {
        // Simple authentication - could be enhanced with actual password checking
        return !userId.isEmpty() && !firstName.isEmpty();
    }
    
    /**
     * Returns a string representation of the Patient.
     * 
     * @return String containing patient details
     */
    @Override
    public String toString() {
        return "Patient ID: " + userId + ", Name: " + getFullName() + 
               ", NHS: " + nhsNumber + ", DOB: " + dateOfBirth + 
               ", Gender: " + gender + ", Email: " + email;
    }
    
    /**
     * Converts patient data to CSV format.
     * 
     * @return CSV string representation
     */
    public String toCSV() {
        return userId + "," + firstName + "," + lastName + "," + dateOfBirth + "," +
               nhsNumber + "," + gender + "," + contactInfo + "," + email + "," +
               "\"" + address + "\"," + postcode + "," + emergencyContactName + "," +
               emergencyContactPhone + "," + registrationDate + "," + gpSurgeryId;
    }
}
