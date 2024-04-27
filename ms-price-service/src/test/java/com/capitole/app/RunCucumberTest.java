package com.capitole.app;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

//extraGlue = "com.capitole.priceservice.pricecommons", // Paquete donde se encuentran tus definiciones de pasos
@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features", // Ubicación de tus archivos .feature
        glue = "com.capitole.app.stepdefs", // Paquete donde se encuentran tus definiciones de pasos
        plugin = {"pretty", "html:target/cucumber-reports"} // Opciones para el formato de los resultados
)

public class RunCucumberTest {
}
