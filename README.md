# IDEXX
 ## Technology stack
 <br> Backend (Rest API)
  - Java 11
  - Spring Boot 2+
  - Maven Build
  - Environment: Embedded Tomcat
  - Deploymnet: Docker container
  
 <br> Front End
  - Angular 9
  - Bootstrap 4
  - CSS
  - Environment: Google chrome, mozilla firefox
  - Deploymnet: Docker container
  
  ## Output
  - step1: A search box : http://localhost:4200/
  <img width="960" alt="Capture" src="https://user-images.githubusercontent.com/22782834/89647075-38b49a00-d8bd-11ea-8975-210206b02199.PNG">
  - step2: Results sorted by Title  
  <img width="949" alt="Capture2" src="https://user-images.githubusercontent.com/22782834/89647189-71547380-d8bd-11ea-800e-daad4bde2fdd.PNG">
  
  ## Architecture approach
  Client - Server architecture
  
  ![idexx](https://user-images.githubusercontent.com/22782834/89648465-87fbca00-d8bf-11ea-8624-34c41d979fd6.png)
  
  Search web app: http://localhost:4200/ <br>
  Search web API: http://localhost:8080/search/api/v1 <br>
  Google books Reference: https://developers.google.com/books/docs/v1/reference/volumes/list <br>
  Itunes Reference: https://affiliate.itunes.apple.com/resources/documentation/itunes-store-web-service-search-api/#searching <br>
  
   ## Instructions to run:
   With Docker: <br>
   - Need to have git installed. Clone the project: git clone https://github.com/vishnuvuyyur1/IDEXX.git
   - Need to have docker installed. 
   - Command prompt: From insider the project folder search-app
   - Step1: build the project : docker build -t search-app-image .
   - Step2: Run the project  : docker run --name search-app-container -d -p 4200:80 search-app-image
   - Command prompt: From insider the project folder search-api
   - Step1: build the project : docker build -t search-api-image .
   - Step2: Run the project  : docker run --name search-api-container -d -p 8080:8080 search-api-image
   - Open Google chrome/ firefox etc Enter : http://localhost:4200/
   
   With out Docker: <br>
   - Need to have git installed. Clone the project: git clone https://github.com/vishnuvuyyur1/IDEXX.git
   - Need to have maven, npm, angular cli installed. 
   - Command prompt: From insider the project folder search-app
   - Step1: build the project : npm install
   - Step2: Run the project  : ng serve --o
   - Command prompt: From insider the project folder search-api
   - Step1: build the project : mvn clean install
   - Step2: Run the project  : java -jar search-api.jar from target folder
   - Open Google chrome/ firefox etc Enter : http://localhost:4200/
   
 ## API Documentation
  Base URL: http://localhost:8080/search/api/v1 <br>
  Health check: http://localhost:8080/actuator/health <br>
  Metrics: http://localhost:8080/actuator/metrics/http.client.requests <br>
  
  API Operations:
  
  |No| Operation | Endpoint | Method | avg resp times
|----|---|---|---| ---|
|1| get books and albums  |/combi?term=value| GET | <6 sec|
|2| get books (optional)| /books?term=value | GET | <6 sec|
|3| get albums (optional)| /albums?term=value |GET | <6 sec|

## 1. get books and albums
- URI: /combi?term=value
- Method: GET
<br>
Request Body : None

Response : Array (5 Books and 5 Albums). Results Configurable, and preconfigured to 5 
 |Attributes|Type|
|----|---|
|title|string | 
|authors|Array[string] | 
|type|string | 

```
[
    {
        "title": "Aeroplane",
        "authors": [
            "Red Hot Chili Peppers"
        ],
        "type": "ALBUM"
    },
    {
        "title": "Aeroplane Construction, Operation and Maintenance",
        "authors": [
            "John B. Rathbun"
        ],
        "type": "BOOK"
    },
    {
        "title": "Loss of Mail by Aeroplane Fire",
        "authors": [
            "United States. Congress. House. Committee on Post Office and Post Roads"
        ],
        "type": "BOOK"
    },
    {
        "title": "Mr. Aeroplane",
        "authors": [
            "Gemáy van Jaarsveld"
        ],
        "type": "BOOK"
    },
    {
        "title": "My Enemy",
        "authors": [
            "Aeroplane"
        ],
        "type": "ALBUM"
    },
    {
        "title": "Sambal (Extended Mix)",
        "authors": [
            "Aeroplane & Purple Disco Machine"
        ],
        "type": "ALBUM"
    },
    {
        "title": "Save Me Now",
        "authors": [
            "Aeroplane"
        ],
        "type": "ALBUM"
    },
    {
        "title": "The Aeroplane",
        "authors": null,
        "type": "BOOK"
    },
    {
        "title": "The Shape of the Aeroplane",
        "authors": null,
        "type": "BOOK"
    },
    {
        "title": "Whispers",
        "authors": [
            "Aeroplane"
        ],
        "type": "ALBUM"
    }
]
```
## API Testing
- Postman Integration Tests
- JUnit 
- Mockito
- Spring MockMVC

## Useful docker commands
- docker build -t search-app-image .
- docker image ls
- docker rmi search-app-image
- docker run --name search-app-container -d -p 4200:80 search-app-image
- docker container ls
- docker stop search-app-container
- docker rm search-app-container

