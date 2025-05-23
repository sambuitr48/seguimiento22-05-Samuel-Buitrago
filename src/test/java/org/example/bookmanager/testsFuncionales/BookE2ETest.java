package org.example.bookmanager.testsFuncionales;

import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class BookE2ETest {

    private static WebDriver driver;

    @BeforeAll
    public static void configurar() {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");

        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @AfterAll
    public static void cerrar() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testAgregarLibroYVerificarEnLista() throws InterruptedException {
        driver.get("http://localhost:8080/books");

        driver.findElement(By.linkText("+ Agregar Libro")).click();

        driver.findElement(By.id("title")).sendKeys("Cien Años de Seriedad");
        driver.findElement(By.id("author")).sendKeys("Samuelin Pinguinin");
        driver.findElement(By.id("genre")).sendKeys("Mitos y Leyendas");
        driver.findElement(By.id("year")).sendKeys("2025");

        driver.findElement(By.tagName("form")).submit();

        Thread.sleep(1000);

        WebElement tabla = driver.findElement(By.tagName("table"));
        Assertions.assertTrue(tabla.getText().contains("Cien Años de Seriedad"));
    }
}
