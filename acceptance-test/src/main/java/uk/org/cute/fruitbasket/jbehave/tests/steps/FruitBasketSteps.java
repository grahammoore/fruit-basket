package uk.org.cute.fruitbasket.jbehave.tests.steps;


import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.springframework.beans.factory.annotation.Autowired;
import uk.org.cute.fruitbasket.*;

import java.math.BigDecimal;
import java.util.Optional;

@Steps
public class FruitBasketSteps {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private BasketFactory basketFactory;

    @Autowired
    private BasketCostingService basketCostingService;

    private Basket basket;

    @Given("a new basket")
    public void newBasket() {
        basket = basketFactory.createBasket();
    }

    @When("$quantity $itemName{s|es} {is|are} added")
    public void addItemToBasket(int quantity, String itemName) {
        Optional<Item> item = itemRepository.findItemByName(itemName);
        assert item.isPresent();
        basket.addItemToBasket(item.get(), quantity);
    }

    @Then("the total basket cost is {£|$}$totalCost")
    public void totalBasketCost(BigDecimal totalCost) {
        BigDecimal actualTotalCost = basketCostingService.calculateTotalCost(basket);
        assert totalCost.compareTo(actualTotalCost) == 0;
    }
}
