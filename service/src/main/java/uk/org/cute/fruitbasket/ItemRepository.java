package uk.org.cute.fruitbasket;


import java.math.BigDecimal;
import java.util.Optional;

public interface ItemRepository {

    Optional<Item> findItemByName(String name);

    Optional<BigDecimal> getUnitPrice(Item item);

}
