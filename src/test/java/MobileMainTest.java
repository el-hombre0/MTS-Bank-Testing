import com.codeborne.selenide.Configuration;
import jdk.jfr.Description;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
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
        Configuration.pageLoadStrategy = "eager";
        Configuration.browser = "chrome";

        bankHomePage = new BankHomePage();
        phonePage = new PhonePage();
        customerServiceLocationsPage = new CustomerServiceLocations();
        creditCardMTSDengi = new CreditCardMTSDengi();
        smallBusinessPage = new SmallBusinessPage();
        paymentAccountPage = new PaymentAccountPage();
    }

    private final static String BASEURL = "https://www.mtsbank.ru/";
    private final static String REGION = "Москва";
    private final static String PHONE_NUMBER = "9991235621";

    /**
     * Тесткейс 1 - Ввод неправильного номера телефона при аутентификации:
     */
    @Test
    @Description("Ввод неправильного номера телефона при аутентификации")
    @DisplayName("Аутентификация по номеру телефона с неправильным кодом")
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
        phonePage.closeByClickEmptySpace();

    }

    /**
     * Тесткейс 2 - Проверка работы фильтров на странице отображения пунктов обслуживания клиентов
     */
    @Test
    @Description("Проверка работы фильтров на странице отображения пунктов обслуживания клиентов")
    @DisplayName("Фильтрация точек обслуживания клиентов")
    public void testFilters() {
        open(BASEURL);
        bankHomePage.chooseOtherCity();
        bankHomePage.searchOtherRegion(REGION);
        bankHomePage.clickSearchRegion(REGION);
        customerServiceLocationsPage.enterCustomerServiceLocations();
        customerServiceLocationsPage.openFilterListViaBurger();
        customerServiceLocationsPage.selectReplenish();
        customerServiceLocationsPage.selectQrCode();
        customerServiceLocationsPage.selectNfc();
        customerServiceLocationsPage.selectWorkAroundTheClock();
        customerServiceLocationsPage.confirmFilters();
        customerServiceLocationsPage.selectListDisplaying();
        while (customerServiceLocationsPage.nextPageMobileButton.isEnabled()) {
            customerServiceLocationsPage.processingCardsOfTerminalsAndATMs();
            customerServiceLocationsPage.nextPageMobileButton.click();
            if (!customerServiceLocationsPage.nextPageMobileButton.isEnabled()) {
                customerServiceLocationsPage.processingCardsOfTerminalsAndATMs();
            }
        }
    }

    /**
     * Тесткейс 4 - Оформление кредитной карты
     */
    @Test
    @Description("Оформление кредитной карты МТС Деньги с вводом персональных данных")
    @DisplayName("Оформление кредитной карты")
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
    @Description("Проверка элементов модального окна условий обработки персональных данных")
    @DisplayName("Условий обработки персональных данных")
    public void testPersonalDataProcessingConditions(){
        open(BASEURL);
        if(bankHomePage.checkCityChooseButtonExists()){
            bankHomePage.chooseOtherCity();
            bankHomePage.searchOtherRegion(REGION);
            bankHomePage.clickSearchRegion(REGION);
        }
        bankHomePage.openSmallBusinessMobilePage();
        paymentAccountPage.openMethodsOfPersonalDataProcessingModalWindow();
        Assertions.assertEquals(3, paymentAccountPage.getPersonalDataProcessingTitlesLength());
    }

}
