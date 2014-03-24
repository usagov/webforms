/*
 * Created on Dec 20, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.firstgov.emailafriend;

import gov.firstgov.util.StringUtils;

import java.util.Date;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;

import FGUtil.FGConfig;

/**
 * @author RussellGONeill
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SendEmailAFriendEnglish extends EmailAFriendImpl {

	/* (non-Javadoc)
	 * @see gov.firstgov.emailafriend.EmailAFriendImpl#sendMessage()
	 */
	public boolean sendMessage() {
        FGConfig fgc = new FGConfig(); //class to get e-mail server information
        String host = fgc.getMailHost(); //the address of the E-mail host
        String subject = ""; //subject line of message
        String msgText = ""; //text of message
        
        //Eric Sun - 09262006
        if (StringUtils.isEmptyOrNull(fromName)) fromName = fromEmail.toString(); //if no name entered, use from e-mail address
        	
		//build the subject line
		subject += fromName;
        subject += " recommends this USA.gov page";
		//build the message body
		msgText += fromName;
        msgText += " recommends a page from <a href=\"http://www.usa.gov/\">www.usa.gov</a>,";
        msgText += " the U.S. government's official web portal.  To view this page, click on the";
        msgText += " link below or copy this address to your browser address bar:<br>\n";
        msgText += "<br>\n";
        msgText += "<a href=\"" + linkToSend + "\">" + linkToSend + "</a>";
        msgText += "<br>\n";
        msgText += "<br>\n";
        msgText += "<hr>\n ";
        msgText += "<span style=\"font-size:75%\">This page has been sent to you through the USA.gov E-Mail this Page feature.<br>\n";
        msgText += "USA.gov respects your <a href=\"http://www.usa.gov/About/Privacy_Security.shtml\">privacy and security</a>.";
        msgText += " For any questions about the federal";
        msgText += " government, e-mail <a href=\"http://www.usa.gov/feedback/FeedbackForm.jsp\">USA.gov</a>";
        msgText += " or call 1-800-FED-INFO (1-800-333-4636).</span>";
		//set up and send e-mail message(s)
		boolean sessionDebug = false;
		Properties props = System.getProperties();
		props.put("mail.host", host);
		props.put("mail.transport.protocol", fgc.getMailType());
        try
        {
			Session mailSession = Session.getDefaultInstance(props, null);
			mailSession.setDebug(sessionDebug);
        	for (int i = 0; i < toEmail.length; i++) //set up and send the message to each address in toEmail array
        	{
				Message msg = new MimeMessage(mailSession);
				msg.setFrom(fromEmail);
				msg.addRecipient(Message.RecipientType.TO, (Address)toEmail[i]);
        		msg.setContent(msgText, "text/html");
				msg.setSubject(subject);
				msg.setSentDate(new Date());
				Transport.send(msg);
        	}
        }
        catch (MessagingException me) {return false;}
        return true;
	}

}
