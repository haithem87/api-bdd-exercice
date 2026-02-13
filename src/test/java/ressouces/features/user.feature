Feature: User management

  @getUser
  Scenario Outline: Get a user using ID
    When I check the API status 200
    And I get the user by id <id>
    Then the response status should be 200

    Examples:
      | id |
      | 1  |
      | 2  |

  @createUser
  Scenario Outline: Create a user using
    When I check the API status 200
    And I create the user from "<fileName>"
    Then the response status should be 200
    And the response should contain an id

    Examples:
      | fileName    |
      | user1		|
      | user2		|
