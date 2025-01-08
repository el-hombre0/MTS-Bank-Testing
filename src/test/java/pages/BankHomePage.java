package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class BankHomePage {
    private final SelenideElement locationButton = $x("//button[@data-testid='button'][2]");
    private final SelenideElement searchRegionInput = $x("//input[@placeholder='Поиск региона']");
    private final SelenideElement loginButton = $x("//i[@data-testid='icon_other/login']/parent::button");
    private final SelenideElement privatePersonsButton = $x("//a[contains(text(), 'Частным лицам')]");

    /**
     * Выбор другого города
     * */
    public void chooseOtherCity(){
        locationButton.click();
    }

    public void searchOtherRegion(String region){
        searchRegionInput.val(region);
    }

    public void clickSearchRegion(String region){
        $x("//button[contains(text(), '" + region + "')]").click();
    }

    public void hoverLoginButton(){
        loginButton.hover();
    }

    public void openPrivatePersonsLogin(){
        privatePersonsButton.click();
    }

}
