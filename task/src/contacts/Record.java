package contacts;


import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

public abstract class Record implements Serializable {
    protected String name;
    protected String number = "";
    protected final String type;
    protected LocalDateTime timeCreated;
    protected LocalDateTime timeEdited;
    @Serial
    private static final long serialVersionUID = 2L;
    protected String gender = "";
    protected LocalDate birthDate = null;
    protected String surname = null;

    protected String address = "";

    // abstract methods
    abstract String getName();

    abstract void setName(Scanner scanner);

    abstract void edit(Scanner scanner, String field);

    abstract void print();

    abstract String getFields();

    abstract String stringForSearchQuery();

    Record(String type)
    {
        this.type = type;
        timeCreated = LocalDateTime.now();
        timeEdited = LocalDateTime.now();
    }

    
    public void setNumber(Scanner scanner)
    {
        System.out.println("Enter the number:");
        String phoneNumber = scanner.next();
        phoneNumber+= scanner.nextLine();
        if(NumberValidator.validateNumber(phoneNumber))
        {
            this.number = phoneNumber;
        }
        else {
            System.out.println("Wrong number format!");
        }
    }



    public String getNumber()
    {
        return number.equals("") ? "[no number]" : number;
    }

    public void setTimeCreated()
    {
        this.timeCreated = LocalDateTime.now();
    }

    public void setTimeEdited()
    {
        this.timeEdited = LocalDateTime.now();
    }

    public LocalDateTime getTimeCreated() {
        return timeCreated.truncatedTo(ChronoUnit.MINUTES);
    }

    public LocalDateTime getTimeEdited()
    {
        return timeEdited.truncatedTo(ChronoUnit.MINUTES);
    }
    public String getType()
    {
        return this.type;
    }
}
