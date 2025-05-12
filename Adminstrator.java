package flight_booking_management;
import java.util.*;
import java.io.*;


public class Adminstrator extends User{
        Scanner input=new Scanner(System.in);
    private String adminId;
    private String securityLevel;

    public Adminstrator() {
    }

    public Adminstrator(String adminId, String securityLevel) {
        this.adminId = adminId;
        this.securityLevel = securityLevel;
    }

    public Adminstrator(String adminId, String securityLevel, String userId, String userName, String name, String email, String password, boolean contactInfo) {
        super(userId, userName, name, email, password, contactInfo);
        this.adminId = adminId;
        this.securityLevel = securityLevel;
    }
    

    public Adminstrator(String userId, String userName, String name, String email, String password, boolean contactInfo) {
        super(userId, userName, name, email, password, contactInfo);
    }

    public static Adminstrator fromFileString(String line) {
        String[] parts = line.split(",");
        return new Adminstrator(
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

    @Override
     public void login(){
         System.out.print("Enter your email: ");
         String email =input.nextLine();
         System.out.print("Enter your password: ");
         String password =input.nextLine();
         User user =File_Manager.authenticateUser(email, password, "administrator");
         
        if (user !=null) {
            System.out.println("welcome back "+this.getName());
            
        }else System.out.println("invalid credentials for "+email);
     }
    @Override
     public void logout(){
         System.out.print("Enter your email: ");
         String email =input.nextLine();
         System.out.print("Enter your password: ");
         String password =input.nextLine();
         
        
         if (email !=null && this.getEmail().equals(email)&&
                 this.getPassword().equals(password)) {
             System.out.println("Goodbye "+this.getName());
             
        }else System.out.println(email+" not active yet!");
     }
    
    public void createUser(){
        System.out.print("user type(customer/agent/administrator): ");
        String type =input.nextLine();
        if (type.equalsIgnoreCase("customer")) {
            User newUser =new Customer();
            File_Manager.saveUser(newUser);
            System.out.println("user "+newUser.getName()+" created successfully");
        
        }else if(type.equalsIgnoreCase("agent")){
            User newUser =new Agent();
            File_Manager.saveUser(newUser);
            System.out.println("user "+newUser.getName()+" created successfully");
        
        }else if (type.equalsIgnoreCase("administrator")) {
            User newUser =new Adminstrator();
            File_Manager.saveUser(newUser);
            System.out.println("user "+newUser.getName()+" created successfully");
        
        }else
            System.out.println("invalid user type!");
        
    }
    
    public void modifySystemSettings() {
        System.out.println("Modify Settings:");
        System.out.println("1. Clear Users File");
        System.out.println("2. Clear Flights File");
        int choice = Integer.parseInt(input.nextLine());

        switch (choice) {
            case 1:
                File_Manager.clearFile(File_Manager.USERS_FILE);
                System.out.println("Users file cleared.");
                break;
            case 2:
                File_Manager.clearFile(File_Manager.FLIGHTS_FILE);
                System.out.println("Flights file cleared.");
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }

    
    public void viewSystemLogs(){
        System.out.println("all users:");
        for (User u: File_Manager.loadUsers()) {
            System.out.println(u.toFileString());
        }
    }
    
    public void manageUserAccess() {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter email of the user to delete: ");
        String email = input.nextLine();

        List<User> users = File_Manager.loadUsers();
        boolean found = false;

        for (int i = 0; i < users.size(); i++) {
            User u = users.get(i);
            if (u.getEmail().equalsIgnoreCase(email)) {
                users.remove(i);
                found = true;
                break;
            }
        }
        
        if (found) {
            try {
                PrintWriter writer = new PrintWriter(new FileWriter(File_Manager.USERS_FILE));
                for (User u : users) {
                    String type = u.getClass().getSimpleName();
                    writer.println(type + "," + u.toFileString());
                }
                writer.close();
                System.out.println("User deleted successfully.");
            } catch (IOException e) {
                System.out.println("Error while updating users file.");
            }
        } else {
            System.out.println("User not found.");
        }
    }
  }
