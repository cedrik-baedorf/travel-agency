# Travel Agency Service

Travel Agency Service is a JavaFX-based application that allows users to manage their travelagency data such as bookings, flights, and hotels for a specific customer. It offers a user-friendly interface and supports multiple languages.

## Table of Contents

- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Usage](#usage)

## Prerequisites

- Java JDK 11 or higher
- JavaFX SDK
- Heidi Server

## Installation

1. Clone the repository:

```
git clone https://github.com/cedrik-baedorf/travelagency-agency.git
```

2. Navigate to the `travelagency-agency/travelagency-agency-service/` directory:

```
cd travelagency-agency/travelagency-agency-service/
```

3. Run all three SQL files to set up the database:

```
mysql -u <username> -p <password> < create_database.sql
mysql -u <username> -p <password> < create_tables.sql
mysql -u <username> -p <password> < insert_data.sql
```

4. Open the project in your favorite Java IDE.

5. Configure the project's arguments in your IDE with the following values:

```
"landing_page.fxml" "de.properties"
```

*Note: Replace `de.properties` with the appropriate language properties file if needed.*

6. Configure your JavaFX SDK and MySQL Server settings in your IDE.

7. Build and run the project.

## Usage

The Travel Agency Service application uses the following color scheme:

- Primary color: #3B768A
- Secondary colors: Yellow/Orange