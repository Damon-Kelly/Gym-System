// Student Name :      Damon Kelly
// Student Id Number : C00307057
// Date :              13/04/2026
// Purpose :           class for validating user input before sending to the database.
//                     Throws ValidationException (custom checked exception) on invalid input.

public class Validator
{
    // Validates that a string is not null or empty (including whitespace-only)
    public static void validateNotEmpty(String input, String fieldName) throws ValidationException
    {
        if (input == null)
            {
                throw new ValidationException(fieldName + " cannot be null.");
            }
        if (input.trim().isEmpty())
            {
                throw new ValidationException(fieldName + " cannot be empty or blank.");
            }
    }

    // Validates that a string is a valid email address (contains @ symbol)
    public static void validateEmail(String email) throws ValidationException
    {
        if (email == null || email.isEmpty())
            {
                throw new ValidationException("Invalid email format, email cannot be null or empty.");
            }
        for (int i = 0; i < email.length(); i++)
            {
                if (email.charAt(i) == '@')
                    {
                        return; // Valid email format
                    }
            }
        throw new ValidationException("Invalid email format, email must contain @ symbol.");
    }

    // Validates that a string is a valid phone number (digits only, length 8-15)
    public static void validatePhoneNumber(String phoneNumber) throws ValidationException
    {
        if (phoneNumber == null || phoneNumber.length() < 8 || phoneNumber.length() > 15)
            {
                throw new ValidationException("Phone number must be between 8 and 15 digits long.");
            }
        for (int i = 0; i < phoneNumber.length(); i++)
            {
                if (phoneNumber.charAt(i) < '0' || phoneNumber.charAt(i) > '9')
                    {
                        throw new ValidationException("Phone number must contain digits only.");
                    }
            }
    }

    // Validates that a string is a valid date in the format YYYY-MM-DD
    public static void validateDate(String date) throws ValidationException
    {
        if (date == null || date.length() != 10 || date.charAt(4) != '-' || date.charAt(7) != '-')
            {
                throw new ValidationException("Date must be in the format YYYY-MM-DD.");
            }
        String yearStr = date.substring(0, 4);
        String monthStr = date.substring(5, 7);
        String dayStr = date.substring(8, 10);
        try
            {
                int year = Integer.parseInt(yearStr);
                int month = Integer.parseInt(monthStr);
                int day = Integer.parseInt(dayStr);
                if (month < 1 || month > 12)
                    {
                        throw new ValidationException("Month must be between 1 and 12.");
                    }
                if (day < 1 || day > 31)
                    {
                        throw new ValidationException("Day must be between 1 and 31.");
                    }
                if (year < 1900 || year > 2100)
                    {
                        throw new ValidationException("Year must be between 1900 and 2100.");
                    }
            }
        catch (NumberFormatException e)
            {
                throw new ValidationException("Date must contain valid numbers.");
            }
    }

    // Validates that a date of birth is reasonable (year between 1940 and 2010)
    public static void validateDateOfBirth(String dateOfBirth) throws ValidationException
    {
        validateDate(dateOfBirth);
        String yearStr = dateOfBirth.substring(0, 4);
        try
            {
                int year = Integer.parseInt(yearStr);
                if (year < 1940 || year > 2010)
                    {
                        throw new ValidationException("Year of birth must be between 1940 and 2010.");
                    }
            }
        catch (NumberFormatException e)
            {
                throw new ValidationException("Date of birth must contain valid numbers.");
            }
    }

    // Validates that a password is at least 6 characters long
    public static void validatePassword(String password) throws ValidationException
    {
        if (password == null || password.length() < 6)
            {
                throw new ValidationException("Password must be at least 6 characters long.");
            }
    }

    // Validates that an ID is a positive integer
    public static void validateID(int id) throws ValidationException
    {
        if (id <= 0)
            {
                throw new ValidationException("ID must be a positive integer.");
            }
    }

    // Validates that a boolean input is 0 or 1
    public static void validateBoolean(int input) throws ValidationException
    {
        if (input != 0 && input != 1)
            {
                throw new ValidationException("Input must be 0 or 1.");
            }
    }

    // Validates that class capacity is a positive integer
    public static void validateClassCapacity(int capacity) throws ValidationException
    {
        if (capacity <= 0)
            {
                throw new ValidationException("Class capacity must be a positive integer.");
            }
    }

    // Validates that start date is before end date
    public static void validateStartEndDate(String startDate, String endDate) throws ValidationException
    {
        validateDate(startDate);
        validateDate(endDate);
        String startYearStr = startDate.substring(0, 4);
        String startMonthStr = startDate.substring(5, 7);
        String startDayStr = startDate.substring(8, 10);
        String endYearStr = endDate.substring(0, 4);
        String endMonthStr = endDate.substring(5, 7);
        String endDayStr = endDate.substring(8, 10);
        try
            {
                int startYear = Integer.parseInt(startYearStr);
                int startMonth = Integer.parseInt(startMonthStr);
                int startDay = Integer.parseInt(startDayStr);
                int endYear = Integer.parseInt(endYearStr);
                int endMonth = Integer.parseInt(endMonthStr);
                int endDay = Integer.parseInt(endDayStr);
                if (startYear > endYear || (startYear == endYear && startMonth > endMonth) ||
                    (startYear == endYear && startMonth == endMonth && startDay >= endDay))
                    {
                        throw new ValidationException("Start date must be before end date.");
                    }
            }
        catch (NumberFormatException e)
            {
                throw new ValidationException("Dates must contain valid numbers.");
            }
    }

    // Validates that a time is in the format HH:MM and represents a valid time
    public static void validateClassTime(String time) throws ValidationException
    {
        if (time == null || time.length() != 5 || time.charAt(2) != ':')
            {
                throw new ValidationException("Time must be in the format HH:MM.");
            }
        String hourStr = time.substring(0, 2);
        String minuteStr = time.substring(3, 5);
        try
            {
                int hour = Integer.parseInt(hourStr);
                int minute = Integer.parseInt(minuteStr);
                if (hour < 0 || hour > 23)
                    {
                        throw new ValidationException("Hour must be between 0 and 23.");
                    }
                if (minute < 0 || minute > 59)
                    {
                        throw new ValidationException("Minute must be between 0 and 59.");
                    }
            }
        catch (NumberFormatException e)
            {
                throw new ValidationException("Time must contain valid numbers.");
            }
    }
}
