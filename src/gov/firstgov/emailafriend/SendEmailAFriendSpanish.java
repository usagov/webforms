/*
 * Created on Dec 20, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.firstgov.emailafriend;

import java.util.Date;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;

import FGUtil.FGConfig;
import java.lang.String;

/**
 * @author RussellGONeill
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SendEmailAFriendSpanish extends EmailAFriendImpl {

	protected boolean sendCopy = false;
	
	protected String title;


	/* (non-Javadoc)
	 * @see gov.firstgov.emailafriend.EmailAFriendImpl#sendMessage()
	 */
	public boolean sendMessage() {
		FGConfig fgc = new FGConfig(); //class to get e-mail server information
        String host = fgc.getMailHost(); //the address of the E-mail host
        String subject = ""; //subject line of message
        String msgText = ""; //text of message
        String allToEmails = ""; //list of addresses e-mail being sent to, used in message sent to user if they requested a copy
        if (fromName.equals("")) fromName = fromEmail.toString(); //if no name entered, use from e-mail address
		//build the subject line
		subject += fromName;
        subject += " te recomienda GobiernoUSA.gov, el sitio Web oficial del Gobierno de los EE. UU.";
		//build the message body
		msgText += fromName;
		
		// CR1148
        msgText += " le recomienda que visite la p&#225;gina \"";
        msgText += "<a href=\"" + linkToSend + "\">" + linkToSend + "</a>\"";
        msgText += " en  <a href=\"http://www.usa.gov/gobiernousa/index.shtml\">GobiernoUSA.gov</a>,";
        
		// CR1148
		msgText += " el sitio web oficial del Gobierno de los Estados Unidos.\n";
        
		msgText += "<br>\n";
        msgText += "<hr>\n ";
        
		// CR1148
		msgText += " <a href=\"http://www.usa.gov/gobiernousa/Contactenos.shtml\">Cont&#225;ctenos</a> con sus preguntas o con&#233;ctese con el Gobierno en ";
		msgText += " <a href=\"http://www.facebook.com/GobiernoUSAgov\">Facebook</a> (<a href=\"http://www.facebook.com/GobiernoUSAgov\">http://www.facebook.com/GobiernoUSAgov</a>) y ";
        msgText += " <a href=\"http://twitter.com/gobiernousa\">Twitter</a> (<a href=\"http://twitter.com/gobiernousa\">http://twitter.com/gobiernousa</a>).<br />\n<br />\n";
        msgText += "Vea la <a href=\"http://www.usa.gov/gobiernousa/Privacidad_Seguridad.shtml\">pol&#237;tica de privacidad</a> de GobiernoUSA.gov.</span>";
		
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
        		allToEmails += toEmail[i].toString() + ", ";
				MimeMessage msg = new MimeMessage(mailSession);
				msg.setFrom(fromEmail);
				msg.addRecipient(Message.RecipientType.TO, (Address)toEmail[i]);
        		msg.setContent(msgText, "text/html");
				msg.setSubject(subject,"ISO-8859-1");
				msg.setSentDate(new Date());
				Transport.send(msg);
        	}
        }
        catch (MessagingException me) {return false;}
        if (sendCopy) { //if user requested a copy of the message, send one to the "from" address
    		allToEmails = allToEmails.substring(0,allToEmails.length()-2); //remove last ',' and ' ' in email list
        	//build the subject line
    		subject = fromName;
            subject += " te recomienda GobiernoUSA.gov, el sitio Web oficial del Gobierno de los EE. UU.";
    		//build the message body
            msgText = "Gracias por recomendar <a href=\"http://www.usa.gov/gobiernousa/index.shtml\">GobiernoUSA.gov</a>,";
            msgText += " el sitio Web oficial del Gobierno de los Estados Unidos.<br />\n";
            msgText += "A continuaci&oacute;n encuentre una copia del e-mail que se le envi&oacute; a ";
            msgText += allToEmails + ".";
            msgText += "<br />\n";
            msgText += "<br />\n";
    		msgText += fromName;
			
			// CR1148
			msgText += " le recomienda que visite la p&#225;gina \"";
            msgText += "<a href=\"" + linkToSend + "\">" + linkToSend + "</a>\"";
            msgText += " en  <a href=\"http://www.usa.gov/gobiernousa/index.shtml\">GobiernoUSA.gov</a>,";

			// CR1148
			msgText += " el sitio web oficial del Gobierno de los Estados Unidos.\n";

            msgText += "<br>\n";
            msgText += "<hr>\n ";

			// CR1148
			msgText += " <a href=\"http://www.usa.gov/gobiernousa/Contactenos.shtml\">Cont&#225;ctenos</a> con sus preguntas o con&#233;ctese con el Gobierno en ";
			msgText += " <a href=\"http://www.facebook.com/GobiernoUSAgov\">Facebook</a> (<a href=\"http://www.facebook.com/GobiernoUSAgov\">http://www.facebook.com/GobiernoUSAgov</a>) y ";
			msgText += " <a href=\"http://twitter.com/gobiernousa\">Twitter</a> (<a href=\"http://twitter.com/gobiernousa\">http://twitter.com/gobiernousa</a>).<br />\n<br />\n";
			msgText += "Vea la <a href=\"http://www.usa.gov/gobiernousa/Privacidad_Seguridad.shtml\">pol&#237;tica de privacidad</a> de GobiernoUSA.gov.</span>";

			//set up and send e-mail message(s)
    		sessionDebug = false;
    		props = System.getProperties();
    		props.put("mail.host", host);
    		props.put("mail.transport.protocol", fgc.getMailType());
            try
            {
    			Session mailSession = Session.getDefaultInstance(props, null);
    			mailSession.setDebug(sessionDebug);
				MimeMessage msg = new MimeMessage(mailSession);
				msg.setFrom(fromEmail);
				msg.addRecipient(Message.RecipientType.TO, (Address)fromEmail);
        		msg.setContent(msgText, "text/html");
				msg.setSubject(subject,"ISO-8859-1");
				msg.setSentDate(new Date());
				Transport.send(msg);
            }
            catch (MessagingException me) {return false;}
        }
        return true;
	}
	public void setSendCopy(boolean sc) {
		sendCopy = sc;
	}
	public void setTitle(String t) {
		title = t;
	}
}
