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

<title>user</title>
</head>
<body>
	<div class="container">
	
	<h1>User</h1>
	
	<form method="post">
	<table class="table">
		<thead>
			<tr>
				<th>key</th>
				<th>value</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<th>id</th>
				<td>
					<input type="text" name="id" />
				</td>
			</tr>
			<tr>
				<th>password</th>
				<td>
					<input type="text" name="password" />
				</td>
			</tr>
			<tr>
				<th>repeat password</th>
				<td>
					<input type="text" name="repeatPassword" />
				</td>
			</tr>
		</tbody>
	</table>
	
	<input type="submit" class="btn btn-primary" />
	</form>
	
	</div>
</body>
</html>