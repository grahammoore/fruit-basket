package uk.org.cute.fruitbasket;

import java.math.BigDecimal;

public interface BasketCostingService {

    BigDecimal calculateTotalCost(Basket basket);

}
