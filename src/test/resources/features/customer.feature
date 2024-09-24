Feature: Customer Management

  Scenario: Create and get customer by id
    Given a customer with name "John Doe", email "john.doe@example.com", and phone "1234567890"
    When the customer is saved
    Then the customer can be retrieved by id

  Scenario: Get customer by id not found
    Given a customer with name "Jane Doe", email "jane.doe@example.com", and phone "0987654321"
    When the customer is saved
    Then the customer retrieval by id should return not found for non-existent id