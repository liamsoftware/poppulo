
Poppulo Lottery REST service - November 17th, 2022

To run the application, execute "mvn spring-boot:run".
The application will run at http://localhost:8080
A collection of REST calls that can be used with Postman have been included in this directory.

Brief Class Description/Design:
- RestService: contains all endpoints
- Ticket: represents a lottery ticket, stores a list of the results for each line of the ticket.
- TicketGenerator: interface that allows for generating and amending tickets.
- RandomTicketGenerator: implements TicketGenerator. Creates a ticket with n lines, amends a ticket with n additional lines if the ticket has not been checked.
- TicketCache: Cache of tickets with the unique ticket id as the key, tickets as the value.
- RulePolicy: interface that allows ticket line results to be checked.
- SimpleRulePolicy: implements RulePolicy. Calculates the result for each line on a ticket and adds that result to the ticket.
- LineResult: stores the result for a line. Implements the Comparator interface to allow ordering.
- IllegalTicketAmendException: thrown if a client tries to update a ticket that has already been checked.
- TicketNotFoundException: thrown if a client tries to check or update a ticket that is not in the cache.

Technology Used:
- Spring boot
- Spring web
- Maven
- Junit
- Mockito

Limitations:
- Calculating Results: this algorithm is not optimal and could be improved.
- Integration tests: there are JUnit integration tests included, but it would have been better to use Cucumber and create BDD tests.
- Ticket creation: a client must create a ticket and then amend to include a number of lines, there should be a create endpoint that also includes the number of desired lines.
- Ticket and line limits: there are no limits on the number of tickets that can be created or the number of lines on a ticket.
- Caching: used a hashmap to cache tickets, but could use an external cache and also persist tickets to a DB.

Thank you for taking the time to read this and review the code.
This was a fun project and I look forward to discussing how it could have been designed and implemented better.