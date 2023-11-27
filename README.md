# Transaction-service

Transaction-service is a Rest API made with Java for dealing with financial transactions.

## Installation

Requirements: Java 17, Maven 3.8, docker, docker-compose

```bash
docker-compose build
```

## Usage

```bash
docker-compose up
```

## Requests
Requirements: Postman

Import the configuration file FIXME in postman

## APIs
Endpoints are available at:

POST http://localhost:18080/accounts

GET http://localhost:18080/accounts/:accountId

POST http://localhost:18080/transactions

## Contributing

Pull requests are welcome. For major changes, please open an issue first
to discuss what you would like to change.

Please make sure to update tests as appropriate.

## Author

Fabio Takeshi Sakamoto

fabiojapa@gmail.com

https://www.linkedin.com/in/fabiosaka/