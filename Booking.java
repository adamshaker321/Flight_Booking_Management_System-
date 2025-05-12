package flight_booking_management;

import java.io.*;
import java.util.*;
import java.util.ArrayList;
import java.util.List;
public class Booking {
    private String bookingReference;
    private Customer customer;
    private Flight flight;
    private List<Passenger> passengers;
    private List<String> seatSelections;
    private String status; // e.g., "confirmed", "pending", "cancelled"
    private String paymentStatus; // e.g., "paid", "pending", "failed"

    // Constructor
    public Booking(String bookingReference, Customer customer, Flight flight) {
        this.bookingReference = bookingReference;
        this.customer = customer;
        this.flight = flight;
        this.passengers = new ArrayList<>();
        this.seatSelections = new ArrayList<>();
        this.status = "pending";  // default status
        this.paymentStatus = "pending";  // default payment status
    }    
    
   public String toFileString() {
    StringBuilder passengerData = new StringBuilder();
    for (Passenger p : passengers) {
        passengerData.append(p.toFileString()).append(";");
    }
    if (!passengers.isEmpty() && passengerData.length() > 0) {
        passengerData.setLength(passengerData.length() - 1);
    }

    StringBuilder seatData = new StringBuilder();
    for (String seat : seatSelections) {
        seatData.append(seat).append("|");
    }
    if (!seatSelections.isEmpty()) {
        seatData.setLength(seatData.length() - 1);
    }
    return String.join("##",
        bookingReference,
        customer.getUserName(),
        flight.getFlightID(),
        seatData.toString(),
        status,
        paymentStatus,
        passengerData.toString()
    );
}
public static Booking fromFileString(String data) {
    try {
        String[] parts = data.split("##", -1);
        if (parts.length < 7) {
            System.out.println("Invalid booking format.");
            return null;
        }
        String bookingRef = parts[0];
        String customerName = parts[1]; // userName
        String flightId = parts[2];
        String[] seatTypes = parts[3].split("\\|");
        String status = parts[4];
        String paymentStatus = parts[5];
        String passengerRaw = parts[6];

        // Load actual customer and flight objects
        Customer customer = null;
        for (User u : File_Manager.loadUsers()) {
            if (u instanceof Customer && u.getUserName().equalsIgnoreCase(customerName)) {
                customer = (Customer) u;
                break;
            }
        }

        Flight flight = null;
        for (Flight f : File_Manager.loadFlights()) {
            if (f.getFlightID().equalsIgnoreCase(flightId)) {
                flight = f;
                break;
            }
        }

        if (customer == null || flight == null) {
            System.out.println("Customer or flight not found.");
            return null;
        }

        // Load passengers
        List<Passenger> passengers = new ArrayList<>();
        if (!passengerRaw.isEmpty()) {
            String[] passengerEntries = passengerRaw.split(";");
            for (String entry : passengerEntries) {
                Passenger p = Passenger.fromFileString(entry);
                if (p != null) passengers.add(p);
            }
        }

        // Create booking
        Booking booking = new Booking(bookingRef, customer, flight);
        booking.setPassengers(passengers);
        booking.setSeatSelections(Arrays.asList(seatTypes));
        booking.setStatus(status);
        booking.setPaymentStatus(paymentStatus);

        return booking;

    } catch (Exception e) {
        System.out.println("Error parsing booking: " + e.getMessage());
        e.printStackTrace();
        return null;
    }
}

    public String getBookingReference() {
        return bookingReference;
    }
    public void setBookingReference(String bookingReference) {
        this.bookingReference = bookingReference;
    }
    public Customer getCustomer() {
        return customer;
    }
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    public Flight getFlight() {
        return flight;
    }
    public void setFlight(Flight flight) {
        this.flight = flight;
    }
    public List<Passenger> getPassengers() {
        return passengers;
    }
    public void setPassengers(List<Passenger> passengers) {
        this.passengers = passengers;
    }
    public List<String> getSeatSelections() {
        return seatSelections;
    }
    public void setSeatSelections(List<String> seatSelections) {
        this.seatSelections = seatSelections;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

 public void addPassenger(Passenger passenger, String seatType) {

    // التحقق من إن نوع المقعد يكون من الثلاثة المسموح بيهم
    if (!seatType.equals("economy") && !seatType.equals("business") && !seatType.equals("firstclass")) {
        System.out.println("Invalid seat type: " + seatType);
        return;
    }

    if (flight.hasAvailableSeats(seatType)) {
            passengers.add(passenger);
            seatSelections.add(seatType);
            switch (seatType) {
                case "economy":
                    flight.reduceEconomySeats();
                    break;
                case "business":
                    flight.reduceBusinessSeats();
                    break;
                case "firstclass":
                    flight.reduceFirstClassSeats();
                    break;
            }
            flight.decreaseAvailableSeats();
            System.out.println("Passenger added successfully to booking.");
        } else {
            System.out.println("No available " + seatType + " seats for this flight.");
        }
    }

    // Calculate total price for the booking
    public double calculateTotalPrice() {
        double total = 0;
        for (String seat : seatSelections) {
            total += flight.getPriceBySeatType(seat);
        }
        return total;
    }

    // Confirm the booking
    public void confirmBooking() {
        if (paymentStatus.equals("paid") && status.equals("pending")) {
            status = "confirmed";
            System.out.println("Booking confirmed!");
        } else {
            System.out.println("Booking cannot be confirmed. Check payment and status.");
        }
    }
    public void cancelBooking() {
        if (status.equals("confirmed")) {
            status = "cancelled";
            System.out.println("Booking cancelled!");
        } else {
            System.out.println("Booking cannot be cancelled as it is not confirmed.");
        }
    }  
   public void generateItinerary() {
        System.out.println("Itinerary : ");
        System.out.println("Booking Reference: " + bookingReference);
        System.out.println("Customer: " + customer.getName() + " (" + customer.getEmail() + ")");
        System.out.println("Flight: " + flight.getFlightID() + " from " + flight.toString());
        System.out.println("Passengers : ");
        for (int i = 0; i < passengers.size(); i++) {
            Passenger p = passengers.get(i);
            System.out.println("  - " + p.getName() + " | Seat Type: " + seatSelections.get(i));
        }
        System.out.println("Total Price: " + calculateTotalPrice());
        System.out.println("Status: " + status + ", Payment: " + paymentStatus);
    }    
}

            

    



