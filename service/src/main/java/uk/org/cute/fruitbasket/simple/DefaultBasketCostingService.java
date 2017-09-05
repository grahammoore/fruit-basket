package uk.org.cute.fruitbasket.simple;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uk.org.cute.fruitbasket.Basket;
import uk.org.cute.fruitbasket.BasketCostingService;
import uk.org.cute.fruitbasket.PricingService;

import java.math.BigDecimal;
import java.util.InputMismatchException;
import java.util.function.BiFunction;
import java.util.function.Function;

@Component("basketCostingService")
public class DefaultBasketCostingService implements BasketCostingService{

    @Autowired
    private PricingService pricingService;

    @Override
    public BigDecimal calculateTotalCost(Basket basket) {
        Function<Basket.BasketEntry, BigDecimal> calculateItemCost = basketEntry ->
                pricingService.getUnitPrice(basketEntry.getItem())
                    .multiply(new BigDecimal(basketEntry.getQuantity()));

        return basket.getItems().stream()
                .map(basketEntry -> calculateItemCost.apply(basketEntry))
                .reduce((totalCost, itemCost) -> totalCost.add(itemCost))
                .orElse(BigDecimal.ZERO);
    }

}
