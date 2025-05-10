package flight_booking_management;
import java.io.PrintWriter;
import java.io.FileWriter;

import java.util.ArrayList;
import java.io.*;
import java.util.*;

public class File_Manager {
 public static final String USERS_FILE ="users.txt";
    public static final String FLIGHTS_FILE ="flights.txt";
    public static final String BOOKINGS_FILE ="bookings.txt";
    public static final String PASSENGERS_FILE ="passengers.txt";
    
    
    //save users type
        
    // Save flight
    public static void saveFlight(Flight flight) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FLIGHTS_FILE, true))) {
            writer.println(flight.toFileString());
        } catch (IOException e) {
            System.out.println("Error saving flight");
        }
    }

    // Load flights
    public static List<Flight> loadFlights() {
        List<Flight> flights = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(FLIGHTS_FILE))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                flights.add(Flight.fromFileString(line));
            }
        } catch (IOException e) {
            System.out.println("Error loading flights");
        }
        return flights;
    }

  
    // Save passenger
    public static void savePassenger(Passenger passenger) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(PASSENGERS_FILE, true))) {
            writer.println(passenger.toFileString());
        } catch (IOException e) {
            System.out.println("Error saving passenger");
        }
    }

    // Load passengers
    public static List<Passenger> loadPassengers() {
        List<Passenger> passengers = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(PASSENGERS_FILE))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                passengers.add(Passenger.fromFileString(line));
            }
        } catch (IOException e) {
            System.out.println("Error loading passengers");
        }
        return passengers;
    }
    
    
    
    
    
    
    
    public static void saveBooking(Booking booking) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(BOOKINGS_FILE, true))) {
            writer.println(booking.toFileString());
        } catch (IOException e) {
            System.out.println("Error saving booking");
        }
    }

    // Load bookings
    public static List<Booking> loadBookings() {
        List<Booking> bookings = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(BOOKINGS_FILE))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                bookings.add(Booking.fromFileString(line));
            }
        } catch (IOException e) {
            System.out.println("Error loading bookings");
        }
        return bookings;
    }
    
    
    
    
    
    
    
    

}

    

