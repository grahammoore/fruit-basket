package uk.org.cute.fruitbasket;


import java.math.BigDecimal;

public interface PricingService {

    BigDecimal getUnitPrice(Item item);

}
