package uk.org.cute.fruitbasket;


import java.math.BigDecimal;
import java.util.Optional;

public interface PricingService {

    Optional<BigDecimal> getUnitPrice(Item item);

}
