package uk.org.cute.fruitbasket;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@JUnitTest
@RunWith(SpringJUnit4ClassRunner.class)
public class BasketCostingServiceTest {

    @Mock
    private ItemRepository itemRepository;

    @Mock
    private PricingService pricingService;

    @InjectMocks
    @Autowired
    private BasketCostingService basketCostingService;

    @Autowired
    private BasketFactory basketFactory;

    private Map<String, Integer> prices = new HashMap<>();

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        prices.put("Orange", 10);
        prices.put("Apple", 11);
        prices.put("Banana", 12);
        prices.put("Peach", 13);

        prices.entrySet().forEach(entry ->
                {
                    final Item item = Mockito.mock(Item.class);
                    when(item.getName()).thenReturn(entry.getKey());
                    when(itemRepository.findItemByName(entry.getKey())).thenReturn(Optional.of(item));
                    when(pricingService.getUnitPrice(item)).thenReturn(Optional.of(new BigDecimal(entry.getValue())));
                }
        );
    }

    @Test
    public void singleItemOneQuantityInBasket() {
        final Basket basket = basketFactory.createBasket();
        Item item1 = itemRepository.findItemByName("Orange").get();
        basket.addItemToBasket(item1, 1);
        final Integer totalCost = basketCostingService.calculateTotalCost(basket).intValue();
        assertEquals(prices.get(item1.getName()), totalCost);
    }

    @Test
    public void singleItemTwoQuantityInBasket() {
        final Basket basket = basketFactory.createBasket();
        Item item1 = itemRepository.findItemByName("Orange").get();
        basket.addItemToBasket(item1, 2);
        final Integer totalCost = basketCostingService.calculateTotalCost(basket).intValue();
        assertEquals((Integer) (prices.get(item1.getName()) * 2), totalCost);
    }

    @Test
    public void twoSameItemTwoQuantityInBasket() {
        final Basket basket = basketFactory.createBasket();
        Item item1 = itemRepository.findItemByName("Orange").get();
        basket.addItemToBasket(item1, 2);
        basket.addItemToBasket(item1, 3);
        final Integer totalCost = basketCostingService.calculateTotalCost(basket).intValue();
        assertEquals((Integer) (prices.get(item1.getName()) * 5), totalCost);
    }

    @Test
    public void fourItemsInBasket() {
        final Basket basket = basketFactory.createBasket();
        Item item1 = itemRepository.findItemByName("Orange").get();
        Item item2 = itemRepository.findItemByName("Apple").get();
        Item item3 = itemRepository.findItemByName("Peach").get();
        basket.addItemToBasket(item1, 2);
        basket.addItemToBasket(item2, 3);
        basket.addItemToBasket(item1, 4);
        basket.addItemToBasket(item3, 5);
        final Integer totalCost = basketCostingService.calculateTotalCost(basket).intValue();

        int expectedTotalCost = prices.get(item1.getName()) * 6;
        expectedTotalCost += prices.get(item2.getName()) * 3;
        expectedTotalCost += prices.get(item3.getName()) * 5;

        assertEquals((Integer) expectedTotalCost, totalCost);
    }
}

