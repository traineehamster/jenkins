package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class StudentRegistrationFormTests extends TestBase {

    private WebSteps steps = new WebSteps();

    @Test
    @Tag("nice_allure_steps")
    @DisplayName("Check the registration can be submitted with test data")
    void successfulSubmitRegistrationForm() {
        steps.openStudentRegistrationPage();
        steps.checkThisIsStudentRegistrationPage();

        steps.fillTheForm(testData.firstName, testData.lastName, testData.email,
                testData.genderM, testData.testPhoneNumber, testData.monthOfBirth,
                testData.yearOfBirth, testData.dayOfBirth, testData.subject1, testData.picture,
                testData.hobby1, testData.testAddress);
        steps.selectState(testData.ncrState);
        steps.selectCity(testData.delhiCity);

        steps.submitTheForm();
        steps.makeScreenshot();

        steps.checkThisIsRegistrationConfirmationPage();
        steps.checkTheValueInFormByLine(testData.firstName, testData.lastName,
                testData.email, testData.genderM, testData.testPhoneNumber, testData.dayOfBirth,
                testData.monthOfBirth, testData.yearOfBirth, testData.subject1, testData.hobby1,
                testData.picture, testData.testAddress, testData.ncrState, testData.delhiCity);
    }
}
