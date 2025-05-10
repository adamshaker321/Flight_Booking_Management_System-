    package flight_booking_management;

    public class Flight {



    private String flightID;

    private String airline;

    private String source;//مكان الاقلاع

    private String destination;

    private String departureTime;

    private String arrivalTime;

    private int totalSeats;

    private int availableSeats;


    private int economySeats;

    private int businessSeats;

    private int firstClassSeats;

    private double economyPrice;

    private double businessPrice;

    private double firstClassPrice;


    public Flight(String flightID, String airline, String source, String destination,
              String departureTime, String arrivalTime,
              int totalSeats, int availableSeats,
              int economySeats, int businessSeats, int firstClassSeats,
              double economyPrice, double businessPrice, double firstClassPrice) {
    this.flightID = flightID;
    this.airline = airline;
    this.source = source;
    this.destination = destination;
    this.departureTime = departureTime;
    this.arrivalTime = arrivalTime;
    this.totalSeats = totalSeats;
    this.availableSeats = availableSeats;
    this.economySeats = economySeats;
    this.businessSeats = businessSeats;
    this.firstClassSeats = firstClassSeats;
    this.economyPrice = economyPrice;
    this.businessPrice = businessPrice;
    this.firstClassPrice = firstClassPrice;
    }

    public Flight() {
    }



    public String toFileString() {
    return flightID + "," + airline + "," + source + "," + destination + "," +
           departureTime + "," + arrivalTime + "," + totalSeats + "," +
           availableSeats + "," + economySeats + "," + businessSeats + "," +
           firstClassSeats + "," + economyPrice + "," +
           businessPrice + "," + firstClassPrice;
    }

    public static Flight fromFileString(String data) {
    String[] parts = data.split(",", -1);
    if (parts.length < 14) {
        System.out.println("Invalid flight data");
        return null;
    }

    String flightID = parts[0];
    String airline = parts[1];
    String source = parts[2];
    String destination = parts[3];
    String departureTime = parts[4];
    String arrivalTime = parts[5];
    int totalSeats = Integer.parseInt(parts[6]);
    int availableSeats = Integer.parseInt(parts[7]);
    int economySeats = Integer.parseInt(parts[8]);
    int businessSeats = Integer.parseInt(parts[9]);
    int firstClassSeats = Integer.parseInt(parts[10]);
    double economyPrice = Double.parseDouble(parts[11]);
    double businessPrice = Double.parseDouble(parts[12]);
    double firstClassPrice = Double.parseDouble(parts[13]);

    return new Flight(flightID, airline, source, destination, departureTime,
            arrivalTime, totalSeats, availableSeats,
            economySeats, businessSeats, firstClassSeats,
            economyPrice, businessPrice, firstClassPrice);
    }

    
    public String getFlightID() {
        return flightID;
    }

    public int getEconomySeats() {
        return economySeats;
    }

    public int getBusinessSeats() {
        return businessSeats;
    }

    public int getFirstClassSeats() {
        return firstClassSeats;
    }

    public void reduceEconomySeats() {
        if (economySeats > 0) economySeats--;
    }

    public void reduceBusinessSeats() {
        if (businessSeats > 0) businessSeats--;
    }

    public void reduceFirstClassSeats() {
        if (firstClassSeats > 0) firstClassSeats--;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void decreaseAvailableSeats() {
        if (availableSeats > 0) {
            availableSeats--;
        } else {
            System.out.println("No available seats left on this flight.");
        }
    }


    public boolean hasAvailableSeats(String seatType) {
     seatType = seatType.toLowerCase();
    switch (seatType) {
        case "economy": return economySeats > 0;
        case "business": return businessSeats > 0;
        case "firstclass": return firstClassSeats > 0;
        default:
            System.out.println("Enter a valid option\n");
            return false;
    }

    }


    public double getPriceBySeatType(String seatType) {
    seatType = seatType.toLowerCase();
    switch(seatType) {
    case "economy": return economyPrice;
    case "business": return businessPrice;
    case "firstclass": return firstClassPrice;
    default:
        System.out.println("Enter a valid option\n");
        return 0;
    }

    }



public String toString() {
    return  flightID + ", "+  airline + ", "
         +  source + ", " + destination + ", "+  departureTime + ", " + arrivalTime + ", "
         +  totalSeats + ", " + availableSeats+  economySeats + ", " + economyPrice + ", "
         +  businessSeats + ", " + businessPrice + ", "+  firstClassSeats + ", " + firstClassPrice+"\n";
}




    }
