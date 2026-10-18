package tests;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;
import tests.enums.Month;

import java.time.LocalDate;
import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.value;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static tests.helper.DateHelper.formatDate;

public class CalendarTest extends TestBase {

    @ParameterizedTest(name = "Выбор месяца: {0}")
    @EnumSource(Month.class)
    void shouldSelectMonth(Month month) {

        open("https://www.playwrightautomation.com/practice.html#section-datepicker-native");

        $("#dp-dd-month").selectOptionByValue(String.valueOf(month.ordinal()));
        $("#dp-dd-month").shouldHave(value(String.valueOf(month.ordinal())));
    }

    static Stream<Arguments> shouldDisplayCorrectDays() {
        return Stream.of(
                Arguments.of(2026, 2, 28),
                Arguments.of(2025, 4, 30),
                Arguments.of(2024, 2, 29),
                Arguments.of(2026, 1, 31)
        );
    }

    @ParameterizedTest(name = "Дата: {2}.{1}.{0}")
    @MethodSource("shouldDisplayCorrectDays")
    void shouldDisplayCorrectDays(int year, int month, int day) {

        open("https://www.playwrightautomation.com/practice.html#section-datepicker-dropdown");

        // Выбираем месяц, год
        $("#dp-dd-month").selectOptionByValue(String.valueOf(month - 1));
        $("#dp-dd-year").selectOptionByValue(String.valueOf(year));

        // Выбираем день
        String attributeDate = LocalDate.of(year, month, day).toString();
        $("#dp-dd-cal-body").$("[data-date='" + attributeDate + "']").click();

        // Проверяем отображаемую дату
        $("#dp-dd-display").shouldHave(exactText(formatDate(year, month, day)));
    }
}
