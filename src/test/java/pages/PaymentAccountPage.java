package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

/**
 * Страница оформления расчетного счета малому бизнесу
 */
public class PaymentAccountPage {
    private final SelenideElement methodsOfPersonalDataProcessing = $x("//a[contains(text(), 'условиями обработки персональных данных')]");
    private final ElementsCollection personalDataProcessingTitles = $$x("//div[@data-testid='modal']/div[@data-testid='text']/strong");
    public void openMethodsOfPersonalDataProcessingModalWindow(){
        methodsOfPersonalDataProcessing.click();
    }
    public int getPersonalDataProcessingTitlesLength(){
        return personalDataProcessingTitles.size();
    }
}
