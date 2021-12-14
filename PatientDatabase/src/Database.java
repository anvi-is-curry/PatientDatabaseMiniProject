import java.io.*;
import java.util.*;

public class Database {
    private final ArrayList<Patient> listOfPatients;

    public Database(){
        listOfPatients = new ArrayList<>();
    }

    public boolean isEmpty(){
        return listOfPatients.size() == 0;
    }

    public void add(Patient patient){
        if(isEmpty()) listOfPatients.add(patient);
        else if(size() == 1){
            if(patient.getLastName().compareToIgnoreCase(listOfPatients.get(0).getLastName()) < 0){
                listOfPatients.add(0, patient);
            }

            //if patient goes after the very last patient in the list
            else if(patient.getLastName().compareToIgnoreCase(listOfPatients.get(0).getLastName()) > 0){
                listOfPatients.add(patient);
            }
        }
        else if(size() > 1){
            for(int i = 0;i < listOfPatients.size() - 1; i++){

                //if patient goes before the current first patient in the list
                if(patient.getLastName().compareToIgnoreCase(listOfPatients.get(i).getLastName()) < 0){
                    listOfPatients.add(0, patient);
                    return;
                }

                //if patient goes after the very last patient in the list
                if(patient.getLastName().compareToIgnoreCase(listOfPatients.get(size()-1).getLastName()) > 0){
                    listOfPatients.add(patient);
                    return;
                }

                //if patient goes somewhere in between the beginning and the end
                if(patient.getLastName().compareToIgnoreCase(listOfPatients.get(i).getLastName()) > 0 && patient.getLastName().compareToIgnoreCase(listOfPatients.get(i + 1).getLastName()) < 0){
                    listOfPatients.add(i + 1, patient);
                    return;
                }
            }
        }
    }

    //You can access a patient by inputting their last name and DOB
    public Patient get(String lastName, String DOB)
    {
        int index = search(lastName, DOB);
        if(index == -1) return null;
        else return listOfPatients.get(index);
    }

    private int search(String lastName, String DOB)
    {
        String firstThreeLetters = lastName.substring(0,3);

        int left = 0;
        int right = size() - 1;

        while(left <= right)
        {
            int mid = left + (right - left) / 2;
            if(listOfPatients.get(mid).getLastName().substring(0,3).equalsIgnoreCase(firstThreeLetters) && listOfPatients.get(mid).getDOB().equals(DOB)) return mid;
            if(listOfPatients.get(mid).getLastName().substring(0,3).compareToIgnoreCase(firstThreeLetters) < 0) left = mid + 1;
            else right = mid - 1;
        }
        return -1;
    }

    //To show all of the patients in the database
    public void showAll()
    {
        if(listOfPatients.isEmpty()) System.out.println("There are no entries in this database");
        else{
            System.out.println("These are all the patients currently in the database:");
            for(Patient p : listOfPatients) System.out.println(p.toString());
        }
    }

    public int size()
    {
        return listOfPatients.size();
    }

    public void addNote(Patient patient, String note) throws IOException
    {
        File patientFile = new File(patient.getFullName() + ".txt");
        FileWriter fw = new FileWriter(patientFile);
        PrintWriter pw = new PrintWriter(fw);

        pw.write(note);
        pw.close();
    }

    //To retrieve a note from a patient
    public void retrieveNote(Patient patient) throws FileNotFoundException
    {
        File patientFile = new File(patient.getFullName() + ".txt");
        Scanner reader = new Scanner(patientFile);

        System.out.println("This is the patient's (" + patient.getFullName() + ") note:");
        while(reader.hasNext()){
            System.out.println(reader.nextLine());
        }
    }
}