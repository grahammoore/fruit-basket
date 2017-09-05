package uk.org.cute.fruitbasket.simple;


import uk.org.cute.fruitbasket.Item;


public class DefaultItem implements Item {

    private final String name;
    private final int calculatedHashCode;

    DefaultItem(final String name) {
        this.name = name.intern();
        calculatedHashCode = name.hashCode();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return String.format("Item[name='%s']", name);
    }

    public int hashCode() {
        return calculatedHashCode;
    }

    public boolean equals(final Object object) {
        if (object == this) {
            return true;
        } else if (!(object instanceof  DefaultItem)) {
            return false;
        }

        final DefaultItem that = (DefaultItem) object;
        return name == that.getName(); // Only works because name is intern'd
    }
}
