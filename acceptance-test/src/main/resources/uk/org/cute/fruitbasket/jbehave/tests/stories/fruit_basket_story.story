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


