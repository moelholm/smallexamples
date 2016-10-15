Feature: Get greeting

  As consumer of the greetings resource
  I should be able to get a greeting

  Scenario Outline: Get greeting using appropriate caller
    Given I use the caller <caller>
    When I request a greeting
    Then I should get response with HTTP status code <status>
    And It should contain the message <message>
    Examples:
      | caller | status | message           |
      | Duke   | 200    | Hello World, Duke |
      | Tux    | 200    | Hello World, Tux  |

  Scenario: Get greeting using caller 0xCAFEBABE
    Given I use the caller 0xCAFEBABE
    When I request a greeting
    Then I should get response with HTTP status code 418
