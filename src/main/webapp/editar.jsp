<%@page import="com.emergente.modelo.Evento"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
    Evento eve = (Evento) request.getAttribute("eve");
%>    
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1><c:if test="${eve.id==0}">
                NUEVO REGISTRO
            </c:if>
            <c:if test="${eve.id!=0}">
               EDITAR REGISTRO 
            </c:if>
        </h1>
        <form action="MainController" method="post">
            <input type="hidden" name="id" value="${eve.id}">
            <table>

                <tr>
                    <td>Titulo</td>
                    <td><input type="text" name="titulo" value="${eve.titulo}">
                    </td>
                </tr>
                <tr>
                    <td>Expositor</td>
                    <td><input type="text" name="expositor" value="${eve.expositor}"></td>
                </tr>
                <tr>
                    <td>Fecha</td>
                    <td><input type="date" name="fecha" value="${eve.fecha}"></td>
                    
                </tr>
                <tr>
                      <td>Hora</td>
                      <td>
                          <select name="hora" value="${eve.hora}">
                              <option value="8:00-10:00">8:00-10:00</option>
                              <option value="10:00-12:00">10:00-12:00</option>
                              <option value="14:00-16:00">14:00-16:00</option>
                          </select>
                      </td> 
                  </tr>
                <tr>
                    <td>Cupo</td>
                    <td><input type="text" name="cupo" value="${eve.cupo}"></td>
                </tr>
                <tr>
                    <td> </td>
                    <td><input type="submit" value="Enviar" /></td>
                </tr>

            </table>

        </form>
    </body>
</html>

