/**
 *
 * Created July, 2011 for USA.gov print copy order form
 *
 **/
package gov.usa.orderform;

import java.io.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;

import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.Message.RecipientType;


/**
 *
 * This is a utils class
 *
 **/
public class Utils {

    public static boolean validation(String inputStr, String expression) {
        boolean isValid = false;

        if(!Utils.isEmptyStr(inputStr) && !Utils.isEmptyStr(expression)) {
            Pattern pattern = Pattern.compile(expression,Pattern.CASE_INSENSITIVE);  
            Matcher matcher = pattern.matcher(inputStr);  
            if(matcher.matches()){  
                isValid = true;  
            }
        }  
        return isValid;   
    }

    /**
     * host is the SMTP server. Set it to localhost if it is blank
     **/
    public static boolean sentEmail(String toAddress, String fromAddress, String subject, String content, String host) {
    	
    	writeDoc("clint.txt","sending email");
        boolean success = true;
        if(host != null && !host.trim().equals("")) {
            host = "localhost";
            writeDoc("clint.txt","host set to localhost");
        }

        //create mail session
	Properties mailProps = new Properties();
	mailProps.put("mail.smtp.host",host);
	mailProps.put("mail.transport.protocol","smtp");
	writeDoc("clint.txt","getting mailsession");
	Session mailSession = Session.getInstance(mailProps);
	
			
        //create mail message
	MimeMessage message = new MimeMessage(mailSession);
			
        try {
            message.setFrom(new InternetAddress(fromAddress));                      //from
	    message.setRecipient(RecipientType.TO,new InternetAddress(toAddress));  //to
            message.setSubject(subject);                                            //subject
	    message.setText(content);                                               //content (body of the email)
	    Transport.send(message);
	} catch (AddressException e) {
		writeDoc("clint.txt","address exception");
	    // TODO Auto-generated catch block
	    success = false;
	    //e.printStackTrace();
	} catch (MessagingException e) {
		writeDoc("clint.txt","messaging exception");
	    // TODO Auto-generated catch block
	    success = false;
	    e.printStackTrace();
	}


        return success;
    }

    /**
     * If the file exists, append the output to the end. Otherwise creates a new one and write the output
     **/
    public static boolean writeDoc(String fileName, String output) {
        boolean success = true;
        
        try{
            // Create file 
            FileWriter fstream = new FileWriter(fileName, true);  //open the file with append as true
            BufferedWriter out = new BufferedWriter(fstream);
            out.write(output);
            
            //Close the output stream
            out.close();
        }catch (IOException e){
            //log
            success = false;
        }

        
        return success;
    }

    public static boolean validateEmailAddress(String address) {
	  boolean success = true;
	  try {
		new InternetAddress(address);
	  } catch (AddressException e) {
		success = false;
	  }
	  return success;
    } 

    /**
     * Check if the input string is empty
     **/
    public static boolean isEmptyStr(String str) {
        boolean empty = false;
        if(str == null || str.trim().length() == 0) {
            empty = true;
        }
        return empty; 
    }  

    /**
     * Append day info in the file name
     **/
    public static String addDayToName(String name) {
        if(Utils.isEmptyStr(name)) {
            return name;
        }
        Date day = new Date();
        SimpleDateFormat dFormat = new SimpleDateFormat("MM-dd-yyyy");
        //append the day to the file name
        int index = -1;
        index = name.lastIndexOf(".");
        if(index < 1) {
            return name;
        }
        return name.substring(0, index) + "-" + dFormat.format(day) + name.substring(index);
        
    }
}