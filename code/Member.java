// Student Name : 		Damon Kelly
// Student Id Number : 	C00307057
// Date :				22/01/2026
// Purpose : 			Make a Member object with relevant attributes and methods

public class Member
{ // begin class 
    // variables
    private int MemberID;
    private String Name;
    private String Email;
    private String PhoneNumber;
    private String dateOfBirth;
    private String Password;
    private int deletedFlag;

    // constructor
    public Member(int memberID, String name, String email, String phoneNumber, String dateOfBirth, String password, int deletedFlag)
    {
        this.MemberID = memberID;
        this.Name = name;
        this.Email = email;
        this.PhoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
        this.Password = password;
        this.deletedFlag = deletedFlag;
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

    public String getPassword() 
        {
            return Password;
        }

    public int getDeleted()
        {
            return deletedFlag;
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

    public void setPassword(String password) 
        {
            Password = password;
        }

    public void setDeleted(int Flag)
        {
            deletedFlag = Flag;
        }

} // end class