package model;

/**
 * Referral class representing patient referrals between clinicians.
 * @author Shubhamkumar Prahladbhai Patel
 */
public class Referral {
    private String referralId;
    private String patientId;
    private String referringClinicianId;
    private String referredToClinicianId;
    private String referringFacilityId;
    private String referredToFacilityId;
    private String referralDate;
    private String urgencyLevel;
    private String referralReason;
    private String clinicalSummary;
    private String requestedInvestigations;
    private String status;
    private String appointmentId;
    private String notes;
    private String createdDate;
    private String lastUpdated;

    public Referral() {
        this.referralId = "";
        this.patientId = "";
        this.referringClinicianId = "";
        this.referredToClinicianId = "";
        this.referringFacilityId = "";
        this.referredToFacilityId = "";
        this.referralDate = "";
        this.urgencyLevel = "Routine";
        this.referralReason = "";
        this.clinicalSummary = "";
        this.requestedInvestigations = "";
        this.status = "New";
        this.appointmentId = "";
        this.notes = "";
        this.createdDate = "";
        this.lastUpdated = "";
    }

    // Getters
    public String getReferralId() { return referralId; }
    public String getPatientId() { return patientId; }
    public String getReferringClinicianId() { return referringClinicianId; }
    public String getReferredToClinicianId() { return referredToClinicianId; }
    public String getReferringFacilityId() { return referringFacilityId; }
    public String getReferredToFacilityId() { return referredToFacilityId; }
    public String getReferralDate() { return referralDate; }
    public String getUrgencyLevel() { return urgencyLevel; }
    public String getReferralReason() { return referralReason; }
    public String getClinicalSummary() { return clinicalSummary; }
    public String getRequestedInvestigations() { return requestedInvestigations; }
    public String getStatus() { return status; }
    public String getAppointmentId() { return appointmentId; }
    public String getNotes() { return notes; }
    public String getCreatedDate() { return createdDate; }
    public String getLastUpdated() { return lastUpdated; }

    // Setters
    public void setReferralId(String id) { this.referralId = id; }
    public void setPatientId(String id) { this.patientId = id; }
    public void setReferringClinicianId(String id) { this.referringClinicianId = id; }
    public void setReferredToClinicianId(String id) { this.referredToClinicianId = id; }
    public void setReferringFacilityId(String id) { this.referringFacilityId = id; }
    public void setReferredToFacilityId(String id) { this.referredToFacilityId = id; }
    public void setReferralDate(String date) { this.referralDate = date; }
    public void setUrgencyLevel(String level) { this.urgencyLevel = level; }
    public void setReferralReason(String reason) { this.referralReason = reason; }
    public void setClinicalSummary(String summary) { this.clinicalSummary = summary; }
    public void setRequestedInvestigations(String investigations) { this.requestedInvestigations = investigations; }
    public void setStatus(String status) { this.status = status; }
    public void setAppointmentId(String id) { this.appointmentId = id; }
    public void setNotes(String notes) { this.notes = notes; }
    public void setCreatedDate(String date) { this.createdDate = date; }
    public void setLastUpdated(String date) { this.lastUpdated = date; }

    public void sendReferral() {
        this.status = "Sent";
        this.lastUpdated = java.time.LocalDate.now().toString();
    }

    public boolean acceptReferral() {
        if ("Sent".equals(this.status) || "New".equals(this.status)) {
            this.status = "Accepted";
            this.lastUpdated = java.time.LocalDate.now().toString();
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Referral ID: " + referralId + ", Patient: " + patientId +
                ", From: " + referringClinicianId + ", To: " + referredToClinicianId +
                ", Urgency: " + urgencyLevel + ", Status: " + status;
    }

    public String toCSV() {
        return referralId + "," + patientId + "," + referringClinicianId + "," +
                referredToClinicianId + "," + referringFacilityId + "," +
                referredToFacilityId + "," + referralDate + "," + urgencyLevel + "," +
                referralReason + ",\"" + clinicalSummary + "\"," +
                requestedInvestigations + "," + status + "," + appointmentId + "," +
                "\"" + notes + "\"," + createdDate + "," + lastUpdated;
    }
}