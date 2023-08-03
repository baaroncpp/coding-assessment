# Project dependencies
1. coding-assessment based gradle spring boot 3.1.2 Rest application, java 17
2. With a mysql database and Flyway migration files located resources/db/migrations
3. Security is Json Web Token, implemented in the security package
4. Project includes one unit test AuthServiceTest

# Setting up and running the project
1. Make git clone from https://github.com/baaroncpp/coding-assessment.git to your preferred location
2. Make sure you have Mysql (mysql  Ver 8.0.33) running
3. Create a database test_db with root user and a password Admin@123# or user environmental variable to set your own customized database connections using the configurations found in the application.yml, 
   ### Example
   - DB_HOST
   - DB_PORT
   - DB_NAME
   - DB_USERNAME
   - DB_PASSWORD


4. Ensure that you can connect to the database
5. move to the project root directory and run "./gradlew clean build", to build a jar file
6. Ensure that port 2023 is free on your device
7. Then move build/libs directory and run the jar file that does not have PLAIN to it
   ### Example
   - java -jar coding-assessment-0.0.1-SNAPSHOT.jar

# RUNNING AND ACCESSING API's
1. The application is REST API back end application, use this postman collection link https://documenter.getpostman.com/view/9011369/2s9XxwwZcP
2. The migration files populate the database with an admin user: username=admin and password=admin, who you can use to get access token
3. From tha you can access all the available apis using the generated token