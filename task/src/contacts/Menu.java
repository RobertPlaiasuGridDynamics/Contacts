package contacts;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Scanner;

public class Menu {
    public static void start(Scanner scanner,String fileName)
    {
        PhoneBook phoneBook = PhoneBook.getInstance(fileName);
        loop: while (true) {
            System.out.print("[menu] Enter action (add, remove, edit, count, info, exit):");
            String action = scanner.next();
            switch (action) {
                case "add":
                    phoneBook.add(scanner);
                    System.out.println("The record added.");
                    break;
                case "remove":
                    if (phoneBook.isEmpty()) {
                        System.out.println("No records to remove!");
                    } else {
                        int i = Integer.parseInt(scanner.next());
                        phoneBook.remove(i - 1);
                        System.out.println("Record removed!");
                    }
                    break;
                case "count":
                    System.out.println("The Phone Book has " + phoneBook.count() + " records.");
                    break;
                case "list":
                    if(!phoneBook.isEmpty()) {
                        phoneBook.showShortList(phoneBook.list());
                        System.out.println("Enter action ([number], back):");
                        String listAction = scanner.next();
                        System.out.println("\n");
                        if(listAction.equals("back"))
                        {
                            break;
                        }
                        else if(isDigit(listAction)){

                            int index  = Integer.parseInt(listAction);
                            Record phoneBookRecord =  phoneBook.info(index-1);
                            phoneBook.show(phoneBookRecord);
                            System.out.println("[record] Enter action (edit, delete, menu):");

                            String actionRecord = scanner.next();

                            if(actionRecord.equals("edit"))
                            {
                                System.out.println("Select a field (" + phoneBookRecord.getFields() + "):");
                                String field = scanner.next();
                                field += scanner.nextLine();
                                phoneBookRecord.edit(scanner,field);
                                phoneBook.serialization();
                            }
                            else if(actionRecord.equals("delete"))
                            {
                                phoneBook.remove(index);

                            }
                            else {
                                break;
                            }

                        }
                        else {
                            System.out.println("Invalid response");
                            break;
                        }
                        System.out.println("\n");
                    }
                    else{
                        System.out.println("List is empty!");
                    }
                    break;
                case "search":

                    while(true)
                    {
                        System.out.println("Enter search query:");
                        String searchedPhrase = scanner.next();
                        searchedPhrase += scanner.nextLine();

                        List<Record> phoneBooksFounded = phoneBook.search(searchedPhrase);

                        if(phoneBooksFounded.isEmpty())
                        {
                            System.out.println("No records founded!");
                            break;
                        }

                        phoneBook.showShortList(phoneBooksFounded);

                        System.out.println("\n[search] Enter action ([number], back, again):");
                        String searchAction = scanner.next();
                        searchAction += scanner.nextLine();

                        if(isDigit(searchAction)){
                            int index  = Integer.parseInt(searchAction);
                            Record phoneBookRecord =  phoneBooksFounded.get(index-1);
                            System.out.println("\n[record] Enter action (edit, delete, menu):");
                            String actionRecord = scanner.next();

                            if(actionRecord.equals("edit"))
                            {
                                System.out.println("Select a field (" + phoneBookRecord.getFields() + "):");
                                String field = scanner.next();
                                field += scanner.nextLine();
                                phoneBookRecord.edit(scanner,field);
                                phoneBook.serialization();
                            }
                            else if(actionRecord.equals("delete"))
                            {
                                phoneBook.remove(index);
                            }
                            break;
                        }
                        else if(searchAction.equals("back"))
                        {
                            break;
                        }
                        System.out.println("\n");
                    }
                    break;
                case "exit":
                    break loop;
                default:
                    break;

            }
            System.out.println("\n");
        }
    }


    private  static  String combineStrings(List<String> options)
    {
        StringBuilder builder = new StringBuilder();
        for(String option : options)
        {
            builder.append(", ");
            builder.append(option);
        }
        return builder.toString();
    }

    private static boolean isDigit(String number)
    {
        if (number == null)
        {
            return false;
        }
        try{
            int intNumber = Integer.parseInt(number);
        }
        catch (NumberFormatException e)
        {
           return false;
        }
        return true;
    }





}



