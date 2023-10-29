Feature: Creating a new checking account
@Test
  Scenario: Create a standard individual checking account
    Given the user logged in as "Elon@gmail.com" "Email1228"
    When the user creates a new checking account with the following data
      |checkingAccountType|accountOwnerShip|accountName              |initialDepositAmount|
      |Standard Checking  |Individual      |Elon Musk Second Checking|10000.00            |
    Then the user should see the green "Successfully created new Standard Checking account named Elon Musk Second Checking" message.
    And the user should see newly added account card
      |accountName              |accountType      |ownership |accountNumber|interestRate |balance|
      |Elon Musk Second Checking|Standard Checking|Individual|486131063     |0.0%       |10000.00|
    And the user should see the following transactions
      |date            |category  |description               | amount       |balance |
      |2023-09-06 20:41|Income    |	845321842 (DPT) - Deposit|10000.00	    |10000.00|