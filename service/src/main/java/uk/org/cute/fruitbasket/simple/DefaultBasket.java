package uk.org.cute.fruitbasket.simple;


import org.springframework.beans.factory.annotation.Autowired;
import uk.org.cute.fruitbasket.Basket;
import uk.org.cute.fruitbasket.Item;
import uk.org.cute.fruitbasket.PricingService;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;

public class DefaultBasket implements Basket {

    class DefaultBasketEntry implements BasketEntry{
        private Item item;
        private int quantity;

        DefaultBasketEntry(Item item , int quantity) {
            this.item = item;
            this.quantity = quantity;
        }

        @Override
        public Item getItem() {
            return item;
        }

        @Override
        public int getQuantity() {
            return quantity;
        }
    }
//
//    @Autowired
//    PricingService pricingService;

    private List<BasketEntry> basketEntries = new CopyOnWriteArrayList<>();

    @Override
    public void addItemToBasket(Item item, int quantity) {
        Objects.requireNonNull(item, "Item is null");

        if (quantity < 1) {
            throw new IllegalArgumentException(String.format("Quantity of item [%s] must be greater than zero [%d]", item, quantity));
        }

        basketEntries.add(new DefaultBasketEntry(item, quantity));
    }

    @Override
    public Collection<BasketEntry> getItems() {
        return Collections.unmodifiableCollection(basketEntries);
    }
}
