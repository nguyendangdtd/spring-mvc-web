<%-- 
    Document   : list-customer
    Created on : Dec 8, 2019, 12:27:02 PM
    Author     : XV
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="tg" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="<c:url value="/resources/css/bootstrap.min.css" />"
	      rel="stylesheet">
	<script src="<c:url value="/resources/js/jquery-3.4.1.min.js" />"></script>
	<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
        <title>JSP Page</title>
    </head>
    <body>
	<div class="container">
	    <div class="col-md-offset-1 col-md-10">
		<h2>CRM - Customer Relationship Manager</h2>
		<!-- Display logout button only for user is admin-->
		<sec:authorize access="hasRole('ROLE_ADMIN')">
			<!-- For login user -->
			<form action="<c:url value='/j_spring_security_logout' />" method="post" id="logoutForm">
					<input type="hidden" name="${_csrf.parameterName}"
							value="${_csrf.token}" />
			</form>
			<script>
					function formSubmit() {
							document.getElementById("logoutForm").submit();
					}
			</script>

			<c:if test="${pageContext.request.userPrincipal.name != null}">
					<h2>
							User : ${pageContext.request.userPrincipal.name} | <a
									href="javascript:formSubmit()"> Logout</a>
					</h2>
			</c:if>
		</sec:authorize>
		<hr />
		<input type="button" value="Add Customer"
		       onclick="window.location.href = 'showForm'; return false;"
		       class="btn btn-primary" />
		<br/><br/>
		<div class="panel panel-info">
		    <div class="panel-heading">
			<div class="panel-title">Customer List</div>
		    </div>
		    <div class="panel-body">
			<table class="table table-striped table-bordered">
			    <tr>
				<th>First Name</th>
				<th>Last Name</th>
				<th>Email</th>
				<th>Action</th>
			    </tr>

			    <!-- loop over and print our customers -->
			    <c:forEach var="tempCustomer" items="${pagedListHolder.pageList}">

				<!-- construct an "update" link with customer id -->
				<c:url var="updateLink" value="/customer/updateForm">
				    <c:param name="customerId" value="${tempCustomer.id}" />
				</c:url>

				<!-- construct an "delete" link with customer id -->
				<c:url var="deleteLink" value="/customer/delete">
				    <c:param name="customerId" value="${tempCustomer.id}" />
				</c:url>

				<tr>
				    <td>${tempCustomer.firstName}</td>
				    <td>${tempCustomer.lastName}</td>
				    <td>${tempCustomer.email}</td>

				    <td>
					<!-- display the update link --> 
					<a href="<c:url value="/customer/updateForm?customerId=${tempCustomer.id}" />">Update</a>|
					<a href="${deleteLink}"	onclick="if (!(confirm('Are you sure you want to delete this customer?')))
					    return false">Delete</a>
				    </td>
				</tr>
			    </c:forEach>
			</table>
			<c:url value="/customer/list" var="pagedLink">
					<c:param name="p" value="~" />
			</c:url>
			<tg:paging pagedListHolder="${pagedListHolder}"	pagedLink="${pagedLink}" />
		    </div>
		</div>
	    </div>
	</div>
    </body>
</html>
