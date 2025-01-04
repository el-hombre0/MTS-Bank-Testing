package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class PhonePage {
    private SelenideElement phoneInput = $x("//input[@id='phone']");
    private SelenideElement phoneSubmitButton = $x("//button[@type='submit']");
    private SelenideElement smsCodeInput = $x("//input[@id='otp']");
    private SelenideElement crossTryAfter5MinsModuleWindow = $x("//div[@data-testid='clear']");

    public void enterPhoneNumber(String phone){
        phoneInput.val(phone);
    }
    public void pushSendPhoneNumberButton(){
        phoneSubmitButton.click();
    }

    public void enterIncorrectCode(){
        smsCodeInput.val("0000");
    }

    public void closeTryAfter5MinsModuleWindow(){
        crossTryAfter5MinsModuleWindow.click();
    }
}
