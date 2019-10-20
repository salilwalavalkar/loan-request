# loan-request
Simulation of a loan processing flow using multiple services

**Completed:**  
- Created seperate microservices which are eureka enabled clients.
- Sample test case for service and controller added.     
- Console based logging enabled.

**Approach:**
- Calling between microservices can be done using **Feign client** to store user and load data.
- DTO/Entities can  be added to a ***core project*** and included as dependency in all the modules so that they can be reused.
- All modules can be added to docker-compose to orchestrate them and use a shared database instance.
- Replace h2 with a proper shared database and remove create-drop, printing of queries from startup yml file. 
