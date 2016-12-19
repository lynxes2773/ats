<%@ page session="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<html margin-top=0 padding=0>
<head>
	<style>
		html, body
		{
		    margin: 0;
		    padding: 0;
		}
	</style>
	<link rel="stylesheet" href="ats.css">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title><spring:message code="label.browser.title"/></title>
</head>
<body>
<table width=60% align='center' border=0 cellpadding=4 cellspacing=0 bgcolor="#FFFFFF">
	<tr id="pageHeaderRow">
		<td>
			<p class="heading1"><spring:message code="label.header.title"/></p> 
		</td>
	</tr>
	<tr bgcolor='#F0F8FF'>
		<td>
			<table cellpadding=0 cellspacing=4>
				<tr>
					<td><a class="headerLinks" href="http://localhost:8080/ats/candidates.htm"><spring:message code="label.header.menu.candidates"/></a></td>
					<td width=10>|</td>
					<td><a class="headerLinks" href="http://localhost:8080/ats/candidateService"><spring:message code="label.header.menu.applications"/></a></td>
				</tr>
			</table>
		</td>
	</tr>
	