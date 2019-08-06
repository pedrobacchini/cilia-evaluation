# Evaluation Project - Java + Spring-boot + Angular Developer
Project description:

1. Create a spring-boot web project using maven or gradle. 
Create the project with https://start.spring.io/

2. Create the following Model classes:
    
    Client
    - name (string, required)
    - email (string, required)
    - date of birth (date, optional)
    
    Product
    - name (string, required)
    - description (string, optional)
    - price (double, required)
    
    Order
    - client (required)
    - products (required at least 1 product)
    
    Note You may have more than 1 product

3. Use on Angular 2 or higher front end
4. Create screens (CRUD) from the above models
5. Use database migration (suggestion: flyway)
6. Use ORM (suggestion: hibernate)