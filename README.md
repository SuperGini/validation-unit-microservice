# validation-unit-microservice

1. To access this microservice you need a JWT token. This token is verified by the validation-unit with keycloak using the publick key.
2. It has a PostgresSQL database that stores parts objects. The prices of this parts is automaticaly updated via kafka when the price 
   of the parts in the warehouse database is updated.
3. The main purpose of this microservice is to validate the requests of the users. 
![Untitled Diagram drawio(5)](https://user-images.githubusercontent.com/58910040/168304977-76a2a111-8454-4c76-b42a-8ac460eabc07.png)
