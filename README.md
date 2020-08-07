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
  - step1: A search box
  <img width="960" alt="Capture" src="https://user-images.githubusercontent.com/22782834/89647075-38b49a00-d8bd-11ea-8975-210206b02199.PNG">
  - step2: Results sorted by Title  
  <img width="949" alt="Capture2" src="https://user-images.githubusercontent.com/22782834/89647189-71547380-d8bd-11ea-800e-daad4bde2fdd.PNG">
  
  ## Architecture approach
  Client - Server architecture
  
  ![idexx](https://user-images.githubusercontent.com/22782834/89648465-87fbca00-d8bf-11ea-8624-34c41d979fd6.png)
  
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
