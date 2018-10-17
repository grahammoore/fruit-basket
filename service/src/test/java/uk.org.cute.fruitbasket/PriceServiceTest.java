package uk.org.cute.fruitbasket;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import uk.org.cute.fruitbasket.simple.DefaultPricingService;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@JUnitTest
@RunWith(SpringJUnit4ClassRunner.class)
public class PriceServiceTest {

    @InjectMocks
    private DefaultPricingService pricingService;

    @Autowired
    @Resource(name = "itemRepository")
    private ItemRepository itemRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetPriceByItemKnown() {
        final Item item = itemRepository.getItemByName("Orange").get();

        final Optional<BigDecimal> price = pricingService.getUnitPrice(item);
        assertTrue(price.isPresent());
        assertEquals(new BigDecimal("0.80"), price.get());
    }

    @Test
    public void testGetPriceByItemUnknown() {
        final Item item = Mockito.mock(Item.class);
        when(item.getName()).thenReturn("Mango");

        final Optional<BigDecimal> price = pricingService.getUnitPrice(item);
        assertFalse(price.isPresent());
    }

}

