<%@ page import="gov.firstgov.util.*" %>
<%
	String toAddress = HTMLEncode.encode(StringUtils.trim(request.getParameter("toad")));
	boolean linkError = true;
	String linkToSend="";
	String title = HTMLEncode.encode(StringUtils.trim(request.getParameter("title")));
	if (request.getParameter("linkerror") == null)
	{
		linkError = false;
		int offset;
		String fullQS = request.getQueryString();
		//FIXME Does the character replacement in title depend on linkerror? If not, this line should move
		//out of the if.
		title = title.replace('.','&');
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
<p>Lo sentimos pero no pudimos enviar la p&aacute;gina "<%= title %>" a <%= toAddress %>. </p>
<p>Si desea puede regresar a <a href="<%= linkToSend %>" title="la &uacute;ltima p&aacute;gina">la &uacute;ltima p&aacute;gina</a> que estaba visitando o a 
	<a href="http://www.usa.gov/gobiernousa/index.shtml" title="la p&aacute;gina principal">la p&aacute;gina principal</a> de GobiernoUSA.gov.</p>
<% }
	else
	{
%>
		<p>S&oacute;lo puede enviar p&aacute;ginas de GobiernoUSA.gov a trav&eacute;s de este servicio. </p>
		<p>Si desea puede regresar a la 
			<a href="http://www.usa.gov/gobiernousa/index.shtml" title="p&aacute;gina principal">p&aacute;gina principal</a>de GobiernoUSA.gov.</p>
<%
	}
%>



