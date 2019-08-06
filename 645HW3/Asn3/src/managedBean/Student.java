/**
 * The survey form gets its inputs from this .java class
 * The setters and getters for all the fields are also generated and defined here.
 * Validation of fields like date is also taking place here
 */
package managedBean;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.ManagedBean;
import javax.faces.*;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import supportingClass.StudentService;
import static supportingClass.StudentService.*;

@ManagedBean(value="surveyBean")
@RequestScoped
public class Student {

	private String firstname, lastname, city, state, email, date, interestedin, raffle, additionalComments, recommendation, streetaddress, phonenumber,zipcode; 
	private List<String> campusOptions;
	private StudentService student;
	private WinningResult winner;

	public Student()
	{
		student = new StudentService();
		winner = new WinningResult();
	}



	public String getFirstname() {
		return firstname;
	}



	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}



	public String getLastname() {
		return lastname;
	}



	public void setLastname(String lastname) {
		this.lastname = lastname;
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



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getDate() {
		return date;
	}



	public void setDate(String date) {
		this.date = date;
	}



	public String getInterestedin() {
		return interestedin;
	}



	public void setInterestedin(String interestedin) {
		this.interestedin = interestedin;
	}



	public String getRaffle() {
		return raffle;
	}



	public void setRaffle(String raffle) {
		this.raffle = raffle;
	}



	public String getAdditionalComments() {
		return additionalComments;
	}



	public void setAdditionalComments(String additionalComments) {
		this.additionalComments = additionalComments;
	}



	public String getRecommendation() {
		return recommendation;
	}



	public void setRecommendation(String recommendation) {
		this.recommendation = recommendation;
	}



	public String getStreetaddress() {
		return streetaddress;
	}



	public void setStreetaddress(String streetaddress) {
		this.streetaddress = streetaddress;
	}



	public String getPhonenumber() {
		return phonenumber;
	}



	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}



	public String getZipcode() {
		return zipcode;
	}



	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}



	public List<String> getCampusOptions() {
		return campusOptions;
	}



	public void setCampusOptions(List<String> campusOptions) {
		this.campusOptions = campusOptions;
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



	public float getMean()
	{
		return winner.getMean();
	}
	public float getStandarddeviation()
	{
		return winner.getStandarddeviation();
	}
	public void validateDate(FacesContext context,UIComponent componenttoValidate, Object value)
	{	
		String validateString = (String)value; 
		context = FacesContext.getCurrentInstance();
		FacesMessage mess = new FacesMessage("*Date must be in the format MM/DD/YYYY");
		Pattern pattern = Pattern.compile("\\d{2}/\\d{2}/\\d{4}");
		Matcher match = pattern.matcher(validateString);
		String validate_state = null;
		if(!(match.matches())){
			throw new ValidatorException(mess);
		}else{
			validate_state = validatedatefull(validateString);
			if(validate_state.equals("0")){
			}else if(validate_state.equals("1")){
				throw new ValidatorException(new FacesMessage("*Please enterdate in a valid format"));
			}
		}
	}
	public void validatingRaffle(FacesContext context, UIComponent componenttobeValidated,Object value)
	{	
		String validateString = (String)value; 
		context = FacesContext.getCurrentInstance();
		FacesMessage message = new FacesMessage("*Invalid raffle ticket number, enter at least ten\n" + 
				"comma separated numbers ranging from 1 through 100");

		if(!(validatingRaffleno(validateString)))
			throw new ValidatorException(message);	
	}

	public String submit()
	{	
		winner.setMean(student.calculatingMean(this.getRaffle()));
		winner.setStandarddeviation(student.calculatingStandardDeviation(this.getRaffle()));

		arraylist.add(this);
		if(winner.getMean() < 90)
			return "SimpleAcknowledgement";
		else
			return "WinnerAcknowledgement";
	}

	public ArrayList<Student> getStudents(){
		return arraylist;
	}

	public boolean validatingRaffleno(String validateString)
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
			}else{
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

}
