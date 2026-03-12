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
						try
							{
								System.out.println("Add Member selected.");
								System.out.println();
								System.out.print("Enter the name : ");
								name = scanner.nextLine();
								Validator.validateNotEmpty(name, "Name");
								System.out.print("Enter the email : ");
								email = scanner.nextLine();
								Validator.validateEmail(email);
								System.out.print("Enter the phone number : ");
								phoneNumber = scanner.nextLine();
								Validator.validatePhoneNumber(phoneNumber);
								System.out.print("Enter the date of birth (YYYY-MM-DD) : ");
								dateOfBirth = scanner.nextLine();
								Validator.validateDate(dateOfBirth);
								System.out.print("Enter the password : ");
								String password = scanner.nextLine();
								Validator.validatePassword(password);
								InsertMember.insertMember(name, email, phoneNumber, dateOfBirth, password);
							}
						catch (Exception e)
							{
								System.out.println("Error occurred while adding member.");
								e.printStackTrace();
							}
					} 

				else if (choice == 2) 
					{
						try
							{
								System.out.println("View Members selected.");
								QueryMember.queryMembers();
							}
						catch (Exception e)
							{
								System.out.println("Error occurred while viewing members.");
								e.printStackTrace();
							}
					} 

				else if (choice == 3) 
					{
						try
							{
								System.out.println("Update Member selected.");
								System.out.println();
								System.out.println("Enter the member ID to update : ");
								memberID = scanner.nextInt();
								Validator.validateID(memberID);
								scanner.nextLine(); // Consume newline
								System.out.println("Enter the member's name : ");
								name = scanner.nextLine();
								Validator.validateNotEmpty(name, "Name");
								System.out.println("Enter the new email : ");
								email = scanner.nextLine();
								Validator.validateEmail(email);
								System.out.println("Enter the new phone number : ");
								phoneNumber = scanner.nextLine();
								Validator.validatePhoneNumber(phoneNumber);
								System.out.println("Enter the new date of birth (YYYY-MM-DD) : ");
								dateOfBirth = scanner.nextLine();
								Validator.validateDate(dateOfBirth);
								System.out.print("Enter the new password : ");
								String password = scanner.nextLine();
								Validator.validatePassword(password);
								UpdateMember.updateMember(memberID, name, email, phoneNumber, dateOfBirth, password);
							}
						catch (Exception e)
							{
								System.out.println("Error occurred while updating member.");
								e.printStackTrace();
							}
					} 

				else if (choice == 4) 
					{
						try
							{
								System.out.println("Delete Member selected.");
								System.out.println();
								System.out.println("Enter the member ID to delete : ");
								memberID = scanner.nextInt();
								Validator.validateID(memberID);
								DeleteMember.deleteMember(memberID);
							}
						catch (Exception e)
							{
								System.out.println("Error occurred while deleting member.");
								e.printStackTrace();
							}
					} 

				else if (choice == 5) 
					{
						try
							{
								System.out.println("Add Membership selected.");
								System.out.println();
								System.out.print("Enter the plan type : ");
								String planType = scanner.nextLine();
								Validator.validateNotEmpty(planType, "Plan Type");
								System.out.print("Enter the start date (YYYY-MM-DD) : ");
								String startDate = scanner.nextLine();
								Validator.validateDate(startDate);
								System.out.print("Enter the end date (YYYY-MM-DD) : ");
								String endDate = scanner.nextLine();
								Validator.validateDate(endDate);
								Validator.validateStartEndDate(startDate, endDate);
								System.out.print("Is the membership active? (1 for true, 0 for false) : ");
								int isActive = scanner.nextInt();
								Validator.validateBoolean(isActive);
								scanner.nextLine(); // Consume newline
								System.out.print("Enter the member ID for this membership : ");
								memberID = scanner.nextInt();
								Validator.validateID(memberID);
								scanner.nextLine(); // Consume newline
								InsertMembership.insertMembership(planType, startDate, endDate, isActive, memberID);
							}
						catch (Exception e)
							{
								System.out.println("Error occurred while adding membership.");
								e.printStackTrace();
							}
					}

				else if (choice == 6) 
					{
						try
							{
								System.out.println("View Active Memberships selected.");
								QueryMembership.queryMembership();
							}
						catch (Exception e)
							{
								System.out.println("Error occurred while viewing memberships.");
								e.printStackTrace();
							}
					}

				else if (choice == 7) 
					{
						try
							{
								System.out.println("Update Membership selected.");
								System.out.println();
								System.out.print("Enter the member ID for the membership to update : ");
								memberID = scanner.nextInt();
								Validator.validateID(memberID);
								scanner.nextLine(); // Consume newline
								System.out.print("Enter the new plan type : ");
								String planType = scanner.nextLine();
								Validator.validateNotEmpty(planType, "Plan Type");
								System.out.print("Enter the new start date (YYYY-MM-DD) : ");
								String startDate = scanner.nextLine();
								Validator.validateDate(startDate);
								System.out.print("Enter the new end date (YYYY-MM-DD) : ");
								String endDate = scanner.nextLine();
								Validator.validateDate(endDate);
								Validator.validateStartEndDate(startDate, endDate);
								System.out.print("Is the membership active? (1 for true, 0 for false) : ");
								int isActive = scanner.nextInt();
								Validator.validateBoolean(isActive);
								scanner.nextLine(); // Consume newline
								UpdateMembership.updateMembership(memberID, planType, startDate, endDate, isActive);
							}
						catch (Exception e)
							{
								System.out.println("Error occurred while updating membership.");
								e.printStackTrace();
							}
					}

				else if (choice == 8) 
					{
						try
							{
								System.out.println("Delete Membership selected.");
								System.out.println();
								System.out.print("Enter the member ID for the membership to delete : ");
								memberID = scanner.nextInt();
								Validator.validateID(memberID);
								scanner.nextLine(); // Consume newline
								DeleteMembership.deleteMembership(memberID);
							}
						catch (Exception e)
							{
								System.out.println("Error occurred while deleting membership.");
								e.printStackTrace();
							}
					}

				else if (choice == 9) 
					{
						try
							{
								System.out.println("Add Trainer selected.");
								System.out.println();
								System.out.print("Enter the name : ");
								name = scanner.nextLine();
								Validator.validateNotEmpty(name, "Name");
								System.out.print("Enter the email : ");
								email = scanner.nextLine();
								Validator.validateEmail(email);
								System.out.print("Enter the password : ");
								String password = scanner.nextLine();
								Validator.validatePassword(password);
								InsertTrainer.insertTrainer(name, email, password);
							}
						catch (Exception e)
							{
								System.out.println("Error occurred while adding trainer.");
								e.printStackTrace();
							}
					}

				else if (choice == 10) 
					{
						try
							{
								System.out.println("View Trainers selected.");
								QueryTrainer.queryTrainers();
							}
						catch (Exception e)
							{
								System.out.println("Error occurred while viewing trainers.");
								e.printStackTrace();
							}
					}

				else if (choice == 11) 
					{
						try
							{
								System.out.println("Update Trainer selected.");
								System.out.println();
								System.out.print("Enter the trainer ID to update : ");
								int trainerID = scanner.nextInt();
								Validator.validateID(trainerID);
								scanner.nextLine(); // Consume newline
								System.out.print("Enter the new name : ");
								name = scanner.nextLine();
								Validator.validateNotEmpty(name, "Name");
								System.out.print("Enter the new email : ");
								email = scanner.nextLine();
								Validator.validateEmail(email);
								System.out.print("Enter the new password : ");
								String password = scanner.nextLine();
								Validator.validatePassword(password);
								UpdateTrainer.updateTrainer(trainerID, name, email, password);
							}
						catch (Exception e)
							{
								System.out.println("Error occurred while updating trainer.");
								e.printStackTrace();
							}
					}

				else if (choice == 12) 
					{
						try
							{
								System.out.println("Delete Trainer selected.");
								System.out.println();
								System.out.print("Enter the trainer ID to delete : ");
								int trainerID = scanner.nextInt();
								Validator.validateID(trainerID);
								scanner.nextLine(); // Consume newline
								DeleteTrainer.deleteTrainer(trainerID);
							}
						catch (Exception e)
							{
								System.out.println("Error occurred while deleting trainer.");
								e.printStackTrace();
							}
					}

				else if (choice == 13)
					{
						try
							{
								System.out.println("Add Class selected.");
								System.out.println();
								System.out.print("Enter the title : ");
								String title = scanner.nextLine();
								Validator.validateNotEmpty(title, "Title");
								System.out.print("Enter the schedule (YYYY-MM-DD 12:00) : ");
								String schedule = scanner.nextLine();
								Validator.validateNotEmpty(schedule, "Schedule");
								Validator.validateDate(schedule.substring(0, 10)); // Validate date part of schedule
								Validator.validateClassTime(schedule.substring(11)); // Validate time part of schedule
								System.out.print("Enter the capacity : ");
								int capacity = scanner.nextInt();
								Validator.validateClassCapacity(capacity);
								scanner.nextLine(); // Consume newline
								InsertClass.insertClass(title, schedule, capacity);
							}
						catch (Exception e)
							{
								System.out.println("Error occurred while adding class.");
								e.printStackTrace();
							}
					}

				else if (choice == 14)
					{
						try
							{
								System.out.println("View Classes selected.");
								QueryClass.queryClasses();
							}
						catch (Exception e)
							{
								System.out.println("Error occurred while viewing classes.");
								e.printStackTrace();
							}
					}

				else if (choice == 15)
					{
						try
							{
								System.out.println("Update Class selected.");
								System.out.println();
								System.out.print("Enter the class ID to update : ");
								int classID = scanner.nextInt();
								Validator.validateID(classID);
								scanner.nextLine(); // Consume newline
								System.out.print("Enter the new title : ");
								String title = scanner.nextLine();
								Validator.validateNotEmpty(title, "Title");
								System.out.print("Enter the new schedule (YYYY-MM-DD 12:00) : ");
								String schedule = scanner.nextLine();
								Validator.validateNotEmpty(schedule, "Schedule");
								Validator.validateDate(schedule.substring(0, 10)); // Validate date part of schedule
								Validator.validateClassTime(schedule.substring(11)); // Validate time part of schedule
								System.out.print("Enter the new capacity : ");
								int capacity = scanner.nextInt();
								Validator.validateClassCapacity(capacity);
								scanner.nextLine(); // Consume newline
								UpdateClass.updateClass(classID, title, schedule, capacity);
							}
						catch (Exception e)
							{
								System.out.println("Error occurred while updating class.");
								e.printStackTrace();
							}
					}

				else if (choice == 16)
					{
						try
							{
								System.out.println("Delete Class selected.");
								System.out.println();
								System.out.print("Enter the class ID to delete : ");
								int classID = scanner.nextInt();
								Validator.validateID(classID);
								scanner.nextLine(); // Consume newline
								DeleteClass.deleteClass(classID);
							}
						catch (Exception e)
							{
								System.out.println("Error occurred while deleting class.");
								e.printStackTrace();
							}
					}

				else if (choice == 17)
					{
						try
							{
								System.out.println("Add Booking selected.");
								System.out.println();
								System.out.print("Enter the booking date (YYYY-MM-DD) : ");
								String bookingDate = scanner.nextLine();
								Validator.validateDate(bookingDate);
								System.out.print("Enter the status : ");
								String status = scanner.nextLine();
								InsertBooking.insertBooking(bookingDate, status);
							}
						catch (Exception e)
							{
								System.out.println("Error occurred while adding booking.");
								e.printStackTrace();
							}
					}

				else if (choice == 18)
					{
						try
							{
								System.out.println("View Bookings selected.");
								QueryBooking.queryBookings();
							}
						catch (Exception e)
							{
								System.out.println("Error occurred while viewing bookings.");
								e.printStackTrace();
							}
					}

				else if (choice == 19)
					{
						try
							{
								System.out.println("Update Booking selected.");
								System.out.println();
								System.out.print("Enter the booking ID to update : ");
								int bookingID = scanner.nextInt();
								Validator.validateID(bookingID);
								scanner.nextLine(); // Consume newline
								System.out.print("Enter the new booking date (YYYY-MM-DD) : ");
								String bookingDate = scanner.nextLine();
								Validator.validateDate(bookingDate);
								System.out.print("Enter the new status : ");
								String status = scanner.nextLine();
								Validator.validateNotEmpty(status, "Status");
								UpdateBooking.updateBooking(bookingID, bookingDate, status);
							}
						catch (Exception e)
							{
								System.out.println("Error occurred while updating booking.");
								e.printStackTrace();
							}
					}

				else if (choice == 20)
					{
						try
							{
								System.out.println("Delete Booking selected.");
								System.out.println();
								System.out.print("Enter the booking ID to delete : ");
								int bookingID = scanner.nextInt();
								Validator.validateID(bookingID);
								scanner.nextLine(); // Consume newline
								DeleteBooking.deleteBooking(bookingID);
							}
						catch (Exception e)
							{
								System.out.println("Error occurred while deleting booking.");
								e.printStackTrace();
							}
					}

				else 
					{
						System.out.println("Invalid choice. Please try again.");
					}
		}
		scanner.close();
	} // end main
} // end class