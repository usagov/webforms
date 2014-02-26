package gov.firstgov.photocontest;

import gov.firstgov.util.taskrunner.TaskInterface;

import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import FGUtil.FGConfig;
import gov.firstgov.photocontest.forms.PhotoContestForm;

public class SendPhotoContestEntryFormTask implements TaskInterface {

    //private static transient Log log = LogFactory.getLog(SendPhotoContestEntryFormTask.class);
    private Log log = LogFactory.getLog(this.getClass());
    /**
     * The entry form to be sent
     */
    private PhotoContestForm entryForm;

    public void setBean(Object form) {
        entryForm = (PhotoContestForm) form;
    }
    
    /**
     * The e-mail address to send the entry form to.
     */
    private String defaultToEmail;


    public SendPhotoContestEntryFormTask() {
        defaultToEmail = PhotoContestConstants.DEFAULT_TO_EMAIL_ADDRESS;
    }

    /**
     * @param email the e-mail address that the entry form will be sent to.
     */
    public SendPhotoContestEntryFormTask(String email) {
        defaultToEmail = email;
    }

    /**
     * Sends the entry form via e-mail to the e-mail address specified in the constructor, or using the setToEmail method().
     * @return true if the message is sent succesfully, false otherwise.
     */
    public boolean execute() {
        //For testing using yahoo smtp over ssl
        //return sendMailViaSMTPAgent();

        return sendMail();
    }

    private boolean sendMail() {
        FGConfig fgc = new FGConfig(); //class to get e-mail server information
        String host = fgc.getMailHost(); //the address of the E-mail host

        //set up and send e-mail message(s)
        boolean sessionDebug = false;
        Properties props = System.getProperties();

        props.put("mail.host", host);
        props.put("mail.transport.protocol", fgc.getMailType());

        try {
            log.debug("Preparing to e-mail entry form");
            Session mailSession = Session.getDefaultInstance(props, null);
            mailSession.setDebug(sessionDebug);

            String userEmail = entryForm.getEmailAddress();
            // Send message to user only if user provides an email address
            if (!userEmail.equals("") && (userEmail != null)) {
                Message msg = new MimeMessage(mailSession);

                //Reply from usa.gov.donotreply@usa.gov
                msg.setFrom(new InternetAddress(PhotoContestConstants.DEFAULT_FROM_EMAIL_ADDRESS));

                //To user who submit entry form for USA.gov photo contest, initialize an email address array
                InternetAddress[] toAddress = {new InternetAddress(userEmail)};
                msg.setRecipients(Message.RecipientType.TO, toAddress);

                //Set send mail subejct for thank user's submission
                msg.setSubject(PhotoContestConstants.THANK_YOU_FOR_SUBMISSION);

                //Set send mail date
                msg.setSentDate(new Date());
                //Set send mail greeting emssage
                msg.setText(greetingMessage());
                log.debug("Sending e-mail message to user");

                ////Send message out
                Transport.send(msg);

            } else {
                // if user doesn't provide email, use default from e-mail address
                userEmail = PhotoContestConstants.DEFAULT_FROM_EMAIL_ADDRESS;
            }

            // Prepare and send another message to usa.gov (jessica.milcetich@gsa.gov) for user's new entry form submission
            String msgText = entryForm.toString(); //text of message
            log.debug(msgText);
            Message msg2 = new MimeMessage(mailSession);
            msg2.setFrom(new InternetAddress(userEmail));

            //defaultToEmail = PhotoContestConstants.DEFAULT_TO_EMAIL_ADDRESS
            msg2.addRecipient(Message.RecipientType.TO, new InternetAddress(defaultToEmail));

            msg2.setSubject(PhotoContestConstants.NEW_SUBMISSION);
            
            msg2.setSentDate(new Date());
            msg2.setContent(msgText, "text/html");
            log.debug("Sending e-mail message to usa.gov");

            //Send message out
            Transport.send(msg2);

        } catch (MessagingException me) {
            log.error(me.toString(), me);
            return false;
        }
        return true;
    }

    //==========Yahoo Plus Mail over SSL=========
    private static final String SMTP_HOST_NAME = "plus.smtp.mail.yahoo.com";
    private static final int SMTP_HOST_PORT = 465;
    private static final String SMTP_AUTH_USER = "hjensun@yahoo.com";
    private static final String SMTP_AUTH_PWD  = "dhw199681";
    //==========Yahoo Plus Mail over SSL=========

