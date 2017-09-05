package uk.org.cute.fruitbasket.jbehave.tests;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({})
@ComponentScan("uk.org.cute.fruitbasket")
public class AcceptanceTestsConfiguration {
}