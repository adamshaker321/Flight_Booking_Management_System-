package flight_booking_management;

import static flight_booking_management.File_Manager.loadFlights;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.*;
import java.io.*;
public class Booking_System {
     private List<User> users;
    private List<Flight> flights;
    private List<Booking> bookings;
    Scanner input = new Scanner(System.in);
    
    public List<User> getUsers() {
    return this.users;
}

    
    public Booking_System() {
        users = File_Manager.loadUsers();
        flights = File_Manager.loadFlights();
        bookings = File_Manager.loadBookings();
    }
      public static Flight searchFlight(String source, String destination){
        List<Flight> flights =loadFlights();
        for (Flight flight : flights){
            if (flight.getSource().equalsIgnoreCase(source)&&
                    flight.getDestination().equalsIgnoreCase(destination)) {
                return flight;
            }
        }
        return null;
    }

    public void createBooking(Customer customer) {
        System.out.print("Enter flight ID: ");
        String flightId = input.nextLine();

        Flight selectedFlight = null;
        for (Flight f : flights) {
            if (f.getFlightID().equalsIgnoreCase(flightId)) {
                selectedFlight = f;
                break;
            }
        }

        if (selectedFlight == null) {
            System.out.println("Flight not found.");
            return;
        }

        System.out.print("Number of seats: ");
        int count = Integer.parseInt(input.nextLine());

        Booking booking = new Booking("BK" + System.currentTimeMillis(), customer, selectedFlight);

        for (int i = 1; i <= count; i++) {
            System.out.println("Passenger " + i);
            System.out.print("ID: ");
            String id = input.nextLine();
            System.out.print("Name: ");
            String name = input.nextLine();
            System.out.print("Passport: ");
            String passport = input.nextLine();
            System.out.print("DOB: ");
            String dob = input.nextLine();
            System.out.print("Special Requests: ");
            String special = input.nextLine();
            System.out.print("Seat Type: ");
            String seatType = input.nextLine();

            Passenger p = new Passenger(id, name, passport, dob, special);
            booking.addPassenger(p, seatType);
            File_Manager.savePassenger(p);
        }
        File_Manager.saveBooking(booking);
        bookings.add(booking);
        System.out.println("Booking created successfully. Ref: " + booking.getBookingReference());
    }
    public void processPayment(String bookingRef) {
        for (Booking booking : bookings) {
            if (booking.getBookingReference().equalsIgnoreCase(bookingRef)) {
                System.out.println("Total amount due: " + booking.calculateTotalPrice());
                System.out.print("Confirm payment? (yes/no): ");
                String confirm = input.nextLine();
                if (confirm.equalsIgnoreCase("yes")) {
                    booking.setPaymentStatus("paid");
                    booking.confirmBooking();
                    File_Manager.saveBooking(booking);
                    System.out.println("Payment successful and booking confirmed.");
                } else {
                    System.out.println("Payment cancelled.");
                }
                return;
            }
        }
        System.out.println("Booking not found.");
    }
    public void generateTicket(String bookingRef) {
        for (Booking booking : bookings) {
            if (booking.getBookingReference().equalsIgnoreCase(bookingRef)) {
                System.out.println("TICKET: ");
                booking.generateItinerary();
                return;
            }
        }
        System.out.println("Booking not found.");
    }


public User getUserById(String id) {
    for (User user : users) {
        if (user.getUserId().equals(id)) {
            return user;
        }
    }
    return null;
}

// ترجع الرحلة بناءً على الـ ID
public Flight getFlightById(String id) {
    for (Flight flight : flights) {
        if (flight.getFlightID().equals(id)) {
            return flight;
        }
    }
    return null;
}

// ترجع الحجز بناءً على المرجع
public Booking getBookingByRef(String ref) {
    for (Booking booking : bookings) {
        if (booking.getBookingReference().equals(ref)) {
            return booking;
        }
    }
    return null;
}

// ترجع كل الرحلات
public List<Flight> getAllFlights() {
    return new ArrayList<>(flights);
}














}
