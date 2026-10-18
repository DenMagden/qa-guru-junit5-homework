package tests;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;


import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.url;

public class OrderPageTest extends TestBase {

    @ParameterizedTest
    @ValueSource(strings = {
            "/order/431149/",
            "/order/431155/",
            "/order/431161/"
    })
    void shouldOpenOrderPage(String orderUrl) {

        open("https://2090000.ru/internet/");

        $("a[href='" + orderUrl + "']").scrollIntoCenter().click();

        webdriver().shouldHave(url("https://2090000.ru" + orderUrl));
        $(".order__block[data-type='customer']").shouldBe(visible);
        $(".order__title").shouldHave(exactText("ОФОРМЛЕНИЕ ЗАКАЗА"));
    }
}
