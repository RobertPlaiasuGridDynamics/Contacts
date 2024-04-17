package contacts;

public class NumberValidator {
    public static boolean validateNumber(String number)
    {

        return number.matches("^\\+?([\\da-zA-Z]+[\\s-]?)?(\\([\\da-zA-Z]{2,}(\\)[\\s-]|\\)$))?([\\da-zA-Z]{2,}[\\s-]?)*([\\da-zA-Z]{2,})?$");
    }
}
