package test;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import page.FlipCoinPage;
import page.MetaPage;
import utils.Credentials;

import java.io.File;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.refresh;


public class MainTest {

    private final static String URL_FLIP_COIN = "https://dg-dev-app.profiterole.group/";
    private final static String URL_META = "chrome-extension://nkbihfbeogaeaoehlefnkodbefgpgknn/home.html#onboarding/welcome";

    @BeforeAll
    public static void SetUp(){
        ChromeOptions options = new ChromeOptions();
        options.addExtensions(new File("src/test/resources/extensions/metamask.crx"));
        options.addArguments("--window-position=250,200");  // x, y координаты

        // Важно: Передаём options в Selenide
        Configuration.browserCapabilities = options;

    }

    @Test
    public void MainHealthTest(){

        //Зашли на мету, подключили кошель
        MetaPage metaPage = new MetaPage(URL_META);
        metaPage.LoginSteps();

        //Зашли на стенд подключаем кошель
        FlipCoinPage flipCoinPage = new FlipCoinPage(URL_FLIP_COIN);
        flipCoinPage.ConnectWallet();

        //Подтвердили коннект кошеля к сайту + первое подключение эфира
        metaPage.ConfirmFirstConnectWallet();

        //Создаем 2 игры на эфире
        flipCoinPage.PlaceBet(Credentials.BET); //разместил ставку - слип странички ждем появления
        int a = 0;
        metaPage.ConfirmBet();
        Selenide.sleep(2000);
        flipCoinPage.PlaceBet(Credentials.BET);
        metaPage.ConfirmBet();

        Selenide.sleep(3000); // даем сайту очухаться после сделанных 2 ставочек

        //Создаем 2 игры на бнб---------------------------------------------------------------------------------------
        flipCoinPage.SwitchNetworkInDropdown(Credentials.BNB); //поменял сеть
        metaPage.ConfirmSwitchNetwork();    //подтвердил в окне ММ

        Selenide.sleep(2000);

        flipCoinPage.PlaceBet(Credentials.BET);
        metaPage.ConfirmBet();
        Selenide.sleep(2000);
        flipCoinPage.PlaceBet(Credentials.BET);
        metaPage.ConfirmBet();

        Selenide.sleep(3000); // даем сайту очухаться после сделанных 2 ставочек

        //Создаем 2 игры на полигон---------------------------------------------------------------------------------------
        flipCoinPage.SwitchNetworkInDropdown(Credentials.POL); //поменял сеть
        metaPage.ConfirmSwitchNetwork();    //подтвердил в окне ММ

        Selenide.sleep(2000);

        flipCoinPage.PlaceBet(Credentials.BET);
        metaPage.ConfirmBet();
        Selenide.sleep(2000);
        flipCoinPage.PlaceBet(Credentials.BET);
        metaPage.ConfirmBet();

        Selenide.sleep(3000); // даем сайту очухаться после сделанных 2 ставочек

        //Создаем 2 игры на авакс---------------------------------------------------------------------------------------
        flipCoinPage.SwitchNetworkInDropdown(Credentials.AVX); //поменял сеть
        metaPage.ConfirmSwitchNetwork();    //подтвердил в окне ММ

        Selenide.sleep(2000);

        flipCoinPage.PlaceBet(Credentials.BET);
        metaPage.ConfirmBet();
        Selenide.sleep(2000);
        flipCoinPage.PlaceBet(Credentials.BET);
        metaPage.ConfirmBet();

        Selenide.sleep(3000); // даем сайту очухаться после сделанных 2 ставочек

        //Создаем 2 игры на оптимизм---------------------------------------------------------------------------------------
        flipCoinPage.SwitchNetworkInDropdown(Credentials.OPT); //поменял сеть
        metaPage.ConfirmSwitchNetwork();    //подтвердил в окне ММ

        Selenide.sleep(2000);

        flipCoinPage.PlaceBet(Credentials.BET);
        metaPage.ConfirmBet();
        Selenide.sleep(2000);
        flipCoinPage.PlaceBet(Credentials.BET);
        metaPage.ConfirmBet();

        Selenide.sleep(3000); // даем сайту очухаться после сделанных 2 ставочек

    }
}
