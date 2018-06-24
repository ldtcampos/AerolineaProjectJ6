<%@page contentType="text/html" pageEncoding="UTF-8"%> <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> <!DOCTYPE html>


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title> </head>
    <body style="font-family: arial"> <h3>${mensaje}</h3>
        <table border="1">
            <thead> 
                <tr>
                    <th>Usuario</th> 
                    <th>Nombre</th> 
                    <th>Apellidos</th> 
                    <th>Email</th> 
                    <th>Telefono</th> 
                    <th>Pais</th> 
                    <th>Rol</th> 
                    <th>Editar</th> 
                    <th>Eliminar</th>
                </tr> 
            </thead>
            <tbody>
                <c:forEach items="${usuarios}" var="u"> <tr>
                        <td>${u.idusuario}</td> 
                        <td>${u.nombres}</td> 
                        <td>${u.apellidos}</td> 
                        <td>${u.email}</td> 
                        <td>${u.telefono}</td> 
                        <td>${u.paises.pais}</td> 
                        <td>${u.roles.rol}</td> 
                        <td></td>
                        <td></td> </tr>
                </c:forEach> </tbody>
        </table>
    </body>
</html>