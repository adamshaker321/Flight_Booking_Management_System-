package flight_booking_management;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Flight_Booking_Management {
    public static void main(String[] args) {
Scanner scanner = new Scanner(System.in);
        Booking_System system = new Booking_System();

        System.out.println("=== Welcome to the Flight Booking System ===");

        User loggedInUser = null;

        // تسجيل الدخول وتحديد النوع
        while (loggedInUser == null) {
            System.out.print("Enter your ID to login: ");
            String id = scanner.nextLine();

            loggedInUser = system.getUserById(id); // لازم تعمل method getUserById في Booking_System
            if (loggedInUser == null) {
                System.out.println("Invalid ID. Try again.");
            }
        }

        System.out.println("Welcome, " + loggedInUser.getClass().getSimpleName() + " #" + loggedInUser.getId());

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
                        System.out.print("Enter Flight ID: ");
                        String fid = scanner.nextLine();
                        System.out.print("Enter Source: ");
                        String src = scanner.nextLine();
                        System.out.print("Enter Destination: ");
                        String dest = scanner.nextLine();
                        System.out.print("Enter Date (yyyy-mm-dd): ");
                        String date = scanner.nextLine();
                        Flight newFlight = new Flight(fid, src, dest, date);
                        system.addFlight(newFlight);
                        System.out.println("Flight added.");
                    } else if (loggedInUser instanceof Agent) {
                        List<Flight> allFlights = system.getAllFlights();
                        allFlights.forEach(System.out::println);
                    }
                    break;

                case 2:
                    if (loggedInUser instanceof Customer) {
                        System.out.print("Enter Flight ID: ");
                        String fid = scanner.nextLine();
                        Flight flight = system.getFlightById(fid); // اعمل الميثود دي في Booking_System
                        if (flight == null) {
                            System.out.println("Flight not found.");
                            break;
                        }

                        System.out.print("Enter number of passengers: ");
                        int num = scanner.nextInt(); scanner.nextLine();
                        List<Passenger> passengers = new ArrayList<>();
                        for (int i = 0; i < num; i++) {
                            System.out.print("Enter name for passenger " + (i+1) + ": ");
                            String pname = scanner.nextLine();
                            passengers.add(new Passenger(pname));
                        }

                        System.out.print("Enter seat selection: ");
                        String seat = scanner.nextLine();
                        Booking booking = system.createBooking((Customer) loggedInUser, flight, passengers, seat);
                        System.out.println("Booking created: " + booking.getBookingReference());

                    } else if (loggedInUser instanceof Adminstrator) {
                        System.out.print("Enter Flight ID to remove: ");
                        String fid = scanner.nextLine();
                        Flight flight = system.getFlightById(fid);
                        if (flight != null) {
                            system.removeFlight(flight);
                            System.out.println("Flight removed.");
                        } else {
                            System.out.println("Flight not found.");
                        }
                    } else if (loggedInUser instanceof Agent) {
                        // نفس عملية الحجز لكن بدور عميل
                        System.out.print("Enter Customer ID: ");
                        String custId = scanner.nextLine();
                        User cust = system.getUserById(custId);
                        if (!(cust instanceof Customer)) {
                            System.out.println("Invalid customer ID.");
                            break;
                        }

                        System.out.print("Enter Flight ID: ");
                        String fid = scanner.nextLine();
                        Flight flight = system.getFlightById(fid);
                        if (flight == null) {
                            System.out.println("Flight not found.");
                            break;
                        }

                        System.out.print("Enter number of passengers: ");
                        int num = scanner.nextInt(); scanner.nextLine();
                        List<Passenger> passengers = new ArrayList<>();
                        for (int i = 0; i < num; i++) {
                            System.out.print("Enter name for passenger " + (i+1) + ": ");
                            String pname = scanner.nextLine();
                            passengers.add(new Passenger(pname));
                        }

                        System.out.print("Enter seat selection: ");
                        String seat = scanner.nextLine();
                        Booking booking = system.createBooking((Customer) cust, flight, passengers, seat);
                        System.out.println("Booking created: " + booking.getBookingReference());
                    }
                    break;

                case 3:
                    if (loggedInUser instanceof Customer) {
                        System.out.print("Enter Booking Reference: ");
                        String ref = scanner.nextLine();
                        Booking booking = system.getBookingByRef(ref); // اعمل الميثود دي
                        if (booking == null) {
                            System.out.println("Booking not found.");
                            break;
                        }
                        System.out.print("Enter Amount: ");
                        double amt = scanner.nextDouble(); scanner.nextLine();
                        System.out.print("Enter Payment Method: ");
                        String method = scanner.nextLine();
                        Payment p = system.processPayment(booking, amt, method);
                        System.out.println("Payment done. Receipt: " + p);
                    }
                    break;

                case 4:
                    if (loggedInUser instanceof Customer) {
                        System.out.print("Enter Booking Reference to cancel: ");
                        String ref = scanner.nextLine();
                        Booking booking = system.getBookingByRef(ref);
                        if (booking != null) {
                            system.cancelBooking(booking);
                            System.out.println("Booking cancelled.");
                        } else {
                            System.out.println("Booking not found.");
                        }
                    } else if (loggedInUser instanceof Administrator) {
                        system.generateReports();
                        System.out.println("Reports generated (dummy).");
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
