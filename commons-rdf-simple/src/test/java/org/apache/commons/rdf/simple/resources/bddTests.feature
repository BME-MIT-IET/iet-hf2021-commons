#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template
@tag
Feature: Is Picasso in the graph?
  Everybody wants to know if Picasso is in or out
      
    Scenario: only one Artist is in the graph
   	Given If only "<artist>" is added to the graph
   	When 	we want to know if Picasso is in the graph
   	Then  we should not be suprised that Graph contains Picasso is "<answer>"
    
    Examples:
    | artist 						| answer |
    | Monet  	  				| false  |
    | Picasso   				| true   |
    | anything else! 	  | false  |
    
      
 		Scenario: We have an empty graph
 		Given We put <some> quads in the graph
 		When  we ask the count of the quads in graph is equal to that
 		But  if we remove a quad from the graph
 		Then the graphs count should be <fewer>
 		
 		Examples:
    | some 							| fewer |
    | 4 	  						| 3 |
    | 5   							| 4 |

 		
 		
 		
 		
 		
 		
 		
 		
 		
 		
 		
 		
 		
 		
 		
 		
 		