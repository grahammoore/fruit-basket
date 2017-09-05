package uk.org.cute.fruitbasket;


import java.math.BigDecimal;
import java.util.Optional;

/**
 * Service for getting the current price of an Item.
 */
public interface PricingService {

    /**
     * Retrieve the current unit price for an Item. Null is never returned, price not found is represented by Optional.empty();
     * @param item Item to look up the price of.
     * @return Price as a BigDecimal or empty.
     */
    Optional<BigDecimal> getUnitPrice(Item item);

}
