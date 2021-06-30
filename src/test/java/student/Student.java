package student;

import gender.Gender;
import hobby.Hobby;

import java.time.LocalDate;
import java.util.List;

public class Student {

    public String firstName;
    public String lastName;
    public String email;
    public Gender gender;
    public String phoneNumber;
    public LocalDate dateOfBirth;
    public List<String> subjects;
    public List<Hobby> hobbies;
    public String address;
    public String pathToLogo;
    public String state;
    public String city;

    public Student(String firstName, String lastName, String email, Gender gender, String phoneNumber,
                   LocalDate dateOfBirth, List<String> subjects, List<Hobby> hobbies, String address,
                   String pathToLogo, String state, String city) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
        this.subjects = subjects;
        this.hobbies = hobbies;
        this.address = address;
        this.pathToLogo = pathToLogo;
        this.state = state;
        this.city = city;
    }
}
