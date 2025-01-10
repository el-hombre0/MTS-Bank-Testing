package pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selenide.$x;

/**
 * Страница кредита наличными
 */
public class CashLoanPage {
    private final SelenideElement knowMoreButton = $x("//div[contains(text(), 'Узнать больше')]");
    private final SelenideElement loanAmountInput = $x("//div[@label='Сумма кредита']/child::input[@data-testid='input-slider']");
    private final SelenideElement loanPeriodInput = $x("//div[@label='Срок кредита']/child::input[@data-testid='input-slider']");
    private final SelenideElement monthlyPaymentTextField = $x("//div[contains(text(), 'Ежемесячный платеж')]/parent::div/following-sibling::h4");
    private final SelenideElement aloneOwnerButton = $x("//div[contains(text(), 'Вы единственный собственник залоговой недвижимости?')]/following-sibling::div/span[1]");
    private final static int DEFAULT_LOAN_AMOUNT_LENGTH = 9;
    private final static int DEFAULT_LOAN_PERIOD_LENGTH = 5;


    public void openCashLoanCalculationPage() {
        knowMoreButton.click();
    }

    public void clearAndInputLoanAmountField(SelenideElement elem, int loanAmount) {
        for (int i = 0; i < DEFAULT_LOAN_AMOUNT_LENGTH; i++) {
            elem.sendKeys(Keys.BACK_SPACE);
        }
        loanAmountInput.sendKeys(String.valueOf(loanAmount));
    }

    public void clearAndInputLoanPeriodField(SelenideElement elem, int loanPeriod) {
        for (int i = 0; i < DEFAULT_LOAN_PERIOD_LENGTH; i++) {
            elem.sendKeys(Keys.BACK_SPACE);
        }
        loanPeriodInput.sendKeys(String.valueOf(loanPeriod));
    }

    public void clickAloneOwnerButton() {
        aloneOwnerButton.click();
    }

    public SelenideElement getMonthlyPaymentTextFiled() {
        return monthlyPaymentTextField;
    }

    public SelenideElement getLoanAmountInput() {
        return loanAmountInput;
    }

    public SelenideElement getLoanPeriodInput() {
        return loanPeriodInput;
    }

}
