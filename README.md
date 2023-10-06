# Full-Stack Project: Learning Management System
Designed LMS Web Application using Java SpringBoot

# Project Description:
- Skillup(Website name) is a comprehensive Learning Management System (LMS) designed to facilitate seamless online learning experiences. The platform provides educators and learners with an intuitive interface for managing courses, accessing educational materials, and tracking progress.
# Technologies Used:
- Front-end: HTML, CSS, JavaScript
- Back-end: Java Spring, Spring Boot
- Database: MySQL
# Impact of the Project
- SkillUp has revolutionized the online learning experience, providing educators with an intuitive platform to deliver engaging content and enabling learners to acquire knowledge effectively. The system's user-friendly interface and robust backend architecture have contributed to increased user satisfaction and improved learning outcomes.

# data flow and control flow of fetching data from database
- server/Controller will get the GETRequest(end points) from the client/UI
- all end points will invoke thier perticular methods in controller
- invoked method will again invoke other method present in service
- service methods invokes JPA Repo methods (JPA Repo implementation is given by Spring itself)
- JPA Repo to Entity classes (which have logic to communicate with database to get the or post the data)
- data is given back to controller
- from controller data will be redirected back to client/UI by embedding into html template using Thymeleaf
