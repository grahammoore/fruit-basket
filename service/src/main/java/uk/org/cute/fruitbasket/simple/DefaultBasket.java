package uk.org.cute.fruitbasket.simple;


import org.springframework.beans.factory.annotation.Autowired;
import uk.org.cute.fruitbasket.Basket;
import uk.org.cute.fruitbasket.Item;
import uk.org.cute.fruitbasket.PricingService;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * This implementation of Basket uses a ConcurrentLinkedQueue to store the BasketEntries. The queue is used to allow fast
 * additions while not interfering with any other threads inspecting the entries. Consumers of getItems() are
 * given an unmodifiable Collection so the basket state cannot change unexpectedly.
 */
public class DefaultBasket implements Basket {

    class DefaultBasketEntry implements BasketEntry{
        private final Item item;
        private final int quantity;

        DefaultBasketEntry(final Item item , final int quantity) {
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

    private final Queue<BasketEntry> basketEntries = new ConcurrentLinkedQueue<>();

    DefaultBasket() {
    }

    /**
     * Add another Item to the Basket. Multiple additions of the same Item are not collated, the same Item will appear
     * the number of times add was called. If a quantity is less than one an exception is thrown.
     *
     * @param item Item to add.
     * @param quantity quantity to associate with that Item.
     * @throws IllegalArgumentException thrown when the quantity is less than 1.
     */
    @Override
    public void addItemToBasket(final Item item, final int quantity) {
        Objects.requireNonNull(item, "Item is null");

        if (quantity < 1) {
            throw new IllegalArgumentException(String.format("Quantity of item [%s] must be greater than zero [%d]", item, quantity));
        }

        basketEntries.add(new DefaultBasketEntry(item, quantity));
    }

    /**
     * Get a collection containing all the Baskets BasketEntries. The collection cannot be modified.
     *
     * @return collection of BasketEntries in the Basket.
     */
    @Override
    public Collection<BasketEntry> getItems() {
        return Collections.unmodifiableCollection(basketEntries);
    }
}
