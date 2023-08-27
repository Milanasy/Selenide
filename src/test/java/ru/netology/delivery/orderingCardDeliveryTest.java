package ru.netology.delivery;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class orderingCardDeliveryTest {

    private String makeDate(int addDays, String pattern) {
        return LocalDate.now().plusDays(addDays).format(DateTimeFormatter.ofPattern(pattern));
    }

        @Test
        void CardDelivery () {
            open("http://localhost:9999");
            $("[data-test-id=city] input").setValue("Белгород");
            String bookedDate = makeDate(4, "dd.MM.yyyy");
            $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME));
            $("[data-test-id='date'] input").sendKeys(bookedDate);
            $("[data-test-id=name] input").setValue("Рахманова Анастасия");
            $("[data-test-id=phone] input").setValue("+70000000000");
            $("[data-test-id=agreement]").click();
            $("button.button").click();
            $(".notification__content").shouldBe(Condition.visible, Duration.ofSeconds(15))
                    .shouldHave(Condition.exactText("Встреча успешно забронирована на " + bookedDate));
        }
    }
