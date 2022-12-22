# Pweb-Jsf-Project

School project made with Spring and JSF, based on an ERP software to manage car rental business (with a web interface).

## Installation

### Prerequisites

- Java 8
- Maven
- PostgreSQL
- Tomcat 9.5
- IDE (Eclipse, Netbeans, IntelliJ, etc.)

### Database

- Create a database named `pweb` in PostgreSQL
- Create a user named `pweb` with password `pweb` and grant all privileges on the database `pweb`
- Restore the database from the repository `pweb-backend` (file `carRent2.backup`)

### Backend

- Clone the repository `pweb-backend`, located at [](https://github.com/ahmedglez/pweb-backend) and import it as a Maven project in your IDE
- Open the project in your IDE ` Run as > Maven install` to install the dependencies and build the project (this will generate a `pweb.war` file in the `target` folder)
- Copy the `pweb.war` file to the `webapps` folder of your Tomcat installation
- Start the Tomcat server

### Project

- Clone the project
- Import the project in your IDE
- Run the project

## Technologies

- Spring
- Spring Security
- JSF
- Primefaces

## Authors

- [**Ahmed Gonzalez**](https://github.com/ahmedglez)
- [**Ernesto Abella**](https://github.com/eaad2000)
- [**Jeankarlos Santana**](https://github.com/JeanCharlie)

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details
