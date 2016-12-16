<%@ include file="/resources/jsp/header.jsp" %>
	<tr>
		<td>
			<table border=0 cellpadding=0 cellspacing=0 width=100%>
				<tr>
					<td align='left'><p class="heading2" width=150><spring:message code="label.candidates.header"/></p></td>
					<td align='left' width=150><font size=2><a href="http://localhost:8080/ats/addNewCandidate.htm" class="free"><spring:message code="label.candidates.link.add"/></a></font></td>
					<td align='left' width=150><font size=2>&nbsp;</font></td>
					<td align='left' width=150><font size=2>&nbsp;</font></td>
					<td align='left' width=150><font size=2>&nbsp;</font></td>
					<td align='left' width=150><font size=2>&nbsp;</font></td>
					<td align='left' width=150><font size=2>&nbsp;</font></td>
					<td align='left' width=150><font size=2>&nbsp;</font></td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td align="center">
			<table border=1 bordercolor='#D1D0CE' cellpadding=2 cellspacing=0 width=75%>
					<tr bgcolor='#F0F8FF'>
						<td><spring:message code="label.candidates.listcolumnheader.name"/></td>
						<td><spring:message code="label.candidates.listcolumnheader.gender"/></td>
						<td><spring:message code="label.candidates.listcolumnheader.email"/></td>
						<td><spring:message code="label.candidates.listcolumnheader.id"/></td>
					</tr>
				<c:forEach items="${candidates}" var="candidate">
					<tr>
						<td>${candidate.firstName} ${candidate.lastName}</td>
						<td align='center'>${candidate.gender}</td>
						<td>${candidate.email}</td>
						<td>${candidate.candidateId}</td>
					</tr>
				</c:forEach>
			</table>
		</td>
	</tr>
<%@ include file="/resources/jsp/footer.jsp" %>