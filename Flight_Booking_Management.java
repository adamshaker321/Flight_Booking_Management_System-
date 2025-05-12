package flight_booking_management;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Flight_Booking_Management {
    public static void main(String[] args) {
Scanner scanner = new Scanner(System.in);
Agent agent=new Agent();
Booking_System system = new Booking_System();
Customer customer=new Customer();
Payment payment=new Payment(); 
System.out.println("=== Welcome to the Flight Booking System ===");

        User loggedInUser = null;

        // تسجيل الدخول وتحديد النوع
        while (loggedInUser == null) {
            System.out.print("Enter your ID to login: ");
            String id = scanner.nextLine();

            loggedInUser = system.getUserById(id); 
            if (loggedInUser == null) {
                System.out.println("Invalid ID. Try again.");
            }
        }

        System.out.println("Welcome, " + loggedInUser.getClass().getSimpleName() );

        int choice;
        do {
            System.out.println("\nAvailable Options:");
            if (loggedInUser instanceof Customer) {
                System.out.println("1. Search for Flight");
                System.out.println("2. Book Flight");
                System.out.println("3. Make Payment");
                System.out.println("4. Cancel Booking");
            } else if (loggedInUser instanceof Adminstrator) {
                System.out.println("1. Add Flight");
                System.out.println("2. Remove Flight");
                System.out.println("3. View All Flights");
                System.out.println("4. Generate Reports");
            } else if (loggedInUser instanceof Agent) {
                System.out.println("1. View All Flights");
                System.out.println("2. Book for Customer");
            }

            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    if (loggedInUser instanceof Customer) {
                        System.out.print("Enter source: ");
                        String source = scanner.nextLine();
                        System.out.print("Enter destination: ");
                        String destination = scanner.nextLine();
                        Flight found = Booking_System.searchFlight(source, destination);
                        if (found != null) {
                            System.out.println("Flight found: " + found);
                        } else {
                            System.out.println("No flight found.");
                        }
                    } else if (loggedInUser instanceof Adminstrator) {
                        
                        
                         System.out.print("Enter flight ID: ");
        String flightID = scanner.nextLine();

        System.out.print("Enter airline name: ");
        String airline = scanner.nextLine();

        System.out.print("Enter source (departure location): ");
        String source = scanner.nextLine();

        System.out.print("Enter destination: ");
        String destination = scanner.nextLine();

        System.out.print("Enter departure time: ");
        String departureTime = scanner.nextLine();

        System.out.print("Enter arrival time: ");
        String arrivalTime = scanner.nextLine();

        System.out.print("Enter total number of seats: ");
        int totalSeats = scanner.nextInt();

        System.out.print("Enter available seats: ");
        int availableSeats = scanner.nextInt();

        System.out.print("Enter number of economy seats: ");
        int economySeats = scanner.nextInt();

        System.out.print("Enter number of business seats: ");
        int businessSeats = scanner.nextInt();

        System.out.print("Enter number of first class seats: ");
        int firstClassSeats = scanner.nextInt();

        System.out.print("Enter price for economy class: ");
        double economyPrice = scanner.nextDouble();

        System.out.print("Enter price for business class: ");
        double businessPrice = scanner.nextDouble();

        System.out.print("Enter price for first class: ");
        double firstClassPrice = scanner.nextDouble();
                        
                        Flight newFlight = new Flight(flightID, airline, source, destination, departureTime, arrivalTime,
           totalSeats, availableSeats, economySeats, businessSeats, firstClassSeats,
           economyPrice, businessPrice, firstClassPrice);
                        
                        File_Manager.addFlight(newFlight);

                    } else if (loggedInUser instanceof Agent) {
                        
                        agent.manageFlights();
                    }
                    break;

                case 2:
                    if (loggedInUser instanceof Customer) {

customer.creatBooking();

                    } else if (loggedInUser instanceof Adminstrator) {
                        System.out.print("Enter Flight ID to remove: ");
                        String fid = scanner.nextLine();
                        Flight flight = system.getFlightById(fid);
                        File_Manager.removeFlight(fid);




                    } else if (loggedInUser instanceof Agent) {
agent.createBookingForCustomer();
                    }
                    break;

                case 3:
                    if (loggedInUser instanceof Customer) {
System.out.print("Enter source: ");
String source = scanner.nextLine();

System.out.print("Enter destination: ");
String destination = scanner.nextLine();

System.out.print("Enter seat type (economy / business / first): ");
String seatType = scanner.nextLine().toLowerCase(); // علشان نبقى متأكدين

System.out.print("Enter number of seats: ");
int numberOfSeats = scanner.nextInt();
scanner.nextLine(); // استهلاك السطر
                        payment.processPayment(source, destination, seatType, numberOfSeats);
                        
                    }
                    break;

                case 4:
                    if (loggedInUser instanceof Customer) {

customer.cancelBooking();

                    } else if (loggedInUser instanceof Adminstrator) {
                        agent.generateReports();
                                }
                    break;

                case 0:
                    System.out.println("Exiting the system. Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 0);

        scanner.close();
    }
}
