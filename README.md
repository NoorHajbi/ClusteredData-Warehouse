## ClusteredData Warehouse

ClusteredData Warehouse is a data warehouse system developed for Bloomberg to analyze FX deals.

### Project Structure



| Title    			     | Description   		                           	  | Link to The Code 																	   |
|------------------------|----------------------------------------------------|----------------------------------------------------------------------------------------|
| Makefile 	  	         |Makefile      			  						  |[Makefile](Makefile)																       |
| Dockerfile  	  		 |for building a Docker image						  |[Dockerfile](Dockerfile  															   |
|docker-compose.yml		 |for deploying the application						  | [docker-compose.yml](docker-compose.yml)											   |
|pom.xml		   		 |includes dependencies and build settings			  | [pom.xml](pom.xml)           														   |
|config.properties 		 |includes db-conn details							  | [config.properties](src/main/resources/config.properties)							   |
|App.java          		 |its main method calls `ClusteredDataService`.		  |[App](src/main/java/com/progresssoft/App.java)         								   |
|ClusteredDataService    |acceptes, validates, and persistes DealDetails Data.|[ClusteredDataService](src/main/java/com/progresssoft/service/ClusteredDataService.java)|



### Dependencies

Main project dependencies specified in the `pom.xml` file:

- MySQL Connector/J (version 8.0.32).
- Mockito Core (version 3.12.4).
- Docker Java (version 3.2.5).
- javax.activation API (version 1.2.0).
- SLF4J API (version 1.7.32).
- Logback Classic (version 1.2.3).
- Log4j Core (version 2.17.1).

### Makefile

A `Makefile`

Includes the following commands:

- `make run`: Builds the Docker image and starts the application using Docker Compose.
- `make stop`: Stops and removes the running containers.

---