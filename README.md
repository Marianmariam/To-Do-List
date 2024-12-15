## Aceasta aplicatie necesita MySQL 

 Actualizați fișierul `application.properties` sau `application.yml` cu detaliile bazei de date:
     ```properties
     spring.datasource.url = jdbc:mysql://localhost:3306/todoManager
     spring.datasource.username=root
     spring.datasource.password=PASSWORD
     spring.jpa.hibernate.ddl-auto = update
