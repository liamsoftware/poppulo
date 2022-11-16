
Lottery REST service.

To run the application, execute "mvn spring-boot:run".
The application will run at http://localhost:8080
A collection of REST calls that can be used with Postman have been included in this directory.

Brief Class Description/Design:
- RestService: contains all endpoints
- Ticket: represents a lottery ticket, stores a list of the results for each line of the ticket.
- TicketGenerator: interface that allows for generating and amending tickets.
- RandomTicketGenerator: implements TicketGenerator. Creates a ticket with n lines, amends a ticket with n additional lines if the ticket has not been checked.
- TicketCache: Cache of tickets with the unique id as the key, tickets as the value.
- RulePolicy: interface that allows ticket line results to be checked.
- SimpleRulePolicy: implements RulePolicy. Calculates the result for each line on a ticket and adds that result to the ticket.
- Result: stores the result for a line. Implements the Comparator interface to allow ordering.
- IllegalTicketAmendException: thrown if a client tries to update a ticket that has already been checked.
- TicketNotFoundException: thrown if a client tries to check or update a ticket that is not in the cache.

Technology Used:
- Spring boot
- Spring web
- Maven
- Junit
- Mockito
- Logback

Limitations:
- Caching: used a hashmap to cache tickets, but could use an external cache (such as Redis) and persist tickets to a DB.
- Calculating Results: calculating results could be a bottleneck if many tickets are queued to be checked. This may cause an out of memory exception.

Scaling:
- This app should be containerised and the cache should be external. Then many instances of the app could be deployed and traffic distributed across the instances by a load balancer.

Thank you for taking the time to read this and review the code.
This was a fun project and I look forward to hearing how it could have been designed and implemented better.