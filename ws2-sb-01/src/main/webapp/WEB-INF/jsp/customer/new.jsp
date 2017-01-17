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
	
	<form method="post">
	<table>
		<thead>
			<tr>
				<th>column</th>
				<th>value</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<th>first name</th>
				<td><input type="text" name="firstName" /></td>
			</tr>
			<tr>
				<th>last name</th>
				<td><input type="text" name="lastName" /></td>
			</tr>
		</tbody>
	</table>
	
	<input type="submit" />
	
	</form>
</body>
</html>
