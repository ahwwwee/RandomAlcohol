# RandomAlcohol
An application which could help you decide, when you can't.

This application is the second attempt at a spring application
The first was too messy so this was restarted.
And first RESTApi in Eclipse and Java. 

Import application into Eclipse and run with a maven build. 
in goals enter tomcat7:run

Navigate to localhost:8080/alcohol/

There is an exposed API at localhose:8080/alcohol/random

The data retrieved is not factual. The author inserted test data. 

A user is able to get access to random alcohol details held in a mongodb database. 
There is code (commented out) which should allow the user to also filter random alcohol by type. 
This code was throwing an error so the author took out this logic. 
After trying for many hours to keep it in. 

An idea, to add in a google maps which would drop a location marker onto the origin of the alcohol. 
Unfortunately the author ran out of time. 
Also pictures of the alcohol, and a more coprehensive database. 
