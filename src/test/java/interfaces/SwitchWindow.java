package interfaces;

import com.codeborne.selenide.Selenide;


import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public interface SwitchWindow {

    default void switchToMetaMaskWindow() {
        switchTo().window(2);
        Selenide.sleep(2000);
    }

    default void switchToMainWindow() {
        switchTo().window(0);
        // Проверить, что вернулись
        $("body").shouldBe(visible);
    }

}
