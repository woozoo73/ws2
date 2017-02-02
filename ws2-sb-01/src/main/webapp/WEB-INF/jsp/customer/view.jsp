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

<script type="text/javascript">
	function updateCustomer() {
		var form = document.getElementById('customerForm');
		form['_method'].value = 'put';
		form.submit();
	}
	
	function deleteCustomer() {
		var form = document.getElementById('customerForm');
		form['_method'].value = 'delete';
		form.submit();
	}
	
	function createEmail() {
		var form = document.getElementById('emailForm');
		form.submit();
	}
	
	function deleteEmail(address) {
		var form = document.getElementById('emailForm');
		form['_method'].value = 'delete';
		form['action'] = '/customer/${customer.id}/email/' + address;
		form.submit();
	}
</script>

<title>customer</title>
</head>
<body>
	<div class="container">
	
	<h1>Customer</h1>
	
	<form:form commandName="customer" id="customerForm">
	<input type="hidden" name="_method" value="" />
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
	
	<a href="javascript:updateCustomer();" class="btn btn-primary">update</a>
	<a href="javascript:deleteCustomer();" class="btn btn-danger">delete</a>
	<a href="/customer" class="btn btn-info">cancel</a>
	</form:form>
	
	<h1>Email</h1>
	
	<form id="emailForm" method="post" action="/customer/${customer.id}/email">
	<input type="hidden" name="_method" value="" />
	<table class="table">
		<thead>
			<tr>
				<th>address</th>
				<th>type</th>
				<th>control</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="item" items="${customer.emailList}">
			<tr>
				<td>${item.address}</td>
				<td>${item.type}</td>
				<td>
					<a href="javascript:deleteEmail('${item.address}');" class="btn btn-danger">delete</a>
				</td>
			</tr>
			</c:forEach>
			<tr>
				<td><input type="text" name="address" /></td>
				<td><input type="text" name="type" /></td>
				<td>
					<a href="javascript:createEmail();" class="btn btn-success">create</a>
				</td>
			</tr>
		</tbody>
	</table>
	</form>
	
	</div>
</body>
</html>