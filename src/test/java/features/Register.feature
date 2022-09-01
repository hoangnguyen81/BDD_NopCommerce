
@register
Feature: Register

  @register_01
  Scenario: Register with empty data
    Given Get home page Url
    When Click to Register link
    And  Click to Register button
    Then Verify error message at First name textbox
    And  Verify error message at Last name textbox
    And  Verify error message at Email textbox
    And  Verify error message at Password textbox
    And  Verify error message at Confirm password textbox

	@register_02
  Scenario Outline: Register with invalid email
    Given Open Register page
    When  Input to "First name" textbox with value "<firstName>"
    And 	Input to "Last name" textbox with value "<lastName>"
    And 	Input to "Email" textbox with value "<email>"
    And 	Input to "Password" textbox with value "<password>"
    And 	Input to "Confirm password" textbox with value "<confirmPassword>"
		And 	Click to Register button
		Then  Verify error message invalid email at Email textbox
   
    Examples:
      | firstName  | lastName | email             | password | confirmPassword | 
      | Automation | FC       | autofc@gmail$.com | 123123   | 123123          | 
      
  @register_03
  Scenario Outline: Register successfully
    Given Open Register page
    When  Input to "First name" textbox with value "<firstName>"
    And 	Input to "Last name" textbox with value "<lastName>"
    And 	Input to "Email" textbox with value "<email>"
    And 	Input to "Password" textbox with value "<password>"
    And 	Input to "Confirm password" textbox with value "<confirmPassword>"
		And 	Click to Register button
		Then  Verify error message register successfully
		
		Examples:
      | firstName  | lastName | email                 | password | confirmPassword | 
      | Automation | FC       | autodemo123@gmail.com | 123123   | 123123          | 
    