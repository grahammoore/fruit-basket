package uk.org.cute.fruitbasket.simple;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uk.org.cute.fruitbasket.Basket;
import uk.org.cute.fruitbasket.BasketCostingService;
import uk.org.cute.fruitbasket.PricingService;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.function.Function;

@Component("basketCostingService")
public class DefaultBasketCostingService implements BasketCostingService {

    @Autowired
    private PricingService pricingService;

    @Override
    public BigDecimal calculateTotalCost(final Basket basket) {
        Objects.requireNonNull(basket, "Basket is null");

        final Function<Basket.BasketEntry, BigDecimal> calculateItemCost = basketEntry ->
                pricingService.getUnitPrice(basketEntry.getItem())
                        .orElseThrow(() -> new IllegalStateException(String.format("No price for item [%s]", basketEntry.getItem())))
                    .multiply(new BigDecimal(basketEntry.getQuantity()));

        return basket.getItems().stream()
                .map(basketEntry -> calculateItemCost.apply(basketEntry))
                .reduce((totalCost, itemCost) -> totalCost.add(itemCost))
                .orElse(BigDecimal.ZERO);
    }

}
