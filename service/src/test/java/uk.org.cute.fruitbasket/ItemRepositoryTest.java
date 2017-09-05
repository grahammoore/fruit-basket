package uk.org.cute.fruitbasket;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@JUnitTest
@RunWith(SpringJUnit4ClassRunner.class)
public class ItemRepositoryTest {

    @InjectMocks
    @Autowired
    @Resource(name = "itemRepository")
    private ItemRepository itemRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetItemByNameKnown() {
        final Optional<Item> item = itemRepository.findItemByName("Orange");
        assertTrue(item.isPresent());
        assertEquals("Orange", item.get().getName());
    }

    @Test
    public void testGetItemByNameUnknown() {
        final Optional<Item> item = itemRepository.findItemByName("Mango");
        assertFalse(item.isPresent());
    }

    @Test
    public void testCompareItems() {
        final Optional<Item> item1 = itemRepository.findItemByName("Peach");
        final Optional<Item> item2 = itemRepository.findItemByName("Peach");
        final Optional<Item> item3 = itemRepository.findItemByName("Apple");
        final Optional<Item> item4 = itemRepository.findItemByName("Apple");
        assertEquals(item1, item2);
        assertFalse(item1.equals(item3));
        assertEquals(item3, item4);
    }

}

