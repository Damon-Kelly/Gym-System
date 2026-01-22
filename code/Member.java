// Student Name : 		Damon Kelly
// Student Id Number : 	C00307057
// Date :				22/01/2026
// Purpose : 			Make a Member object with relevant attributes and methods

public class Member
{ // begin class 
    // variables
    private int MemberID;
    public static int nextID = 1;
    private String Name;
    private String Email;
    private String PhoneNumber;
    private String dateOfBirth;

    // constructor
    public Member(String name, String email, String phoneNumber, String dateOfBirth)
    {
        this.MemberID = nextID;
        nextID++;
        this.Name = name;
        this.Email = email;
        this.PhoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
    }

    // getters and setters
    public int getMemberID() 
        {
            return MemberID;
        }
    
    public String getName() 
        {
            return Name;
        }
    
    public String getEmail() 
        {
            return Email;
        }

    public String getPhoneNumber() 
        {
            return PhoneNumber;
        }

    public String getDateOfBirth() 
        {
            return dateOfBirth;
        }

    public void setName(String name) 
        {
            Name = name;
        }

    public void setEmail(String email) 
        {
            Email = email;
        }

    public void setPhoneNumber(String phoneNumber) 
        {
            PhoneNumber = phoneNumber;
        }

    public void setDateOfBirth(String dateOfBirth) 
        {
            this.dateOfBirth = dateOfBirth;
        }

} // end class