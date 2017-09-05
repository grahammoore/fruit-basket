package uk.org.cute.fruitbasket.simple;


import uk.org.cute.fruitbasket.Item;

import java.math.BigDecimal;


public class DefaultItem implements Item {

    private String name;
    private BigDecimal pricePerUnit;
    private int calculateHashCode;

    DefaultItem(String name, BigDecimal pricePerUnit) {
        this.name = name.intern();
        this.pricePerUnit = pricePerUnit;
        calculateHashCode = name.hashCode();
    }

    @Override
    public String getName() {
        return name;
    }

    public BigDecimal getPricePerUnit() {
        return pricePerUnit;
    }

    @Override
    public String toString() {
        return String.format("Item[name='%s']", name);
    }

    public int hashCode() {
        return calculateHashCode;
    }

    public boolean equals(Object object) {
        if (object == this) {
            return true;
        } else if (!(object instanceof  DefaultItem)) {
            return  false;
        }

        DefaultItem that = (DefaultItem) object;

        return name == that.getName(); // Only works because name is intern'd
    }
}
