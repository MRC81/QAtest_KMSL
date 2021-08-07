import io.qameta.allure.Description;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.urlStartingWith;
import static io.qameta.allure.Allure.step;

public class Scenario1 extends SetUp{


    @Test
    @Description("This test opens the www.kmslh.com/automation-test/ URL, fills the fields with randomly generated " +
            "values, submits them, and verify that this leads to the confirmation page.")
    public void scenario1_Test() {

        step("Step 1: Go to the 'www.kmslh.com/automation-test' ", () ->
            open("automation-test/")
        );

        RandomClient client = step("Step 2: Create a new Client object with randomly generated data",
                RandomClient::new);

        step("Step 3: Fill the registration fields with the Client's data", () -> {
            $("[name='firstname']").shouldBe(visible).sendKeys(client.getFirstName());
            $("[name='lastname']").shouldBe(visible).sendKeys(client.getLastName());
            $("[name='email']").shouldBe(visible).sendKeys(client.getEmail());
            $("[name='phone']").shouldBe(visible).sendKeys(client.getPhone());
            $("[name='company']").shouldBe(visible).sendKeys(client.getCompany());
        });

        step("Step 5: Click the 'Submit' button", () ->
                $("[type='submit']").shouldBe(visible, enabled).click()
        );

        step("Step 6: Verify that you were redirected to the 'saleforce-promotion' page", () ->
                webdriver().shouldHave(urlStartingWith("https://www.kmslh.com/" +
                        "saleforce-promotion-june-2020-thank-you/?submissionGuid="))
        );

        step("Step 7: Verify that the page has 'Thank you for singing up!' text", () ->
                $("div.elementor-widget-container h1")
                        .shouldBe(visible, Duration.ofSeconds(10))
                        .shouldHave(text("Thank you for singing up!\n" +
                                "We will be in touch with you shortly."))
        );
    }



}
