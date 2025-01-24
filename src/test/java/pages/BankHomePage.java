package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class BankHomePage {
    private final SelenideElement locationButton = $x("//button[@data-testid='button'][2]");
    private final SelenideElement searchRegionInput = $x("//input[@placeholder='Поиск региона']");
    private final SelenideElement loginButton = $x("//i[@data-testid='icon_other/login']/parent::button");
    private final SelenideElement privatePersonsButton = $x("//a[contains(text(), 'Частным лицам')]");
    private final SelenideElement creditsLink = $x("//a[contains(text(), 'Кредиты')]");
    private final SelenideElement creditCardsButton = $x("//a[contains(text(), 'Карты')]");
    private final SelenideElement creditCardMTSDengiLink = $x("//a[contains(text(), 'Кредитная карта МТС')][1]");
    private final SelenideElement interestDepositsRatesButton = $x("//div[contains(text(), 'Ставки по вкладам')]");
    private final SelenideElement smallBusinessAndIPLink= $x("//a[contains(text(), 'Малый бизнес и ИП')]");
    private final SelenideElement privatePersonsList = $x("//div[contains(text(), 'Чаcтным лицам')]");

    @Step("Выбор другого города")
    public void chooseOtherCity() {
        locationButton.click();
    }

    @Step("Проверка существования кнопки выбора региона")
    public boolean checkCityChooseButtonExists(){
        return locationButton.exists();
    }

    @Step("Ввод региона в поле поиска")
    public void searchOtherRegion(String region) {
        searchRegionInput.val(region);
    }

    @Step("Касание кнопки поиска региона")
    public void clickSearchRegion(String region) {
        $x("//button[contains(text(), '" + region + "')]").click();
    }

    @Step("Наведение на кнопку аутентификации")
    public void hoverLoginButton() {
        loginButton.hover();
    }

    @Step("Открытие страницы аутентификации")
    public void openPrivatePersonsLogin() {
        privatePersonsButton.click();
    }

    @Step("Открытие страницы кредитов")
    public void openCreditsPage() {
        creditsLink.shouldBe(visible);
        creditsLink.click();
    }

    @Step("Открытие выпадающего меню кредитов")
    public void openCreditCardsDropMenu(){
        creditCardsButton.hover();
    }

    @Step("Открытие страницы Кредитной карты МТС Деньги")
    public void openCreditCardMTSDengiLinkPage(){
        creditCardMTSDengiLink.click();
    }

    @Step("Открытие страницы Малый бизнес и ИП")
    public void openSmallBusinessPage(){
        smallBusinessAndIPLink.click();
    }

    @Step("Открытие страницы Малый бизнес и ИП для МУ")
    public void openSmallBusinessMobilePage(){
        privatePersonsList.hover();
        smallBusinessAndIPLink.click();
    }

}
