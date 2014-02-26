<%@ page import="gov.firstgov.util.*"%>
<%//This java code sets three boolean variables so custom error messages may
			//be printed if form entries failed validation
			//In addition, it sets values of form fields with values that were previouslly
			//submitted by the user, but was found to be invalid.
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
			int error; //error code returned by EmailAFriendServlet
			int offset; //where in the query string the link to be e-mailed begins
			boolean toAddressWrong = false; //true if the address to send the link to was invalid
			boolean fromAddressWrong = false; //true if the user sending the link's e-mail address was invalid
			boolean anyError = false; //true if any error was returned
			//FIXME Since error can be retrieved by Null proof parseInt, should you move the error
			//initialization out of the if and test if against error != 0?
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
					//check for embedded email
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


<p>At USA.gov, we honor your privacy. When you send an e-mail from the
    USA.gov website, we will include your e-mail address in the message,
    allowing the recipient to respond directly to you. When you choose to
    provide personal information to us, we use it only to fulfill your
    request. This e-mail message will not be saved or recorded on our site.
    For more detailed information, please visit our <a href="/About/Privacy_Security.shtml" title="Privacy and Security statement">Privacy and Security statement</a>.</p>
<%if (anyError) {

			%>
<p class="FontFormError">There was an error processing your request.
Please correct the problems noted below, and resend.</p>
<%}

			%>
<form name="myForm" id="myForm" method="POST"
	action="/feedback/servlet/EmailAFriendServlet"><input type="hidden"
	name="lts" id="lts" value="<%= linkToSend %>" />
    <p>
        A field with an asterisk (*) before it is a required field.
        <br><br>
        <label for="toad">
            <strong>* Send this page to <span class="FontRequired">(Type the e-mail address of your friend[s])</span></strong>:
            <br>

            To send this page to up to ten recipients, separate the e-mail addresses with commas, with no spaces.
            <br>
        </label>

        <%if (toAddressWrong) {
        %>
		<span class="FontFormError"><br />
			You entered an invalid e-mail address. Please
			enter a valid e-mail address to which we can send the page. If you are
			entering multiple addresses, please ensure the addresses are separated
			by commas.
		</span> <BR />
        <%}
        %>

        <input aria-required="true" required type="text" name="toad" id="toad" size="40" value="">
    </p>


    <p>
        <label for="frad">
            <strong>* Your e-mail address:</strong>
            <br>
        </label>
        <%if (fromAddressWrong) {
        %>
		<span class="FontFormError"><br />
			You entered an invalid e-mail address. Please
			enter your e-mail address. This is used so your friend may reply
			directly back to you.
		</span> <br />
        <%}
        %>

        <input aria-required="true" required type="text" name="frad" id="frad" size="40" value="">
    </p>
    <p>
        <input type="submit" name="p_submit" id="label_for_p_submit" class="redbutton" value="Submit">
    </p>


</form>


