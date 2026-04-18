// Student Name : 		Damon Kelly
// Student Id Number : 	C00307057
// Date :				29/01/2026
// Purpose : 			Make a Booking object with relevant attributes and methods

public class Booking
{ // begin class 
    // variables
    private int BookingID;
    private String BookingDate;
    private String Status;
    private int deletedFlag;
    private int memberID;
    private int classID;

    // constructor
    public Booking(int bookingID, String bookingDate, String status, int memberID, int classID, int deletedFlag)
    {
        this.BookingID = bookingID;
        this.BookingDate = bookingDate;
        this.Status = status;
        this.deletedFlag = deletedFlag;
        this.memberID = memberID;
        this.classID = classID;
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

    public int getDeleted()
        {
            return deletedFlag;
        }

    public int getClassID()
        {
            return classID;
        }

    public int getMemberID()
        {
            return memberID;
        }

    public void setBookingDate(String bookingDate) 
        {
            BookingDate = bookingDate;
        }

    public void setStatus(String status) 
        {            
            Status = status;
        }

    public void setDeleted(int Flag)
        {
            deletedFlag = Flag;
        }

    public void setClassID(int classId)
        {
            classID = classId;
        }

    public void setMemberID(int memberId)
        {
            memberID = memberId;
        }

} // end class