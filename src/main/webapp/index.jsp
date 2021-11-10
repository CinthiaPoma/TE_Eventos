<%@page import="com.emergente.modelo.Evento"%>
<%@page import="java.util.List"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    List<Evento> lista = (List<Evento>)request.getAttribute("lista");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
    <tr>
        <th>
            SEGUNDO PARCIAL TEM-742 <br>
            NOMBRE : CINTHIA POMA MAMANI<br>
            CI: 9933081 LP
        </th>
    </tr>
        <h1>REGISTRO DE SEMINARIOS </h1>
        <p>
            <a href="MainController?op=nuevo">Nuevo</a>
        </p>
        <table border="1">
            <tr>
                    <th>Id</th>
                    <th>Titulo</th>
                    <th>Expositor</th>
                    <th>Fecha</th>
                    <th>Hora</th>
                    <th>Cupo</th>
                    <th></th>
                    <th></th>
                </tr>
                <c:forEach var="item" items="${lista}">
                    <tr>
                    <td>${item.id}</td>
                    <td>${item.titulo}</td>
                    <td>${item.expositor}</td>
                    <td>${item.fecha}</td>
                    <td>${item.hora}</td>
                    <td>${item.cupo}</td>
                    <td><a href="MainController?op=Editar&id=${item.id}">Editar</a></td>
                    <td><a href="MainController?op=Eliminar&id=${item.id}"
                           onclick="return(confirm('Esta Seguro de eliminar?'))">Eliminar</a></td>
                </tr>
                </c:forEach>
                
        </table>

</html>
