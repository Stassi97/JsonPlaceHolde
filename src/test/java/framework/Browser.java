package framework;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class Browser {
    public static Browser instance;
    public static WebDriver driver;

    public static Browser getInstance() {
        if (instance == null) {
            driver = DriverFactory.getDriver();
            driver.manage().timeouts().implicitlyWait(PropertyReader.getIntProperty("timeout"), TimeUnit.SECONDS); //неявное ожидание драйвера 10сек
        } else{
            System.err.println("Driver doesn't instance!");
        }
        return instance = new Browser(); //
    }

    public static void windowMaximize() {
        driver.manage().window().maximize();
    }

    public static void navigateTo(String url) { //метод перехода по сслыке
        driver.get(url);
    }

    public static void close() { //метод закрытия браузера
        driver.quit();
        instance = null;
        System.out.println("Driver has been closed.");
    }

    public static void waitForPageLoad() {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(PropertyReader.getIntProperty("page.load.timeout"))); //на протяжении 20сек обращается к документу и проверяет статус
        wait.until(driver->executor.executeScript("return document.readyState").equals("complete")); //wait until явное ожидание

    }

    public static WebDriver getDriver() {
        return driver;
    }
}
