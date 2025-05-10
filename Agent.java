package flight_booking_management;

public class Agent extends User{
 
    private int agentId;
    private String department;
    private double commission;

    public Agent(int agentId, String department, double commission) {
        this.agentId = agentId;
        this.department = department;
        this.commission = commission;
    }

    public Agent() {
    }

    public Agent(int userId, String userName, String name, String email, String password, String contactInfo) {
        super(userId, userName, name, email, password, contactInfo);
    }

    public static Agent fromFileString(String line) {
        String[] parts = line.split(",");
        return new Agent(
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
