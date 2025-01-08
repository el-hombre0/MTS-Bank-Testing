package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Assertions;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class CustomerServiceLocations {
    private final SelenideElement officesAndATM = $x("//a[contains(text(), 'Офисы и банкоматы')]");
    private final SelenideElement filterButton = $x("//div[@data-testid='text'][contains(text(), 'Фильтр')]/parent::div/parent::div");
    private final SelenideElement replenishButton = $x("//button[contains(text(), 'Пополнить')]");
    private final SelenideElement qrCodeCheckbox = $x("//div[contains(text(), 'QR-Code (куаркод)')]");
    private final SelenideElement nfcCheckbox = $x("//div[contains(text(), 'NFC (беcконтактно)')]");
    private final SelenideElement workAroundTheClockChckbox = $x("//div[contains(text(), 'Работает круглосуточно')]");
    private final SelenideElement viewAsList = $x("//div[contains(text(), 'Списком')]/parent::button");
    public SelenideElement nextPageButton = $x("//*[name()='svg'][@data-testid='icon_baseX24/arrowRight']/parent::*");

    public void enterCustomerServiceLocations() {
        officesAndATM.click();
    }

    public void openFiltersList() {
        filterButton.click();
    }


    public void selectReplenish() {
        replenishButton.click();
    }

    public void selectQrCode() {
        qrCodeCheckbox.click();
    }

    public void selectNfc() {
        nfcCheckbox.click();
    }

    public void selectWorkAroundTheClock() {
        workAroundTheClockChckbox.click();
    }

    public void closeFiltersList() {
        filterButton.click();
    }

    public void selectListDisplaying() {
        viewAsList.click();
    }

    public void processingCardsOfTerminalsAndATMs() {
        ElementsCollection opened = $$x("//div[@data-testid='tabpanel']/div/div[1]/div[1]/div[3]/div");
        ElementsCollection serviceForIndividuals = $$x("//div[@data-testid='tabpanel']/div/div[2]/div[1]/div/div[@data-testid='text']");
        ElementsCollection rubRefill = $$x("//div[@data-testid='tabpanel']/div/div[2]/div[2]/div[2]/div[2]/div[1]/*[local-name() = 'svg']");
        ElementsCollection additionalServicesButton = $$x("//div[@data-testid='tabpanel']/div/div[2]/div[3]/div");
        ElementsCollection qrReader = $$x("//div[@data-testid='tabpanel']/div/div[2]/div[3]/div[2]//div[contains(text(), 'QR-ридер')]/preceding::*[name()='svg'][1]");
        ElementsCollection nfc = $$x("//div[@data-testid='tabpanel']/div/div[2]/div[3]/div[2]//div[contains(text(), 'NFC')]/preceding::*[name()='svg'][1]");

        for (SelenideElement card : opened) {
            card.shouldHave(text("Открыто"));
        }

        for (SelenideElement card : serviceForIndividuals) {
            card.shouldHave(text("круглосуточно"));
        }

        for (SelenideElement card : rubRefill) {
            Assertions.assertEquals("icon_map/accept", card.getAttribute("data-testid"));
        }

        for (SelenideElement card : additionalServicesButton) {
            card.click();
        }

        for (SelenideElement card : qrReader) {
            Assertions.assertEquals("icon_map/accept", card.getAttribute("data-testid"));
        }

        for (SelenideElement card : nfc) {
            Assertions.assertEquals("icon_map/accept", card.getAttribute("data-testid"));
        }

    }
}
