/*survey page input is mapped here.
 *  defining the setters and getters 
 *   and validating the fields*/
package managedBeans;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
//import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import supportingClasses.StudentService;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import static supportingClasses.StudentService.student_list;


@ManagedBean(name="sBean", eager=true)

public class Student {
	
private String fname, lname, city, state, mail, interestes, raffle, comments, recommendations, address, number,zip; 
private List<String> options;
private Date date, startdate;
private StudentService student;
private WinningResult winner;
public static final String likes="Likely,Very Likely,Unlikely";
public static final String[] likesa = {"Likely", "Very Likely", "Unlikely"};

public Student()
{
    student = new StudentService();
	winner = new WinningResult();
}



public List<String> getOptions() {
	return options;
}



public void setOptions(List<String> options) {
	this.options = options;
}



public void setcOptions(List<String> campusOptions) 
{
	this.options = campusOptions;
}

public List<String> getcOptions() 
{
	return options;
}

public String getFname() {
	return fname;
}



public void setFname(String fname) {
	this.fname = fname;
}



public String getLname() {
	return lname;
}



public void setLname(String lname) {
	this.lname = lname;
}



public String getCity() {
	return city;
}



public void setCity(String city) {
	this.city = city;
}



public String getState() {
	return state;
}



public void setState(String state) {
	this.state = state;
}



public String getMail() {
	return mail;
}



public void setMail(String mail) {
	this.mail = mail;
}



public String getInterestes() {
	return interestes;
}



public void setInterestes(String interestes) {
	this.interestes = interestes;
}



public String getRaffle() {
	return raffle;
}



public void setRaffle(String raffle) {
	this.raffle = raffle;
}



public String getComments() {
	return comments;
}



public void setComments(String comments) {
	this.comments = comments;
}



public String getRecommendations() {
	return recommendations;
}



public void setRecommendations(String recommendations) {
	this.recommendations = recommendations;
}



public String getAddress() {
	return address;
}



public void setAddress(String address) {
	this.address = address;
}



public String getNumber() {
	return number;
}



public void setNumber(String number) {
	this.number = number;
}



public String getZip() {
	return zip;
}



public void setZip(String zip) {
	this.zip = zip;
	restFunction();
}



public Date getDate() {
	return date;
}



public void setDate(Date date) {
	this.date = date;
}



public Date getStartdate() {
	return startdate;
}



public void setStartdate(Date startdate) {
	this.startdate = startdate;
}



public StudentService getStudent() {
	return student;
}



public void setStudent(StudentService student) {
	this.student = student;
}



public WinningResult getWinner() {
	return winner;
}



public void setWinner(WinningResult winner) {
	this.winner = winner;
}



public static String getLikes() {
	return likes;
}



public static String[] getLikesa() {
	return likesa;
}



public float getMean()
{
	return winner.getMean();
}
public float getStandarddeviation()
{
	return winner.getStandarddeviation();
}
public ArrayList<Student> getStudents(){
	return student_list;
}
public void validateDate(FacesContext context,UIComponent componenttoValidate, Object value)
{	
	String validateString = (String)value; 
	context = FacesContext.getCurrentInstance();
	FacesMessage message = new FacesMessage("*Date must be in the form MM/DD/YYYY");
	Pattern pattern = Pattern.compile("\\d{2}/\\d{2}/\\d{4}");
    Matcher matcher = pattern.matcher(validateString);
	String validate_state = null;
    if(!(matcher.matches())){
		throw new ValidatorException(message);
	}else{
			validate_state = validatedatefull(validateString);
			if(validate_state.equals("1")){
			}else if(validate_state.equals("0")){
				throw new ValidatorException(new FacesMessage("*Enter a valid date"));
			}
	}
}
public void validateRaffle(FacesContext context, UIComponent componenttobeValidated,Object value)
{	
	String validateString = (String)value; 
	context = FacesContext.getCurrentInstance();
	FacesMessage message = new FacesMessage("*Invalid raffle number enter 10 comma separated values between 1 and 100");

	if(!(validateRaffleno(validateString)))
		throw new ValidatorException(message);	
}

public String submit()
{	
	if(startdate.before(date))
	{
		   System.out.println("Invalid Message for Start Date");
			FacesContext context = FacesContext.getCurrentInstance(); 
			FacesMessage message = new FacesMessage("You cannot take a survey before your semester start date");
			context.addMessage("Survey:postartdateID", message); 
			return null; 
	   }
	winner.setMean(student.calculateMean(this.getRaffle()));
	winner.setStandarddeviation(student.calculateStandardDeviation(this.getRaffle()));
	
	student_list.add(this);
	if(winner.getMean() > 90)
		return "WinnerAcknowledgement";
	else
		return "SimpleAcknowledgement";
}



public boolean validateRaffleno(String validateString)
{
	
	String[] integerArray = validateString.split(",");

	for(int i=0 ;i<integerArray.length; i++){
		if(integerArray[i] == "")
			continue;
		else
			integerArray[i] = integerArray[i].trim();
	}
	
	try{
		if(integerArray.length > 10){
				return false;
			}
		else if(integerArray.length < 10){
				return false;
				}
		else{
				for(int i=0; i<integerArray.length; i++){
				   if(Integer.parseInt(integerArray[i]) > 100){
					   return false;
				   }
				}
				return true;
			}
	}catch(NumberFormatException e){
		return false;
	}
}

public String validatedatefull(String string)
{
	String[] intarray = string.split("/");
	String result = null;
	int count = 0;
	
	if(Integer.parseInt(intarray[count++]) > 12 || Integer.parseInt(intarray[count++]) > 31 || Integer.parseInt(intarray[count]) > 9999)	
		result = "0";
	else
		result = "1";
	
	
	return result;
}

public List<String> TestMethod(String prefix) {
	
	List<String> resultval = new ArrayList<String>();
	resultval.add("hello");
	resultval.add("world");
	
	return resultval;
	
}

public List<String> completeRec(String query) 
{
    List<String> results = new ArrayList<String>();
    System.out.println(query);
    for(String possibleLanguages:likesa)
    {
    	if(possibleLanguages.toUpperCase().startsWith(query.toUpperCase())) {
			results.add(possibleLanguages);
    	}
    }
    //results.add("Very Likely");
    //results.add("Likely"); 
    //results.add("Unlikely"); 
    System.out.println(results + " for query= " + query);
    return results;
}
public void restFunction()
{
	String result = null; 
	String state = null; 
	String city = null; 
	String[] rarray = null; 
	
	try{ 
		Client client = ClientBuilder.newClient(); 
		WebTarget webTarget = client.target("http://ec2-3-17-23-115.us-east-2.compute.amazonaws.com/hw4/api/identifycitystate").path(zip.toString()); 
		Invocation.Builder invocationBuilder = webTarget.request(); 
		Response response = invocationBuilder.get(); 
		
		result = response.readEntity(String.class);  
		System.out.println(result);
		rarray = result.split(":");
		
		if(rarray.length == 2)
		{
		city = rarray[0]; 
		state = rarray[1]; 
		}
	  }catch(Exception exception)
	  {
		  exception.printStackTrace(); 
	  }
	 
	if(rarray != null && rarray.length == 2)
	{
		this.setCity(city);
		this.setState(state);
	}
}

}
