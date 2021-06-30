package tests;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import components.DatePicker;
import components.DropMenu;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.openqa.selenium.OutputType;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class WebSteps {

    private static final String STUDENT_REGISTRATION_FORM = "https://demoqa.com/automation-practice-form",
            STUDENT_REGISTRATION_FORM_HEADER_TEXT = "Student Registration Form",
            THANKS_FOR_SUBMITTIG_TEXT = "Thanks for submitting the form";

    public SelenideElement registrationFormPageHeader = $(".practice-form-wrapper"),
            firstNameInputField = $("#firstName"),
            lastNameInputField = $("#lastName"),
            emailInputField = $("#userEmail"),
            maleGenderRadioButton = $("#gender-radio-1").parent(),
            femaleGenderRadioButton = $("#gender-radio-2").parent(),
            otherGenderRadioButton = $("#gender-radio-3").parent(),
            phoneNumberInputField = $("#userNumber"),
            subjectInputField = $("#subjectsInput"),
            uploadPictureButton = $("#uploadPicture"),
            sportsHobbyCheckBox = $("#hobbies-checkbox-1").parent(),
            readingHobbyCheckBox = $("#hobbies-checkbox-2").parent(),
            musicHobbyCheckBox = $("#hobbies-checkbox-3").parent(),
            addressInputField = $("#currentAddress"),
            stateDropMenu = $("#state svg"),
            cityDropMenu = $("#city"),
            submitButton = $("#submit"),
            thanksForSubmitting = $("#example-modal-sizes-title-lg"),
            studentNameLine = $x("//td[text()='Student Name']").parent(),
            emailLine = $x("//td[text()='Student Email']").parent(),
            genderLine = $x("//td[text()='Gender']").parent(),
            phoneNumberLine = $x("//td[text()='Mobile']").parent(),
            dateOfBirthLine = $x("//td[text()='Date of Birth']").parent(),
            subjectLine = $x("//td[text()='Subjects']").parent(),
            hobbiesLine = $x("//td[text()='Hobbies']").parent(),
            pictureLine = $x("//td[text()='Picture']").parent(),
            addressLine = $x("//td[text()='Address']").parent(),
            stateAndCity = $x("//td[text()='State and City']").parent();
    ElementsCollection listInDropMenu = $$("[id^='react-select-']");

//    @Step("Open main github page")
//    public void openMainPage() {
//        open(BASE_URL);
//    }

    @Step("Open Student Registration Form Page")
    public void openStudentRegistrationPage() {
        open(STUDENT_REGISTRATION_FORM);
    }

    @Step("Check the Registration Form is opened")
    public void checkThisIsStudentRegistrationPage() {
        registrationFormPageHeader.shouldHave(text(STUDENT_REGISTRATION_FORM_HEADER_TEXT));
    }

    @Step("Fill the form")
    public void fillTheForm(String firstName, String lastName, String email, String gender,
                            String phoneNumber, String dayOfBirth, String monthOfBirth, String yearOfBirth,
                            String subject, String picture, String hobby, String address) {
        firstNameInputField.val(firstName);
        lastNameInputField.val(lastName);
        emailInputField.val(email);

        if ("Male".equals(gender)) {
            maleGenderRadioButton.click();
        } else if ("Female".equals(gender)) {
            femaleGenderRadioButton.click();
        } else if ("Other".equals(gender)) {
            otherGenderRadioButton.click();
        }

        phoneNumberInputField.val(phoneNumber);
        new DatePicker().inputDateOfBirth(dayOfBirth, monthOfBirth, yearOfBirth);
        subjectInputField.setValue(subject).pressEnter();
        uploadPictureButton.uploadFromClasspath(picture);

        if ("Sports".equals(hobby)) {
            sportsHobbyCheckBox.click();
        } else if ("Reading".equals(hobby)) {
            readingHobbyCheckBox.click();
        } else if ("Music".equals(hobby)) {
            musicHobbyCheckBox.click();
        }

        addressInputField.val(address);
    }

    @Step("Select State")
    public void selectState(String item) {
        new DropMenu().selectItemFromDropMenu(stateDropMenu, listInDropMenu, item);
    }

    @Step("Select City")
    public void selectCity(String item) {
        new DropMenu().selectItemFromDropMenu(cityDropMenu, listInDropMenu, item);
    }

    @Step("Submit the Form")
    public void submitTheForm() {
        submitButton.scrollTo().click();
    }

    @Step("Check the Registration Confirmation Page is opened")
    public void checkThisIsRegistrationConfirmationPage() {
        thanksForSubmitting.shouldHave(text(THANKS_FOR_SUBMITTIG_TEXT));
    }

    @Step("Check the value on Confirmation Page line by line")
    public void checkTheValueInFormByLine(String firstName, String lastName, String email,
                                          String gender, String phoneNumber, String dayOfBirth,
                                          String monthOfBirth, String yearOfBirth, String subject,
                                          String hobby, String picture, String address,
                                          String state, String city) {
        studentNameLine.shouldHave(text(firstName + " " + lastName));
        emailLine.shouldHave(text(email));
        genderLine.shouldHave(text(gender));
        phoneNumberLine.shouldHave(text(phoneNumber));
        dateOfBirthLine.shouldHave(text(dayOfBirth + " " + monthOfBirth + "," + yearOfBirth));
        subjectLine.shouldHave(text(subject));
        hobbiesLine.shouldHave(text(hobby));
        pictureLine.shouldHave(text(picture));
        addressLine.shouldHave(text(address));
        stateAndCity.shouldHave(text(state + " " + city));
    }

    @Attachment(value = "Screenshot", type = "image/png")
    public byte[] makeScreenshot() {
        return screenshot(OutputType.BYTES);
    }
}
