package util;

import model.*;
import java.io.*;
import java.util.*;

/**
 * CSVHandler utility for reading/writing CSV files.
 * @author Shubhamkumar Prahladbhai Patel
 */
public class CSVHandler {
    private String dataDirectory;
    
    public CSVHandler(String dataDirectory) {
        this.dataDirectory = dataDirectory;
    }
    
    public List<Patient> loadPatients() {
        List<Patient> patients = new ArrayList<>();
        String file = dataDirectory + "/patients.csv";
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line = br.readLine(); // Skip header
            while ((line = br.readLine()) != null) {
                String[] data = parseLine(line);
                if (data.length >= 14) {
                    Patient p = new Patient(data[0], data[1], data[2], data[3],
                        data[4], data[5], data[6], data[7], data[8], data[9],
                        data[10], data[11], data[12], data[13]);
                    patients.add(p);
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading patients: " + e.getMessage());
        }
        return patients;
    }
    
    public List<Appointment> loadAppointments() {
        List<Appointment> appointments = new ArrayList<>();
        String file = dataDirectory + "/appointments.csv";
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line = br.readLine(); // Skip header
            while ((line = br.readLine()) != null) {
                String[] data = parseLine(line);
                if (data.length >= 13) {
                    int duration = 0;
                    try {
                        duration = Integer.parseInt(data[6]);
                    } catch (NumberFormatException e) {}
                    Appointment a = new Appointment(data[0], data[1], data[2],
                        data[3], data[4], data[5], duration, data[7], data[8],
                        data[9], data[10], data[11], data[12]);
                    appointments.add(a);
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading appointments: " + e.getMessage());
        }
        return appointments;
    }
    
    public List<Prescription> loadPrescriptions() {
        List<Prescription> prescriptions = new ArrayList<>();
        String file = dataDirectory + "/prescriptions.csv";
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line = br.readLine(); // Skip header
            while ((line = br.readLine()) != null) {
                String[] data = parseLine(line);
                if (data.length >= 15) {
                    int duration = 0, quantity = 0;
                    try {
                        duration = Integer.parseInt(data[8]);
                        quantity = Integer.parseInt(data[9]);
                    } catch (NumberFormatException e) {}
                    Prescription p = new Prescription(data[0], data[1], data[2],
                        data[3], data[4], data[5], data[6], data[7], duration,
                        quantity, data[10], data[11], data[12], data[13], data[14]);
                    prescriptions.add(p);
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading prescriptions: " + e.getMessage());
        }
        return prescriptions;
    }
    
    public List<Referral> loadReferrals() {
        List<Referral> referrals = new ArrayList<>();
        String file = dataDirectory + "/referrals.csv";
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line = br.readLine(); // Skip header
            while ((line = br.readLine()) != null) {
                String[] data = parseLine(line);
                if (data.length >= 16) {
                    Referral r = new Referral();
                    r.setReferralId(data[0]);
                    r.setPatientId(data[1]);
                    r.setReferringClinicianId(data[2]);
                    r.setReferredToClinicianId(data[3]);
                    r.setReferringFacilityId(data[4]);
                    r.setReferredToFacilityId(data[5]);
                    r.setReferralDate(data[6]);
                    r.setUrgencyLevel(data[7]);
                    r.setReferralReason(data[8]);
                    r.setClinicalSummary(data[9]);
                    r.setRequestedInvestigations(data[10]);
                    r.setStatus(data[11]);
                    r.setAppointmentId(data[12]);
                    r.setNotes(data[13]);
                    r.setCreatedDate(data[14]);
                    r.setLastUpdated(data[15]);
                    referrals.add(r);
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading referrals: " + e.getMessage());
        }
        return referrals;
    }
    
    public boolean savePatients(List<Patient> patients) {
        String file = dataDirectory + "/patients.csv";
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            bw.write("patient_id,first_name,last_name,date_of_birth,nhs_number,gender,phone_number,email,address,postcode,emergency_contact_name,emergency_contact_phone,registration_date,gp_surgery_id");
            bw.newLine();
            for (Patient p : patients) {
                bw.write(p.toCSV());
                bw.newLine();
            }
            return true;
        } catch (IOException e) {
            System.err.println("Error saving patients: " + e.getMessage());
            return false;
        }
    }
    
    public boolean saveAppointments(List<Appointment> appointments) {
        String file = dataDirectory + "/appointments.csv";
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            bw.write("appointment_id,patient_id,clinician_id,facility_id,appointment_date,appointment_time,duration_minutes,appointment_type,status,reason_for_visit,notes,created_date,last_modified");
            bw.newLine();
            for (Appointment a : appointments) {
                bw.write(a.toCSV());
                bw.newLine();
            }
            return true;
        } catch (IOException e) {
            System.err.println("Error saving appointments: " + e.getMessage());
            return false;
        }
    }
    
    public boolean savePrescriptions(List<Prescription> prescriptions) {
        String file = dataDirectory + "/prescriptions.csv";
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            bw.write("prescription_id,patient_id,clinician_id,appointment_id,prescription_date,medication_name,dosage,frequency,duration_days,quantity,instructions,pharmacy_name,status,issue_date,collection_date");
            bw.newLine();
            for (Prescription p : prescriptions) {
                bw.write(p.toCSV());
                bw.newLine();
            }
            return true;
        } catch (IOException e) {
            System.err.println("Error saving prescriptions: " + e.getMessage());
            return false;
        }
    }
    
    public boolean saveReferrals(List<Referral> referrals) {
        String file = dataDirectory + "/referrals.csv";
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            bw.write("referral_id,patient_id,referring_clinician_id,referred_to_clinician_id,referring_facility_id,referred_to_facility_id,referral_date,urgency_level,referral_reason,clinical_summary,requested_investigations,status,appointment_id,notes,created_date,last_updated");
            bw.newLine();
            for (Referral r : referrals) {
                bw.write(r.toCSV());
                bw.newLine();
            }
            return true;
        } catch (IOException e) {
            System.err.println("Error saving referrals: " + e.getMessage());
            return false;
        }
    }
    
    private String[] parseLine(String line) {
        List<String> result = new ArrayList<>();
        boolean inQuotes = false;
        StringBuilder current = new StringBuilder();
        
        for (char c : line.toCharArray()) {
            if (c == '"') {
                inQuotes = !inQuotes;
            } else if (c == ',' && !inQuotes) {
                result.add(current.toString());
                current = new StringBuilder();
            } else {
                current.append(c);
            }
        }
        result.add(current.toString());
        return result.toArray(new String[0]);
    }
}
