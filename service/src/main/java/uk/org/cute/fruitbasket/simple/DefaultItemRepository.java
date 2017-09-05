package uk.org.cute.fruitbasket.simple;


import org.springframework.stereotype.Component;
import uk.org.cute.fruitbasket.Item;
import uk.org.cute.fruitbasket.ItemRepository;
import uk.org.cute.fruitbasket.PricingService;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Component("defaultItemRepository")
public class DefaultItemRepository implements ItemRepository, PricingService {

    private final Map<String, Item> nameToItem = new ConcurrentHashMap<>();
    private final Map<Item, BigDecimal> itemToPrice = new ConcurrentHashMap<>();

    DefaultItemRepository() {
        itemToPrice.put(new DefaultItem("Banana"), new BigDecimal("0.90"));
        itemToPrice.put(new DefaultItem("Orange"), new BigDecimal("0.80"));
        itemToPrice.put(new DefaultItem("Apple"), new BigDecimal("0.50"));
        itemToPrice.put(new DefaultItem("Lemon"), new BigDecimal("0.25"));
        itemToPrice.put(new DefaultItem("Peach"), new BigDecimal("0.85"));

        itemToPrice.keySet().forEach(item -> nameToItem.put(item.getName(), item));
    }

    @Override
    public Optional<Item> findItemByName(final String name) {
        Objects.requireNonNull(name, "Name is null");
        return Optional.ofNullable(nameToItem.get(name));
    }

    @Override
    public Optional<BigDecimal> getUnitPrice(final Item item) {
        Objects.requireNonNull(item, "Item is null");
        return Optional.ofNullable(itemToPrice.get(item));
    }

}
