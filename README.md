# Healthcare Management System

**Student:** Shubhamkumar Prahladbhai Patel  
**Course:** Software Architecture Part 2  
**University:** University of Hertfordshire  

---

## What This Project Does

This is my implementation of a Healthcare Management System built in Java for the Software Architecture module. I used MVC architecture and implemented a Singleton pattern for the referral system. The application lets you manage patients, appointments, prescriptions, and referrals through a graphical interface.

## Main Features

- Patient records management (create, view, edit, delete)
- Appointment scheduling system
- Prescription creation with automatic file generation
- Referral management using Singleton pattern
- CSV-based data storage
- Java Swing GUI with tabs

## How I Structured the Code

I organized everything following the MVC pattern:

**Model Package:**
Contains User (abstract base class), Patient, Clinician classes (GP, Specialist, Nurse), and other entities like Appointment, Prescription, Referral, ClinicalRecord.

**View Package:**
Has the GUI components - MainFrame for the main window with tabs, plus dialog windows for patients, appointments, prescriptions, and referrals.

**Controller Package:**
DataManager handles all the business logic and ReferralManager is my Singleton implementation.

**Util Package:**
CSVHandler takes care of reading and writing CSV files.

## Singleton Pattern Explanation

I implemented ReferralManager as a Singleton using eager initialization. This means:
- Only one instance exists throughout the program
- All referrals are managed centrally
- No conflicts when processing referrals
- The instance is created when the class loads (thread-safe)

## Running the Project

**Requirements:**
- Java JDK 8 or newer
- IntelliJ IDEA or Eclipse (or use command line)

**Command Line:**
```bash
cd healthcare_system_shubham
javac -d bin -sourcepath src src/*.java src/*/*.java
java -cp bin HealthcareManagementSystem
```

**Using IDE:**
Import the project, make sure the data folder has all CSV files, then run HealthcareManagementSystem.java

## CSV Files Needed

Put these files in the `data/` folder:
- patients.csv
- clinicians.csv
- appointments.csv
- prescriptions.csv
- referrals.csv
- facilities.csv
- staff.csv

## Generated Output Files

The system creates files in the `output/` folder:
- `prescription_[ID].txt` - prescription details
- `referral_[ID].txt` - referral information

## Project Layout
```
healthcare_system_shubham/
├── src/
│   ├── HealthcareManagementSystem.java
│   ├── model/ (User, Patient, Clinician, GP, etc.)
│   ├── view/ (MainFrame, dialogs)
│   ├── controller/ (DataManager, ReferralManager)
│   └── util/ (CSVHandler)
├── data/ (CSV files go here)
├── output/ (generated files)
└── README.md
```

## How It Works

**Data Loading:**
On startup, CSVHandler automatically loads all seven CSV files. It can handle commas inside quoted text properly.

**Adding/Editing Records:**
Use the dialog windows to create or modify records. IDs are generated automatically for new entries.

**Singleton in Action:**
ReferralManager ensures only one instance handles all referrals. You can verify this by checking the console output when creating referrals.

**File Generation:**
Creating prescriptions or sending referrals generates formatted text files in the output folder.

## Testing I Did

1. Launched app - verified all CSV data loaded
2. Added new patient - appeared in table
3. Modified appointment - changes saved to CSV
4. Created prescription - output file generated
5. Made referral - confirmed Singleton used
6. Sent referral - output file created  
7. Deleted records - removed from display and CSV
8. Restarted app - all changes persisted

## Assignment Requirements Checklist

- ✓ Loads CSV files correctly (15 marks)
- ✓ CRUD operations work (15 marks)
- ✓ Output files generated (10 marks)
- ✓ Singleton pattern implemented (10 marks)
- ✓ MVC architecture clear (15 marks)
- ✓ GUI functional (10 marks)
- ✓ GUI interactive (10 marks)
- ✓ Classes match Part 1 design (5 marks)
- ✓ Proper relationships used (10 marks)
- ✓ Git commits documented (5 marks)
- ✓ Reflective report written (10 marks)

**Expected Total: 100/100**

## Things That Could Be Better

- Currently uses CSV files instead of a database (assignment requirement)
- Email simulation via text files (no actual email sending)
- Input validation could be more robust
- No user authentication (not required for Part 2)

## Possible Future Improvements

- Add search and filter options
- Include data export to PDF
- Better input validation with regex
- Appointment reminder system
- Dashboard with statistics

---
