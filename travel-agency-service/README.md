# Travel Agency Service

Travel Agency Service is a JavaFX-based application that allows users to manage travel data such as bookings, flights, and hotels for a specific customer. It offers a user-friendly interface and supports multiple languages.

## Table of Contents

- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Usage](#usage)

## Prerequisites

- Java JDK 11 or higher
- JavaFX SDK
- HeidiSQL Server

## Installation

1. Clone the repository:

```
git clone https://github.com/cedrik-baedorf/travelagency-agency.git
```

2. Navigate to the `travelagency-agency/travelagency-agency-service/` directory:

```
cd travelagency-agency/travelagency-agency-service/
```

3. Navigate to the `travel-agency-service/docs/database` directory:

use the `DatenbankSetup.pdf` guide to set up the database properly

4. Open the project in your favorite Java IDE.

5. Attempt to build the project and run it using the javafx:run plugin:

note that in some cases this does not work. In that case please configure a regular run configuration manually using the
module `travel-agency-service` and main class `TravelAgencyServiceApplication`.

6. To change the language add the name of the new language file as the first argument of your javafx:run/regular run configuration

note that the default language file `en_US.properties` will be loaded if no argument is provided.

7. For further information in the projects architecture check out our documentation

database scheme : `travel-agency-service/docs/database/Database - UML Diagrams.pdf`
service architecture : `travel-agency-service/docs/service/Service - UML Diagrams.pdf`

## Packages

1. JPA Entities

package `travel-agency-service/src/main/java/travelagency/service/entities` contains all jpa entities used for the
data scheme this service is based on.

2. Database Connection

package `travel-agency-service/src/main/java/travelagency/service/database` contains interface and implementation for
`TravelAgencyServiceFactory` & `TravelAgencyAuthenticator`.

3. Services

package `travel-agency-service/src/main/java/travelagency/service/service` contains the services this module provides for the user interface,
restful api, and any other external program that wants to use them.
The data services are in package `data` & the consumption services are in package `consumption`.

4. JavaFX Controllers

package `travel-agency-service/src/main/java/travelagency/service/controllers` contains the controllers this modules uses for the
user interface prototype application.
Note that the user interface is only a prototype and does not cover all functions from the mock up.
The services are also used separately from the user interface (e.g. restful api)

## Usage

The Travel Agency Service application uses the following color scheme:

- Primary color: #3B768A
- Secondary colors: Yellow/Orange
