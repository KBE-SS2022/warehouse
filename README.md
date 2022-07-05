# Commands
- Create Project via this guideline: https://spring.io/quickstart
- You need to have Maven (mvn) oder Maven Wrapper (mvnw) installed
- Run the Spring application with: mvn spring-boot:run (or mvnw spring-boot:run)
- (You may have to go to the project directory before running the application)


- Create Repositories via Github, add README and .gitignore (Maven template)

# Warehouse

Run following commands to use Docker:
1. `docker build -t warehouse .`
2. `docker run -p 8080:8080 warehouse`

The Application will run on `localhost:8080`
