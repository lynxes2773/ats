<%@ include file="/resources/jsp/header.jsp" %>
<script type="text/javascript" src="//code.jquery.com/jquery-1.12.4.js"></script>
<script type="text/javascript" src="https://cdn.datatables.net/1.10.16/js/jquery.dataTables.min.js"></script>
<link rel="stylesheet" type="text/css" href="//cdn.datatables.net/1.10.16/css/jquery.dataTables.css">
<script type="text/javascript" src="resources/scripts/formatting/candidateDataTables.js"></script>
<script type="text/javascript">
candidateTableModule.init();
</script>
<div id="invisible-placement-container">
		<div id="full-width-box">
			<div id="page-title-area">
				<table border=0 cellspacing=0 cellpadding=0>
					<tr>
						<td valign='center'><span class="heading2"><spring:message code="label.candidates.header"/></span></td>
						<td width=25>&nbsp;</td>
						<td valign='center'>
							<a href="${pageContext.servletContext.contextPath}/addNewCandidate.htm" class="free">
								<i class="fa fa-plus-square-o" aria-hidden="true"></i>&nbsp;
								<spring:message code="label.common.link.addnew"/>
							</a>
						</td>
					</tr>
				</table>
			</div>
			<div id="main-content-box" style="padding-top: 15px; padding-bottom: 0px;">
				<table id="candidatesTable" class="listing" border=1 bordercolor='#EFEEEC' cellpadding=2 cellspacing=0 width=100%>
					<thead>
						<tr bgcolor='#EFEEEC'>
							<td><spring:message code="label.candidates.listcolumnheader.id"/></td>
							<td><spring:message code="label.candidates.listcolumnheader.name"/></td>
							<td align='center'><spring:message code="label.candidates.listcolumnheader.gender"/></td>
							<td><spring:message code="label.candidates.listcolumnheader.address"/></td>
							<td><spring:message code="label.candidates.listcolumnheader.status"/></td>
							<td><spring:message code="label.candidates.listcolumnheader.summary"/></td>
						</tr>
					</thead>
					<tbody>
					<c:forEach items="${candidates}" var="candidate">
						<tr class="listing">
							<td align='center' valign='top'>${candidate.candidateId}</td>
							<td valign='top'>
								${candidate.firstName} ${candidate.lastName}<br>
								<i class="fa fa-envelope-o" aria-hidden="true"></i>&nbsp;&nbsp; ${candidate.email}<br>
								<i class="fa fa-phone" aria-hidden="true"></i>&nbsp;&nbsp; ${candidate.phone}
							</td>
							<td align='center' valign='top'>${candidate.gender}</td>
							<td valign='top'>
								${candidate.streetAddress1}, ${candidate.streetAddress2}, <br>
								${candidate.city} ${candidate.state} ${candidate.zipCode} <br>
								${candidate.country}
							</td>
							<td valign='top'>${candidate.status}</td>
							<td valign='top'>${candidate.summary}</td>
						</tr>
					</c:forEach>
				</table>
 			</div>	
			<div class="post-content-spacing">
				&nbsp;
			</div>
		</div>
</div>
<%@ include file="/resources/jsp/footer.jsp" %>
