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

    private DefaultBasketCostingService() {
    }

    /**
     * Calculate the total cost of the Basket. If no price exists an exception is thrown.
     *
     * @param basket Basket to calculate total cost of.
     * @return BigDecimal representing the current total cost of the Basket.
     * @throws IllegalStateException Thrown when there is no price for an Item.
     */
    @Override
    public BigDecimal calculateTotalCost(final Basket basket) {
        Objects.requireNonNull(basket, "Basket is null");

        // This function takes a BasketEntry, looks up it's price and multiplies it by the quantity.
        // If no price exists an exception is thrown.
        final Function<Basket.BasketEntry, BigDecimal> calculateItemCost = basketEntry ->
                pricingService.getUnitPrice(basketEntry.getItem())
                        .orElseThrow(() -> new IllegalStateException(String.format("No price for item [%s]", basketEntry.getItem())))
                    .multiply(new BigDecimal(basketEntry.getQuantity()));

        // This lambda goes through each BasketEntry and calculate the Item cost using the above function.
        // This cost is then reduced by adding it to a previous total of Item costs. The final single total cost is
        // returned. However if the Basket is empty zero is returned.
        return basket.getItems().parallelStream()
                .map(basketEntry -> calculateItemCost.apply(basketEntry))
                .reduce((totalCost, itemCost) -> totalCost.add(itemCost))
                .orElse(BigDecimal.ZERO);
    }

}
