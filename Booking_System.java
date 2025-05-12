package flight_booking_management;

import static flight_booking_management.File_Manager.loadFlights;
import java.io.*;
import java.util.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
