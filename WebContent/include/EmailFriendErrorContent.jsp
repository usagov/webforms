<%@ page import="gov.firstgov.util.*" %>
<%
	String toAddress = HTMLEncode.encode(StringUtils.trim(request.getParameter("toad")));
	boolean linkError = true;
	String linkToSend="";
	if (request.getParameter("linkerror") == null)
	{
		linkError = false;
		int offset;
		String fullQS = request.getQueryString();
		//FIXME When there is no lts in query string, the offset is wrongly set.
		if ( fullQS != null ) {
			offset = fullQS.indexOf("&lts=") + 5; //calculate beginning of URL to be sent
			linkToSend = HTMLEncode.encode(StringUtils.trim(fullQS.substring(offset)));
		}
        //FIXME Other variables set to "" when they are null. This is an exception. Please be consistent.
		else {
		    linkToSend = "";
		}
	}
%>
<% if (! linkError)
	{
%> 
	<p>We apologize, but we were unable to send the following URL to <%= toAddress %>.</p>
	<p><%= linkToSend %></p>
	<p>You may go back to the <a href="<%= linkToSend %>">last page</a> you were viewing and retry your request, or you may return to the <a href="/index.shtml">USA.gov</a> homepage.</p>
<% }
	else
	{
%>
		<p>We apologize, but we're experiencing technical difficulties right now, and can't send your e-mail message.  Please retry your request at a later time.</p>
<%
	}
%>


