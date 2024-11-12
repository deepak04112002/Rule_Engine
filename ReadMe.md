# Drools Rule Engine Project

This project demonstrates the implementation of a rule engine using Drools with Spring Boot for processing card-based discounts.

## Getting Started

### Prerequisites
- Java 17 or later
- Maven 3.6.x or later
- Your favorite IDE (IntelliJ IDEA, Eclipse, or VS Code)

### Project Structure
```
src/
├── main/
│ ├── java/
│ │ └── com/ruleengine/droolengine/
│ │ ├── config/
│ │ │ └── DroolConfig.java
│ │ ├── controller/
│ │ │ └── OfferController.java
│ │ ├── model/
│ │ │ └── Order.java
│ │ └── DroolengineApplication.java
│ └── resources/
│ └── KieRule/
│ └── offer.drl
```


### Docker Support

#### Prerequisites
- Docker installed on your machine
- Docker Compose (included with Docker Desktop)

#### Docker Files
The project includes the following Docker-related files:
- `Dockerfile`: Contains instructions for building the Docker image
- `docker-compose.yml`: Defines the service configuration


### Building and Running with Docker

1. Build the Docker image:
```bash
docker build -t drools-rule-engine .
````
2. Run using Docker:
````bash
docker run -p 8080:8080 drools-rule-engine
````
3. Or using Docker Compose:
````bash
docker-compose up -d
````
### Configuration

#### Maven Dependencies
The project requires the following key dependencies:
- spring-boot-starter-web
- drools-core
- drools-compiler
- drools-decisiontables
- drools-mvel
#### Business Rules
The current implementation includes rules for the following card types:
- HDFC: 10% discount for orders above ₹10,000
- ICICI: 8% discount for orders above ₹15,000
- SBI: 15% discount for orders above ₹15,000
#### API Endpoints
Process Order Discount
````
POST /order
Content-Type: application/json

{
    "cardType": "HDFC",
    "price": 20000
}
````
### Running the Application
1. Clone the repository
````
git clone https://github.com/deepak04112002/pixel_generator.git
````
2. Navigate to the project directory
````
cd droolengine
````
3. Run the application:
````bash
mvn spring-boot:run
````
The application will start on port 8080

### Testing
To test the rules, send a POST request to http://localhost:8080/order with the appropriate request body.

Example using curl:
````
curl -X POST \
  http://localhost:8080/order \
  -H 'Content-Type: application/json' \
  -d '{
    "cardType": "HDFC",
    "price": 20000
}'
````
#### Adding New Rules
To add new rules:

1. Open src/main/resources/KieRule/offer.drl

2. Add your rule following the format:
````
rule "RuleName"
when
    orderObject: Order(cardType == "NEW_CARD", price > amount)
then
    orderObject.setDiscount(discountPercentage);
end
````
### Troubleshooting
Common issues and solutions:
1. Rule not firing:
- Verify rule syntax in offer.drl file.
- Check if the rule conditions match your input.
- Ensure proper package declaration in DRL file.
2. Application fails to start:
- Verify proper placement of offer.drl file
- Check for proper dependency versions.
- Ensure Java version compatibility.
