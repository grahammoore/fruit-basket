package uk.org.cute.fruitbasket.simple;


import org.springframework.stereotype.Component;
import uk.org.cute.fruitbasket.Item;
import uk.org.cute.fruitbasket.ItemRepository;
import uk.org.cute.fruitbasket.PricingService;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Component("defaultItemRepository")
public class DefaultItemRepository implements ItemRepository, PricingService {

    private static final Item[] ITEMS = {
            new DefaultItem("Banana", new BigDecimal("0.90")),
            new DefaultItem("Orange", new BigDecimal("0.80")),
            new DefaultItem("Apple", new BigDecimal("0.50")),
            new DefaultItem("Lemon", new BigDecimal("0.25")),
            new DefaultItem("Peach", new BigDecimal("0.85")),
    };

    private final Map<String, Item> nameToItem = new ConcurrentHashMap<>();


    DefaultItemRepository() {
        Arrays.stream(ITEMS).forEach(item -> nameToItem.put(item.getName(), item));
    }

    @Override
    public Optional<Item> findItemByName(String name) {
        Objects.requireNonNull(name, "Name is null");
        return Optional.ofNullable(nameToItem.get(name));
    }

    @Override
    public BigDecimal getUnitPrice(Item item) {
        Objects.requireNonNull(item, "Item is null");
        return ((DefaultItem) item).getPricePerUnit();
    }

}
