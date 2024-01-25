Feature: CreateAccount Test Scenarios
  @DB
  Scenario: Create a valid account
    Given The user with "Steve@gmail.com" is not in DB

    Given the following user is in db
      |title|firstName|lastName|gender|dob        |ssn        |emailAddress   |password  | address        |locality|region|postalCode|country|homePhone    |mobilePhone |    workPhone|
      |Mr.  |Steven    |Jobs    |M     |03/20/1954 |122-14-3782|Steve@gmail.com|Test123$$|1 infinite loop|CA      |CA    |4444312  |US     |444-221233   |            |             |

    When the following banking account is created
      |accountName                      |accountTypeCode|openingDeposit|ownerTypeCode|
      |Steve Jobs Test Standard Checking|   SCK         |12000.00      |IND          |
    Then the following account details are returned in the response
      |accountName                      |accountTypeCode|openingDeposit|ownerTypeCode|accountStandingName|
      |Steve Jobs Test Standard Checking|   SCK         |12000.00      |IND          |Open               |
    Then the following account details in the db
      |accountName                      |accountTypeCode|openingDeposit|ownerTypeCode|accountStandingName|
      |Steve Jobs Test Standard Checking|   SCK         |12000.00      |IND          |Open               |



  Scenario: Create an account with wrong Account Name

  Scenario: Create an account with wrong Account Type Code

  Scenario: Create an account with wrong opening Deposit

  Scenario: Create an account with wrong ownerTypeCode