package contacts;

import java.io.Serializable;
import java.util.Scanner;

public class Organization extends Record implements Serializable {
    private final transient String fields = "address, number";

    Organization(Scanner scanner) {
        super("organization");
        setName(scanner);
        setAddress(scanner);
        setNumber(scanner);
    }



    @Override
    public void edit(Scanner scanner,String field) {

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
            case "address":
                setAddress(scanner);
                System.out.println("Saved");
                break;
            default:
                System.out.println("Field doesn't exist!");
                break;

        }
        setTimeEdited();

    }



    private void setAddress(Scanner scanner)
    {
        System.out.println("Address:");
        String address = scanner.next();
        address += scanner.nextLine();
        this.address = address;
    }

    public String getAddress() {
        return this.address;
    }

    public String getName()
    {
        return this.name;
    }

    public void setName(Scanner scanner)
    {
        System.out.println("Enter the organization name: ");
        String name = scanner.next();
        name +=  scanner.nextLine();
        this.name = name;
    }

    @Override
    public void print() {
        System.out.println("Organization name: " + getName());
        System.out.println("Address: " + getAddress());
        System.out.println("Number: " + getNumber());
        System.out.println("Time created: " + getTimeCreated());
        System.out.println("Time last edit: " + getTimeEdited());
    }

    public String getFields()
    {
        return fields;
    }

    @Override
    String stringForSearchQuery() {
        return this.getNumber() + " " + this.getAddress() + " " + this.getName();
    }


}
