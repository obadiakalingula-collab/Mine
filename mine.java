import java.io.*;

public class mine {

    // a) Constructors vs Garbage Collection
    static class Patient {
        int patientID;
        String name;
        
        // Constructor - initializes object when created
        public Patient(int patientID, String name) {
            this.patientID = patientID;
            this.name = name;
            System.out.println("Patient object created: " + name);
        }
        
        // No destructor needed - Garbage Collection handles cleanup
    }
    
    // b) i) this keyword resolves naming conflicts
    static class PatientRecord {
        int patientID;
        String allergyNotes;
        
        public PatientRecord(int patientID, String allergyNotes) {
            this.patientID = patientID;      // this.patientID refers to instance variable
            this.allergyNotes = allergyNotes; // this.allergyNotes refers to instance variable
        }
    }
    
    // b) ii) a) Static method for shared utilities
    static class BMICalculator {
        public static double calculateBMI(double weight, double height) {
            return weight / (height * height);
        }
        
        public static double calculateBMI(int weightKg, int heightCm) {
            double heightM = heightCm / 100.0;
            return weightKg / (heightM * heightM);
        }
    }
    
    // b) ii) b) Method overloading for multiple report formats
    static class MedicalReport {
        public void generateReport(String patientName) {
            System.out.println("Report for: " + patientName);
        }
        
        public void generateReport(String patientName, String format) {
            System.out.println("Report for: " + patientName + " in " + format);
        }
        
        public void generateReport(String patientName, String format, boolean urgent) {
            System.out.println("Report for: " + patientName + " in " + format + " Urgent: " + urgent);
        }
    }
    
    // c) final class prevents further modification of medical constants
    final class MedicalConstants {
        public static final int MAX_PATIENTS = 1000;
        public static final double MAX_BMI = 40.0;
        public static final String HOSPITAL = "St. Mary's Hospital Lacor";
    }
    
    // d) Java program to read file with exception handling
    public static void main(String[] args) {
        File medicalLogFile = new File("medicalLog.txt");
        
        try (BufferedReader br = new BufferedReader(new FileReader(medicalLogFile))) {
            String line;
            System.out.println("=== Medical Log Contents ===");
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (FileNotFoundException e) {
            System.err.println("FileNotFoundException: Medical log file not found - " + e.getMessage());
        } catch (IOException e) {
            System.err.println("IOException: Error reading medical log file - " + e.getMessage());
        } finally {
            System.out.println("Report generation process completed. File closed automatically.");
        }
    }
}

