import com.codeborne.selenide.Configuration;
import enums.StrConsts;
import jdk.jfr.Description;
import org.junit.jupiter.api.*;
import pages.*;

import static com.codeborne.selenide.Selenide.open;

/**
 * Класс тестирования десктопной версии
 */
public class DesktopMainTest extends BaseTest{
    private BankHomePage bankHomePage;
    private PhonePage phonePage;
    private CustomerServiceLocations customerServiceLocationsPage;
    private CreditCardMTSDengi creditCardMTSDengi;
    private SmallBusinessPage smallBusinessPage;
    private PaymentAccountPage paymentAccountPage;



    @BeforeEach
    public void setup() {
        bankHomePage = new BankHomePage();
        phonePage = new PhonePage();
        customerServiceLocationsPage = new CustomerServiceLocations();
        creditCardMTSDengi = new CreditCardMTSDengi();
        smallBusinessPage = new SmallBusinessPage();
        paymentAccountPage = new PaymentAccountPage();
    }


    /**
     * Тесткейс 1 - Ввод неправильного номера телефона при аутентификации
     */
    @Test
    @Description("Ввод неправильного номера телефона при аутентификации")
    @DisplayName("Аутентификация по номеру телефона с неправильным кодом")
    public void testNumberAuth() {
        open(StrConsts.BASEURL.toString());
        if(bankHomePage.checkCityChooseButtonExists()){
            bankHomePage.chooseOtherCity();
            bankHomePage.searchOtherRegion(StrConsts.REGION.toString());
            bankHomePage.clickSearchRegion(StrConsts.REGION.toString());
        }
        bankHomePage.hoverLoginButton();
        bankHomePage.openPrivatePersonsLogin();
        phonePage.enterPhoneNumber(StrConsts.PHONE_NUMBER.toString());
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
    @Description("Проверка работы фильтров на странице отображения пунктов обслуживания клиентов")
    @DisplayName("Фильтрация точек обслуживания клиентов")
    public void testFilters() {
        open(StrConsts.BASEURL.toString());
        if(bankHomePage.checkCityChooseButtonExists()){
            bankHomePage.chooseOtherCity();
            bankHomePage.searchOtherRegion(StrConsts.REGION.toString());
            bankHomePage.clickSearchRegion(StrConsts.REGION.toString());
        }
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
    @Description("Оформление кредитной карты МТС Деньги с вводом персональных данных")
    @DisplayName("Оформление кредитной карты")
    public void testMakingCreditCard() {
        open(StrConsts.BASEURL.toString());
        if(bankHomePage.checkCityChooseButtonExists()){
            bankHomePage.chooseOtherCity();
            bankHomePage.searchOtherRegion(StrConsts.REGION.toString());
            bankHomePage.clickSearchRegion(StrConsts.REGION.toString());
        }
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
        open(StrConsts.BASEURL.toString());
        if(bankHomePage.checkCityChooseButtonExists()){
            bankHomePage.chooseOtherCity();
            bankHomePage.searchOtherRegion(StrConsts.REGION.toString());
            bankHomePage.clickSearchRegion(StrConsts.REGION.toString());
        }
        bankHomePage.openSmallBusinessPage();
        smallBusinessPage.openPaymentAccountPage();
        paymentAccountPage.openMethodsOfPersonalDataProcessingModalWindow();
        Assertions.assertEquals(3, paymentAccountPage.getPersonalDataProcessingTitlesLength());
    }

}
