<%@ page language="java" %>
<%@ page import="java.util.*, gov.usa.orderform.FieldType" %>

<p>
<%
Vector errors = (Vector) session.getAttribute("errorMessage");

if(errors != null) {
    Iterator it = errors.iterator ();
    while (it.hasNext ()) {
        FieldType ft = (FieldType) it.next ();
%>
       Su <b><%=ft.getLabel() %></b> <%=ft.getError() %> <br />
<%
    }
    session.removeAttribute("errorMessage");
} else {
    String error = (String) session.getAttribute("errorString");
    
    if(error != null) {
%>
        <%=error %>   <!-- print the error message -->
<%
        session.removeAttribute("errorString");
    }
}
%>
</p>

<h3>Por favor utilice el botón Atrás de su navegador para volver a ingresar su orden.</h3>
