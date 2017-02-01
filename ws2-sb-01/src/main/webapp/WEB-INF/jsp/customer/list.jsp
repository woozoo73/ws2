<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
	integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

<title>customer</title>
</head>
<body>
	<div class="container">
	
	<h1>Customer</h1>
	
	<table class="table">
		<thead>
			<tr>
				<th>id</th>
				<th>first name</th>
				<th>last name</th>
				<th>type</th>
				<th>creator</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="item" items="${customerList}">
				<tr>
					<td>
						<a href="/customer/${item.id}">${item.id}</a>
					</td>
					<td>${item.firstName}</td>
					<td>${item.lastName}</td>
					<td>${item.type}</td>
					<td>${item.creator.id}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
	<a href="/customer/new" class="btn btn-primary">create new</a>
	
	</div>
</body>
</html>