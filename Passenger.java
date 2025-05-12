package flight_booking_management;

import java.util.*;
import java.io.*;
public class Passenger {
    private String passengerId;
    private String name;
    private String passportNumber;
    private String dateOfBirth;
    private String specialRequests;
    public Passenger(String passengerId, String name, String passportNumber, String dateOfBirth, String specialRequests) {
        this.passengerId = passengerId;
        this.name = name;
        this.passportNumber = passportNumber;
        this.dateOfBirth = dateOfBirth;
        this.specialRequests = specialRequests;
    }
    public String getName() {
        return name;
    }
    public String toFileString() {
        return passengerId + "," + name + "," + passportNumber +","
                + dateOfBirth + "," +specialRequests;
    }
    public static Passenger fromFileString(String data){
        System.out.println("reading: ");
        String[] parts =data.split(",",-1);
        if (parts.length <5) {
            System.out.println("invalid passenger data");
            return null;
        }        
                String passengerId = parts[0];
                String name = parts[1];
                String passportNumber = parts[2];
                String dateOfBirth = parts[3];
                String specialRequests = parts[4];
        return new Passenger(passengerId, name, passportNumber, dateOfBirth, specialRequests);
    }
public void updateInfo(String passengerId, String name, String passportNumber, String dateOfBirth, String specialRequests) {
    this.passengerId = passengerId; 
    this.name = name;
    this.passportNumber = passportNumber;
    this.dateOfBirth = dateOfBirth;
    this.specialRequests = specialRequests;
}
public void getPassengerDetailsByID(String targetId) {
    File file = new File("passengers.txt");
    if (!file.exists()) {
        System.out.println("File not found: passengers.txt");
        return;
    }
    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
        String line;

        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length >= 5 && parts[0].equals(targetId)) {
                // هنا يجب تمرير 'passengerId' كـ int وليس String
                updateInfo(parts[0], parts[1], parts[2], parts[3], parts[4]);
                System.out.println("Passenger Found:");
                System.out.println("Passenger ID: " + passengerId);
                System.out.println("Name: " + name);
                System.out.println("Passport Number: " + passportNumber);
                System.out.println("Date of Birth: " + dateOfBirth);
                System.out.println("Special Requests: " + specialRequests);
                return;
            }
        }
        System.out.println("Passenger with ID " + targetId + " not found.");
    } catch (IOException e) {
        System.out.println("Error reading the file: " + e.getMessage());
    }
}
    public void getPassengerDetails(){
        System.out.println("name: "+name);
        System.out.println("passenger Id: "+passengerId);
        System.out.println("passport number: "+passportNumber);
        System.out.println("date of birth: "+dateOfBirth);
        System.out.println("special requests: "+specialRequests);
    }
}