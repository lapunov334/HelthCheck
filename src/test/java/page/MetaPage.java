package page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import interfaces.SwitchWindow;
import org.openqa.selenium.WebDriver;
import utils.Credentials;

import java.time.Duration;
import java.util.Set;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class MetaPage implements SwitchWindow {

    private final SelenideElement checkLogoVisible = $x("//button[@data-testid=\"app-header-logo\"]");

    private final SelenideElement clickCheckbox = $x("//input[@type='checkbox']/ancestor::div[1]");

    private final SelenideElement importWalletButton = $x("//button[@data-testid=\"onboarding-import-wallet\"]");
    private final SelenideElement agreeButton = $x("//button[@data-testid=\"metametrics-i-agree\"]");

    private final ElementsCollection collectionSeedPhrase = $$x("//div[@class=\"MuiFormControl-root MuiTextField-root\"]//input");
    private final SelenideElement connectButton = $x("//button[@data-testid=\"import-srp-confirm\"]");


    private final ElementsCollection inputsForPassword = $$x("//input[@type=\"password\"]");

    private final SelenideElement importWalletButtonSub = $x("//button[@data-testid=\"create-password-import\"]");

    private final SelenideElement buttonOne = $x("//button[@data-testid=\"onboarding-complete-done\"]");
    private final SelenideElement buttonTwoThree = $x("//button[@class=\"button btn--rounded btn-primary\"]");


    public MetaPage(String url) {
        open(url);
        checkLogoVisible.shouldBe(visible);
        switchTo().window(2);
        closeWindow();
        switchTo().window(0);
    }

    public void LoginSteps(){
        clickCheckbox.click();
        importWalletButton.click();
        agreeButton.click();

        collectionSeedPhrase.shouldHave(size(12));
        for (int i = 0; i < collectionSeedPhrase.size(); i++) {
           collectionSeedPhrase.get(i).setValue(Credentials.SECRET_META.get(i));
        }

        connectButton.click();

        inputsForPassword.shouldHave(size(2));
        for(SelenideElement elem : inputsForPassword){
            elem.setValue(Credentials.PASSWORD_META);
        }

        clickCheckbox.click();
        importWalletButtonSub.click();

        buttonOne.click();
        buttonTwoThree.click();
        buttonTwoThree.click();
    }

    public void ConfirmFirstConnectWallet(){
        switchToMetaMaskWindow();
        refresh();
        $x("//button[text()='Подключиться']").shouldBe(visible,Duration.ofSeconds(15)).click();
        $x("//button[@data-testid=\"page-container-footer-next\"]").shouldBe(visible,Duration.ofSeconds(15)).click();
        switchToMainWindow();
        refresh();
    }

    public void ConfirmBet(){
        switchToMetaMaskWindow();
        refresh();
        $x("//button[text()='Подтвердить']").shouldBe(visible,Duration.ofSeconds(15)).click();
        switchToMainWindow();
    }


    public void ConfirmSwitchNetwork(){
        switchToMetaMaskWindow();
        refresh();
        $x("//button[text()='Одобрить']").shouldBe(visible,Duration.ofSeconds(15)).click();
        switchToMainWindow();
    }
}
