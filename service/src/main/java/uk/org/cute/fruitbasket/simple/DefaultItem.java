package uk.org.cute.fruitbasket.simple;


import uk.org.cute.fruitbasket.Item;


/**
 * Implementation of an immutable Item (no setters). Includes hashCode() and equals() which both use the Items name.
 * Therefore the hash code is calculated and stored at constructor time. The stored name is also intern'd so that object
 * reference comparison can be used. Note that also an Item with name "WIDGET" is different to "Widget" because the name
 * is left as is and is not normalized (lower case, trimmed, etc).
 */
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
