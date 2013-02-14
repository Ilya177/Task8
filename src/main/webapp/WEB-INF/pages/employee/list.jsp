<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://epam.com/tags-pagination" prefix="pagination"%>
<!DOCTYPE html>
<html>
<head>
<title>Task 8</title>
<link rel="stylesheet" href="css/style.css" type="text/css" />
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/script.js"></script>
</head>
<body>
	<bean:define id="numberItemsPerPage" name="employeeForm"
		property="numberItemsPerPage" />

	<bean:define id="currentPageNumber" name="employeeForm"
		property="pageNumber" />

	<bean:define id="totalCountEmployees" name="employeeForm"
		property="totalCountEmployees" />

	<div align="center">
		<pagination:pager url="Employee.do" pageId="pageNumber"
			currentPageNumber="${currentPageNumber}"
			range="${numberItemsPerPage}" results="${totalCountEmployees}" />
	</div>
	<br />

	<table>
		<tr>
			<html:form styleId="countItemsForm" action="/EmployeeList">
				<html:hidden property="pageNumber" value="1" />
				<td align="right">Number of items per page:</td>
				<td><html:text property="numberItemsPerPage" /></td>
				<td><html:submit>Submit</html:submit></td>
			</html:form>
		</tr>
		<tr>
			<html:form styleId="pageNumberForm" action="/EmployeeList">
				<td align="right">Go to page:</td>
				<td><html:text property="pageNumber" /></td>
				<td><html:submit>Submit</html:submit></td>
			</html:form>
		</tr>
	</table>

	<bean:size id="size" name="employeeForm" property="employees" />
	<logic:equal name="size" value="0">
		<h2 align="center">Employees not found</h2>
	</logic:equal>

	<logic:greaterThan name="size" value="0">
		<table id="employees" border="1">
			<tr>
				<th>#</th>
				<th>First Name</th>
				<th>Last Name</th>
				<th>Address</th>
				<th>Jobs</th>
			</tr>
			<logic:iterate id="employee" name="employeeForm" property="employees">
				<tr>
					<td align="center">${employee.employeeId}</td>
					<td>${employee.firstName}</td>
					<td>${employee.lastName}</td>
					<td><c:out value="${employee.address.city.cityName}, " /> <c:out
							value="${employee.address.city.country.countryName}, " /> <c:out
							value="${employee.address.streetName}, " /> <c:out
							value="${employee.address.houseNumber} -" /> <c:out
							value="${employee.address.officeNumber}" /></td>
					<td><logic:iterate id="job" name="employee" property="jobs">
							<c:out value="Company: ${job.office.company.companyName}" />
							<br />
							<c:out value="Address: ${job.office.address.city.cityName}, " />
							<c:out value="${job.office.address.city.country.countryName}, " />
							<c:out value="${job.office.address.streetName}, " />
							<c:out value="${job.office.address.houseNumber} -" />
							<c:out value="${job.office.address.officeNumber}" />
							<br />
							<c:out value="Employee count: ${job.office.employeeCount} " />
							<c:out value="Position: ${job.position.positionName}" />
							<br />
							<br />
						</logic:iterate></td>
				</tr>
			</logic:iterate>
		</table>
	</logic:greaterThan>

	<br />
	<div align="center">
		<pagination:pager url="Employee.do" pageId="pageNumber"
			currentPageNumber="${currentPageNumber}"
			range="${numberItemsPerPage}" results="${totalCountEmployees}" />
	</div>
</body>
</html>