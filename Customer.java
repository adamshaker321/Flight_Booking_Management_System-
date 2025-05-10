package flight_booking_management;

import java.util.ArrayList;

import java.util.Scanner;
public class Customer extends User {
   private ArrayList<Booking> bookingHistory;
    public int customerId;
    String address;
    ArrayList<String> customer_preferences;

    public Customer(int customerId) {
        this.customerId = customerId;
    }

 
    
    public Customer() {
    }

    public Customer(int userId, String userName, String name, String email, String password, String contactInfo) {
        super(userId, userName, name, email, password, contactInfo);
    }
    
    public static Customer fromFileString(String line) {
        String[] parts = line.split(",");
        return new Customer(
            Integer.parseInt(parts[0]),
            parts[1],
            parts[2],
            parts[3],
            parts[4],
            parts[5]
        );
    }
    public String toString(){
        return getUserId()+","+getUserName()+","+getName()+","+getEmail();
    }    


    public Customer(int customerId, String address, ArrayList<String> customer_preferences,
                    int Userid, String Username, String Name,
                    String Email, String Password, String ContactInfo) {
        
        super(Userid, Username, Name, Email, Password, ContactInfo);
        this.customerId = customerId;
        this.address = address;
        this.customer_preferences = customer_preferences;
        this.bookingHistory =new ArrayList<>();
    }

    @Override
     public void login(String email, String password){
         
     }
    @Override
     public void logout(String email, String password){
         
     }
 
    public void searchflights(){
        
    }
    
    public void creatBooking(Booking booking){
    
    }
    
    public void viewBookings(){
        
    }
    
    public void cancelBooking(){
        
    }

}