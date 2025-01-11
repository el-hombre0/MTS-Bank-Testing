package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

/**
 * Страница ставок по вкладам
 */
public class DepositRatesPage {
    private final SelenideElement yearLink = $x("//a[@data-testid='link']/div[contains(text(), '2022')]");

    public void chooseYear(){
        yearLink.click();
    }
}
