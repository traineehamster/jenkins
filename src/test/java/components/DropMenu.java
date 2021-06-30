package components;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.exactText;

public class DropMenu {

    public void selectItemFromDropMenu(SelenideElement element, ElementsCollection listInDropMenu, String item) {
        element.scrollTo().click();
        listInDropMenu.find(exactText(item)).click();
    }
}
