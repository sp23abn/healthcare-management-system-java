package model;

/**
 * Abstract Clinician class representing medical professionals.
 * This serves as a parent class for GP, Specialist, and Nurse.
 * 
 * @author Shubhamkumar Prahladbhai Patel
 * @version 1.0
 */
public abstract class Clinician extends User {
    protected String clinicianId;
    protected String specialty;
    protected String workplaceId;
    protected String workplaceType;
    protected String gmcNumber;
    protected String title;
    protected String employmentStatus;
    protected String startDate;
    
    /**
     * Default constructor for Clinician class.
     */
    public Clinician() {
        super();
        this.clinicianId = "";
        this.specialty = "";
        this.workplaceId = "";
        this.workplaceType = "";
        this.gmcNumber = "";
        this.title = "";
        this.employmentStatus = "";
        this.startDate = "";
    }
    
    /**
     * Parameterized constructor for Clinician class.
     * 
     * @param clinicianId Unique clinician identifier
     * @param firstName Clinician's first name
     * @param lastName Clinician's last name
     * @param title Professional title (Dr., Sister, etc.)
     * @param specialty Medical specialty
     * @param gmcNumber GMC registration number
     * @param contactInfo Phone number
     * @param email Email address
     * @param workplaceId ID of workplace facility
     * @param workplaceType Type of workplace (GP Surgery, Hospital)
     * @param employmentStatus Full-time, Part-time
     * @param startDate Employment start date
     */
    public Clinician(String clinicianId, String firstName, String lastName, String title,
                     String specialty, String gmcNumber, String contactInfo, String email,
                     String workplaceId, String workplaceType, String employmentStatus,
                     String startDate) {
        super(clinicianId, "", "", firstName, lastName, contactInfo, "Clinician", email);
        this.clinicianId = clinicianId;
        this.specialty = specialty;
        this.workplaceId = workplaceId;
        this.workplaceType = workplaceType;
        this.gmcNumber = gmcNumber;
        this.title = title;
        this.employmentStatus = employmentStatus;
        this.startDate = startDate;
    }
    
    // Getter methods
    public String getClinicianId() { return clinicianId; }
    public String getSpecialty() { return specialty; }
    public String getWorkplaceId() { return workplaceId; }
    public String getWorkplaceType() { return workplaceType; }
    public String getGmcNumber() { return gmcNumber; }
    public String getTitle() { return title; }
    public String getEmploymentStatus() { return employmentStatus; }
    public String getStartDate() { return startDate; }
    
    // Setter methods
    public void setClinicianId(String clinicianId) { this.clinicianId = clinicianId; }
    public void setSpecialty(String specialty) { this.specialty = specialty; }
    public void setWorkplaceId(String workplaceId) { this.workplaceId = workplaceId; }
    public void setWorkplaceType(String workplaceType) { this.workplaceType = workplaceType; }
    public void setGmcNumber(String gmcNumber) { this.gmcNumber = gmcNumber; }
    public void setTitle(String title) { this.title = title; }
    public void setEmploymentStatus(String status) { this.employmentStatus = status; }
    public void setStartDate(String date) { this.startDate = date; }
    
    /**
     * Gets the full professional name with title.
     * 
     * @return Professional name with title
     */
    @Override
    public String getFullName() {
        return title + " " + firstName + " " + lastName;
    }
    
    /**
     * Abstract method to create clinical records.
     * Must be implemented by subclasses.
     * 
     * @param patient Patient for whom record is created
     * @return Created clinical record
     */
    public abstract ClinicalRecord createRecord(Patient patient);
    
    /**
     * Authenticates the clinician (placeholder implementation).
     * 
     * @return true if authenticated
     */
    @Override
    public boolean authenticate() {
        return !clinicianId.isEmpty() && !gmcNumber.isEmpty();
    }
    
    /**
     * Returns a string representation of the Clinician.
     * 
     * @return String containing clinician details
     */
    @Override
    public String toString() {
        return "Clinician ID: " + clinicianId + ", Name: " + getFullName() + 
               ", Specialty: " + specialty + ", Workplace: " + workplaceId;
    }
    
    /**
     * Converts clinician data to CSV format.
     * 
     * @return CSV string representation
     */
    public String toCSV() {
        return clinicianId + "," + title + " " + firstName + "," + lastName + "," + 
               title + "," + specialty + "," + gmcNumber + "," + contactInfo + "," + 
               email + "," + workplaceId + "," + workplaceType + "," + 
               employmentStatus + "," + startDate;
    }
}
