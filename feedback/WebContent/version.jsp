<%@ page import="FGUtil.FGConfig, java.net.InetAddress" %>
<html>
<title>USA.gov Version Utility</title>
<body>
<h1>This is USA.gov Dynamic Application TIDE UTF-8 version.</h1>
<p>Release: <strong>16.0.2</strong><br />Build: <strong>2</strong></p>
<% 
	if (request.getParameter("fgconfigused") != null)
	{
		FGConfig fgc = new FGConfig();
%>
		<p>FGConfig Information:</p>
		<p><strong>Mailhost URL used:</strong> <%= fgc.getMailHost() %></p>
<%
	}
%>
</body>