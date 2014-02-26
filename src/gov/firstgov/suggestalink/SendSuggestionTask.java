/*
 * Created on May 4, 2006
 *
 */
package gov.firstgov.suggestalink;

import gov.firstgov.util.taskrunner.TaskInterface;
import gov.firstgov.util.*;

import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;

import FGUtil.FGConfig;

/**
 * @author RussellGONeill
 *
 * This task will send the suggestion to the E-mail address specified with either the constructor or setToEmail()
 * It is designed to be used with a SuggeestALinkRunner
 * @see	SuggestALinkRunner
 */
public class SendSuggestionTask implements TaskInterface {
	/**
	 * The suggestion to be sent
	 */
	private SuggestALinkBean suggestion;

	/**
	 * @param theSuggestalinkbean2 The suggestion to set.
	 */
	public void setBean(Object bean) {
		suggestion = (SuggestALinkBean) bean;
	}

	/**
	 * The e-mail address to send the suggestion to.
	 */
	private String toEmail;

	/**
	 * @return Returns the e-mail address that the suggestion will be sent to.
	 */
	private String getToEmail() {
		return toEmail;
	}

	/**
	 * @param theToEmail The e-mail address that suggestion will be sent to.
	 */
	private void setToEmail(String theToEmail) {
		toEmail = theToEmail;
	}

	/**
	 * @param email	the e-mail address that the suggestion will be sent to.
	 */
	public SendSuggestionTask(String email) {
		toEmail = email;
	}

	/**
	 * Sends the suggestion via e-mail to the e-mail address specified in the constructor, or using the setToEmail method().
	 * @return true if the message is sent succesfully, false otherwise.
	 */
	public boolean execute() {
		return (suggestion.getLang().toUpperCase().equals(SuggestALinkConstants.ESPANOL) ? sendEspanol() : sendEnglish());
	}
	
	private boolean sendEspanol() {
		Logger logger = SuggestALinkLogger.getLogger();
        FGConfig fgc = new FGConfig(); //class to get e-mail server information
        String host = fgc.getMailHost(); //the address of the E-mail host
        String subject = "Sugiera un enlace"; //subject line of message
        String msgText = suggestion.toString(); //text of message
        String email = suggestion.getEmail();
        if (email.equals("")) email = SuggestALinkConstants.ESPANOL_DEFAULT_FROM_ADDRESS;//if no name entered, use from e-mail address
		//set up and send e-mail message(s)
		boolean sessionDebug = false;
		Properties props = System.getProperties();
		props.put("mail.host", host);
		props.put("mail.transport.protocol", fgc.getMailType());
        try
        {
        	logger.debug("Preparing to e-mail suggestion");
			Session mailSession = Session.getDefaultInstance(props, null);
			mailSession.setDebug(sessionDebug);
			Message msg = new MimeMessage(mailSession);
			msg.setFrom(new InternetAddress(email));
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
			msg.setSubject(subject);
			msg.setSentDate(new Date());
    		msg.setContent(msgText, "text/plain");
			logger.debug("Sending e-mail message");
			Transport.send(msg);
        }
        catch (MessagingException me) {logger.error(me.toString(),me); return false;}
        return true;
	}
	
	private boolean sendEnglish() {
		Logger logger = SuggestALinkLogger.getLogger();
        FGConfig fgc = new FGConfig(); //class to get e-mail server information
        String host = fgc.getMailHost(); //the address of the E-mail host

        //set up and send e-mail message(s)
		boolean sessionDebug = false;
		Properties props = System.getProperties();
		props.put("mail.host", host);
		props.put("mail.transport.protocol", fgc.getMailType());
        try
        {
        	logger.debug("Preparing to e-mail suggestion");
			Session mailSession = Session.getDefaultInstance(props, null);
			mailSession.setDebug(sessionDebug);
			
	        String email = suggestion.getEmail();
	        // Send message to user only if user provides an email address
	        if (!email.equals("")) {
	            Message msg = new MimeMessage(mailSession);
	            msg.setFrom(new InternetAddress(SuggestALinkConstants.ENGLISH_DEFAULT_FROM_ADDRESS));
	            InternetAddress[] address = {new InternetAddress(email)};
	            msg.setRecipients(Message.RecipientType.TO, address);
	            msg.setSubject("Re: Thank You for Your Feedback");
	            msg.setSentDate(new Date());
	            msg.setText(greetingMessage());
				logger.debug("Sending e-mail message to user");
	            Transport.send(msg);
	        }
	        else {
	        	// if user doesn't provide email, use default from e-mail address
	        	email = SuggestALinkConstants.ENGLISH_DEFAULT_FROM_ADDRESS;	        	
	        }
	        // Send message to usa.gov
	        String msgText = suggestion.toString(); //text of message
	        logger.debug(msgText);
			Message msg2 = new MimeMessage(mailSession);
			msg2.setFrom(new InternetAddress(email));
			msg2.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
			msg2.setSubject("Suggest-A-Link");
			msg2.setSentDate(new Date());
    		msg2.setContent(msgText, "text/plain");
			logger.debug("Sending e-mail message to usa.gov");
			Transport.send(msg2);
        }
        catch (MessagingException me) {logger.error(me.toString(),me); return false;}
        return true;
	}
	private String greetingMessage() {
		Logger logger = SuggestALinkLogger.getLogger();
        StringBuffer greeting = new StringBuffer("");
        greeting = greeting.append("\nDear USA.gov User, \n\n");
        greeting = greeting.append("Thank you for visiting the USA.gov website and sending your suggestion \n");
        greeting = greeting.append("through this site.  Our Content Team will review your suggestion against \n");
        greeting = greeting.append("our linking criteria and determine whether to add it at this time. \n\n");
        greeting = greeting.append("Please visit us again soon at http://www.usa.gov. \n\n\n");
        greeting = greeting.append("Sincerely, \n\nThe USA.gov Team \n\n\n");
        greeting = greeting.append("\n\n******************************************************************\n");
        greeting = greeting.append("Please do not reply to this e-mail. Mail\n");
        greeting = greeting.append("sent to this address cannot be answered.\n");
        greeting = greeting.append("******************************************************************\n");
        logger.debug(greeting.toString());
        return greeting.toString();
	}
}