     private boolean sendMailViaSMTPAgent() { //For testing purpose

        //set up and send e-mail message(s)
        boolean sessionDebug = true;
        Properties props = System.getProperties();

        //==========Yahoo Plus Mail over SSL=========
        props.put("mail.transport.protocol", "smtps");
        props.put("mail.smtps.host", SMTP_HOST_NAME);
        props.put("mail.smtps.auth", "true");
        //==========Yahoo Plus Mail over SSL=========

        try {
            log.debug("Preparing to e-mail entry form");
            Session mailSession = Session.getDefaultInstance(props, null);
            mailSession.setDebug(sessionDebug);

            //==========Yahoo Plus Mail over SSL=========
            Transport transport = mailSession.getTransport();
            transport.connect(SMTP_HOST_NAME, SMTP_HOST_PORT, SMTP_AUTH_USER, SMTP_AUTH_PWD);
            //==========Yahoo Plus Mail over SSL=========

            String userEmail = entryForm.getEmailAddress();
            // Send message to user only if user provides an email address
            if (!userEmail.equals("") && (userEmail != null)) {
                Message msg = new MimeMessage(mailSession);
                //Reply from hjensun@yahoo.com
                msg.setFrom(new InternetAddress("hjensun@yahoo.com"));
                //To user who submit entry form for USA.gov photo contest, initialize an email address array
                InternetAddress[] toAddress = {new InternetAddress(userEmail)};
                msg.setRecipients(Message.RecipientType.TO, toAddress);
                //BCC to jessica.milcetich@gsa.gov
                //msg.addRecipient(Message.RecipientType.BCC, new InternetAddress(PhotoContestConstants.DEFAULT_BCC_ADDRESS));
                //Set send mail subejct for thank user's submission
                msg.setSubject(PhotoContestConstants.THANK_YOU_FOR_SUBMISSION);
                //Set send mail date
                msg.setSentDate(new Date());
                //Set send mail greeting emssage
                msg.setText(greetingMessage());
                log.debug("Sending e-mail message to user");

                //==========Notice=========
                //You should call trans.sendMessage(msg, addrs) to send the message.
                //The send method is a static convenience method that acquires its own Transport object
                //and creates its own connection to use for sending; it does not use the connection associated
                //with any Transport object through which it is invoked.
                 transport.sendMessage(msg, msg.getRecipients(Message.RecipientType.TO));
                //==========Notice=========

            } else {
                // if user doesn't provide email, use default from e-mail address
                userEmail = PhotoContestConstants.DEFAULT_FROM_EMAIL_ADDRESS;
            }

            // Prepare and send another message to usa.gov (jessica.milcetich@gsa.gov)
            String msgText = entryForm.toString(); //text of message
            log.debug(msgText);
            Message msg2 = new MimeMessage(mailSession);
            msg2.setFrom(new InternetAddress(userEmail));
            msg2.addRecipient(Message.RecipientType.TO, new InternetAddress("hjensun@yahoo.com"));
            //msg2.addRecipient(Message.RecipientType.TO, new InternetAddress("hungjen.sun@gsa.gov"));
            msg2.setSubject(PhotoContestConstants.NEW_SUBMISSION);
            msg2.setSentDate(new Date());
            msg2.setContent(msgText, "text/html");
            log.debug("Sending e-mail message to usa.gov");

            //==========Notice=========
            transport.sendMessage(msg2, msg2.getRecipients(Message.RecipientType.TO));
            transport.close();
            //==========Notice=========
            
        } catch (MessagingException me) {
            log.error(me.toString(), me);
            return false;
        }
        return true;
    }

    private String greetingMessage() {
        
        StringBuffer greeting = new StringBuffer("");
        greeting.append("\nDear USA.gov User, \n\n");
        greeting.append("Thank you for your submission to the USA.gov photo contest. ");
        greeting.append("If you are selected as a winner, we will contact you at the e-mail address you provided.\n\n");
        greeting.append("Gracias por enviar su entrada al concurso de fotos a través del sitio de USA.gov.  Si usted es seleccionado ganador, lo contactaremos usando el e-mail que nos proveyó.");
        greeting.append("Sincerely, \n\nThe USA.gov Team \n\n\n");
        greeting.append("The personal information you submit on the entry form will only be used to contact you in the event you win the contest or if there is a question or issue regarding your photo or its content. We will not disclose, give, sell, or transfer any personal information about you, unless required for law enforcement or by statute. As your photo will be made available to the public, you should not include personal information about yourself or anyone else. All information collected on the entry form will be deleted at the conclusion of the contest.");
        greeting.append("\n\n******************************************************************\n");
        greeting.append("Please do not reply to this e-mail. Mail\n");
        greeting.append("sent to this address cannot be answered.\n");
        greeting.append("******************************************************************\n");

        log.debug(greeting.toString());
        
        return greeting.toString();
    }
}
