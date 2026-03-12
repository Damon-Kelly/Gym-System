// Student Name : 		Damon Kelly
// Student Id Number : 	C00307057
// Date :			    26/02/2026
// Purpose : 		    Trainer class

public class Trainer
{ // begin class 
    // variables
    private int TrainerID;
    private String Name;
    private String Email;
    private String Password;

    // constructor
    public Trainer(int TrainerID, String Name, String Email, String Password)
    {
        this.TrainerID = TrainerID;
        this.Name = Name;
        this.Email = Email;
        this.Password = Password;
    }

    // getters and setters
    public int getTrainerID() 
        {
            return TrainerID;
        }

    public String getName() 
        {
            return Name;
        }
    
    public String getEmail() 
        {
            return Email;
        }
    
    public String getPassword() 
        {
            return Password;
        }

    public void setName(String name) 
        {
            Name = name;
        }

    public void setEmail(String email) 
        {
            Email = email;
        }
    
    public void setPassword(String password) 
        {
            Password = password;
        }
} // end class