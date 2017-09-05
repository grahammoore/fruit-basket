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
    private Exception exception;

    @Given("a new basket")
    public void newBasket() {
        basket = basketFactory.createBasket();
    }

    @When("$quantity $itemName{s|es} {is|are} added")
    public void addKnownItemToBasket(final int quantity, String itemName) {
        addItemToBasket(quantity, itemName, true);
    }

    @When("$quantity $itemName{s|es} {is|are} added and item is unknown")
    public void addUnknownItemToBasket(final int quantity, final String itemName) {
        addItemToBasket(quantity, itemName, false);
    }

    private void addItemToBasket(final int quantity, final String itemName, final boolean validItem) {
        final Optional<Item> item = itemRepository.getItemByName(itemName);

        if (validItem) {
            assert item.isPresent() : "Item not found";

            try {
                basket.addItemToBasket(item.get(), quantity);
            } catch (Exception e) {
                exception = e;
            }
        } else {
            assert !item.isPresent() : "Item was incorrectly found";
        }
    }

    @Then("the total basket cost is {£|$}$totalCost")
    public void totalBasketCost(final BigDecimal totalCost) {
        final BigDecimal actualTotalCost = basketCostingService.calculateTotalCost(basket);
        assert totalCost.compareTo(actualTotalCost) == 0 : String.format("Basket total cost incorrect [£%s]", actualTotalCost);
        assert exception == null : String.format("Unexpected exception thrown [%s]", exception.getMessage());
    }

    @Then("there is an illegal argument")
    public void fails() {
        assert exception != null : "Expected an exception to be thrown";
        assert exception instanceof IllegalArgumentException : "Expected illegal argument exception";
    }
}
