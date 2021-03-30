<%@page import="com.emergentes.modelo.Nota"%>
<%
    Nota reg = (Nota) request.getAttribute("minota");
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Registro de Actividad</h1>
        <form action="ProcesaServlet" method="Post">
            <table>
                <tr>
                    <td>ID</td>
                    <td><input type="text" name="id" value="<%=reg.getId()%>"></td>
                </tr>
                <tr>
                    <td>Hora</td>
                    <td><input type="text" name="hora" value="<%=reg.getHora()%>" ></td>
                </tr>
                <tr>
                    <td>Actividad</td>
                    <td><input type="text" name="actividad" value="<%=reg.getActividad()%>"></td>
                </tr>
                <tr>
                    <td>Estado</td>
                    <td><input type="text" name="completado" value="<%=reg.getCompletado()%>"></td>
                </tr>
                <tr>
                    <td></td>
                    <td><input type="submit" value="Enviar"></td>
                </tr>

            </table>

        </form>
    </body>
</html>