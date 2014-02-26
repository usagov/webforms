<%@page import="java.sql.*"%>
<%@page import="java.lang.*"%>
<%@page import="FGUtil.*"%>

<%
FGConfig fgcdb = new FGConfig();
String driver = fgcdb.getDBDriver();
String user= fgcdb.getDBUser();
String pass = fgcdb.getDBPass();

String dbURL = fgcdb.getDBURL();

Connection conn;

try {
	Class.forName(driver);
    conn = DriverManager.getConnection(dbURL, user, pass);
}
catch (Exception e) {
	//TODO: log details
	System.out.println("Connection Error:" + e);
	System.out.println("DB URL:" + dbURL);
	System.out.println("DB User:" + user);
	System.out.println("DB Password:" + pass);
	request.setAttribute("javax.servlet.jsp.jspException", e);
%>
<jsp:forward page="ApplicationError.jsp"/>
<%
}
%>
