<%@ include file="/resources/jsp/header.jsp" %>
<script type="text/javascript" src="//code.jquery.com/jquery-1.12.4.js"></script>
<script type="text/javascript" src="https://cdn.datatables.net/1.10.16/js/jquery.dataTables.min.js"></script>
<link rel="stylesheet" type="text/css" href="//cdn.datatables.net/1.10.16/css/jquery.dataTables.css">

		<div id="content-area">
			<div id="page-title-area">
				<table border=0 cellspacing=0 cellpadding=0>
					<tr>
						<td valign='bottom'><span class="heading2"><spring:message code="label.candidates.header"/></span></td>
						<td width=25>&nbsp;</td>
						<td valign='bottom'><a href="${pageContext.servletContext.contextPath}/addNewCandidate.htm" class="free"><spring:message code="label.common.link.addnew"/></a></td>
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
							<td>${candidate.candidateId}</td>
							<td>
								${candidate.firstName} ${candidate.lastName}<br>
								<i class="fa fa-envelope-o" aria-hidden="true"></i>&nbsp;&nbsp; ${candidate.email}<br>
								<i class="fa fa-phone" aria-hidden="true"></i>&nbsp;&nbsp; ${candidate.phone}
							</td>
							<td align='center'>${candidate.gender}</td>
							<td>
								${candidate.streetAddress1}, ${candidate.streetAddress2}, <br>
								${candidate.city} ${candidate.state} ${candidate.zipCode} <br>
								${candidate.country}
							</td>
							<td>${candidate.status}</td>
							<td>${candidate.summary}</td>
						</tr>
					</c:forEach>
				</table>
				<script>
					$(document).ready( function () 
					{
						$.noConflict();
				    	var table = $('#candidatesTable').DataTable({
							"paging": 		true,
							"searching": 	true,
							"ordering": 	true,
							"select": 		true,
							"pageLength": 	10
				    	});
					});
				</script>
 			</div>	
			<div class="post-content-spacing">
				&nbsp;
			</div>
		</div>
<%@ include file="/resources/jsp/footer.jsp" %>
