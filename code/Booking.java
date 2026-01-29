// Student Name : 		Damon Kelly
// Student Id Number : 	C00307057
// Date :				29/01/2026
// Purpose : 			Make a Booking object with relevant attributes and methods

public class Booking
{ // begin class 
    // variables
    private int BookingID;
    public static int nextID = 1;
    private String BookingDate;
    private String Status;

    // constructor
    public Booking(String bookingDate, String status)
    {
        this.BookingID = nextID;
        nextID++;
        this.BookingDate = bookingDate;
        this.Status = status;
    }

    // getters and setters
    public int getBookingID() 
        {
            return BookingID;
        }

    public String getBookingDate() 
        {
            return BookingDate;
        }

    public String getStatus() 
        {
            return Status;
        }

    public void setBookingDate(String bookingDate) 
        {
            BookingDate = bookingDate;
        }

    public void setStatus(String status) 
        {            
            Status = status;
        }

} // end class