import com.reecegroup.addressbooktracker.AddressBookManager;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        //Display Menu

        System.out.println("Welcome Branch Manager!.\n\n");

        System.out.println("Please enter 1 to see Address Book with name and phone numbers of contact entries\n\n");
        System.out.println("Please enter 2 to add new contact entries\n\n");

        Scanner reader = new Scanner(System.in);
        String option = reader.nextLine();

        AddressBookManager.performOperations(option);
    }

}
