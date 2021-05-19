# _Number_Generator_

_This project is created as a proof of concept for Spring Boot backend service._

## _Prerequisites_

```
Java  - JDK 1.8 or above
Maven - 3.6.3 or above
PostgreSQL
Git installed on remote
```

## _Installation of the appliation_

- _Clone the repository using command `git clone https://github.com/stasim101/number_generator.git`_
- _Change the directory using command `cd number_generator`_


## _Configuration_

_For configuring the database credentials, we need to refer `application.properties` file for required configuration._
_Kindly refer the mentioned url for the same_
_`https://github.com/stasim101/number_generator/blob/main/bin/src/main/resources/application.properties`_

## _Run the application_

- _Run the spring boot application using maven with command `mvn spring-boot:run`._
- _By default it runs on port 8080._

## _Valid REST APIs_

#### _File upload and task tracking_

- _`POST` request along with the file attached in the Body(form-data) and below mentioned request_
  - _Request URL_
   ```
   http://localhost:8080/api/employee?action=upload
   ```
   
- _`GET` request to fetch the status of file upload task. For instance ID is 43243_
  - _Request URL_
   ```
   http://localhost:8080/api/task/43243
   ```   
  

#### _CRUD Operations_

- _`PUT` request to create an employee with sample JSON_
  - _Request URL_
   ```
   http://localhost:8080/api/create
   ```
  - _Body(raw:JSON)_
   ```javascript
  {
    "employeeName":"Chetana Kundur",
    "employeeAge":"22"
  }
   ```

- _`GET` request to fetch an employee with sample ID. For instance ID is 2445_
  - _Request URL_
   ```
   http://localhost:8080/api/find/2445
   ```

- _`PATCH` request to update an employee with sample ID. For instance ID is 2445 and the employee details are one from above body sample used to create and employee_ 
  _mentioned above. We need to change the age of Chetana from 22 to 23._

  - _Request URL_
   ```
   http://localhost:8080/api/update/2445
   ```
  - _Body(raw:JSON)_
  ```javascript
  {
    "employeeAge":"23"
  }
   ```
   

- _`DELETE` request to delete an employee with sample ID._

  - _Request URL_
   ```
   http://localhost:8080/api/delete/2445
   ```
