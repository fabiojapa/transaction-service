# Transaction-service

Transaction-service is a Rest API made with Java to deal with financial transactions.

## Installation

Requirements: Java 17, Maven 3.8, docker, docker-compose

```bash
docker-compose build
```

## Usage

```bash
docker-compose up
```

## APIs
Endpoints are available at:

POST http://localhost:18080/accounts

GET http://localhost:18080/accounts/:accountId

POST http://localhost:18080/transactions

## Postman
There is a file to be imported in Postman:
```
src/test/resources/postman/transactions.postman_collection.json
```

## Contributing

Pull requests are welcome. For major changes, please open an issue first
to discuss what you would like to change.

Please make sure to update tests as appropriate.

## Author

Fabio Takeshi Sakamoto

fabiojapa@gmail.com

https://www.linkedin.com/in/fabiosaka/