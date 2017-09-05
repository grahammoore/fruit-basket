package uk.org.cute.fruitbasket;

import java.math.BigDecimal;

/**
 * Service for taking a Basket and calculating the cost of the basket using current prices.
 */
public interface BasketCostingService {

    /**
     * Calculate the total cost of the Basket.
     *
     * @param basket Basket to calculate total cost of.
     * @return BigDecimal representing the current total cost of the Basket.
     */
    BigDecimal calculateTotalCost(Basket basket);

}
