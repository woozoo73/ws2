<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>customer</title>
</head>
<body>
	<h1>Customer list</h1>
	
	<table>
		<thead>
			<tr>
				<th>id</th>
				<th>first name</th>
				<th>last name</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="item" items="${customerList}">
			<tr>
				<td>${item.id}</td>
				<td>${item.firstName}</td>
				<td>${item.lastName}</td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
	
	<a href="/customer/new">create new customer</a>
</body>
</html>
