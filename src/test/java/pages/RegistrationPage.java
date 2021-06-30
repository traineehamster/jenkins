package pages;

import io.qameta.allure.Step;
import java.io.File;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import hobby.Hobby;
import gender.Gender;
import student.Student;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selectors.byText;

public class RegistrationPage {

    private static final SelenideElement firstNameInput = $("#firstName");
    private static final SelenideElement lastNameInput = $("#lastName");
    private static final SelenideElement emailInput = $("#userEmail");
    private static final SelenideElement maleGenderCheckbox = $$(".custom-control-label").findBy(text("Male"));
    private static final SelenideElement femaleGenderCheckbox = $$(".custom-control-label").findBy(text("Female"));
    private static final SelenideElement otherGenderCheckbox = $$(".custom-control-label").findBy(text("Other"));
    private static final HashMap<Gender, SelenideElement> genderCheckboxes = new HashMap<Gender, SelenideElement>() {{
        put(Gender.Male, maleGenderCheckbox);
        put(Gender.Female, femaleGenderCheckbox);
        put(Gender.Other, otherGenderCheckbox);
    }};
    private static final SelenideElement phoneInput = $("#userNumber");
    private static final SelenideElement dateOfBirtInput = $("#dateOfBirthInput");
    private static final SelenideElement yearSelector = $(".react-datepicker__year-select");
    private static final SelenideElement monthSelector = $(".react-datepicker__month-select");
    private static final ElementsCollection daysInMonth = $$(".react-datepicker__day");
    private static final SelenideElement subjectsInput = $("#subjectsInput");
    private static final SelenideElement subjectsAutoCompleteMenu = $(".subjects-auto-complete__menu");
    private static final SelenideElement sportsCheckbox = $$(".custom-control-label").findBy(text("Sports"));
    private static final SelenideElement musicCheckbox = $$(".custom-control-label").findBy(text("Music"));
    private static final SelenideElement readingCheckbox = $$(".custom-control-label").findBy(text("Reading"));
    private static final HashMap<Hobby, SelenideElement> hobbiesCheckboxes = new HashMap<Hobby, SelenideElement>() {{
        put(Hobby.Sports, sportsCheckbox);
        put(Hobby.Music, musicCheckbox);
        put(Hobby.Reading, readingCheckbox);
    }};
    private static final SelenideElement addressTextarea = $("#currentAddress");
    private static final SelenideElement uploadInput = $("#uploadPicture");
    private static final SelenideElement stateInput = $("#state");
    private static final SelenideElement stateAutoCompleteMenu = $(".css-26l3qy-menu");
    private static final SelenideElement cityInput = $("#city");
    private static final SelenideElement cityAutoCompleteMenu = $(".css-26l3qy-menu");
    private static final SelenideElement submitButton = $("#submit");

    @Step("Open page with form")
    public static void openPageWithForm(){
        open(Configuration.baseUrl + "/automation-practice-form");
    }

    @Step("Enter {firstName} as first name")
    public static void enterFirstName(String firstName){
        firstNameInput.setValue(firstName);
    }

    @Step("Enter {lastName} as last name")
    public static void enterLastName(String lastName){
        lastNameInput.setValue(lastName);
    }

    @Step("Enter {email} as email")
    public static void enterEmail(String email){
        emailInput.setValue(email);
    }

    @Step("Chose '{gender.name}' gender")
    public static void setGender(Gender gender){
        genderCheckboxes.get(gender).click();
    }

    @Step("Enter {phoneNumber} as phone number")
    public static void enterPhoneNumber(String phoneNumber){
        phoneInput.setValue(phoneNumber);
    }

    @Step("Set {date} as date of birth")
    public static void setDateOfBirth(LocalDate date){
        dateOfBirtInput.click();
        yearSelector.selectOptionByValue(String.valueOf(date.getYear()));
        monthSelector.selectOptionByValue(String.valueOf(date.getMonthValue()-1));
        daysInMonth.findBy(exactText(String.valueOf(date.getDayOfMonth()))).click();
    }

    @Step("Select subjects: {subjects}")
    public static void addSubjects(List<String> subjects) {
        for (String subject: subjects) {
            subjectsInput.setValue(subject.substring(0,3));
            subjectsAutoCompleteMenu.$(byText(subject)).click();
        }
    }

    @Step("Select hobbies: {hobbies}")
    public static void checkHobbiesCheckboxes(List<Hobby> hobbies){
        for (Hobby hobby:hobbies) {
            hobbiesCheckboxes.get(hobby).click();
        }
    }

    @Step("Enter address: {address}")
    public static void enterCurrentAddress(String address){
        addressTextarea.setValue(address);
    }

    @Step("Attach file with name: {path}")
    public static void attachFileToForm(String path){
        uploadInput.uploadFile(new File(path));
    }

    @Step("Select state name: {stateName}")
    public static void selectState(String stateName){
        stateInput.click();
        stateAutoCompleteMenu.$(byText(stateName)).click();
    }

    @Step("Select city name: {cityName}")
    public static void selectCity(String cityName){
        cityInput.click();
        cityAutoCompleteMenu.$(byText(cityName)).click();
    }

    @Step("Click submit button")
    public static void clickSubmitButton(){
        submitButton.click();
    }

    public static void registerStudent(Student student) {
        openPageWithForm();
        enterFirstName(student.firstName);
        enterLastName(student.lastName);
        enterEmail(student.email);
        setGender(student.gender);
        enterPhoneNumber(student.phoneNumber);
        setDateOfBirth(student.dateOfBirth);
        addSubjects(student.subjects);
        checkHobbiesCheckboxes(student.hobbies);
        attachFileToForm(student.pathToLogo);
        enterCurrentAddress(student.address);
        selectState(student.state);
        selectCity(student.city);
        clickSubmitButton();
    }
}
