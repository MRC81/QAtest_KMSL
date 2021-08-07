import io.qameta.allure.Description;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.WebDriverConditions.url;
import static io.qameta.allure.Allure.step;

public class Scenario2 extends SetUp {

    @Test
    @Description("This test opens the www.kmslh.com URL, clicks on 'self-service' item, on the 'self-service' page " +
            "verifies that there's the Pop-Up (and the frame with video behind it), then clicks on the 'Click Here' " +
            "button of the Pop-Up and verifies the this leads to the 'Benchmark Guide...' page.")
    public void scenario2_Test() {
        step("Step 1: Wait for the Umbrella to be opened and click the Self-Service icon", () ->
                $(".product-item.item-6:nth-child(6) > a:nth-child(1)")
                        .shouldBe(visible, Duration.ofSeconds(20)).click()
        );

        step("Step 2: Verify that you were redirected to the 'Self-Service' page", () ->
                webdriver().shouldHave(url("https://www.kmslh.com/products/self-service/"))
        );

        step("Step 3: Verify that the pop-up is shown", () ->
                $(".animated").should(appear, Duration.ofSeconds(20))
        );

        step("Step 4: Verify that the video frame is shown too", () ->
                $("[class='elementor-video']").shouldBe(visible)
        );

        step("Step 5: Click on the 'Click here' button since there's no 'Copy link' button", () ->
                $x("//span[contains(text(),'Click here')]").shouldBe(visible).click()
        );

        step("Step 6: Verify that you were redirected to the 'Benchmark Guide...' page", () ->
                webdriver().shouldHave(url("https://www.kmslh.com/" +
                                "a-benchmark-guide-for-call-center-automation-in-2021-and-beyond/"),
                        Duration.ofSeconds(20))
        );
    }

}
