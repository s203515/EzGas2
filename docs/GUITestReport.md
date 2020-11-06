# GUI  Testing Documentation

Authors:

Date:

Version:

# GUI testing

This part of the document reports about testing at the GUI level. Tests are end to end, so they should cover the Use Cases, and corresponding scenarios.

## Coverage of Scenarios and FR

```
<Complete this table (from IntegrationApiTestReport.md) with the column on the right. In the GUI Test column, report the name of the .py  file with the test case you created.>
```

###

| Scenario ID | Functional Requirements covered | GUI Test(s) |
| ----------- | ------------------------------- | ----------- |
| UC1 | FR1.1 | UC1-1Test | 
|         | FR1.1 | UC1-2Test | 
| UC2 | FR1.1 | UC2-1Test |
|         | FR1.1 | UC2-2Test | 
| UC3 | FR1.2 | UC3-1Test | 
| UC4 | FR3.1 | UC4-1Test |
| UC5 | FR3.1 | UC5-1Test | 
| UC6 | FR3.2 | UC6-1Test |
| UC7 | FR5.1 | UC7-1Test | 
| UC8 | FR4.5 | UC8-1Test |
| UC9 | FR5.2 | UC9-1Test |
| UC10 | FR5.3 | UC10-1Test | 

# REST  API  Testing

This part of the document reports about testing the REST APIs of the back end. The REST APIs are implemented by classes in the Controller package of the back end.
Tests should cover each function of classes in the Controller package

## Coverage of Controller methods


<Report in this table the test cases defined to cover all methods in Controller classes >

| class.method name | Functional Requirements covered |REST  API Test(s) |
| ----------- | ------------------------------- | ----------- |
| GasStationController.getGasStationById()           |FR4                 |testGasStationByid()    |     
| GasStationController.getAllGasStations()           |FR3.3               |testGetAllGasStations() |             
|GasStationController.saveGasStation()               |FR3.1               |testSaveGasStation()    |             
| GasStationController.deleteUser()                  |FR3.2               |testDeleteGasStation    |             
|GasStationController.getGasStationsByGasolineType() |FR4.3 FR4.4 FR4.5 |testGetGasStationByGasolineType|             
|GasStationController.getGasStationsByProximity()    |FR4.1               |testGetGasStationsByProximity()             |             
|GasStationController.getGasStationsWithCoordinates()|FR4.3 FR4.4 FR4.5   |testGetGasStationsWithCoordinates             |          
|GasStationController.setGasStationReport()          |FR5.1               |testSetGasStationReport()|          
| UserController.getUserById() | FR1.4 | testGetUserById() |
| UserController.getAllUsers() | FR1.3 | testGetAllUsers() |
| UserController.deleteUser() | FR1.2 | testdeleteUser() |
| UserController.increaseUserReputation() | FR1.1 | testIncreaseUserReputation() |
| UserController.decreaseUserReputation() | FR1.1 | testDecreaseUserReputation() | | UserController.saveUser() | FR1.1 | testSaveUser() |
| UserController.login() | FR2 | testLogin() |


















