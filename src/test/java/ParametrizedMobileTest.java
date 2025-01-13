import com.codeborne.selenide.Configuration;
import jdk.jfr.Description;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import pages.*;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.switchTo;

public class ParametrizedMobileTest {
    private final static String BASEURL = "https://www.mtsbank.ru/";
    private final static String REGION = "Москва";

    private BankHomePage bankHomePage;
    private LoansPage loansPage;
    private CashLoanPage cashLoanPage;


    @BeforeEach
    public void setup() {
        Configuration.browserSize = "430x932";
//        Configuration.pageLoadStrategy = "eager";
        Configuration.browser = "chrome";
        bankHomePage = new BankHomePage();
        loansPage = new LoansPage();
        cashLoanPage = new CashLoanPage();
    }


    /**
     * Тесткейс 3. Параметризованный тест расчета кредита наличными
     *
     * @param loanPeriod     срок кредита
     * @param loanAmount     сумма кредита
     * @param monthlyPayment ежемесячный платеж
     */
    @ParameterizedTest
    @Description("Параметризованный тест расчета кредита наличными с вводом суммы и периода кредитования")
    @DisplayName("Расчет кредита наличными")
    @CsvSource({"5,3500000,96468"})
    public void testCreditConditionsComputation(int loanPeriod, int loanAmount, int monthlyPayment) {
        open(BASEURL);
        bankHomePage.chooseOtherCity();
        bankHomePage.searchOtherRegion(REGION);
        bankHomePage.clickSearchRegion(REGION);
        bankHomePage.openCreditsPage();
        loansPage.openCashLoanDescriptionMobilePage();
        cashLoanPage.openCashLoanCalculationMobilePage();
        switchTo().window(1);
        cashLoanPage.clearAndInputLoanAmountField(cashLoanPage.getLoanAmountInput(), loanAmount);
        cashLoanPage.clickAloneOwnerButton();
        cashLoanPage.clearAndInputLoanPeriodField(cashLoanPage.getLoanPeriodInput(), loanPeriod);
        cashLoanPage.clickAloneOwnerButton();
        Assertions.assertEquals(String.valueOf(monthlyPayment), cashLoanPage.getMonthlyPaymentTextFiled().getText().replaceAll("[^0-9]", ""));
    }
}

