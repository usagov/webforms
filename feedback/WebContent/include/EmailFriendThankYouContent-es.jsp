<%@ page import="gov.firstgov.util.*" %>
<%
	String toAddress = HTMLEncode.encode(StringUtils.trim(request.getParameter("toad")));
	String linkToSend;
	int offset;
	String title = StringUtils.trim(request.getParameter("title"));
	title = StringUtils.HtmlEncodeAcute(title);
	//FIXME The cc is not used in the code.
	String cc = request.getParameter("cc");
	if (cc == null) cc = "";
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
<p>Gracias. La p&aacute;gina "<%= title %>" ha sido enviada a <%= toAddress %>.</p>
<p>Si desea puede regresar a <a href="<%= linkToSend %>" title="la &uacute;ltima p&aacute;gina">la &uacute;ltima p&aacute;gina</a> que estaba visitando o a 
<a href="http://www.usa.gov/gobiernousa/index.shtml" title="la p&aacute;gina principal">la p&aacute;gina principal</a> de GobiernoUSA.gov.</p>
