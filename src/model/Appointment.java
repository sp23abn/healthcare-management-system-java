package model;

/**
 * Appointment class representing scheduled appointments in the healthcare system.
 * 
 * @author Shubhamkumar Prahladbhai Patel
 * @version 1.0
 */
public class Appointment {
    private String appointmentId;
    private String patientId;
    private String clinicianId;
    private String facilityId;
    private String appointmentDate;
    private String appointmentTime;
    private int durationMinutes;
    private String appointmentType;
    private String status; // Scheduled, Confirmed, Cancelled, Completed
    private String reasonForVisit;
    private String notes;
    private String createdDate;
    private String lastModified;
    
    /**
     * Default constructor for Appointment class.
     */
    public Appointment() {
        this.appointmentId = "";
        this.patientId = "";
        this.clinicianId = "";
        this.facilityId = "";
        this.appointmentDate = "";
        this.appointmentTime = "";
        this.durationMinutes = 0;
        this.appointmentType = "";
        this.status = "Scheduled";
        this.reasonForVisit = "";
        this.notes = "";
        this.createdDate = "";
        this.lastModified = "";
    }
    
    /**
     * Parameterized constructor for Appointment class.
     */
    public Appointment(String appointmentId, String patientId, String clinicianId,
                       String facilityId, String appointmentDate, String appointmentTime,
                       int durationMinutes, String appointmentType, String status,
                       String reasonForVisit, String notes, String createdDate,
                       String lastModified) {
        this.appointmentId = appointmentId;
        this.patientId = patientId;
        this.clinicianId = clinicianId;
        this.facilityId = facilityId;
        this.appointmentDate = appointmentDate;
        this.appointmentTime = appointmentTime;
        this.durationMinutes = durationMinutes;
        this.appointmentType = appointmentType;
        this.status = status;
        this.reasonForVisit = reasonForVisit;
        this.notes = notes;
        this.createdDate = createdDate;
        this.lastModified = lastModified;
    }
    
    // Getter methods
    public String getAppointmentId() { return appointmentId; }
    public String getPatientId() { return patientId; }
    public String getClinicianId() { return clinicianId; }
    public String getFacilityId() { return facilityId; }
    public String getAppointmentDate() { return appointmentDate; }
    public String getAppointmentTime() { return appointmentTime; }
    public int getDurationMinutes() { return durationMinutes; }
    public String getAppointmentType() { return appointmentType; }
    public String getStatus() { return status; }
    public String getReasonForVisit() { return reasonForVisit; }
    public String getNotes() { return notes; }
    public String getCreatedDate() { return createdDate; }
    public String getLastModified() { return lastModified; }
    
    // Setter methods
    public void setAppointmentId(String id) { this.appointmentId = id; }
    public void setPatientId(String id) { this.patientId = id; }
    public void setClinicianId(String id) { this.clinicianId = id; }
    public void setFacilityId(String id) { this.facilityId = id; }
    public void setAppointmentDate(String date) { this.appointmentDate = date; }
    public void setAppointmentTime(String time) { this.appointmentTime = time; }
    public void setDurationMinutes(int duration) { this.durationMinutes = duration; }
    public void setAppointmentType(String type) { this.appointmentType = type; }
    public void setStatus(String status) { this.status = status; }
    public void setReasonForVisit(String reason) { this.reasonForVisit = reason; }
    public void setNotes(String notes) { this.notes = notes; }
    public void setCreatedDate(String date) { this.createdDate = date; }
    public void setLastModified(String date) { this.lastModified = date; }
    
    /**
     * Schedules the appointment.
     * 
     * @return true if scheduling successful
     */
    public boolean schedule() {
        if (!appointmentDate.isEmpty() && !appointmentTime.isEmpty()) {
            this.status = "Scheduled";
            this.lastModified = java.time.LocalDate.now().toString();
            return true;
        }
        return false;
    }
    
    /**
     * Reschedules the appointment to a new date/time.
     * 
     * @param newDate New appointment date
     * @param newTime New appointment time
     * @return true if rescheduling successful
     */
    public boolean reschedule(String newDate, String newTime) {
        if (!newDate.isEmpty() && !newTime.isEmpty()) {
            this.appointmentDate = newDate;
            this.appointmentTime = newTime;
            this.lastModified = java.time.LocalDate.now().toString();
            return true;
        }
        return false;
    }
    
    /**
     * Cancels the appointment.
     * 
     * @return true if cancellation successful
     */
    public boolean cancel() {
        this.status = "Cancelled";
        this.lastModified = java.time.LocalDate.now().toString();
        return true;
    }
    
    /**
     * Notifies participants about the appointment.
     * 
     * @param participants List of people to notify
     */
    public void notifyParticipants(String participants) {
        // Placeholder for notification logic
        System.out.println("Notifying participants: " + participants);
    }
    
    /**
     * Returns a string representation of the Appointment.
     * 
     * @return String containing appointment details
     */
    @Override
    public String toString() {
        return "Appointment ID: " + appointmentId + ", Patient: " + patientId + 
               ", Clinician: " + clinicianId + ", Date: " + appointmentDate + 
               " " + appointmentTime + ", Type: " + appointmentType + 
               ", Status: " + status;
    }
    
    /**
     * Converts appointment data to CSV format.
     * 
     * @return CSV string representation
     */
    public String toCSV() {
        return appointmentId + "," + patientId + "," + clinicianId + "," + 
               facilityId + "," + appointmentDate + "," + appointmentTime + "," + 
               durationMinutes + "," + appointmentType + "," + status + "," + 
               reasonForVisit + ",\"" + notes + "\"," + createdDate + "," + lastModified;
    }
}
