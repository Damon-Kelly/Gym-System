// Student Name :      Damon Kelly
// Student Id Number : C00307057
// Date :              26/02/2026
// Purpose :           Custom exception class for input validation errors.
//                     Extends Exception (checked exception), mirrors Q2 pattern.

public class ValidationException extends Exception
{ // begin class

    // Default constructor
    public ValidationException()
    {
        super("Validation error: invalid input.");
    }

    // Constructor that accepts a custom error message
    public ValidationException(String message)
    {
        super(message);
    }

} // end class
