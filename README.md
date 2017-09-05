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

