import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.*;

import static com.codeborne.selenide.Selenide.open;

/**
 * Класс тестирования мобильной версии
 */
public class MobileMainTest {
    private BankHomePage bankHomePage;
    private PhonePage phonePage;
    private CustomerServiceLocations customerServiceLocationsPage;
    private CreditCardMTSDengi creditCardMTSDengi;
    private SmallBusinessPage smallBusinessPage;
    private PaymentAccountPage paymentAccountPage;

    @BeforeEach
    public void setup() {
        Configuration.browserSize = "430x932";

        bankHomePage = new BankHomePage();
        phonePage = new PhonePage();
        customerServiceLocationsPage = new CustomerServiceLocations();
        creditCardMTSDengi = new CreditCardMTSDengi();
        smallBusinessPage = new SmallBusinessPage();
        paymentAccountPage = new PaymentAccountPage();
    }

    private final static String BASEURL = "https://www.mtsbank.ru/";
    private final static String REGION = "Москва";
    private final static String PHONE_NUMBER = "9991235622";

    /**
     * Тесткейс 1 - Ввод неправильного номера телефона при аутентификации:
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
     * Тесткейс 2 - Проверка работы фильтров на странице отображения пунктов обслуживания клиентов
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

    /**
     * Тесткейс 4 - Оформление кредитной карты
     */
    @Test
    public void testMakingCreditCard() {
        open(BASEURL);
        bankHomePage.chooseOtherCity();
        bankHomePage.searchOtherRegion(REGION);
        bankHomePage.clickSearchRegion(REGION);
        bankHomePage.openCreditCardsDropMenu();
        bankHomePage.openCreditCardMTSDengiLinkPage();
        creditCardMTSDengi.enterPhoneNumberInput(creditCardMTSDengi.generatePhoneNumber());
        creditCardMTSDengi.enterBirthDateInput(creditCardMTSDengi.generateRandomBirthDate());
        creditCardMTSDengi.enterClientName(creditCardMTSDengi.generateRandomFullName());
        creditCardMTSDengi.enterEmail(creditCardMTSDengi.generateRandomEmail());
        creditCardMTSDengi.pressNextButton();
        creditCardMTSDengi.checkFormConfirmation();
    }

    /**
     * Тесткейс 5 - Модальное окно условий обработки персональных данных
     */
    @Test
    public void testInterestDepositsRates(){
        open(BASEURL);
        if(bankHomePage.checkCityChooseButtonExists()){
            bankHomePage.chooseOtherCity();
            bankHomePage.searchOtherRegion(REGION);
            bankHomePage.clickSearchRegion(REGION);
        }
        bankHomePage.openSmallBusinessPage();
        smallBusinessPage.openPaymentAccountPage();
        paymentAccountPage.openMethodsOfPersonalDataProcessingModalWindow();
        Assertions.assertEquals(3, paymentAccountPage.getPersonalDataProcessingTitlesLength());
    }

}
