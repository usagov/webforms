package gov.firstgov.suggestalink.forms;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorForm;
/**
 * @author 
 * 
 */
public class SuggestALinkForm extends ValidatorForm {
	//private static final long serialVersionUID = 4758699871085901238L;
	
	private String language;
	private String url;
	private String firstName;
	private String lastName;
	private String emailAddress;
	private String telephoneNumber;
	private String linkDescription;
	private String suggestedCategoryDescription;
	private String invisibleBlank;
	private String answer;
	
	
    public String getLanguage(){
    	return language;
    }
    
    public void setLanguage(String string){
    	language = string;
    }
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String string) {
		url = string;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String string) {
		firstName = string;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String string) {
		lastName = string;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String string) {
		emailAddress = string;
	}

	public String getTelephoneNumber() {
		return telephoneNumber;
	}

	public void setTelephoneNumber(String string) {
		telephoneNumber = string;
	}

	public String getLinkDescription() {
		return linkDescription;
	}

	public void setLinkDescription(String string) {
		linkDescription = string;
	}
	
	public String getSuggestedCategoryDescription() {
		return suggestedCategoryDescription;
	}

	public void setSuggestedCategoryDescription(String string) {
		suggestedCategoryDescription = string;
	}
	
	public String getInvisibleBlank() {
		return invisibleBlank;
	}

	public void setInvisibleBlank(String string) {
		invisibleBlank = string;
	}
	
	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String string) {
		answer = string;
	}
	
	/*
	 * EXAMPLE
	 * 
	 * In v1.0 of this application the struts validator has met the validation requirements.
	 * This method may be used in the future for further validation if needed.
	 * 
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
		
		 ActionErrors errors = super.validate(mapping, request);
	
	    //example
		if(errors.size() == 0) {
			if( getEmailAddress().equals("bad@fg.com")) {
				errors.add("emailAddress", new ActionError("error.title"));
			}
		}
		return errors;				
	}
	*/
	
	
}
