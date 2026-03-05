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
				System.out.println("---------------------");
				System.out.println("0. Exit");
				System.out.println("1. Add Member");
				System.out.println("2. View Members");
				System.out.println("3. Update Member");
				System.out.println("4. Delete Member");
				System.out.println("5. Add Membership");
				System.out.println("6. View Active Memberships");
				System.out.println("7. Update Membership");
				System.out.println("8. Delete Membership");
				System.out.println("9. Add Trainer");
				System.out.println("10. View Trainers");
				System.out.println("11. Update Trainer");
				System.out.println("12. Delete Trainer");
				System.out.println("13. Add Class");
				System.out.println("14. View Classes");
				System.out.println("15. Update Class");
				System.out.println("16. Delete Class");
				System.out.println("17. Add Booking");
				System.out.println("18. View Bookings");
				System.out.println("19. Update Booking");
				System.out.println("20. Delete Booking");
				System.out.println();
				System.out.print("Enter your choice: ");
				choice = scanner.nextInt();
				scanner.nextLine();

				if (choice == 0) 
					{
						System.out.println("Exiting the program. Bye bye bye bye bye!");
						running = false;
					} 

				else if (choice == 1) 
					{
						System.out.println("Add Member selected.");
						System.out.println();
						System.out.print("Enter the name : ");
						name = scanner.nextLine();
						System.out.print("Enter the email : ");
						email = scanner.nextLine();
						System.out.print("Enter the phone number : ");
						phoneNumber = scanner.nextLine();
						System.out.print("Enter the date of birth (YYYY-MM-DD) : ");
						dateOfBirth = scanner.nextLine();
						InsertMember.insertMember(name, email, phoneNumber, dateOfBirth);
					} 

				else if (choice == 2) 
					{
						System.out.println("View Members selected.");
						QueryMember.queryMembers();
					} 

				else if (choice == 3) 
					{
						System.out.println("Update Member selected.");
						System.out.println();
						System.out.println("Enter the member's name : ");
						name = scanner.nextLine();
						System.out.println("Enter the new email : ");
						email = scanner.nextLine();
						UpdateMember.updateMember(name, email);
					} 

				else if (choice == 4) 
					{
						System.out.println("Delete Member selected.");
						System.out.println();
						System.out.println("Enter the member ID to delete : ");
						memberID = scanner.nextInt();
						DeleteMember.deleteMember(memberID);
					} 

				else if (choice == 5) 
					{
						System.out.println("Add Membership selected.");
						System.out.println();
						System.out.print("Enter the plan type : ");
						String planType = scanner.nextLine();
						System.out.print("Enter the start date (YYYY-MM-DD) : ");
						String startDate = scanner.nextLine();
						System.out.print("Enter the end date (YYYY-MM-DD) : ");
						String endDate = scanner.nextLine();
						System.out.print("Is the membership active? (1 for true, 0 for false) : ");
						int isActive = scanner.nextInt();
						scanner.nextLine(); // Consume newline
						System.out.print("Enter the member ID for this membership : ");
						memberID = scanner.nextInt();
						InsertMembership.insertMembership(planType, startDate, endDate, isActive, memberID);
					}

				else if (choice == 6) 
					{
						System.out.println("View Active Memberships selected.");
						QueryMembership.queryMembership();
					}

				else if (choice == 7) 
					{
						System.out.println("Update Membership selected.");
						System.out.println();
						System.out.print("Enter the member ID for the membership to update : ");
						memberID = scanner.nextInt();
						scanner.nextLine(); // Consume newline
						System.out.print("Enter the new plan type : ");
						String planType = scanner.nextLine();
						System.out.print("Enter the new start date (YYYY-MM-DD) : ");
						String startDate = scanner.nextLine();
						System.out.print("Enter the new end date (YYYY-MM-DD) : ");
						String endDate = scanner.nextLine();
						System.out.print("Is the membership active? (1 for true, 0 for false) : ");
						int isActive = scanner.nextInt();
						UpdateMembership.updateMembership(memberID, planType, startDate, endDate, isActive);
					}

				else if (choice == 8) 
					{
						System.out.println("Delete Membership selected.");
						System.out.println();
						System.out.print("Enter the member ID for the membership to delete : ");
						memberID = scanner.nextInt();
						DeleteMembership.deleteMembership(memberID);
					}

				else if (choice == 9) 
					{
						System.out.println("Add Trainer selected.");
						System.out.println();
						System.out.print("Enter the name : ");
						name = scanner.nextLine();
						System.out.print("Enter the email : ");
						email = scanner.nextLine();
						InsertTrainer.insertTrainer(name, email);
					}

				else if (choice == 10) 
					{
						System.out.println("View Trainers selected.");
						QueryTrainer.queryTrainers();
					}

				else if (choice == 11) 
					{
						System.out.println("Update Trainer selected.");
						System.out.println();
						System.out.print("Enter the trainer ID to update : ");
						int trainerID = scanner.nextInt();
						scanner.nextLine(); // Consume newline
						System.out.print("Enter the new name : ");
						name = scanner.nextLine();
						System.out.print("Enter the new email : ");
						email = scanner.nextLine();
						UpdateTrainer.updateTrainer(trainerID, name, email);
					}

				else if (choice == 12) 
					{
						System.out.println("Delete Trainer selected.");
						System.out.println();
						System.out.print("Enter the trainer ID to delete : ");
						int trainerID = scanner.nextInt();
						DeleteTrainer.deleteTrainer(trainerID);
					}

				else if (choice == 13)
					{
						System.out.println("Add Class selected.");
						System.out.println();
						System.out.print("Enter the title : ");
						String title = scanner.nextLine();
						System.out.print("Enter the schedule (dd/mm/yyyy 12:00) : ");
						String schedule = scanner.nextLine();
						System.out.print("Enter the capacity");
						int capacity = scanner.nextInt();
						InsertClass.insertClass(title, schedule, capacity);
					}

				else if (choice == 14)
					{
						System.out.println("View Classes selected.");
						QueryClass.queryClasses();
					}

				else if (choice == 15)
					{
						System.out.println("Update Class selected.");
						System.out.println();
						System.out.print("Enter the class ID to update : ");
						int classID = scanner.nextInt();
						scanner.nextLine(); // Consume newline
						System.out.print("Enter the new title : ");
						String title = scanner.nextLine();
						System.out.print("Enter the new schedule : ");
						String schedule = scanner.nextLine();
						System.out.print("Enter the new capacity : ");
						int capacity = scanner.nextInt();
						UpdateClass.updateClass(classID, title, schedule, capacity);
					}

				else if (choice == 16)
					{
						System.out.println("Delete Class selected.");
						System.out.println();
						System.out.print("Enter the class ID to delete : ");
						int classID = scanner.nextInt();
						DeleteClass.deleteClass(classID);
					}

				else if (choice == 17)
					{
						System.out.println("Add Booking selected.");
						System.out.println();
						System.out.print("Enter the booking date (dd/mm/yyyy) : ");
						String bookingDate = scanner.nextLine();
						System.out.print("Enter the status : ");
						String status = scanner.nextLine();
						InsertBooking.insertBooking(bookingDate, status);
					}

				else if (choice == 18)
					{
						System.out.println("View Bookings selected.");
						QueryBooking.queryBookings();
					}

				else if (choice == 19)
					{
						System.out.println("Update Booking selected.");
						System.out.println();
						System.out.print("Enter the booking ID to update : ");
						int bookingID = scanner.nextInt();
						scanner.nextLine(); // Consume newline
						System.out.print("Enter the new booking date (dd/mm/yyyy) : ");
						String bookingDate = scanner.nextLine();
						System.out.print("Enter the new status : ");
						String status = scanner.nextLine();
						UpdateBooking.updateBooking(bookingID, bookingDate, status);
					}

				else if (choice == 20)
					{
						System.out.println("Delete Booking selected.");
						System.out.println();
						System.out.print("Enter the booking ID to delete : ");
						int bookingID = scanner.nextInt();
						DeleteBooking.deleteBooking(bookingID);
					}

				else 
					{
						System.out.println("Invalid choice. Please try again.");
					}
		}
		scanner.close();
	} // end main
} // end class