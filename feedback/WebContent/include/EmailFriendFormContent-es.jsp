<%@ page import="gov.firstgov.util.*"%>
<%//This java code sets three boolean variables so custom error messages may
			//be printed if form entries failed validation
			//In addition, it sets values of form fields with values that were previouslly
			//submitted by the user, but was found to be invalid.

			// Set character encoding to utf-8 so HTTP variables can be properly decoded.
			//request.setCharacterEncoding("UTF8");
			//System.out.println("Servlet encoding=" + request.getCharacterEncoding());

			String linkToSend = HTMLEncode.encode(StringUtils.trim(request
					.getParameter("lts")));//the link to be e-mailed
			//if javascript disabled, so no LTS parameter sent, try getting URL from HTTP Header
			if (linkToSend.equals(""))
				linkToSend = HTMLEncode.encode(StringUtils.trim(request
						.getHeader("http_referer")));
			//if header does not exist, then set to null so error can be displayed.
			//FIXME Other variables set to "" when they are null. This is an exception. Please be consistent.
			if (linkToSend == null)
				linkToSend = "null";
			String toAddress = ""; //the e-mail address to send the link to
			String fromAddress = ""; //the user's e-mail address that is sending the link
			String fullQS = request.getQueryString(); //the entire query string
			String cc = ""; //copy to visitor?

			String titleInURL = StringUtils.trim(request.getParameter("title")); //title of page (with . instead of &)
			String title = StringUtils.HtmlEncodeAcute(titleInURL);
			titleInURL = HTMLEncode.encode(titleInURL);
			int error; //error code returned by EmailAFriendServlet
			int offset; //where in the query string the link to be e-mailed begins
			boolean toAddressWrong = false; //true if the address to send the link to was invalid
			boolean fromAddressWrong = false; //true if the user sending the link's e-mail address was invalid
			boolean anyError = false; //true if any error was returned
			if (request.getParameter("error") != null) //reset from default values only if an error was reported
			{
				try {
					error = StringUtils.parseInt(request.getParameter("error")); //get error code
					anyError = true;
					toAddress = HTMLEncode.encode(StringUtils.trim(request
							.getParameter("toad"))); //get address to send the link to
					fromAddress = HTMLEncode.encode(StringUtils.trim(request
							.getParameter("frad"))); //get user who is sending the link's e-mail address
					//FIXME When there is no lts in query string, the offset is wrongly set.
					offset = fullQS.indexOf("&lts=") + 5; //calculate beginning of URL to be sent
					linkToSend = HTMLEncode.encode(StringUtils.trim(fullQS
							.substring(offset)));
					cc = request.getParameter("cc");
					if (cc == null) {
						cc = "";
					} else {
						cc = "checked";
					}
					//FIXME Lack of default.
					switch (error) //based on error code, set boolena values
					{
					//FIXME Does case 3 intend to fall thru? How do I add another error case?
					case 3:
						toAddressWrong = true;
					case 2:
						fromAddressWrong = true;
						break;
					case 1:
						toAddressWrong = true;
						break;
					}
				} catch (NumberFormatException nfe) {
				} //if invalid error code, then continue with defaults
			}

			%>

<p><Strong><%=title%></Strong></p>
<%if (anyError) {

			%>
<p class="FontFormError">Su mensaje no pudo ser enviado. Por favor
corrija los problemas indicados a continuaci&oacute;n y vuelva a enviar
su mensaje.</p>
<%}

			%>
<form name="myForm" id="myForm" method="POST"
	action="/feedback/servlet/EmailAFriendServlet"><input type="hidden"
	name="lts" id="lts" value="<%= linkToSend %>" /> <input type="hidden"
	name="title" id="title" value="<%= titleInURL %>" /> <input
	type="hidden" name="lang" id="lang" value="es" />
<fieldset>
<p><label for="toad"> Env&iacute;e esta p&aacute;gina a <span class="FontRequired">[escriba el e-mail de su(s) amigos(s) - necesario]</span>:
</label><br />
<%if (toAddressWrong) {

			%>
<span class="FontFormError"><br />El e-mail del destinatario no es v&aacute;lido.
Si est&aacute; enviando esta p&aacute;gina a varias personas,
a&ntilde;da una coma despu&eacute;s de cada e-mail.</span><br />
<%}

			%> <input type="text" name="toad" id="toad" size="40" tabindex="109" value="<%= toAddress %>" /><br />
Si desea enviar esta p&aacute;gina a varias personas (hasta 10),
a&ntilde;ada una coma despu&eacute;s de cada e-mail. </p>
<p><label for="frad">Su e-mail:</label> <span class="FontRequired">(necesario)</span>
<br />
<%if (fromAddressWrong) {

			%>
<span class="FontFormError"><br />Su e-mail no es v&aacute;lido. Es necesario que
escriba su e-mail para que su(s)<br />
amigo(s) pueda(n) comunicarse directamente con usted.</span> <br />
<%}

			%> <input type="text" name="frad" id="frad" size="40" tabindex="111" value="<%= fromAddress %>" /></p>
<p><label for="cc">Env&iacute;e esta p&aacute;gina a mi e-mail </label> <input type="checkbox" id="cc" name="cc" <%= cc %> /> </p>
<div align="left"><label for='label_for_p_submit'> <input type='submit' name='p_submit' id='label_for_p_submit' value='Enviar' tabindex="112">
</label></div>
</fieldset>
</form>
<p>Su e-mail ser&aacute; incluido en el mensaje para que el (los)
destinatario(s) pueda(n) comunicarse directamente con usted. <br />
<br />
GobiernoUSA.gov mantiene absoluta confidencialidad de los datos
personales de los visitantes. Para m&aacute;s informaci&oacute;n, visite
la p&aacute;gina de <a
	href="http://www.usa.gov/gobiernousa/Privacidad_Seguridad.shtml"
	title="pol&iacute;tica de privacidad">pol&iacute;tica de privacidad.</a></p>


