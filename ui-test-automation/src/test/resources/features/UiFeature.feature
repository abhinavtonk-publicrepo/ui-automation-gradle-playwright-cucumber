@test
Feature: UI Features
# "https://jqueryui.com/"
  Background: Launching the application
    Given User has launched the application


  Scenario: Test Draggable
    Then User verifies the Draggable link

  Scenario: Test Droppable
    Given User verifies the Droppable link

  Scenario: Test Resizable
    Given User verifies the Resizable link

  Scenario: Test Selectable
    Given User verifies the Selectable link
