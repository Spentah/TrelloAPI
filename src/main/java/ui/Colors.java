package ui;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

public enum Colors {
    GREEN(Selenide.$x("//button[@class='_31xT7xOqkxPLkw _1hFyzxe1-LRBw8']")),
    YELLOW(Selenide.$x("//button[@class='_31xT7xOqkxPLkw _1kFCqdmLY2X1fa']")),
    ORANGE(Selenide.$x("//button[@class='_31xT7xOqkxPLkw _3fbBEWI2O2NMNN']")),
    RED(Selenide.$x("//button[@class='_31xT7xOqkxPLkw _1lQzY6C5CVUUxw']")),
    PURPLE(Selenide.$x("//button[@class='_31xT7xOqkxPLkw _3GdCpFdaWSN4H4']")),
    DARK_BLUE(Selenide.$x("//button[@class='_31xT7xOqkxPLkw _3Rilp7XwjrQrfV']")),
    BLUE(Selenide.$x("//button[@class='_31xT7xOqkxPLkw _3PYjFb7GK6Y5Cy']")),
    SALAT(Selenide.$x("//button[@class='_31xT7xOqkxPLkw _3WED4RNr6dLCTr']")),
    PINK(Selenide.$x("//button[@class='_31xT7xOqkxPLkw _3LXcWy8pkF_N7j']")),
    BLACK(Selenide.$x("//button[@class='_31xT7xOqkxPLkw _2v4IMkqDq3aDWf']"));

    private SelenideElement color;

    Colors(SelenideElement color) {
        this.color = color;
    }

    public SelenideElement getColor() {
        return color;
    }
}
