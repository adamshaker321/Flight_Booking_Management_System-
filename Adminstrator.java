package flight_booking_management;


public class Adminstrator extends User{
        private String adminId;
    private String securityLevel;
    
    public void adminInfo() {
        super.setName();
        super.setUserId();
        super.setUserName();
        super.setEmail();
        super.setPassword();
        super.setContactInfo();
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
         System.out.print("Enter your role: ");
         String role =input.nextLine();
         User user =File_Manager.authenticateUser(email, password, role);
         
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
    
        }
