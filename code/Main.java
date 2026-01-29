import java.util.Scanner;

// Student Name : 		Damon Kelly
// Student Id Number : 	C00307057
// Date :				26/01/2026
// Purpose : 			Driver program for the gym system

public class Main
{ // begin class 
	public static void main(String args[]) 
	{ // begin main method
		int choice;
		boolean running = true;
		Scanner scanner = new Scanner(System.in);
		int memberID;
		String name;
		String email;
		String phoneNumber;
		String dateOfBirth;

		while (running) 
			{
				// Display menu
				System.out.println();
				System.out.println("Gym Management System");
				System.out.println("1. Add Member");
				System.out.println("2. Update Member");
				System.out.println("3. Delete Member");
				System.out.println("4. View Members");
				System.out.println("5. Exit");
				System.out.println();
				System.out.print("Enter your choice: ");
				choice = scanner.nextInt();
				scanner.nextLine();

				if (choice == 1) 
					{
						System.out.println("Add Member selected.");
						System.out.println();
						System.out.println("Enter the name : ");
						name = scanner.nextLine();
						System.out.println("Enter the email : ");
						email = scanner.nextLine();
						System.out.println("Enter the phone number : ");
						phoneNumber = scanner.nextLine();
						System.out.println("Enter the date of birth (YYYY-MM-DD) : ");
						dateOfBirth = scanner.nextLine();
						InsertMember.insertMember(name, email, phoneNumber, dateOfBirth);
					} 
				else if (choice == 2) 
					{
						System.out.println("Update Member selected.");
						System.out.println();
						System.out.println("Enter the member's name : ");
						name = scanner.nextLine();
						System.out.println("Enter the new email : ");
						email = scanner.nextLine();
						UpdateMember.updateMember(name, email);
					} 
				else if (choice == 3) 
					{
						System.out.println("Delete Member selected.");
						System.out.println();
						System.out.println("Enter the member ID to delete : ");
						memberID = scanner.nextInt();
						DeleteMember.deleteMember(memberID);
					} 
				else if (choice == 4) 
					{
						System.out.println("View Members selected.");
						QueryMember.queryMembers();
					} 
				else if (choice == 5) 
					{
						System.out.println("Exiting the program. Bye bye bye bye bye!");
						running = false;
					} 
				else 
					{
						System.out.println("Invalid choice. Please try again.");
					}
		}
		scanner.close();
	} // end main
} // end class