package page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.impl.JavaScript;
import interfaces.SwitchWindow;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import utils.Credentials;

import java.time.Duration;
import java.util.Set;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class FlipCoinPage implements SwitchWindow {

    private final SelenideElement connectWalletButton = $x("//button[@class=\"ant-btn css-o4pwa6 ant-btn-primary blue ConnectWalletButton\"]");
    private final SelenideElement agreeButton = $x("//button[@class=\"ant-btn css-o4pwa6 ant-btn-primary ant-btn-lg blue undefined\"]");
    private final SelenideElement inputArea = $x("//textarea");
    private final SelenideElement connectWalletInBlock = $x("//button[@class=\"ant-btn css-o4pwa6 ant-btn-primary ant-btn-lg blue ConnectWalletButton\"]");
    private final SelenideElement readAccept = $x("//span[text()=\"I read and accept\"]");
    private final SelenideElement chooseMeta = $x("//div[text()=\"MetaMask\"]");
    //---------------------------------------------------//
    private final SelenideElement buttonClickListAllNetworks = $x("//div[@class=\"sc-9hyxaf-3 hvFmbp step-13\"]");
    private final SelenideElement getNetwork = $x("//div[@class=\"ant-popover css-o4pwa6 css-o4pwa6 ant-popover-placement-bottom\"]//div[text()=");


    private final SelenideElement inputBet = $x("//input[@type=\"number\"]");
    private final SelenideElement chooseCoinSide = $x("//button[@class=\"sc-ku3a6p-0 iPQYIz\"]"); //пятерка
    private final SelenideElement flipButton = $x("//div[@class=\"sc-15xr1vo-0 iNdkzC common-btn\"]//span[text()='Flip']"); //пятерка

    public FlipCoinPage(String url) {
        open(url);
    }

    public void ConnectWallet(){
        switchToMainWindow();

        connectWalletButton.click();
        agreeButton.click();

        inputArea.setValue(Credentials.SECRET_KEY);

        connectWalletInBlock.click();
        readAccept.click();
        chooseMeta.click();
    }

    public void SwitchNetworkInDropdown(String str){
        buttonClickListAllNetworks.click();
        $x(getNetwork + str).click();
    }

    public void PlaceBet(String bet){
        inputBet.setValue(bet);
        chooseCoinSide.shouldBe(visible,Duration.ofSeconds(5)).click();
        flipButton.shouldBe(visible,Duration.ofSeconds(5)).click();
        Selenide.sleep(1500);
    }





}
