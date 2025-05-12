package flight_booking_management;
import java.io.*;
import java.util.*;
import java.util.Scanner;

import java.util.Scanner;
public class Customer extends User {
      
      
    Scanner input =new Scanner(System.in);
    private ArrayList<Booking> bookingHistory;
    private int customerId;
    String address;
    ArrayList<String> customer_preferences;

    public Customer() {
    }

    public Customer(ArrayList<Booking> bookingHistory, int customerId, String address, ArrayList<String> customer_preferences) {
        this.bookingHistory = bookingHistory;
        this.customerId = customerId;
        this.address = address;
        this.customer_preferences = customer_preferences;
    }

    public Customer(ArrayList<Booking> bookingHistory, int customerId, String address, ArrayList<String> customer_preferences, String userId, String userName, String name, String email, String password, boolean contactInfo) {
        super(userId, userName, name, email, password, contactInfo);
        this.bookingHistory = bookingHistory;
        this.customerId = customerId;
        this.address = address;
        this.customer_preferences = customer_preferences;
    }

    public Customer(String userId, String userName, String name, String email, String password, boolean contactInfo) {
        super(userId, userName, name, email, password, contactInfo);
    }



    public ArrayList<Booking> getBookingHistory() {
        return bookingHistory;
    }

    public int getCustomerId() {
        return customerId;
    }

    public String getAddress() {
        return address;
    }

    public ArrayList<String> getCustomer_preferences() {
        return customer_preferences;
    }
    


    
    public static Customer fromFileString(String line) {
        String[] parts = line.split(",");
        return new Customer(
            parts[0],
            parts[1],
            parts[2],
            parts[3],
            parts[4],
            Boolean.parseBoolean(parts[5])
        );
    }
    public String toString(){
        return getUserId()+","+getUserName()+","+getName()+","+getEmail();
    }    


    public Customer(int customerId, String address, ArrayList<String> customer_preferences,
                    String Userid, String Username, String Name,
                    String Email, String Password, boolean ContactInfo) {
        
        super(Userid, Username, Name, Email, Password, ContactInfo);
        this.customerId = customerId;
        this.address = address;
        this.customer_preferences = customer_preferences;
        this.bookingHistory =new ArrayList<>();
    }

    @Override
     public void login(){
         System.out.print("Enter your email: ");
         String email =input.nextLine();
         System.out.print("Enter your password: ");
         String password =input.nextLine();
         User user =File_Manager.authenticateUser(email, password, "customer");
         
        if (user !=null) {
            System.out.println("welcome back "+user.getName());
            this.loggedInUser =user;
            
        }else System.out.println("invalid credentials for "+email);
     }
    @Override
     public void logout(){
         System.out.print("Enter your email: ");
         String email =input.nextLine();
         System.out.print("Enter your password: ");
         String password =input.nextLine();
         
        
         if (email !=null && loggedInUser.getEmail().equals(email)&&
                 password !=null&&loggedInUser.getPassword().equals(password)) {
             System.out.println("Goodbye "+loggedInUser.getName());
             
        }else System.out.println(email+" not active yet!");
     }
 
    public void searchflights(){
        
        System.out.print("departure ctiy: ");
        String source =input.nextLine();
        System.out.print("destination city: ");
        String destination =input.nextLine();
        Flight flight =File_Manager.searchFlight(source, destination);
        
        if (flight !=null) {
            System.out.println("flight details");
            flight.displayDetails();
        }else 
            System.out.println("not match with your information!");
        
    }
    
    public void creatBooking() {

    System.out.print("Enter flight id: ");
    String flightId = input.nextLine();

    List<Flight> flights = File_Manager.loadFlights();
    Flight selectedFlight = null;

    for (Flight flight : flights) {
        if (flight.getFlightID().equalsIgnoreCase(flightId)) {
            selectedFlight = flight;
            break;
        }
    }

    if (selectedFlight == null) {
        System.out.println("Flight not found.");
        return;
    }

    System.out.print("How many seats to book? ");
    int seatCount = Integer.parseInt(input.nextLine());

    ArrayList<Passenger> passengers = new ArrayList<>();
    ArrayList<String> seatSelections = new ArrayList<>();

    for (int i = 1; i <= seatCount; i++) {
        System.out.println("Enter passenger " + i + " details:");
        System.out.print("Passenger ID: ");
        String id = input.nextLine();
        System.out.print("Name: ");
        String name = input.nextLine();
        System.out.print("Passport Number: ");
        String passport = input.nextLine();
        System.out.print("Date of Birth (yyyy-mm-dd): ");
        String dob = input.nextLine();
        System.out.print("Special Requests: ");
        String special = input.nextLine();
        System.out.print("Seat Type (economy/business/firstclass): ");
        String seatType = input.nextLine().toLowerCase();

        Passenger p = new Passenger(id, name, passport, dob, special);
        passengers.add(p);
        seatSelections.add(seatType);

        File_Manager.savePassenger(p);
    }

        String bookingRef = "BK" + System.currentTimeMillis();
        Booking booking = new Booking(bookingRef, this, selectedFlight); 

        for (int i = 0; i < passengers.size(); i++) {
            booking.addPassenger(passengers.get(i), seatSelections.get(i)); 
        }

        File_Manager.saveBooking(booking);
        System.out.println("Booking created successfully! Reference: " + bookingRef);
    }


        public void viewBookings() {
        List<Booking> bookings = File_Manager.loadBookings();
        boolean found = false;

        for (Booking booking : bookings) {
            if (booking.getCustomer().getUserName().equalsIgnoreCase(this.getUserName())) {
                System.out.println("Booking Ref: " + booking.getBookingReference());
                System.out.println("Flight Id: " + booking.getFlight().getFlightID());
                System.out.println("Seats: " + booking.getSeatSelections());
                System.out.println("Status: " + booking.getStatus() + " | Payment: " + booking.getPaymentStatus());
                System.out.println("------");
                found = true;
            }
        }

        if (!found) {
            System.out.println("You have no bookings.");
        }
        }

        public void cancelBooking() {
        System.out.print("Enter booking reference to cancel: ");
        String ref = input.nextLine();

        List<Booking> bookings = File_Manager.loadBookings();
        boolean removed = false;

        Iterator<Booking> iterator = bookings.iterator();
        while (iterator.hasNext()) {
            Booking booking = iterator.next();
            if (booking.getBookingReference().equalsIgnoreCase(ref) &&
                booking.getCustomer().getUserName().equalsIgnoreCase(this.getUserName())) {
                iterator.remove();
                removed = true;
                break;
            }
        }

        if (removed) {
            try (PrintWriter writer = new PrintWriter(new FileWriter(File_Manager.BOOKINGS_FILE))) {
                for (Booking b : bookings) {
                    writer.println(b.toFileString());
                }
                System.out.println("Booking cancelled successfully.");
            } catch (IOException e) {
                System.out.println("Error writing to bookings file.");
            }
        } else {
            System.out.println("Booking not found or not yours.");
        }
    }


}