import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.BankHomePage;
import pages.CustomerServiceLocations;
import pages.PhonePage;

import static com.codeborne.selenide.Selenide.open;

public class MainTest {
    private BankHomePage bankHomePage;
    private PhonePage phonePage;

    private CustomerServiceLocations customerServiceLocationsPage;

    @BeforeEach
    public void setup() {
        // Тесткейс 1
        bankHomePage = new BankHomePage();
        phonePage = new PhonePage();

        // Тесткейс 2
        customerServiceLocationsPage = new CustomerServiceLocations();
    }

    private final static String BASEURL = "https://www.mtsbank.ru/";
    private final static String REGION = "Москва";
    private final static String PHONE_NUMBER = "9991235622";

    /**
     * Тесткейс 1
     */
    @Test
    public void testNumberAuth() {
        open(BASEURL);
        bankHomePage.chooseOtherCity();
        bankHomePage.searchOtherRegion(REGION);
        bankHomePage.clickSearchRegion(REGION);
        bankHomePage.hoverLoginButton();
        bankHomePage.openPrivatePersonsLogin();
        phonePage.enterPhoneNumber(PHONE_NUMBER);
        phonePage.pushSendPhoneNumberButton();
        for (int i = 0; i < 3; i++) {
            phonePage.enterIncorrectCode();
        }
        phonePage.closeTryAfter5MinsModuleWindow();

    }

    /**
     * Тесткейс 2
     */
    @Test
    public void testFilters() {
        open(BASEURL);
        bankHomePage.chooseOtherCity();
        bankHomePage.searchOtherRegion(REGION);
        bankHomePage.clickSearchRegion(REGION);
        customerServiceLocationsPage.enterCustomerServiceLocations();
        customerServiceLocationsPage.openFiltersList();
        customerServiceLocationsPage.selectReplenish();
        customerServiceLocationsPage.selectQrCode();
        customerServiceLocationsPage.selectNfc();
        customerServiceLocationsPage.selectWorkAroundTheClock();
        customerServiceLocationsPage.closeFiltersList();
        customerServiceLocationsPage.selectListDisplaying();
        while (customerServiceLocationsPage.nextPageButton.isEnabled()) {
            customerServiceLocationsPage.processingCardsOfTerminalsAndATMs();
            customerServiceLocationsPage.nextPageButton.click();
            if (!customerServiceLocationsPage.nextPageButton.isEnabled()) {
                customerServiceLocationsPage.processingCardsOfTerminalsAndATMs();
            }
        }
    }

}
