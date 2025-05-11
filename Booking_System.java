package flight_booking_management;

import static flight_booking_management.File_Manager.loadFlights;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Booking_System {
    private List<User> users;
    private List<Flight> flights;
    private List<Booking> bookings;
    private List<Payment> payments;
    
    // الكونستركتور
    public Booking_System() {
        // هنا بنقوم بتحميل البيانات من الملفات
        this.users = File_Manager.loadUsers();
        this.flights = File_Manager.loadFlights();
        this.bookings = File_Manager.loadBookings();
        this.payments = File_Manager.loadPayments();
    }
    
    // البحث عن الرحلة
    public static Flight searchFlight(String source, String destination) {
        List<Flight> flights = loadFlights();
        for (Flight flight : flights) {
            if (flight.getSource().equalsIgnoreCase(source) && flight.getDestination().equalsIgnoreCase(destination)) {
                return flight;
            }
        }
        return null;
    }    
    
    // إنشاء حجز
    public Booking createBooking(String booking_reference ,Customer customer, Flight flight) {
        // هنا بنقوم بإنشاء حجز جديد للعميل بناءً على الرحلة المحددة
        Booking newBooking = new Booking(booking_reference,customer, flight);
        bookings.add(newBooking);
        File_Manager.saveBooking(bookings);
        return newBooking;
    }
    
    
    
//    String paymentId, String bookingReference, double amount, 
//                   String method, String status,LocalDateTime transactionDate
//    
    
    // معالجة الدفع
    public Payment processPayment(String paymentId,String booking_reference, double amount,String method,String status, LocalDateTime transactionDate) {
        // هنا نقوم بمعالجة الدفع بناءً على نوع الدفع وكمية المبلغ
        Payment payment = new Payment(paymentId,booking_reference, amount,method,status,transactionDate);
        payments.add(payment);
        File_Manager.savePayments(payments);
        return payment;
    }
    
    // توليد التذكرة
    public String generateTicket(Booking booking) {
        return "Ticket for booking reference: " + booking.getBookingReference();
    }
    
    // توليد التقارير
    public void generateReports() {
        // يمكن إنشاء تقارير عن الحجوزات أو الرحلات أو المدفوعات هنا
    }
    
    // حفظ البيانات
    public void saveAllData() {
        File_Manager.saveUser(users);
        File_Manager.saveFlight(flights);
        File_Manager.saveBooking(bookings);
        File_Manager.savePayments(payments);
    }

    

    // إلغاء الحجز
    public void cancelBooking(Booking booking) {
        bookings.remove(booking);
        File_Manager.saveBooking(bookings);
    }

    // عرض جميع الرحلات المتاحة
    public List<Flight> getAllFlights() {
        return this.flights;
    }
    
    // إضافة رحلة جديدة
    public void addFlight(Flight flight) {
        flights.add(flight);
        File_Manager.saveFlight(flights);
    }
    
    // حذف رحلة
    public void removeFlight(Flight flight) {
        flights.remove(flight);
        File_Manager.saveFlight(flights);
    }
}
