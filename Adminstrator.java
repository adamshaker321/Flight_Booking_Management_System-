package flight_booking_management;


public class Adminstrator extends User{
  private String adminId;
    private String securityLevel;
    
    public Adminstrator() {
    }

    public Adminstrator(int userId, String userName, String name, String email, String password, String contactInfo) {
        super(userId, userName, name, email, password, contactInfo);
    }

    public static Adminstrator fromFileString(String line) {
        String[] parts = line.split(",");
        return new Adminstrator(
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
    
    @Override
     public void login(String email, String password){
         
     }
    @Override
     public void logout(String email, String password){
         
     }
        }
