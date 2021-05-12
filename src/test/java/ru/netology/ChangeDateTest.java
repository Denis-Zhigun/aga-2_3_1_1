package ru.netology;
import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class ChangeDateTest {
    DeliveryDate dataText = new DeliveryDate();


    @BeforeEach
    public void setUp() {
        open("http://localhost:9999");
    }


    @Test
    void shouldTestAllFields() {
        String dataDelivery = dataText.ReturnDate(7);
        $("[data-test-id=city] [placeholder='Город']").setValue("Краснодар");
        $("[data-test-id=date] [placeholder='Дата встречи']").
                sendKeys(Keys.CONTROL + "A", Keys.DELETE);
        $("[data-test-id=date] [placeholder='Дата встречи']").
                setValue(String.valueOf(dataDelivery));
        $("[data-test-id=name] [name='name']").setValue("Даша Иванова");
        $("[data-test-id=phone] [name='phone']").setValue("+79112578998");
        $("[class=checkbox__box]").click();
        $(withText("Запланировать")).click();
        $(withText("Встреча успешно запланирована на")).
                shouldBe(Condition.visible, Duration.ofSeconds(25));
        $(withText(dataDelivery)).
                shouldBe(Condition.visible, Duration.ofSeconds(25));
    }
}
