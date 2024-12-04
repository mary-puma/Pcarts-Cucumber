package org.example;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features",   // Ruta a los archivos .feature
        glue = "org/example/stepDefinitions",       // Ruta de las definiciones de pasos
        plugin = {
                "pretty",                                // Muestra los resultados en consola de forma legible
                "html:target/cucumber-reports.html",     // Genera un reporte en formato HTML
                "json:target/cucumber-reports.json"      // Genera un reporte en formato JSON
        },
        monochrome = true                            // Mejora la legibilidad en la consola
)
public class TestRunner extends AbstractTestNGCucumberTests {
    // No es necesario agregar ningún método aquí
}
