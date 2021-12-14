import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        //Data fields we will need
        Database database = new Database();
        Scanner keyboard = new Scanner(System.in);
        int choice;

        //main application
        do{
            int secondChoice, thirdChoice;
            String firstName, middleName, lastName, gender, address, phoneNumber, DOB, patientNote;
            middleName = null;
            boolean hasMiddleName = false;
            boolean moreToEdit = true;

            //the printed main menu
            System.out.println("*********MENU*********");
            System.out.println("What would you like to do?");
            System.out.println("1. Add a patient");
            System.out.println("2. Create and add a note associated with an existing patient");
            System.out.println("3. Retrieve note");
            System.out.println("4. Show all patients currently in the database");
            System.out.println("5. Exit");
            choice = keyboard.nextInt();

            //Input validation for choice
            while(choice < 0 || choice > 5)
            {
                System.out.println("That is not an option. Please choose one of the following choices:");
                System.out.println("1. Add a patient");
                System.out.println("2. Create and add a note associated with an existing patient");
                System.out.println("3. Retrieve note");
                System.out.println("4. Show all patients currently in the database");
                System.out.println("5. Exit");
                choice = keyboard.nextInt();
            }
            keyboard.nextLine();
            //Choice
            switch (choice)
            {
                case 1 ->
                        {
                    Patient dummyPatient = new Patient();

                    //Obtain patient's first name
                    System.out.println("Please enter the patient's first name:");
                    firstName = keyboard.nextLine();
                    dummyPatient.setFirstName(firstName);

                    //Ask if the patient has a middle name. If so, get their first name, otherwise continue with the program
                    System.out.println("Does the patient have a middle name?");
                    if(keyboard.nextLine().toUpperCase().charAt(0) == 'Y') hasMiddleName = true;
                    if(hasMiddleName){
                        System.out.println("Please enter the patient's middle name:");
                        middleName = keyboard.nextLine();
                        dummyPatient.setMiddleName(middleName);
                    }

                    //Obtain patient's last name
                    System.out.println("Please enter the patient's last name:");
                    lastName = keyboard.nextLine();
                    dummyPatient.setLastName(lastName);

                    //Obtain patient's gender
                    System.out.println("Please enter the patient's gender:");
                    gender = keyboard.nextLine();
                    dummyPatient.setGender(gender);

                    //obtain patient's address
                    System.out.println("Please enter the patient's address:");
                    address = keyboard.nextLine();
                    dummyPatient.setAddress(address);

                    //obtain patient's phone number
                    System.out.println("Please enter the patient's phone number with no dashes, spaces, or parentheses:");
                    phoneNumber = keyboard.nextLine();

                    //input validation for number; all characters must be digits
                    while(!phoneNumber.matches("[0-9]+")){
                        System.out.println("Please re-enter the patient's number with no dashes, parentheses, or spaces:");
                        phoneNumber = keyboard.nextLine();
                    }

                    //Input validation for number length
                    while(phoneNumber.length() != 10)
                    {
                        System.out.println("The phone number you entered is not 10 digits long. " +
                                "Please re-enter the patient's 10-digit phone number with no dashes, spaces, or parentheses:");
                        phoneNumber = keyboard.nextLine();
                    }

                    dummyPatient.setPhoneNumber(Long.parseLong(phoneNumber));

                    //Obtain patient's DOB
                    System.out.println("Please enter the patient's date of birth in the following format MM/dd/yyyy:");
                    DOB = keyboard.nextLine();
                    dummyPatient.setDOB(DOB);

                    //Ask user to confirm the data is correct
                    System.out.println("Is this patient's information correct? ");
                    System.out.println(dummyPatient);
                    System.out.println("1. Yes, confirm and add to database");
                    System.out.println("2. No, I'd like to edit something");

                    secondChoice = keyboard.nextInt();

                    //Input validation for patient confirmation
                    while(secondChoice < 1 || secondChoice > 2)
                    {
                        System.out.println("The option selected is not available. Please choose one of the following two options:");
                        System.out.println("1. Yes, confirm and add to database");
                        System.out.println("2. No, I'd like to edit something");
                        secondChoice = keyboard.nextInt();
                    }

                    //Activated if user wants to add the patient to the database or edit patient's info
                    switch (secondChoice)
                    {
                        case 1 -> {
                            if(hasMiddleName) database.add(new Patient(firstName, middleName, lastName, gender, address, Long.parseLong(phoneNumber), DOB));
                            else database.add(new Patient(firstName, lastName, gender, address, Long.parseLong(phoneNumber), DOB));

                            System.out.println(dummyPatient.getFullName() + " has been added to the database");
                            System.out.println();
                        }
                        case 2 -> {
                            //while loop continues until the user is sure they have inputted the patient's info correctly
                            while(moreToEdit){
                                System.out.println("What would you like to edit?");
                                System.out.println("1. Patient's first name");

                                //If patient has a middle name continue down this path
                                if(hasMiddleName)
                                {
                                    System.out.println("2. Patient's middle name");
                                    System.out.println("3. Patient's last name");
                                    System.out.println("4. Patient's gender");
                                    System.out.println("5. Patient's address");
                                    System.out.println("6. Patient's phone number");
                                    System.out.println("7. Patient's date of birth");
                                    System.out.println("8. Exit");

                                    thirdChoice = keyboard.nextInt();

                                    keyboard.nextLine();
                                    switch (thirdChoice) {
                                        case 1 -> {
                                            System.out.println("Please enter the patient's first name:");
                                            dummyPatient.setFirstName(keyboard.nextLine());
                                        }
                                        case 2 -> {
                                            System.out.println("Please enter the patient's middle name:");
                                            dummyPatient.setMiddleName(keyboard.nextLine());
                                        }
                                        case 3 -> {
                                            System.out.println("Please enter the patient's last name:");
                                            dummyPatient.setLastName(keyboard.nextLine());
                                        }
                                        case 4 -> {
                                            System.out.println("Please enter the patient's gender:");
                                            dummyPatient.setGender(keyboard.nextLine());
                                        }
                                        case 5 -> {
                                            System.out.println("Please enter the patient's address:");
                                            dummyPatient.setAddress(keyboard.nextLine());
                                        }
                                        case 6 -> {
                                            System.out.println("Please enter the patient's phone number with no dashes, spaces, or parentheses:");
                                            phoneNumber = keyboard.nextLine();
                                            while(!phoneNumber.matches("[0-9]+")){
                                                System.out.println("Please re-enter the patient's number with no dashes, parentheses, or spaces:");
                                                phoneNumber = keyboard.nextLine();
                                            }
                                            while(phoneNumber.length() != 10){
                                                System.out.println("The phone number you entered is not 10 digits long. " +
                                                        "Please re-enter the patient's 10-digit phone number with no dashes, spaces, or parentheses:");
                                                phoneNumber = keyboard.nextLine();
                                            }
                                            dummyPatient.setPhoneNumber(Long.parseLong(phoneNumber));
                                        }
                                        case 7 -> {
                                            System.out.println("Please enter the patient's date of birth in the following format MM/dd/yyyy:");
                                            dummyPatient.setDOB(keyboard.nextLine());
                                        }
                                        case 8 -> {
                                            break;
                                        }
                                    }
                                }
                                //if patient has no middle name, continue down this path
                                else {
                                    System.out.println("2. Patient's last name");
                                    System.out.println("3. Patient's gender");
                                    System.out.println("4. Patient's address");
                                    System.out.println("5. Patient's phone number");
                                    System.out.println("6. Patient's date of birth");
                                    System.out.println("7. Exit");

                                    thirdChoice = keyboard.nextInt();

                                    keyboard.nextLine();
                                    switch (thirdChoice) {
                                        case 1 -> {
                                            System.out.println("Please enter the patient's first name:");
                                            firstName = keyboard.nextLine();
                                            dummyPatient.setFirstName(firstName);
                                        }
                                        case 2 -> {
                                            System.out.println("Please enter the patient's last name:");
                                            lastName = keyboard.nextLine();
                                            dummyPatient.setLastName(lastName);
                                        }
                                        case 3 -> {
                                            System.out.println("Please enter the patient's gender:");
                                            gender = keyboard.nextLine();
                                            dummyPatient.setGender(gender);
                                        }
                                        case 4 -> {
                                            System.out.println("Please enter the patient's address:");
                                            address = keyboard.nextLine();
                                            dummyPatient.setAddress(address);
                                        }
                                        case 5 -> {
                                            System.out.println("Please enter the patient's phone number with no dashes, spaces, or parentheses:");
                                            phoneNumber = keyboard.nextLine();
                                            while(!phoneNumber.matches("[0-9]+")){
                                                System.out.println("Please re-enter the patient's number with no dashes, parentheses, or spaces:");
                                                phoneNumber = keyboard.nextLine();
                                            }
                                            while(phoneNumber.length() != 10){
                                                System.out.println("The phone number you entered is not 10 digits long. " +
                                                        "Please re-enter the patient's 10-digit phone number with no dashes, spaces, or parentheses:");
                                                phoneNumber = keyboard.nextLine();
                                            }
                                            dummyPatient.setPhoneNumber(Long.parseLong(phoneNumber));
                                        }
                                        case 6 -> {
                                            System.out.println("Please enter the patient's date of birth in the following format MM/dd/yyyy:");
                                            DOB = keyboard.nextLine();
                                            dummyPatient.setDOB(DOB);
                                        }
                                        case 7 ->{
                                            break;
                                        }
                                    } //end of switch(thirdChoice)
                                } //end of else

                                //Ask the user if they want to continue editing the patient's information
                                System.out.println("Would you like to continue editing the current patient's info?");
                                System.out.println(dummyPatient);
                                System.out.println("1. Yes, I want to continue editing");
                                System.out.println("2. No, I'm done editing and I'd like to add this patient to the database");
                                int moreToEditFlag = keyboard.nextInt();

                                //Input validation
                                while(moreToEditFlag < 1 || moreToEditFlag > 2)
                                {
                                    System.out.println("The option selected isn't available. Please choose one of the following options: ");
                                    System.out.println("1. Yes, I want to continue editing this patient");
                                    System.out.println("2. No, I'm done editing and I'd like to add this patient to the database");
                                    moreToEditFlag = keyboard.nextInt();
                                }
                                if(moreToEditFlag == 2) moreToEdit = false;
                            }
                            if(hasMiddleName) database.add(new Patient(firstName, middleName, lastName, gender, address, Long.parseLong(phoneNumber), DOB));
                            else database.add(new Patient(firstName, lastName, gender, address, Long.parseLong(phoneNumber), DOB));

                            System.out.println(dummyPatient.getFullName() + " has been added to the database");
                            System.out.println();
                        } //End of case 2
                    } //End of switch(secondChoice)
                } //End of main switch case 1
                case 2 ->
                        {
                    Patient patient;

                    System.out.println("Please enter the patient's last name:");
                    lastName = keyboard.nextLine();
                    System.out.println("Please enter the patient's date of birth in the following format MM/dd/yyyy");
                    DOB = keyboard.nextLine();
                    patient = database.get(lastName, DOB);

                    if(patient == null)
                    {
                        System.out.println("The patient you're looking for is not in this database");
                        System.out.println();
                        break;
                    }
                    System.out.println("Please input any notes you'd like to add as they pertain to the patient. " +
                            "Inputting ENTER will add the note to the database");
                    patientNote = keyboard.nextLine();
                    database.addNote(patient, patientNote);

                    System.out.println();
                    System.out.println("The patient's (" + patient.getFullName() + ") note has been saved in the current directory.");
                    System.out.println();
                } //End of main switch case 2
                case 3 ->
                        {
                    Patient patient;

                    System.out.println("Please enter the patient's last name:");
                    lastName = keyboard.nextLine();
                    System.out.println("Please enter the patient's date of birth in the following format MM/dd/yyyy");
                    DOB = keyboard.nextLine();
                    patient = database.get(lastName, DOB);

                    if(patient == null){
                        System.out.println("The patient you're looking for is not in this database");
                        System.out.println();
                        break;
                    }
                    database.retrieveNote(patient);
                    System.out.println();
                } //End of case 3
                case 4 ->
                        {
                    database.showAll();
                    System.out.println();
                } //End of case 4 main switch
            } //End of main menu switch
        } while(choice != 5);

        System.out.println("Thank you for using this database. Goodbye.");
    }
}