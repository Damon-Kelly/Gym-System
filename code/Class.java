// Student Name : 		Damon Kelly
// Student Id Number : 	C00307057
// Date :				29/01/2026
// Purpose : 			Make a Class object with relevant attributes and methods

public class Class
{ // begin class 
    // variables
    private int ClassID;
    private String Title;
    private String Schedule;
    private int Capacity;
    private int deletedFlag;

    // constructor
    public Class(int classID, String Title, String Schedule, int Capacity, int deletedFlag)
    {
        this.ClassID = classID;
        this.Title = Title;
        this.Schedule = Schedule;
        this.Capacity = Capacity;
        this.deletedFlag = deletedFlag;
    }

    // getters and setters
    public int getClassID() 
        {
            return ClassID;
        }

    public String getTitle() 
        {
            return Title;
        }

    public String getSchedule() 
        {
            return Schedule;
        }

    public int getCapacity() 
        {
            return Capacity;
        }
    
    public int getDeleted()
        {
            return deletedFlag;
        }

    public void setTitle(String title) 
        {
            Title = title;
        }

    public void setSchedule(String schedule) 
        {
            Schedule = schedule;
        }
    
    public void setCapacity(int capacity) 
        {
            Capacity = capacity;
        }

    public void setDeleted(int Flag)
        {
            deletedFlag = Flag;
        }

} // end class