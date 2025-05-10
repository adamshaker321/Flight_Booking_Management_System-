package flight_booking_management;

import java.io.*;
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

    // Key Methods

    // Add a passenger to the booking
    public void addPassenger(Passenger passenger) {
        passengers.add(passenger);
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

    // Cancel the booking
    public void cancelBooking() {
        if (status.equals("confirmed")) {
            status = "cancelled";
            System.out.println("Booking cancelled!");
        } else {
            System.out.println("Booking cannot be cancelled as it is not confirmed.");
        }
    }

    
    
    
    public String toFileString() {
        StringBuilder passengersString = new StringBuilder();
        for (Passenger p : passengers) {
            passengersString.append(p.getPassengerId()).append(";");
        }

        StringBuilder seatsString = new StringBuilder();
        for (String seat : seatSelections) {
            seatsString.append(seat).append(";");
        }

        return bookingReference + ", " + customer.getCustomerID() + ", " + flight.getFlightID() + ", "
                + passengersString.toString() + ", " + seatsString.toString() + ", " + status + ", " + paymentStatus;
    }

    // Load Booking from file string
    public static Booking fromFileString(String data) {
        String[] parts = data.split(",", -1);
        if (parts.length < 7) {
            System.out.println("Invalid booking data");
            return null;
        }

        String bookingReference = parts[0];
        Customer customer = new Customer(parts[1]);  // Assuming Customer class has a constructor accepting customerID
        Flight flight = new Flight();  // You can load flight data similarly to how you load from the file
        List<Passenger> passengers = new ArrayList<>();
        for (String passengerId : parts[3].split(";")) {
            passengers.add(new Passenger(Integer.parseInt(passengerId), "", "", "", ""));  // Adjust as needed
        }
        List<String> seatSelections = List.of(parts[4].split(";"));
        String status = parts[5];
        String paymentStatus = parts[6];

        Booking booking = new Booking(bookingReference, customer, flight);
        booking.setPassengers(passengers);
        booking.setSeatSelections(seatSelections);
        booking.setStatus(status);
        booking.setPaymentStatus(paymentStatus);

        return booking;
    }
    
    
    
    
    
    
    
    
    
    
    
    

}

            

    



