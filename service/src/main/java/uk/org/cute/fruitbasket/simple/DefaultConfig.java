package uk.org.cute.fruitbasket.simple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import uk.org.cute.fruitbasket.ItemRepository;
import uk.org.cute.fruitbasket.PricingService;

/**
 * Spring config to facilitate auto-wiring. Needed because defaultItemRepository fulfills two beans, itemRepository
 * and pricingService.
 */
@Configuration
public class DefaultConfig {

    DefaultConfig() {
    }

    @Autowired
    private DefaultItemRepository defaultItemRepository;

    @Bean("itemRepository")
    public ItemRepository getItemRepository() {
        return defaultItemRepository;
    }

    @Bean("pricingService")
    public PricingService getPricingService() {
        return defaultItemRepository;
    }


}
