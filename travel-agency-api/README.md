# Travel Agency API

Welcome to the Travel Agency API! This RESTful API provides easy access to bookings data for developers and travel agencies.
Here you can find the according **Swagger UI** for further information src/main/resources/index.html.

## Features

- Retrieve a list of bookings with all information according to the specific bookings.

## Getting Started

To use the Travel Agency API, you'll need to follow these steps:

1. **Request API access**: Contact us at [anna.gosser@hwg-lu.de](mailto:email@example.com), [cedrik.baedorf@hwg-lu.de](mailto:email@example.com), [jonas.becker@hwg-lu.de](mailto:email@example.com), [florian.veitz@hwg-lu.de](mailto:email@example.com) to request access to our API. You'll receive an API key that you'll need to include in your requests.

2. **Integrate the API**: Use the provided API endpoints to fetch booking data in your application.

## API Endpoints

The Travel Agency API provides the following endpoints:

### Bookings

- **URL**: `/getBookings`
- **Method**: `GET`
- **URL Params**: `username=[string]&password=[string]`
- **Response**: A JSON-formatted list of hotels and their relevant information.


## Authentication

The Travel Agency API requires authentication for each request. You'll need to include your username and password as URL parameters in each API call. For example:

```
https://api.example.com/getHotels?username=demo&password=123
```


## Support and Feedback

If you encounter any issues or have suggestions for improving the Travel Agency API, please don't hesitate to reach out to us at [anna.gosser@hwg-lu.de](mailto:email@example.com), [cedrik.baedorf@hwg-lu.de](mailto:email@example.com), [jonas.becker@hwg-lu.de](mailto:email@example.com), [florian.veitz@hwg-lu.de](mailto:email@example.com). We value your feedback and are committed to continuously improving our API.

Happy coding!