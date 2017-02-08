<%@ include file="/resources/jsp/header.jsp" %>
		<div id="content-area">
			<div id="page-title-area">
				<table border=0 cellspacing=0 cellpadding=0>
					<tr>
						<td valign='bottom'><span class="heading2"><spring:message code="label.candidates.header"/></span></td>
						<td width=25>&nbsp;</td>
						<td valign='bottom'><a href="http://localhost:8080/ats/addNewCandidate.htm" class="free"><spring:message code="label.common.link.addnew"/></a></td>
					</tr>
				</table>
			</div>
			<div class="main-content-box">
				<table class="listing" border=1 bordercolor='#EFEEEC' cellpadding=2 cellspacing=0 width=75%>
						<tr bgcolor='#EFEEEC'>
							<td><spring:message code="label.candidates.listcolumnheader.name"/></td>
							<td align='center'><spring:message code="label.candidates.listcolumnheader.gender"/></td>
							<td><spring:message code="label.candidates.listcolumnheader.email"/></td>
							<td><spring:message code="label.candidates.listcolumnheader.id"/></td>
						</tr>
					<c:forEach items="${candidates}" var="candidate">
						<tr class="listing">
							<td>${candidate.firstName} ${candidate.lastName}</td>
							<td align='center'>${candidate.gender}</td>
							<td>${candidate.email}</td>
							<td>${candidate.candidateId}</td>
						</tr>
					</c:forEach>
				</table>
			</div>	
			<div class="post-content-spacing">
				&nbsp;
			</div>
		</div>
<%@ include file="/resources/jsp/footer.jsp" %>