package uk.org.cute.fruitbasket.simple;


import org.springframework.stereotype.Component;
import uk.org.cute.fruitbasket.Item;
import uk.org.cute.fruitbasket.PricingService;

import java.math.BigDecimal;
import java.util.Optional;

@Component("defaultPricingService")
public class DefaultPricingService extends DefaultItemRepository implements PricingService {

    @Override
    public Optional<BigDecimal> getUnitPrice(final Item item) {
        return super.getUnitPrice(item);
    }

}
