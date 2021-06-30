package tests;

import gender.Gender;
import hobby.Hobby;
import org.junit.jupiter.api.Test;
import pages.RegistrationPage;
import pages.ResultTable;
import student.Student;

import java.time.LocalDate;
import java.util.Arrays;

public class StudentRegistrationFormTests extends TestBase {

    @Test
    void successfulRegistration() {
        Student student = new Student("Name1",
                "LastName1",
                "student@email.com",
                Gender.Male,
                "9998887776",
                LocalDate.of(2000, 2, 15),
                Arrays.asList("Computer Science", "Physics"),
                Arrays.asList(Hobby.Sports, Hobby.Music),
                "Student address 123 123",
                "src/test/resources/img/1.png",
                "NCR",
                "Delhi"
        );

        RegistrationPage.registerStudent(student);
        ResultTable.verifyInputDataForStudent(student);
    }
}
