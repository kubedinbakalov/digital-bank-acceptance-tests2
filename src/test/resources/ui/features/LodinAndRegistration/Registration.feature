@DB
Feature: Digital Bank Registration Page

  Background:
    Given The user with "Elon@gmail.com" is not in DB
    Given User navigates to Digital Bank signup page
@Test
  Scenario: Positive Test Case. As a user, I want to successfully create Digital Bank account
    When User creates account with following fields
      |title|firstName|lastName|gender|dob        |ssn        |email         |password |confirmPassword| address    |locality     |region|postalCode|country|homePhone    |mobilePhone |    workPhone|termsCheckMark|
      |Mr.  |Elon     |Musk    |M     |12/12/1990 |324-59-5545|Elon@gmail.com|Email1228|  Email1228     |123 Digital|Internet cit |CA    |94302     |US     |(547)392-5436|(547)392-5436|(547)392-5436|true         |

    Then User should be displayed with the message "Success Registration Successful. Please Login."
    Then the following user info should be saved in the db
      |title|firstName|lastName|gender|dob        |ssn        |email         |password |confirmPassword| address    |locality     |region|postalCode|country|homePhone    |mobilePhone  |  workPhone  |accountNonExpired|accountNonLocked|credentialsNonExpired|enabled|
      |Mr.  |Elon     |Musk    |M     |12/12/1990 |324-59-5545|Elon@gmail.com|Email1228|  Email1228     |123 Digital|Internet cit |CA    |94302     |US     |(547)392-5436|(547)392-5436|(547)392-5436|  true            | true          |true                 |true   |

  @NegativeRegistrationCases
  Scenario Outline: Negative Test Case. As a Digital Bank Admin, I want to make sure users can not register without providing all valid data
    When User creates account with following fields
      |title  |firstName  |lastName  |gender  |dob  |ssn  |email|password  |confirmPassword|address  |  locality|region  |postalCode  | country   |homePhone | mobilePhone  | workPhone |termsCheckMark  |
      |<title>|<firstName>|<lastName>|<gender>|<dob>|<ssn>|<email>|<password>|<confirmPassword>|<address>|<locality>|<region>|<postalCode>|<country>|<homePhone>|<mobilePhone>|<workPhone>|<termsCheckMark>|
    Then The user should see the "<fieldWithError>" required field error message "<errorMessage>"

    Examples:
      |title|firstName|lastName|gender|dob       |ssn   |email |password |confirmPassword|address         |locality     |region|postalCode|country|homePhone    |mobilePhone  |workPhone    |termsCheckMark|fieldWithError|errorMessage |
      |     |         |        |      |          |      |      |         |               |                |             |      |          |       |             |             |             |              |  title       |Please select an item in the list.|
      |  Mr.|         |        |      |          |      |      |         |               |                |             |      |          |       |             |             |             |              |firstName     |Please fill out this field.|
      |  Mr.|Elon     |        |      |          |      |      |         |               |                |             |      |          |       |             |             |             |              |lastName      |Please fill out this field.|
      |  Mr.|Elon     | Musk   |      |          |      |      |         |               |                |             |      |          |       |             |             |             |              |gender        |Please select one of these options.|

