Narrative:
As a tester I want to define stories to demonstrate the fruit basket totaller service.
The tests below are not exhaustive.


Scenario: Empty basket

Given a new basket
Then the total basket cost is £0.00


Scenario: Single basket with one of each item

Given a new basket
When 1 Orange is added
And 1 Peach is added
And 1 Lemon is added
And 1 Banana is added
And 1 Apple is added
Then the total basket cost is £3.30


Scenario: Single basket with more than one of each item

Given a new basket
When 1 Orange is added
And 1 Peach is added
And 1 Lemon is added
And 1 Banana is added
And 1 Apple is added
And 2 Orange is added
And 3 Peach is added
And 4 Lemon is added
And 5 Banana is added
And 6 Apple is added
Then the total basket cost is £15.95


Scenario: Single basket with incorrect quantity

Given a new basket
When 0 Orange is added
Then there is an illegal argument


Scenario: Single basket with unknown item

Given a new basket
When 1 Mango is added and item is unknown
Then there is an illegal argument