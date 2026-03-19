// Student Name : 		Damon Kelly
// Student Id Number : 	C00307057
// Date :				29/01/2026
// Purpose : 			Make a Membership object with relevant attributes and methods

public class Membership
{ // begin class 
    // variables
    private int MembershipID;
    private String PlanType;
    private String StartDate;
    private String EndDate;
    private int IsActive;
    private int MemberID;
    private int deletedFlag;

    // constructor
    public Membership(int membershipID, String PlanType, String StartDate, String EndDate, int IsActive, int memberID, int deletedFlag)
    {
        this.MembershipID = membershipID;
        this.PlanType = PlanType;
        this.StartDate = StartDate;
        this.EndDate = EndDate;
        this.IsActive = IsActive;
        this.MemberID = memberID;
        this.deletedFlag = deletedFlag;
    }

    // getters and setters
    public int getMembershipID() 
        {
            return MembershipID;
        }

    public String getPlanType() 
        {
            return PlanType;
        }

    public String getStartDate() 
        {
            return StartDate;
        }

    public String getEndDate() 
        {
            return EndDate;
        }

    public int getIsActive() 
        {
            return IsActive;
        }

    public int getMemberID() 
        {
            return MemberID;
        }

    public int getDeleted()
        {
            return deletedFlag;
        }

    public void setPlanType(String planType) 
        {
            PlanType = planType;
        }

    public void setStartDate(String startDate) 
        {
            StartDate = startDate;
        }

    public void setEndDate(String endDate) 
        {
            EndDate = endDate;
        }

    public void setIsActive(int isActive) 
        {
            IsActive = isActive;
        }

    public void setMemberID(int memberID) 
        {
            MemberID = memberID;
        }

    public void setDeleted(int Flag)
        {
            deletedFlag = Flag;
        }

} // end class