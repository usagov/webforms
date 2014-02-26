/*
 * Created on May 4, 2006
 *
 */
package gov.firstgov.suggestalink;

/**
 * A bean/dao that represents a suggestion.  Fields for the suggestion can be set/retrieved by the appropriate getter/setter function.
 * @author RussellGONeill
 */
public class SuggestALinkBean {
	/**
	 * First name of the user submitting the suggestion
	 */
	private String fname="";

	/**
	 * @return Returns the first name.
	 */
	public String getFname() {
		return fname;
	}

	/**
	 * @param theFname The first name to set.
	 */
	public void setFname(String theFname) {
		fname = theFname;
	}

	/**
	 * Last name of the user submitting the suggestion
	 */
	private String lname="";

	/**
	 * @return Returns the last name.
	 */
	public String getLname() {
		return lname;
	}

	/**
	 * @param theLname The last name to set.
	 */
	public void setLname(String theLname) {
		lname = theLname;
	}

	/**
	 * The e-mail address of the user submitting the suggestion
	 */
	private String email="";

	/**
	 * @return Returns the email address.
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param theEmail The email address to set.
	 */
	public void setEmail(String theEmail) {
		email = theEmail;
	}

	/**
	 * The daytime phone number for the user submitting the suggestion
	 */
	private String dayPhone="";

	/**
	 * @return Returns the daytime phone number.
	 */
	public String getDayPhone() {
		return dayPhone;
	}

	/**
	 * @param theDayPhone The daytime phone number to set.
	 */
	public void setDayPhone(String theDayPhone) {
		dayPhone = theDayPhone;
	}

	/**
	 * The URL being suggested
	 */
	private String url="";

	/**
	 * @return Returns the url.
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param theUrl The url to set.
	 */
	public void setUrl(String theUrl) {
		url = theUrl;
	}

	/**
	 * The description of the suggested link entered by the user.
	 */
	private String description="";

	/**
	 * @return Returns the description.
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param theDescription The description to set.
	 */
	public void setDescription(String theDescription) {
		description = theDescription;
	}

	/**
	 * The suggested locations for the link.
	 */
	private String locationSuggestion="";

	/**
	 * @return Returns the suggested locations for the URL.
	 */
	public String getLocationSuggestion() {
		return locationSuggestion;
	}

	/**
	 * @param theLocationSuggestion The suggested locations for the URL to set.
	 */
	public void setLocationSuggestion(String theLocationSuggestion) {
		locationSuggestion = theLocationSuggestion;
	}

	/**
	 * The language of the form used to submit the request
	 */
	private String lang="";

	/**
	 * @return Returns the language (in ISO 639-1 standard)
	 */
	public String getLang() {
		return lang;
	}

	/**
	 * @param theLang The language to set.  It should follow the ISO 639-1 standard.
	 */
	public void setLang(String theLang) {
		lang = theLang;
	}
	
	public String toString(){
		StringBuffer retValue = new StringBuffer("");
		retValue.append("First Name: ");
		retValue.append(fname);
		retValue.append("\nLast Name: ");
		retValue.append(lname);
		retValue.append("\nE-mail address: ");
		retValue.append(email);
		retValue.append("\nDaytime phone: ");
		retValue.append(dayPhone);
		retValue.append("\nURL: ");
		retValue.append(url);
		retValue.append("\nDescription: ");
		retValue.append(description);
		retValue.append("\nLocation: ");
		retValue.append(locationSuggestion);
		return retValue.toString();
	}
}
