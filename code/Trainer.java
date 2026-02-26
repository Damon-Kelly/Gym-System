// Student Name : 		Damon Kelly
// Student Id Number : 	C00307057
// Date :			    26/02/2026
// Purpose : 		    Trainer class

public class Trainer
{ // begin class 
    // variables
    private int TrainerID;
    public static int nextID = 1;
    private String Name;

    // constructor
    public Trainer(String Name)
    {
        this.TrainerID = nextID;
        nextID++;
        this.Name = Name;
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

    public void setName(String name) 
        {
            Name = name;
        }
} // end class