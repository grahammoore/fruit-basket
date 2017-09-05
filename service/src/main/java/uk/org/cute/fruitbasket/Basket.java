package uk.org.cute.fruitbasket;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.stream.Stream;

public interface Basket {

    interface BasketEntry {
        Item getItem();
        int getQuantity();
    }

    void addItemToBasket(Item item, int quantity);

    Collection<BasketEntry> getItems();

}
