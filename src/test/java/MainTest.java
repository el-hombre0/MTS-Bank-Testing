import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.BankHomePage;
import pages.PhonePage;

import static com.codeborne.selenide.Selenide.open;

public class MainTest {
    private BankHomePage bankHomePage;
    private PhonePage phonePage;

    @BeforeEach
    public void setup(){
        bankHomePage = new BankHomePage();
        phonePage = new PhonePage();
    }

    private final static String BASEURL = "https://www.mtsbank.ru/";
    private final static String REGION = "Саратов";
    private final static String PHONE_NUMBER = "9993231122";
    @Test
    public void testNumberAuth(){
        open(BASEURL);
        bankHomePage.chooseOtherCity();
        bankHomePage.searchOtherRegion(REGION);
        bankHomePage.clickSearchRegion(REGION);
        bankHomePage.hoverLoginButton();
        bankHomePage.openPrivatePersonsLogin();
        phonePage.enterPhoneNumber(PHONE_NUMBER);
        phonePage.pushSendPhoneNumberButton();
        for(int i = 0; i < 3; i++){
            phonePage.enterIncorrectCode();
        }
        phonePage.closeTryAfter5MinsModuleWindow();

    }
}
