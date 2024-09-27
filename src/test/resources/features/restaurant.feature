Feature: Restaurant Management

  @smoke @high
  Scenario: Create a restaurant successfully
    Given a restaurant with name "Mock Restaurant"
    When the restaurant is saved
    Then the restaurant can be retrieved by name "Mock Restaurant"
  @smoke @high
  Scenario: Retrieve restaurant by cep
    Given a restaurant with cep "12345"
    When the restaurant is saved
    Then the restaurant can be retrieved by cep "12345"
  @smoke @high
  Scenario: Retrieve restaurant by cuisine type
    Given a restaurant with cuisine type "Italian"
    When the restaurant is saved
    Then the restaurant can be retrieved by cuisine type "Italian"