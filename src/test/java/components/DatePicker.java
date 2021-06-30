package components;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DatePicker {

    SelenideElement dateInputField = $("#dateOfBirthInput"),
            monthSelector = $(".react-datepicker__month-select"),
            yearSelector = $(".react-datepicker__year-select");
    ElementsCollection daySelector = $$(".react-datepicker__day");

    public void inputDateOfBirth(String month, String year, String day) {
        dateInputField.click();
        monthSelector.selectOption(month);
        yearSelector.selectOption(year);
        daySelector.find(exactText(day)).click();
    }
}
