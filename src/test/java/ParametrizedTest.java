import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import pages.*;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.switchTo;

public class ParametrizedTest {
    private final static String BASEURL = "https://www.mtsbank.ru/";
    private final static String REGION = "Москва";

    private BankHomePage bankHomePage;
    private LoansPage loansPage;
    private CashLoanPage cashLoanPage;


    @BeforeEach
    public void setup() {
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
    @CsvSource({"5,3500000,96468"})
    public void testCreditConditionsComputation(int loanPeriod, int loanAmount, int monthlyPayment) {
        open(BASEURL);
        bankHomePage.chooseOtherCity();
        bankHomePage.searchOtherRegion(REGION);
        bankHomePage.clickSearchRegion(REGION);
        bankHomePage.openCreditsPage();
        loansPage.unhoverCreditsPage();
        loansPage.openCashLoanDescriptionPage();
        cashLoanPage.openCashLoanCalculationPage();
        switchTo().window(1);
        cashLoanPage.clearAndInputLoanAmountField(cashLoanPage.getLoanAmountInput(), loanAmount);
        cashLoanPage.clearAndInputLoanPeriodField(cashLoanPage.getLoanPeriodInput(), loanPeriod);
        cashLoanPage.clickAloneOwnerButton();
        Assertions.assertEquals(String.valueOf(monthlyPayment), cashLoanPage.getMonthlyPaymentTextFiled().getText().replaceAll("[^0-9]", ""));
    }
}
