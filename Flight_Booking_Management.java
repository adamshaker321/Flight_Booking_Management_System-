package flight_booking_management;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Flight_Booking_Management {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
                int option = 0;

        do {
            System.out.println("\n====== Flight Booking System ======");
            System.out.println("1. Add flight");
            System.out.println("2. Add passenger");
            System.out.println("3. Get passenger info by ID");
            System.out.println("10. Exit");
            System.out.print("Choose an option: ");

            try {
                option = in.nextInt();
                in.nextLine(); // Consume newline

                switch (option) {
                    case 1: {
                        
                    }
                    break;

                    case 2: {

                          }
                          break;

                    case 3: {
                        
                    }
                    break;

                    case 10: {
                        System.out.println("Exiting program.");
                    }
                     break;

                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                in.nextLine(); // Clear wrong input
            }
                } while (option != 10);
    }
}
