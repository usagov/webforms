<%@ page import="java.io.*" %>
<%@ page isErrorPage="true" %>

<% 
	if (exception != null) 
    { 
%>
		<p>Please report the following errors to the system administrator:</p>
        <p><strong>Exception:&nbsp;</strong> <%= exception.getClass() %> </p>
        <p><strong>Message:&nbsp;</strong><%= exception.getMessage() %> </p>
		<p>Click to return to <a href="http://www.usa.gov">USA.gov</a>. </p>
 <% 
 		exception = null; 
     }
 %>
