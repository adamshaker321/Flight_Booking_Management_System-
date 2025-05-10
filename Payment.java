package flight_booking_management;

import java.time.LocalDateTime;


public class Payment {
    private String paymentId;
    private String bookingReference;
    private double amount;
    private String method;
    private String status;
    private LocalDateTime transactionDate;

    // Constructor
    public Payment(String paymentId, String bookingReference, double amount, 
                   String method, String status,LocalDateTime transactionDate) {
        this.paymentId = paymentId;
        this.bookingReference = bookingReference;
        this.amount = amount;
        this.method = method;
        this.status = status;
        this.transactionDate=transactionDate;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public String getBookingReference() {
        return bookingReference;
    }

    public double getAmount() {
        return amount;
    }

    public String getMethod() {
        return method;
    }

    public String getStatus() {
        return status;
    }

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    
     public boolean validatePaymentDetails() {
        boolean isValid = true;

        if (amount <= 0) {
            System.out.println("Invalid amount.");
            isValid = false;
        }

        if (method == null || method.isEmpty()) {
            System.out.println("Payment method is required.");
            isValid = false;
        }

        if (bookingReference == null || bookingReference.isEmpty()) {
            System.out.println("Booking reference is required.");
            isValid = false;
        }

        return isValid;
    }

public void processPayment() {
        
    if (validatePaymentDetails()) {
            this.status = "Confirmed";
            this.transactionDate = LocalDateTime.now();
            System.out.println("Payment processed successfully.");
        }
        else {
            
            this.status = "Failed";
            System.out.println("Payment validation failed. Payment not processed.");
        
        }
    }
public void updateStatus(String newStatus) {
        this.status = newStatus;
        System.out.println("Payment status updated to: " + newStatus);
    }




public String toFileString() {
        return paymentId + "," + bookingReference + "," + amount +","
                + method + "," +status+ ","+ transactionDate;
    }


public static Payment fromFileString(String data) {
    String[] parts = data.split(",", -1); // -1 علشان يحتفظ بالفارغين لو فيه
    if (parts.length < 6) {
        System.out.println("Invalid payment data");
        return null;
    }

    try {
        String paymentId = parts[0];
        String bookingReference = parts[1];
        double amount = Double.parseDouble(parts[2]);
        String method = parts[3];
        String status = parts[4];
        LocalDateTime transactionDate = LocalDateTime.parse(parts[5]);

        return new Payment(paymentId, bookingReference, amount, method, status, transactionDate);
    } catch (Exception e) {
        System.out.println("Error parsing payment data: " + e.getMessage());
        return null;
    }
}


    @Override
public String toString() {
    return paymentId + ", " + bookingReference + ", " + amount + ", " + method + ", " + status + ", " + transactionDate+"\n";
}

    
}
