/* Generated by Together */

/**
 * This class implements e-mail a friend functionality for USA.gov
 * It includes e-mail address validation routines, as well as the functionality to send the e-mail
 * @author Russell O'Neill
 * @version 1.0
 * @since 7/14/2003 
 */
package gov.firstgov.emailafriend;
import gov.firstgov.util.*;

import java.util.StringTokenizer;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import org.apache.regexp.RE;
import org.apache.regexp.RESyntaxException;

import org.apache.commons.lang.StringEscapeUtils;

public abstract class EmailAFriendImpl {

	/**
     * from name in e-mail message
     */
    protected String fromName;

	/**
     * from e-mail address in message
     */
    protected InternetAddress fromEmail;

	/**
     * to e-mail address(es) in message
     */
    protected InternetAddress[] toEmail;

    /**
     * Link to send via e-mail
     */
    protected String linkToSend;

	/**
     * Default constructor--creates the object and initializes variables
     */
	public EmailAFriendImpl()
    {
        fromName = StringUtils.EMPTY;
        linkToSend = StringUtils.EMPTY;
    }

	/**
     * Sets the name of the user sending the message
     * @param fromNm	The String to be set at the user's name.
     */
    public void setFromName(String fromNm)
    {
        	fromName = fromNm;
    }

    /**
     * Sets the link to be transmitted via e-mail
     * @param lts	The String to be set as the URL to be sent.
     */
    public void setLinkToSend(String lts)
    {
        	linkToSend = lts;
            linkToSend = StringEscapeUtils.escapeHtml(linkToSend);                 //Malicious HTML protection
    }

	/**
     * Sends the link via an e-mail message.
     * To address of the message is in toEmail (con contain an array of addresses)
     * From address of the message is in fromEmail
     * Returns true if the message was sent successfully, false otherwise.
     * @return <code>True</true> if the message was sent correctly, <code>false</code> otherwise.
     */
    public abstract boolean sendMessage();

	/**
     * Validates the to e-mail address(es).  Returns true if every address in the to list
     * is valid, false otherwise.  It also places the to addresses in the toEmail array
     * @param email	The String containing the list of e-mail addresses to send the link to.  Each address should be seperated by a comma.
     * @return	Returns <code>true</code> if the address list is valid, <code>false</code> otherwise.
     */
    public boolean validateToEmail(String email)
    {
    	return validateToEmail(email, Constants.TO_EMAIL_MAXSIZE);
    }
    
    public boolean validateToEmail(String email, int eMax)
    {
        try
		{
			StringTokenizer tokenizer = new StringTokenizer(email, StringUtils.COMMA); //tokenize list of email messages
        	String oneAddress = StringUtils.EMPTY; //one address on list
        	int i = 0; //loop counter
			toEmail = new InternetAddress[tokenizer.countTokens()]; //set up address array
			while (tokenizer.hasMoreTokens() && i < eMax) //while more addresses and within eMax boundary, check each address
        	{
				oneAddress = tokenizer.nextToken().trim();
           	 	if (validateEmail(oneAddress))
            	{
					toEmail[i] = new InternetAddress(oneAddress);
            	}
            	else
            	{
					toEmail = null;
                	return false;
            	}
           	 	i++;
       	 	}
        	return true;
		}
        catch (AddressException ae) {return false;}
    }
    

    /**
     * Validates the from e-mail address.  Returns true if the  address in the from field
     * is valid, false otherwise.  It also places the to addresses in the fromEmail variable
     * @param email	The String containing the e-mail address of the user who is sending the link.
     * @return	Returns <code>true</code> if the address is valid, <code>false</code> otherwise.
     */
    public boolean validateFromEmail(String email)
    {
        try
        {
			if (validateEmail(email)) //if valid address, send true and set variable
        	{
            	fromEmail = new InternetAddress(email);
            	return true;
        	}
        	else //else return false
        	{
            	return false;
        	}
        }
        catch (AddressException ae) {return false;}
    }
    
    private boolean validateEmail(String email)
    //validates one e-mail address using regular expressions
    //email is the address to be validated.
    //returns true if the address is valid, false otherwise.
	//regular expressions for e-mail address validation from
    //http://www.perlscriptsjavascripts.com/js/check_email.html
    {
        try
		{
			RE reg1 = new RE("(@.*@)|(\\.\\.)|(@\\.)|(^\\.)");
 			RE reg2 = new RE("^.+\\@(\\[?)[a-zA-Z0-9\\-\\.]+\\.([a-zA-Z]{2,3}|[0-9]{1,3})(\\]?)$");
			return ((! reg1.match(email)) && (reg2.match(email)));
        }
        catch (RESyntaxException rese) {return false;}
    }

}
