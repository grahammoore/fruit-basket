# Fruit Basket

## Java Question - Fruit Basket

Please write a program that takes a basket of items and outputs its total cost.
The basket can contain one or more of the following items: Bananas, Oranges, Apples, Lemons, Peaches


### Points to Note

- For convenience the item repository and price service are implemented by the same Java class.

- Fixed prices of each item. Ideally should be injected into the pricing service so can set up in test.

- Expensive basket insert (copy on write basket) but safe reading.

- Basket keeps one entry per item added even if the item has already been added.

- Currency is not considered.

- Case of item names is not considered.