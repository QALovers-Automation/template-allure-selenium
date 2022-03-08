
package com.aura.qa;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Properties;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.qameta.allure.Step;

@TestMethodOrder(OrderAnnotation.class)
public class AuraWebTest {

	private static Logger logger = LoggerFactory.getLogger(AuraWebTest.class);

	private static WebDriver driver;

	@BeforeAll
	public static void setup() throws Exception {

		TestFunctions.loadConfiguration();
		driver = TestFunctions.configureDriver(Constants.DRIVER_SELECTED);
		driver.manage().window().maximize();
		TestFunctions.setTimeOut(driver);

		logger.info("*****************LOG******************");

		App app = new App();
		Properties prop = app.loadPropertiesFile("allure.properties");
		prop.forEach((k, v) -> System.out.println(k + ":" + v));

		logger.info("*****************END LOG******************");

	}

	@Test
	@DisplayName("Página inicial")
	@Tag("Acceso")
	@Step("Acceso directo a URL")
	@Order(1)
	public void testInicio() {

		String TEXT_TO_CHECK = "AURA GROUP | Consultoría tecnológica para empresas";
		String URL_TO_CHECK = "https://auragroup.es";

		driver.get(URL_TO_CHECK);
		TestFunctions.await(driver);
		assertEquals(TEXT_TO_CHECK, driver.getTitle());
	}

	@Test
	@DisplayName("Página cultura")
	@Tag("Acceso")
	@Step("Acceso directo a URL")
	@Order(2)
	public void testCultura() {
		String TEXT_TO_CHECK = "Cultura - Cultura digital y corporativa de Aura";
		String URL_TO_CHECK = "https://auragroup.es/cultura";

		driver.get(URL_TO_CHECK);
		TestFunctions.await(driver);
		assertEquals(TEXT_TO_CHECK, driver.getTitle());
	}

	@Test
	@DisplayName("Página unete")
	@Tag("Acceso")
	@Step("Acceso directo a URL")
	@Order(3)
	public void testUnet() {
		String TEXT_TO_CHECK = "UNETE | Ofertas de empleo IT para trabajar en Aura Group";
		String URL_TO_CHECK = "https://auragroup.es/unet";

		driver.get(URL_TO_CHECK);
		TestFunctions.await(driver);
		assertEquals(TEXT_TO_CHECK, driver.getTitle());
	}

	@Test
	@DisplayName("Página blog")
	@Tag("Acceso")
	@Step("Acceso directo a URL")
	@Order(4)
	public void testBlog() {
		String TEXT_TO_CHECK = "BLOG DE TECNOLOGIA | Todo sobre tecnología e innovación empresarial";
		String URL_TO_CHECK = "https://auragroup.es/blog-tecnologia";

		driver.get(URL_TO_CHECK);
		TestFunctions.await(driver);
		assertEquals(TEXT_TO_CHECK, driver.getTitle());
	}

	@Test
	@DisplayName("Página contacto")
	@Tag("Acceso")
	@Step("Acceso directo a URL")
	@Order(5)
	public void testContacto() {
		String TEXT_TO_CHECK = "Contacto - Aura IRC";
		String URL_TO_CHECK = "https://auragroup.es/contacto/";

		driver.get(URL_TO_CHECK);
		TestFunctions.await(driver);
		assertEquals(TEXT_TO_CHECK, driver.getTitle());
	}

	@Test
	@DisplayName("Página contacto lang")
	@Tag("Idioma")
	@Step("Acceso directo a URL")
	@Order(6)
	public void testContactoLang() {

		String TEXT_TO_CHECK = "Contact - Aura IRC";
		String URL_TO_CHECK = "https://auragroup.es/contacto";

		String xPathExpre = "//img[@class='wpml-ls-flag'][@alt=\"Inglés\"][1]";

		driver.get(URL_TO_CHECK);
		TestFunctions.await(driver);

		WebElement langIcon = driver.findElement(By.xpath(xPathExpre));

		if (langIcon != null) {
			langIcon.click();
			TestFunctions.await(driver);
		}

		assertEquals(TEXT_TO_CHECK, driver.getTitle());
	}

	@Test
	@DisplayName("Página contacto lang")
	@Tag("Idioma")
	@Step("Acceso directo a URL")
	@Order(7)
	public void testContactoForm() {

		String TEXT_TO_CHECK = "Contacto - Aura IRC";
		String URL_TO_CHECK = "https://auragroup.es/contacto";
		String NAME_FIELD = "et_pb_contact_name_0";
		String EMAIL_FIELD = "et_pb_contact_email_0";
		String MENSAJE_FIELD = "et_pb_contact_message_0";
		String SEND_BUTTON = "et_builder_submit_button";

		driver.get(URL_TO_CHECK);

		WebElement nameText = driver.findElement(By.id(NAME_FIELD));
		nameText.clear();
		nameText.sendKeys("Bot Selenium");

		WebElement emailText = driver.findElement(By.id(EMAIL_FIELD));
		emailText.clear();
		emailText.sendKeys("seleniumBot@auragroup.es");

		WebElement mensajeText = driver.findElement(By.id(MENSAJE_FIELD));
		mensajeText.clear();
		mensajeText.sendKeys("Texto automatizado escrito con selenium");

		WebElement sendButton = driver.findElement(By.name(SEND_BUTTON));

		sendButton.click();

		// No se cambia de pantall porque no hemos rellenado el captcha
		assertEquals(TEXT_TO_CHECK, driver.getTitle());
	}

	@AfterAll
	public static void tearDown() throws Exception {
		driver.quit();
	}

}
