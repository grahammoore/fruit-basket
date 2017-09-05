# Fruit Basket

## Java Question - Fruit Basket

Please write a program that takes a basket of items and outputs its total cost.
The basket can contain one or more of the following items: Bananas, Oranges, Apples, Lemons, Peaches


### Points to Note

- For convenience the item repository and price service are implemented by the same Java class. Real world 
  would have separate services.

- Fixed prices of each item in item repository. Ideally should be injected into the pricing service so can 
  set up in test. In fact universe of items should be injected.

- The basket uses a concurrent linked queue to allow quick and concurrent adding of items while providing an 
  iterator that won't fail while adding is happening.

- Basket keeps one entry per item added even if the item has already been added 
  (for example: [ ("Orange", 1), ("Orange", 2)] ). This is adding does the minimum required, the effort 
  to collate is left to the basket costing service.

- Currency is not considered.

- Case of item names is not considered.

- Item names are intern'd so that object reference equality can be used to compare items.

- Test coverage not complete.

- Purposefully not fleshed out interfaces such as Basket to remove and change Items, 
  wanted to keep the scope down as it would affect the amount of testing required in
  the exercise.


### Design

- Item / DefaultItem - Class for representing items in a basket.

- BasketFactory / DefaultBasketFactory - Class for creating new basket objects.

- ItemRepository / DefaultItemRepository - Look up service for getting items from. In this implementation all the 
  items are canned and pre-created. The implementation also covers the PricingService as it was convenient to locate 
  together for this exercise. All data is held in memory using concurrent collections.
  
- PricingService - See DefaultItemRepository as is it provides the implementation. Real world this would be a standalone
  service providing current prices for the item catalogue.

- Basket / DefaultBasket - Holder of added items with associated quantites. All data is held in memory using concurrent 
  collections.
  
- BasketCostingService / DefaultBasketCostingService - Strategy class that given a basket will calculate the total cost
  of the basket (for each item sum of unit price * quantity). The pricing service is injected into the class by Spring
  configuration (annotations).

