package uk.org.cute.fruitbasket;


/**
 * Factory class for creating new Baskets.
 */
public interface BasketFactory {

    /**
     * Create a new Basket.
     *
     * @return New  basket with no Items.
     */
    Basket createBasket();

}
