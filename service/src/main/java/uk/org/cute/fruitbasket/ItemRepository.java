package uk.org.cute.fruitbasket;


import java.math.BigDecimal;
import java.util.Optional;


/**
 * The ItemRepository is where Item objects are acquired.
 */
public interface ItemRepository {

    /**
     * Retrieve an Item by name. Null is never returned, Item not found is represented by Optional.empty();
     * @param name name of Item.
     * @return Item or empty.
     */
    Optional<Item> getItemByName(String name);

}
