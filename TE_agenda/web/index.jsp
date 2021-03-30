

<%@page import="java.util.ArrayList"%>
<%@page import="com.emergentes.modelo.Nota"%>
<%
    if (session.getAttribute("listaage") == null) {
        ArrayList<Nota> lis = new ArrayList<Nota>();
        session.setAttribute("listaage", lis);
    }
    ArrayList<Nota> lista = (ArrayList<Nota>) session.getAttribute("listaage");
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Agenda personal</h1>
        <a href="ProcesaServlet?op=nuevo" method="Post">Nueva actividad</a>
        <table border="1" cellspacing="0">                
            <tr>
                <th>Id</th>
                <th>Hora</th>
                <th>Actividad</th>
                <th>Estado</th>
                <th colspan="2">Opcion</th>
               
            </tr>
            <%
                if (lista != null) {
                    for (Nota nota : lista) {
            %>
            <tr>
                <td><%=nota.getId()%></td>
                <td><%=nota.getHora()%></td>
                <td><%=nota.getActividad()%></td>
                <td><%=nota.getCompletado()%></td>
                <td><a href="ProcesaServlet?op=editar&id=<%=nota.getId() %>">Editar</a></td>
                <td><a href="ProcesaServlet?op=eliminar&id=<%=nota.getId() %>" onclick="return(confirm('Esta seguro de eliminar ?'))">Eliminar</a></td>
              
            </tr>
            <%
                    }
                }
            %>
        </table>

    </body>
</html>