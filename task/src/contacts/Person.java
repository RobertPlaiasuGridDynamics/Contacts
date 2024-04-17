package contacts;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Person extends Record implements Serializable {


    private final transient String fields = "name, surname, birth, gender, number";
    Person(Scanner scanner) {

        super("person");
        setName(scanner);
        setSurname(scanner);
        setBirthDate(scanner);
        setGender(scanner);
        setNumber(scanner);
    }

    // returns full name
    @Override
    public String getName() {
        return this.name + " " + this.surname;
    }

    @Override
    public void setName(Scanner scanner) {
        System.out.println("Enter the name: ");
        String name = scanner.next();
        name += scanner.nextLine();
        this.name = name;
    }

    public String getSurname()
    {
        return this.surname;
    }

    public void setSurname(Scanner scanner)
    {
        System.out.println("Enter surname: ");
        String surname = scanner.next();
        surname += scanner.nextLine();
        this.surname = surname;
    }

    public String getFamilyName()
    {
        return this.name;
    }

    @Override
    public void edit(Scanner scanner, String field) {
        switch (field)
        {
            case "name":
                setName(scanner);
                System.out.println("Saved");
                break;
            case "number":
                setNumber(scanner);
                System.out.println("Saved");
                break;
            case "surname":
                setSurname(scanner);
                System.out.println("Saved");
                break;
            case "gender":
                setGender(scanner);
                System.out.println("Saved");
                break;
            case "birth":
                setBirthDate(scanner);
                System.out.println("Saved");
                break;
            default:
                System.out.println("Field doesn't exist!");
                break;

        }
        setTimeEdited();
    }

    public String getBirthDate() {
        return birthDate==null ? "[no data]" : birthDate.toString();
    }

    public void setBirthDate(Scanner scanner)
    {
        System.out.println("Enter the birth date:");

        String birthDate = scanner.nextLine();
        // birthDate += scanner.nextLine();
        if(BirthDateValidator.birthDateIsValid(birthDate)) {
            this.birthDate = LocalDate.parse(birthDate);
        }
        else {
            System.out.println("Bad birth date!");
        }
    }

    public String getGender() {
         return this.gender.isEmpty() ? "[no data]" : this.gender;
    }
    public void setGender(Scanner scanner)
    {
        System.out.println("Enter the gender (M, F): ");
        // gender += scanner.nextLine();
        this.gender = scanner.nextLine();

    }

    @Override
    public void print() {
        System.out.println("Name: " + getFamilyName());
        System.out.println("Surname: " + getSurname());
        System.out.println("Birth date: " + getBirthDate());
        System.out.println("Gender: " + getGender());
        System.out.println("Number: " + getNumber());
        System.out.println("Time created: " + getTimeCreated());
        System.out.println("Time last edit: " + getTimeEdited());
    }

    private static class BirthDateValidator
    {
        static boolean birthDateIsValid(String birthDate)
        {
            try {
                LocalDate date = LocalDate.parse(birthDate);
            }
            catch (DateTimeParseException e)
            {
                return false;
            }
            return  true;
        }
    }

    public String getFields()
    {
        return fields;
    }

    @Override
    String stringForSearchQuery() {
        return this.getNumber() + " " + this.getGender() + " " + this.getName() + this.getBirthDate();
    }


}
