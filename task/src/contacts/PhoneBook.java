package contacts;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PhoneBook {
    private static PhoneBook instance = null;
    List<Record> phoneBookRecords;
    private transient String fileName = "default.db";
    private PhoneBook(String fileName)
    {
        phoneBookRecords = new ArrayList<>();
        if(fileName != null) {
            this.fileName = fileName;
            deserialization();
        }
    }



    public static PhoneBook getInstance(String fileName)
    {
        if(instance == null)
        {
            instance = new PhoneBook(fileName);
        }

        return instance;
    }
    
    public void add(Scanner scanner)
    {
        System.out.println("Enter the type (person, organization):");
        String type = scanner.next();
        type += scanner.nextLine();
        if(type.equals("person"))
        {
            this.list().add(new Person(scanner));

        }
        else if(type.equals("organization"))
        {
            this.list().add(new Organization(scanner));
        }
        else
        {
            System.out.println("Invalid type!");
        }
    }

    public void serialization() {
        try{
            FileOutputStream file = new FileOutputStream(fileName);
            ObjectOutputStream out = new ObjectOutputStream(file);

            out.writeObject(phoneBookRecords);

            file.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void deserialization()
    {
        try{
            FileInputStream file = new FileInputStream(fileName);
            ObjectInputStream in = new ObjectInputStream(file);

            // Method for deserialization of object
            phoneBookRecords = (List<Record>) in.readObject();

            in.close();
            file.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    
    public void remove(int index)
    {
        phoneBookRecords.remove(index);
        serialization();
        System.out.println("Record removed");
    }
    
    public List<Record> list()
    {
        return phoneBookRecords;
    }
    
    public void edit(Scanner scanner)
    {
        System.out.println("Select a record:");
        int index = Integer.parseInt( scanner.next());
        scanner.nextLine();
        System.out.println("Select a field (" + phoneBookRecords.get(index-1).getFields() + ")");
        String field = scanner.next();
        scanner.nextLine();
        phoneBookRecords.get(index-1).edit(scanner,field);
        serialization();
    }
    
    public int count()
    {
        return phoneBookRecords.size();
    }
    
    public Record info(int index)
    {
        return phoneBookRecords.get(index);
    }

    public boolean isEmpty()
    {
        return phoneBookRecords.isEmpty();
    }

    public void showAll()
    {
        for (Record record : phoneBookRecords)
        {
            this.show(record);
        }
    }

    public void show(Record record)
    {
        record.print();
    }


    public void showShortList(List<Record> list)
    {
        for(int i = 0;i < list.size();i++)
        {
            System.out.println(i + 1 + ". " + list.get(i).getName());
        }
    }

    public List<Record> search(String searchedPhrase)
    {
        List<Record> founded = new ArrayList<>();
        for (int i = 0;i < phoneBookRecords.size();i++)
        {
            if(foundWordInPhrase(phoneBookRecords.get(i).stringForSearchQuery(),searchedPhrase))
            {
                founded.add(phoneBookRecords.get(i));
            }
        }


        return founded;

    }

    private static boolean foundWordInPhrase(String phrase,String searchedWord)
    {
        String[] splitText = phrase.split(" ",0);
        for (String s : splitText )
        {
            if(s.toLowerCase().contains(searchedWord.toLowerCase()))
            {
                return true;
            }
        }
        return false;

    }
}
