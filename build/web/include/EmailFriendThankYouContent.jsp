<%@ page import="gov.firstgov.util.*" %>
<%
	String toAddress = HTMLEncode.encode(StringUtils.trim(request.getParameter("toad")));
	String linkToSend;
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
%>
<p>The following URL has been sent to <%= toAddress %>.</p>
<p><%= linkToSend %></p>
<p>Thanks for using USA.gov's E-mail this Page feature.  You may go back to the <a href="<%= linkToSend %>">last page</a> you were viewing, or you may return to the <a href="/index.shtml">USA.gov</a> homepage.</p>	


