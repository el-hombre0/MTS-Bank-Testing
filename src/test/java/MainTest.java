import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.BankHomePage;

import static com.codeborne.selenide.Selenide.open;

public class MainTest {
    private BankHomePage bankHomePage;

    @BeforeEach
    public void setup(){
        bankHomePage = new BankHomePage();
    }

    private final static String BASEURL = "https://www.mtsbank.ru/";
    private final static String REGION = "Саратов";
    @Test
    public void testNumberAuth(){
        open(BASEURL);
        bankHomePage.chooseOtherCity();
        bankHomePage.searchOtherRegion(REGION);
        bankHomePage.clickSearchRegion(REGION);
        bankHomePage.hoverLoginButton();
        bankHomePage.openPrivatePersonsLogin();
    }
}
