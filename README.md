## Currency converter 

### Full-stack web application using Spring Boot(REST API) & Angular.

### Functional specification:

Currency exchange application main function is to convert an amount of money from one currency into another.
Exchange rates are taken from European central bank website (XML format). Once logged in, the latest currency data fetched and user redirected to
main page where source currency, target currency and amount to convert have to be selected.
At every conversion request - currency data in database is compared with the latest on ECB website and updated if required.
Also, every conversion request and all relevant data are saved in database and available on 'Conversion requests history' tab with filtering search.
Currency rates data on ECB website is updated every weekday at 16:00CET therefore Scheduled task implemented on backend
to update rates every day at 16:00CET.

All forms inputs are validated.


#### Used Technologies and Tools:

## Front-end

* TypeScript 4.2.4

* Node 14 & Angular 12 & NPM 6

* Bootstrap 5

## Back-end

* Java 11

* Spring-Boot 2.3.2 (Spring REST/ Spring Data Jpa)

* Hibernate

* Docker

* Postgres 12

## Start-up instructions:

Some sample data will be saved to database during first start of Docker container once SQL script is executed.
To login - username: **user**; and password **user01**; can be used OR register a new user.

###Start sequence:

**Choose one of the option:**

Automated option:

1. Clone repository `https://github.com/ilja115610/Full-Stack-Currency-Converter.git`

2. Being in project root directory - execute script depending on your Operating system.
   Windows: `app-start.win.bat` ; Linux: `sh app-start-nix.sh`;

* Dockerized Postgres 12 database will be started on default port 5432 with volume to keep data
* Spring Boot backend will be started in background on port 8080
* Angular frontend will be started on port 4200

To terminate frontend: CTRL+C/CTRL+Z; To terminate Docker: docker stop exchange-app-ctr;
To terminate Spring-Boot: CTRL+Brk or 'exit' terminal window.

Manual option:

1. Clone repository `https://github.com/ilja115610/Full-Stack-Currency-Converter.git`

2. From project root directory change dir to 'db-postgres' and execute 2 commands:
   `docker build -t exchange-app-img .` and then `docker run -d -p 5432:5432 --rm -v logs:/var/lib/postgresql/data --name exchange-app-ctr exchange-app-img`

3. From project root directory change dir to Andevis-backend and execute command:
   `mvn spring-boot:run`

4. From new terminal window and project root directory change dir to Andevis-frontend and execute commands:
   `npm install`
   `ng serve`


