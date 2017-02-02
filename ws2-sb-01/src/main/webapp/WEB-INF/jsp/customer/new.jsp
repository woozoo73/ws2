<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

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
	
	<form:form commandName="customer">
	<table class="table">
		<thead>
			<tr>
				<th>key</th>
				<th>value</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<th>first name</th>
				<td>
					<form:input path="firstName" />
					<form:errors path="firstName" />
				</td>
			</tr>
			<tr>
				<th>last name</th>
				<td>
					<form:input path="lastName" />
					<form:errors path="lastName" />
				</td>
			</tr>
			<tr>
				<th>type</th>
				<td>
					<form:select path="type">
						<option />
						<form:options items="${typeList}" itemLabel="value" itemValue="value" />
					</form:select>
					<form:errors path="type" />
				</td>
			</tr>
		</tbody>
	</table>
	
	<input type="submit" class="btn btn-success" value="create" />
	</form:form>
	
	</div>
</body>
</html>