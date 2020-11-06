# Requirements Document

Authors: Davide Battaglia, Andrea Sergio Ferraris, Lorenzo Lanari, Vittorio Pellittieri

Date: 2020-04-15

Version: 1.0

# Contents

- [Abstract](#abstract)
- [Stakeholders](#stakeholders)
- [Context Diagram and interfaces](#context-diagram-and-interfaces)
	+ [Context Diagram](#context-diagram)
	+ [Interfaces](#interfaces)

- [Requirements Document](#requirements-document)
- [Contents](#contents)
- [Stakeholders](#stakeholders)
- [Context Diagram and interfaces](#context-diagram-and-interfaces)
	- [Context Diagram](#context-diagram)
	- [Interfaces](#interfaces)
- [Stories and personas](#stories-and-personas)
- [Functional and non functional requirements](#functional-and-non-functional-requirements)
	- [Functional Requirements](#functional-requirements)
	- [Non Functional Requirements](#non-functional-requirements)
- [Use case diagram and use cases](#use-case-diagram-and-use-cases)
	- [Use case diagram](#use-case-diagram)
	- [Use cases](#use-cases)
		- [Use case 1, UC1](#use-case-1-uc1-fr1-handle-gas-station)
				- [Scenario 1.1](#scenario-11)
				- [Scenario 1.2](#scenario-12)
		- [Use case 2, UC2](#use-case-2-uc2-fr2-report-price-change)
		                - [Scenario 2.1](#scenario-21)
		- [Use case 3, UC3](#use-case-3-uc3-fr3-compute-the-route-to-the-gas-station)
		                - [Scenario 3.1](#scenario-31)
		- [Use case 4, UC4](#use-case-4-uc4-fr4-search-gas-stations)
		                - [Scenario 4.1](#scenario-41)
		- [Use case 5, UC5](#use-case-5-uc5-fr51-login)
		- [Use case 6, UC6](#use-case-6-uc6-fr52-register)
		- [Use case 7, UC7](#use-case-7-uc7-fr6-plot-the-history-of-the-prices)  
		- [Use case 8, UC8](#use-case-8-uc8-fr7-track-a-gas-station)
		                - [Scenario 8.1](#scenario-81)
		- [Use case 9, UC9](#use-case-9-uc9-fr8-manage-the-location-of-user-and-gas-stations)                
- [Glossary](#glossary)
- [System Design](#system-design)
- [Deployment Diagram](#deployment-diagram)

# Abstract

We often need to refuel our motor vehicle. Many gas stations are available out there, but it is not always immediate to locate them, especially when we are driving in unknown areas. We must also consider that fuel prices are constantly changing, so it is difficult to be updated even about gas stations that we know very well.

EZGas was born to solve these problems. It is a crowdsourcing  service  supported by a web  application accessible both via smartphone and PC. The user is able to locate gas stations in any area, together with the prices. In particular, exploiting the current position on the map showed by the application, the user is able to apply different filters to locate,  in that specific area, a set of available gas stations  which meet the search criteria.

Registered users have extra possibilities. They can contribute to:

- insert and update data about gas stations
- insert and update data about prices of fuels
- collect prices of fuels in different gas stations
- insert a feedback about a gas station

They also have the possibility to receive a report about  their collected gas stations with updated prices .

# Stakeholders

| Stakeholder name | Description |
| ----------------- |:-----------:|
| User | Uses the application directly to read and insert information |
| Map | Provides the geolocation service |
| Developer | Develops and maintains the application |
| Application Database | Supports the information’s storing |

# Context Diagram and interfaces

## Context Diagram

```plantuml
left to right direction
actor User as u
actor Map as m
u -- (EZGas)
m -- (EZGas)
```

## Interfaces

| Actor | Logical Interface | Physical Interface |
| ------------- |:-------------:| -----:|
| User | GUI | Smartphone or PC |
| Map | API | Internet connection |

# Stories and personas

Luca, a commuting university student, has so many project for his future; he would like to become a researcher in one of the best universities in Germany.
Unfortunately he does not come from a wealthy family and for this reason it is necessary that he saves money as much as he can in order to be able to realize his inspirations.
One of his most daily expenses is the gas supply he has to do in order to reach his university which is far 30 minutes from home.
Therefore Luca every day, before going out, takes his phone and thanks to the EZGas app he searches all the gas stations he'll meet in his path and chooses the most convenient one.

Francesco is a carrier of goods and every morning at 4 am, when the sun has not yet risen, he gets inside of his truck and starts driving to his daily destination.
Before leaving he looks for the stations where the gas is cheaper and fortunately he finds one that is located along its route.
Once arrived he discovers that the price that was reported in the app was lower than that requested by the station.
For this reason, before getting back to driving, he reports the actual price so that all those who will use the app in the future will not see a price that is different from the real one.

# Functional and non functional requirements

## Functional Requirements

| ID | Description |
| ------------- |:-------------:|
| FR1 | Handle gas station |
| FR1.1 | Insert gas station |
| FR1.2 | Update gas station |
| FR1.3 | Delete gas station |
| FR2 | Report price change |
| FR3 | Compute the route to the station |
| FR4 | Search gas stations |
| FR5 | Authorize and Authenticate |
| FR5.1 | Register |
| FR5.2 | Login |
| FR5.3 | Logout |
| FR6 | Plot the history of the prices |
| FR7 | Track a gas station |
| FR8 | Manage the location of user and stations |

## Non Functional Requirements

| ID | Type (efficiency, reliability, ..) | Description  | Refers to |
| ------------- |:-------------:| :-----:| -----:|
|  NFR1 | Usability | Application should be used easily while in a car | All FRs |
|  NFR2 | Performance | All functions should be completed in < 0.5 s | All FRs |
|  NFR3 |Portability | The application should run on 90% of the devices | All FRs |
|  NFR4 | Reliability  | Application should be available 99% of the times | All FRs |

# Use case diagram and use cases

## Use case diagram

```plantuml

left to right direction
actor User as U
actor Map as M

rectangle EZGas {
usecase UC1 as (FR1 Handle gas station)
usecase UC2 as (FR2 Report price change)
usecase UC3 as (FR3 Compute the route to gas station)
usecase UC4 as (FR4 Search gas stations)
usecase UC5 as (FR5 Authorize and Authenticate)
usecase UC6 as (FR6 Plot the history of the prices)
usecase UC7 as (FR7 Track a gas station)
usecase UC8 as (FR8 Manage the location of users and gas stations)
}

U --> UC1
U --> UC2
U --> UC4
U --> UC5
U --> UC6
U --> UC7

M --> UC3
M --> UC8

UC1 ..> UC5 : <<include>>
UC2 ..> UC5 : <<include>>
UC3 ..> UC5 : <<include>>
UC6 ..> UC5 : <<include>>
UC7 ..> UC5 : <<include>>

```

## Use cases

### Use case 1, UC1 – FR1 Handle gas station
| Actors Involved | Registered user |
| ------------- |:-------------:|
|  Precondition | Registered user RU exists and has an account |
|  Post condition | S exists/change/ |
|  Nominal Scenario     | RU inserts/update/delete a new gas station |
|  Variants     | Station S already exists or the user U hasn't an account, issue warning |

#### Scenario 1.1
| Scenario 1.1 |  Corrisponds do UC1 |
| ------------- |:-------------:|
| Precondition | Registered User RU has logged in his personal account |
| Post condition | A new gas station S is added to the database |
| Step# | Description |
| 1 | RU selects the functionality to add a new station |
| 2 | RU inserts all the information about the gas station S ( name, fuels' type, prices, position ) |

#### Scenario 1.2
| Scenario 1.2 | Corrisponds to UC1 |
| ------------- |:-------------:|
| Precondition | Registered User RU has logged in his personal account |
| Post condition | An existing gas station S is notificated to the system as a non more available |
| Step# | Description  |
| 1 |RU selects the functionality to comunicate a gas station S is not available |
| 2 |RU specifies the gas station S |

### Use case 2, UC2 – FR2 Report price change
| Actors Involved | Registered User |
| ------------- |:-------------:|
| Precondition | Registered User RU exists, Gas station S exists, Fuel F exists |
| Post condition |  S.F.price >0 |
| Nominal Scenario | Registered User RU changes gas station S Fuel's Price |

#### Scenario 2.1
| Scenario 2.1 | Corrisponds to UC2 |
| ------------- |:-------------:|
| Precondition | Registered User RU has logged in his personal account |
| Post condition | An existing gas station S has its information changed |
| Step# | Description |
| 1 | RU selects the S that he wants to update |
| 2 | RU selects the F type that he wants to update |
| 3 | RU inserts the updated price |

### Use case 3, UC3 – FR3 Compute the route to the gas station
| Actors Involved | User, Map |
| ------------- |:-------------:|
| Precondition | User U exists, Gas station S exists |
| Nominal Scenario | User U asks for the path to the gas station S |

#### Scenario 3.1
| Scenario 3.1 | Corresponds to UC3 |
| ------------- |:-------------:|
| Precondition | Gas station S exists |
| Step# | Description |
| 1 |  U selects the S where he wants to go |
| 2 |  U receives the path computed by Map |

### Use case 4 UC4 – FR4 Search gas stations
| Actors Involved | User |
| ------------- |:-------------:|
| Precondition | User U exists |
| Post condition | User U receives the list of gas stations S with the searched criteria |
| Nominal Scenario | User U wants to know the list of gas stations S with some features (ex. Price, type of fuel, distance,...) |
| Variants | There are no gas station S that satisfies the filter asked by Registered User RU |

#### Scenario 4.1
| Scenario 4.1 | Corrispons to UC4 |
| ------------- |:-------------:|
| Precondition  | User U exists |
| Step#        | Description |
| 1 | U selects the information he needs about the gas stations |
| 2 | U selects the fuel's type filter |
| 3 | The system returns a list of gas station S with the requested features |

### Use case 5 UC5 – FR5.1 Login
| Actors Involved | Registered user RU |
| ------------- |:-------------:|
| Precondition | Registered user RU exists |
| Post condition | Registered User RU logged in  |
| Nominal Scenario | Registered User RU wants to access to his personal space on the platform |

### Use case 6 UC6 – FR5.2 Register
| Actors Involved | User |
| ------------- |:-------------:|
| Precondition | Registered user RU doesn't exist |
| Post condition | Registered User RU exists |
| Nominal Scenario | User U wants to register |
| Variants | The input data is not valid |

### Use case 7 UC7 – FR6 Plot the history of the prices
| Actors Involved | Registered user RU |
| ------------- |:-------------:|
| Precondition | Register User RU exists, Gas station S exists |
| Post condition |  |
| Nominal Scenario | Registered User RU wants to know how the prices of fuel F changed |
| Variants | No fuel F price of the Station S has been notified |

### Use case 8 UC8 – FR7 Track a gas station
| Actors Involved | Registered user RU |
| ------------- |:-------------:|
| Precondition | RU exists,  Gas station S exists |
| Post condition | RU.stationlist.length++ |
| Nominal Scenario | Registered User RU asks to be updated for the change of the price of a gas station's S fuel F |

#### Scenario 8.1
| Scenario 8.1 | Corrisponds to UC8 |
| ------------- |:-------------:|
| Precondition | Registered user RU exists |
| Step# | Description |
| 1 |  RU selects the gas station S |
| 2 |  RU chooses to track his F prices' changes |

### Use case 9 UC9 – FR8 Manage the location of user and gas stations
| Actors Involved | User , Map |
| ------------- |:-------------:|
| Precondition | User U exists, Gas station S exists |
| Nominal Scenario | User U asks to locate himself and the gas stations S around him |

# Glossary

```plantuml
title EZGas App - Glossary

class "EZGas_App" {
    +Version
}

class "Gas_Station" {
    +Name
    +Station ID
    +Latitude
    +Longitude
}

class Fuel{
    +Fuel type
    +Price
}

class User {
    +User ID
}

class User_Position {
    +Longitude
    +Latitude
}

class "Registered_User" {
    +Name
    +Surname
    +Email
}

class Account {
    +Username
    +Password
}

User "*" --  EZGas_App: Uses
Gas_Station "0..*" --  EZGas_App : is tracked by
User   --   User_Position : is located
Registered_User   --   User_Position : is located
Gas_Station    -- "1..*"    Fuel : sells
Registered_User    --    EZGas_App
Registered_User     -- "0..*"   Account : got

```

The EZgas application is a completely crowdsourcing application whose main objective is to let users check, insert and collect prices of gas stations.
A person can either register to it or not. He/She can register using a Username and a Password which will be used to login, and by inserting Name, Surname and Email. Every user, registered or not, is associated with unique User ID and a User_Position identified by Longitude and Latitude.  
A registered user can insert and update information about a Gas Station, by defining its Name and position (expressed as Longitude and Latitude), the App will then assign a unique Station ID to it. Each Gas Station sells fuels, each one identified by a Fuel Type and a Price, which can be update and inserted by Registered Users.


# System Design
\<describe here system design>

\<must be consistent with Context diagram>

# Deployment Diagram

```plantuml
artifact "EZGas Application" as e

node "Personal Computer client" as p
node "Smartphone client" as s
node "Application Server" as a
database "Database Server" as d

a -- d
p -- a
s -- a
e -- a
```
