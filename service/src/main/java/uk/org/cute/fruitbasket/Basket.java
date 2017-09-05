package uk.org.cute.fruitbasket;

import java.util.Collection;

/**
 * Basket is a container of Items and associated quantities.
 */
public interface Basket {

    /**
     * The BasketEntry represents one entry in the Basket and contains an Item and a quantity.
     */
    interface BasketEntry {
        Item getItem();
        int getQuantity();
    }

    /**
     * Add another Item to the Basket. The effect of adding the same Item twice is implementation defined. A quantity
     * less than 1 is implementation defined.
     *
     * @param item Item to add.
     * @param quantity quantity to associate with that Item.
     */
    void addItemToBasket(Item item, int quantity);

    /**
     * Get a collection containing all the Baskets BasketEntries.
     *
     * @return collection of BasketEntries in the Basket.
     */
    Collection<BasketEntry> getItems();

}
