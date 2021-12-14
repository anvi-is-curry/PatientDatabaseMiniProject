import java.time.*;
import java.time.format.*;

public class Patient
{
    //data members
    private final DateTimeFormatter DOBFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");

    private String firstName;
    private String middleName;
    private String lastName;
    private String gender;
    private String address;

    private long phoneNumber;
    private LocalDate DOB;
    //no-arg constructor
    public Patient(){
        firstName = middleName = lastName = gender = address = null;
        phoneNumber = 0;
        DOB = null;
    }
    //Constructors
    public Patient(String firstName, String lastName, String gender, String address, long phoneNumber, String DOB)
    {
        this.firstName = firstName;
        this.middleName = null;
        this.lastName = lastName;
        this.gender = gender;
        this.address = address;
        this.phoneNumber = phoneNumber;
        setDOB(DOB);
    }

    public Patient(String firstName, String middleName, String lastName, String gender, String address, long phoneNumber, String DOB)
    {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.gender = gender;
        this.address = address;
        this.phoneNumber = phoneNumber;
        setDOB(DOB);
    }

    @Override
    public String toString() {
        return "Patient Information {" + '\n' +
                "\tFirst name: " + firstName + '\n' +
                "\tMiddle name: " + middleName + '\n' +
                "\tLast name: " + lastName + '\n' +
                "\tGender: " + gender + '\n' +
                "\tAddress: " + address + '\n' +
                "\tPhone number: " + phoneNumber + '\n' +
                "\tDate of birth: " + DOB.format(DOBFormatter) + '\n' +
                '}';
    }
    //Setters and getters
    public String getFullName(){
        if(this.middleName == null) return firstName + " " + lastName;
        else return firstName + " " + middleName + " " + lastName;
    }

    public String getAddress(){
        return address;
    }

    public void setAddress(String address){
        this.address = address;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDOB() {
        return DOB.format(DOBFormatter);
    }

    public void setDOB(String newDOB) {
        String[] arrayContainingMonthDayYear;

        arrayContainingMonthDayYear = newDOB.split("/");

        int month   = Integer.parseInt(arrayContainingMonthDayYear[0]);
        int day     = Integer.parseInt(arrayContainingMonthDayYear[1]);
        int year    = Integer.parseInt(arrayContainingMonthDayYear[2]);

        this.DOB = LocalDate.of(year, month, day);
    }
}