<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
    <title>Affiche Table</title>
</head>
<sql:setDataSource var="conn" driver="${initParam.driver}" url="${initParam.url}" user="${initParam.login}" password="${initParam.mdp}"/>
<sql:query var="rs" dataSource="${conn}">select * from avion</sql:query>
<body>
<table border="1">
    <c:forEach var="ligne" items="${rs.ligs}">
<%--        <tr>--%>
<%--            <td><c:out value="${ligne.ano}"/></td>--%>
<%--            <td><c:out value="${ligne.type}"/></td>--%>
<%--            <td><c:out value="${ligne.places}"/></td>--%>
<%--            <td><c:out value="${ligne.compagnie}"/></td>--%>
<%--        </tr>--%>
        <c:out value="${ligne}"/>
    </c:forEach>
</table>
</body>
</html>
