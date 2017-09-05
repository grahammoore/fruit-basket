package uk.org.cute.fruitbasket;


import java.util.Optional;

public interface ItemRepository {

    Optional<Item> findItemByName(String name);

}
