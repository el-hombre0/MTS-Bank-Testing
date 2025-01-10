package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

/**
 * Страница кредитов
 */
public class LoansPage {
    private final SelenideElement creditByCashLink = $x("//h2[contains(text(), 'КРЕДИТ НАЛИЧНЫМИ')]/parent::a[1]");
    private final SelenideElement moreButton = $x("//a[contains(text(), 'Ещё')]/parent::div");

    public void openCashLoanDescriptionPage() {
        creditByCashLink.click();
    }

    public void unhoverCreditsPage() {
        moreButton.hover();
    }
}
