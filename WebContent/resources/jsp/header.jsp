<%@ page session="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<html margin-top=0 padding=0>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="Cache-Control" content="no-cache"><meta http-equiv="Pragma" content="no-cache">
	<meta http-equiv="Expires" content="0">
	<style>
	html, body
	{
	    margin: 0;
	    padding: 0;
	}
	</style>
	<link rel="stylesheet" href="resources/styles/content.css">
	<link rel="stylesheet" href="resources/styles/layouts.css">
	<link rel="stylesheet" href="resources/styles/untested.css">
	<title><spring:message code="label.browser.title"/></title>
</head>
<body>
	<div id="main">
			<div id="header">
				<p class="heading1"><spring:message code="label.header.title"/></p>
			</div>
			<div id="nav">
				<table cellpadding=0 cellspacing=0>
					<tr>
						<td><a class="headerLinks" href="http://localhost:8080/ats/candidates.htm"><spring:message code="label.header.menu.candidates"/></a></td>
						<td width=10>|</td>
						<td><a class="headerLinks" href="http://localhost:8080/ats/candidateService"><spring:message code="label.header.menu.applications"/></a></td>
					</tr>
				</table>
			</div>
