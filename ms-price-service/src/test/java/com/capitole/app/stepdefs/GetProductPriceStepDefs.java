package com.capitole.app.stepdefs;

import com.capitole.app.PriceServiceApplication;
import com.capitole.app.model.entity.PriceEntity;
import com.capitole.app.model.repository.PriceRepository;
import com.capitole.app.service.PriceServiceImpl;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.time.OffsetDateTime;
import java.time.format.DateTimeParseException;
import java.util.Locale;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


@SpringBootTest(classes = PriceServiceApplication.class)
@ContextConfiguration(classes = {PriceServiceApplication.class})
@CucumberContextConfiguration
public class GetProductPriceStepDefs {

    private final Logger log = LoggerFactory.getLogger(GetProductPriceStepDefs.class);

    @Autowired
    private PriceRepository priceRepository;

    @Autowired
    private PriceServiceImpl priceService;

    public PriceEntity priceEntity;

    public String price;

    private Exception lastException = null;


    @Given("the system has price configurations set")
    public void theSystemHasPriceConfigurationsSet() {
        log.info("Test");
        initializeInMemoryDBWithPriceRates();
    }

    private void initializeInMemoryDBWithPriceRates() {
        log.info("Call to initializeInMemoryDBWithPriceRates");
        //Limpiar cualquier dato previo si es necesario
        priceRepository.deleteAll();

        // Crear objetos PriceRate con datos iniciales
        PriceEntity rate1 = new PriceEntity();
        rate1.setBrandId(1);
        rate1.setStartDate(OffsetDateTime.parse("2020-06-14T00:00:00Z"));
        rate1.setEndDate(OffsetDateTime.parse("2020-12-31T23:59:59Z"));
        rate1.setPriceList(1);
        rate1.setProductId(35455);
        rate1.setPriority(0);
        rate1.setPrice(35.50);
        rate1.setCurr("EUR");

        PriceEntity rate2 = new PriceEntity();
        rate2.setBrandId(1);
        rate2.setStartDate(OffsetDateTime.parse("2020-06-14T15:00:00Z"));
        rate2.setEndDate(OffsetDateTime.parse("2020-06-14T18:30:00Z"));
        rate2.setPriceList(2);
        rate2.setProductId(35455);
        rate2.setPriority(1);
        rate2.setPrice(25.45);
        rate2.setCurr("EUR");

        PriceEntity rate3 = new PriceEntity();
        rate3.setBrandId(1);
        rate3.setStartDate(OffsetDateTime.parse("2020-06-15T00:00:00Z"));
        rate3.setEndDate(OffsetDateTime.parse("2020-06-15T11:00:00Z"));
        rate3.setPriceList(3);
        rate3.setProductId(35455);
        rate3.setPriority(1);
        rate3.setPrice(30.50);
        rate3.setCurr("EUR");

        PriceEntity rate4 = new PriceEntity();
        rate4.setBrandId(1);
        rate4.setStartDate(OffsetDateTime.parse("2020-06-15T16:00:00Z"));
        rate4.setEndDate(OffsetDateTime.parse("2020-12-31T23:59:59Z"));
        rate4.setPriceList(4);
        rate4.setProductId(35455);
        rate4.setPriority(1);
        rate4.setPrice(38.95);
        rate4.setCurr("EUR");

//         Guardar estos objetos en el repositorio (y por lo tanto en la base de datos en memoria)
        priceRepository.save(rate1);
        priceRepository.save(rate2);
        priceRepository.save(rate3);
        priceRepository.save(rate4);
    }


    @When("I request the price for product {string} from brand {string} at {string}")
    public void iRequestThePriceForProductFromBrandAt(String productId, String brandId, String dateTime) {
        try {
            if (isNullOrEmpty(productId) || isNullOrEmpty(brandId) || isNullOrEmpty(dateTime)) {
                throw new Exception("Missing parameter");
            }

            OffsetDateTime offsetDateTime = OffsetDateTime.parse(dateTime);
            priceEntity = priceService.findPriceByProductIdAndBrandIdAndDate(Integer.parseInt(productId), Integer.parseInt(brandId), offsetDateTime);

            if (priceEntity == null) {
                throw new Exception("Price not found");
            }

            price = String.format(Locale.US, "%.2f", priceEntity.getPrice());
            lastException = null;
        } catch (DateTimeParseException e) {
            lastException = new Exception("Invalid date format");
        } catch (Exception e) {
            lastException = e;
        }
    }

    private boolean isNullOrEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }

    @Then("the system should return a price of {string} EUR")
    public void theSystemShouldReturnAPriceOfEUR(String expectedPrice) {
        System.out.println("priceEntity = " + price);
        assertEquals(
                expectedPrice,
                price
        );
    }

    @Then("the system should return a {string} error")
    public void theSystemShouldReturnAError(String expectedMessage) {
        assertNotNull("Expected an exception to be thrown", lastException);
        assertEquals(expectedMessage, lastException.getMessage());
    }
}
