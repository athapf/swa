
*** Settings ***
Library    de.thaso.swa.app.rf.SimpleKeywordLibrary

Force Tags  stack

*** Test Cases ***

Create and test stack
    [Tags]  quick
    
    create an empty queue
    add an element  "Hello"
    add an element  "World"
    the first element should be  "Hello"
    pull element
    the first element should be  "World"
