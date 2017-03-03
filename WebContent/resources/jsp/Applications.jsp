<%@ include file="/resources/jsp/header.jsp" %>
		<div id="content-area">
			<div id="page-title-area">
				<div class="row" style="padding-top: 0px; padding-bottom: 0px;">
					<div class="col-sm-2">
						<span class="heading2"><spring:message code="label.applications.header"/></span>
					</div>
					<div class="col-xs-9">
						<a href="http://localhost:8080/ats/addNewApplication.htm" class="free"><spring:message code="label.common.link.addnew"/></a>
					</div>
					<div class="col-sm-8">
						&nbsp;
					</div>
				</div>
			</div>
			<div id="main-content-box" style="padding-top: 15px; padding-bottom: 0px;">
				<table class="listing" border=1 bordercolor='#EFEEEC' cellpadding=2 cellspacing=0 width=75%>
					<tr bgcolor='#EFEEEC'>
						<td><spring:message code="label.applications.listcolumnheader.positionName"/></td>
						<td><spring:message code="label.applications.listcolumnheader.endClient"/></td>
						<td><spring:message code="label.applications.listcolumnheader.appliedOn"/></td>
						<td><spring:message code="label.applications.listcolumnheader.status"/></td>
					</tr>
					<c:forEach items="${applications}" var="app">
						<tr class="listing">
							<td>${app.positionName}</td>
							<td>${app.endClient}</td>
							<td>${app.applicationDate}</td>
							<td>${app.applicationStatus}</td>
						</tr>
					</c:forEach>
				</table>
				
			</div>	
			<div class="post-content-spacing">
				&nbsp;
			</div>
		</div>
<%@ include file="/resources/jsp/footer.jsp" %>