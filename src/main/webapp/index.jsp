<%-- 
    Document   : index
    Created on : Dec 8, 2019, 1:54:40 PM
    Author     : XV
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JSP Page</title>
</head>
<body>
<h1>Hello World!</h1>
<a href="<c:url value="customer/list" />">List of customer</a>
<br>
<a href="admin/customer/list">List admin of customer</a>
</body>
</html>
