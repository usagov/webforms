<%
/*
Code will send a feedback message via email to Aspen systesm for storage/handeling
Message info. is transfered via e-mail headers (Spanish version)
*/
%>
<%@ page session="false"%>
<%@ page language="java"
	import="java.sql.*,java.text.*,java.io.*,FGUtil.FGConfig"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.util.*"%>
<%@ page import="javax.mail.*"%>
<%@ page import="javax.mail.internet.*"%>
<%@ page import="javax.activation.*"%>
<%@ page import="gov.firstgov.util.*" %>

<%
try {
	int OTHER_CATEGORY = 10;
	String FROM = "Espanol@mail.fedinfo.gov";
	//String FROM = "henry.chang@gsa.gov";
	String CONTACT_SOURCE = "12";
	String MESSAGE_SUBJECT = "GobiernoUSA.gov comment";
	String NO_TO_EMAIL = "noemail@mail.fedinfo.gov";
	String EMAIL_TYPE = "B";
	FGConfig fgc = new FGConfig();
	int catID = OTHER_CATEGORY;
	String feedback = StringUtils.truncate(request.getParameter("feedback"), Constants.FEEDBACK_MAXLENGTH);
	
	// CR#1123
	feedback = feedback.concat("\n\n[FORMGEN]");
	
	System.out.println(feedback);
	String email = StringUtils.trim(request.getParameter("email"));
	//FIXME dropdown is not reference anywhere
	//String dropdown = HTMLEncode.encode(StringUtils.trim(request.getParameter("category")));
	if (StringUtils.isEmptyOrNull(email)) email= NO_TO_EMAIL;
	String category = request.getParameter("category");
	if (!StringUtils.isEmptyOrNull(category))
	{
		catID = Integer.parseInt(category);
	}
	//check for embedded email
	if (StringUtils.foundEmbeddedEmail(feedback))
	{
		feedback = StringUtils.EMPTY;
	}
	System.out.println(feedback);
	// Mailhost settings
	String host = fgc.getMailHost();
	int rowAdded=1;
	int atsearch=email.indexOf("@");
	int dotsearch=email.indexOf(".");
	if ((atsearch==-1) || (dotsearch==-1)) email = NO_TO_EMAIL;
	String to = email;
	if (to == null || to.equals("")) to = NO_TO_EMAIL;
	boolean sessionDebug = false;
	Properties props = System.getProperties();
	props.put("mail.host", host);
	props.put("mail.transport.protocol", fgc.getMailType());
	Session mailSession = Session.getDefaultInstance(props, null);
	mailSession.setDebug(sessionDebug);
	Message msg = new MimeMessage(mailSession);
	msg.setFrom(new InternetAddress(to,to));
	msg.addHeader("Email",to);
	msg.addHeader("ContactSource",CONTACT_SOURCE);
	msg.addHeader("EmailType",EMAIL_TYPE);
	msg.addHeader("EmailCategory",""+catID);
	InternetAddress[] address = {new InternetAddress(FROM)};
	msg.setRecipients(Message.RecipientType.TO, address);
	msg.setSubject(MESSAGE_SUBJECT);
	msg.setSentDate(new Date());
	msg.setText(feedback);
	Transport.send(msg);
%>
<br />
<p class="FontBK9">Gracias por visitar GobiernoUSA.gov y
por enviarnos sus comentarios. <br />
<br />
Atentamente, <br />
<br />
El equipo de GobiernoUSA.gov <br />
<br />
</p>
<%
}
catch (Exception E) {
%>
<br />
<p class="FontBK9">Lo sentimos pero su mensaje no pudo ser enviado. <br />
		Por favor env&iacute;e su mensaje nuevamente.</p>
<%
}
%>