package contacts;

import java.io.FileOutputStream;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        if (args.length < 2)
        {

            Menu.start(scanner,null);
        }
        else{
            Menu.start(scanner,args[1]);
        }
        scanner.close();
    }

}
