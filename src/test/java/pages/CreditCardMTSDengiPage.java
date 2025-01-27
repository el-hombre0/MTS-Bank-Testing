package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class CreditCardMTSDengiPage {
    private final SelenideElement phoneNumberInput = $x("//input[@name='phoneNumber']");
    private final SelenideElement clientNameInput = $x("//textarea[@name='clientFio']");
    private final SelenideElement birthDateInput = $x("//input[@name='birthDate']");
    private final SelenideElement emailInput = $x("//input[@name='email']");
    private final SelenideElement nextButton = $x("//div[contains(text(), 'Далее')]");
    private final SelenideElement confirmationPhoneNumberText = $x("//h4[contains(text(), 'Подтвердите номер телефона')]");

    public void enterPhoneNumberInput(String phone) {
        phoneNumberInput.val(phone);
    }

    public void enterBirthDateInput(String birthDate) {
        birthDateInput.val(birthDate);
    }

    public void enterClientName(String name) {
        clientNameInput.val(name);
    }

    public void enterEmail(String email) {
        emailInput.val(email);
    }

    public void pressNextButton() {
        nextButton.scrollTo();
        nextButton.click();
    }

    public void checkFormConfirmation() {
        confirmationPhoneNumberText.shouldBe(visible);
    }
}
