# StripeInvoiceLibrary
Application for managing invoices in Stripe API

**Description:**\
This application allows logged in user to create and download his invoice using Stripe API.


**Usage**\
To test this applicaton I used Postman for making API calls. There are three functionalities, that can be used.
  1. Logging in. Logging in can be done by making POST request at \
    URL: "localhost:8080/login"\
    Body: form-data:\
      key: "username", value: "user"\
      key: "password", value: "password"
  2. Creating invoice for logged in user can be done by making POST request at\
    URL: "localhost:8080/api"
  3. Getting download link for logged in user invoice can be done by making GET request at\
    URL: "localhost:8080/api"
    

**Stack tech**
  * Java 11
  * Spring boot 2.5.5
  * Hibernate 5.4.32.Final
  * Apache Maven 3.8.2
  * Apache Tomcat 9.0.53
  * Stripe-java 20.79.0
  * Gson 2.8.8
  * Apache Commons Lang 3.12.0
  * H2 Database 1.4.200
  * Lombok
