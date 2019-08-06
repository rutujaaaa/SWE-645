ASSIGNMENT 3 	rjadhav2@gmu.edu


 MVC-based Web application using JSF2 framework
 
 Creating a JSF 2.2 project 
 
The application begins with index.xhtml page whose navigation begins through faces-config.xml which contains interactions/navigation to the application.
 
Then we have a StudentSurvey.xhtml web page which has the survey form which attributes present in it.

ListAllSurvey.xhtml will display all the fields which we had entered throughout the session and display them.
 
SimpleAcknowledgement and WinnerAcknowledgement are the pages which get displayed on successful submission of the form. Both JSF pages will also display student name
entered on the Survey form as well as the Mean and Standard Deviation computed by the
StudentService using the data entered in the Raffle.

StudentService class that encapsulates business logic to store and read the Survey
data to/from a file

Student class is a POJO and WinningResult contains the methods to calculate mean and the SD

Also restWS is implemented in the identifycitystate class. On entering a particular zip code we get values for corresponding city and state. Also calendars are used to get user input for dates and a drop down menu which is asking for reccomendation