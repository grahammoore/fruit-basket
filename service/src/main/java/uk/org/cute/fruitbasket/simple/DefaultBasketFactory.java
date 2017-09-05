package uk.org.cute.fruitbasket.simple;


import org.springframework.stereotype.Component;
import uk.org.cute.fruitbasket.Basket;
import uk.org.cute.fruitbasket.BasketFactory;


@Component("basketFactory")
public class DefaultBasketFactory implements BasketFactory {

    private DefaultBasketFactory() {
    }

    @Override
    public Basket createBasket() {
        return new DefaultBasket();
    }
}
