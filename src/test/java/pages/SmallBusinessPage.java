package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

/**
 * Страница малого бизнеса и ИП
 */
public class SmallBusinessPage {
    private SelenideElement paymentAccountLink = $x("//a[contains(text(), 'Расчётный счёт')]");

    public void openPaymentAccountPage(){
        paymentAccountLink.click();
    }
}
