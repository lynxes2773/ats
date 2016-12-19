<%@page import="example.hibernate.Candidate"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%@ include file="/resources/jsp/header.jsp" %>
	<tr>
		<td>
			<table border=0 cellpadding=0 cellspacing=0 width=100%>
				<tr>
					<td align='left'><p class="heading2" width=150>Add Candidate</p></td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td align="center">
			<sf:form method="POST" commandName="candidate" action="http://localhost:8080/ats/addSubmittedCandidate.htm">
			<table border=0 bordercolor='#D1D0CE' cellpadding=2 cellspacing=2 width=75%>
				<tr><td colspan=2><sf:errors path="firstName" cssClass="error" /></td></tr>
				<tr><td width=25%>First Name</td><td width=75%><sf:input path="firstName" size='20'/></td></tr>
				<tr><td colspan=2><sf:errors path="lastName" cssClass="error" /></td></tr>
				<tr><td>Last Name</td><td><sf:input path="lastName" size='20' /></td></tr>
				<tr><td colspan=2><sf:errors path="email" cssClass="error" /></td></tr>
				<tr><td>Email Address</td><td><sf:input path="email" size='50' /></td></tr>
				<tr>
					<td>Gender</td>
					<td>
						<sf:select path="gender">
							<sf:option value="M">Male</sf:option>
							<sf:option value="F">Female</sf:option>
						</sf:select>
					</td>
				</tr>
				<tr><td><input type="submit" value="Submit Entry"/></td></tr>
			</table>
			</sf:form>
		</td>
		
	</tr>
<%@ include file="/resources/jsp/footer.jsp" %>