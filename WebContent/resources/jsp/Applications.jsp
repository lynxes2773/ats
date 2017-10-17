<%@ include file="/resources/jsp/header.jsp" %>
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<script type="text/javascript" src="resources/scripts/charts.js"></script>
<script type="text/javascript">
pieChartModule.init('${chartData}');
</script>
<div id="invisible-placement-container">
	<div id="left-column-wide-box" class="col-lg-9">
			<div id="page-title-area">
				<div class="row" style="padding-top: 0px; padding-bottom: 0px; width:100%;">
					<div class="col-sm-2">
						<span class="heading2"><spring:message code="label.applications.header"/></span>
					</div>
					<div class="col-xs-9">
						<a href="${pageContext.servletContext.contextPath}/addNewApplication.htm" class="free"><spring:message code="label.common.link.addnew"/></a>
					</div>
					<div class="col-sm-8">
						&nbsp;
					</div>
				</div>
			</div>
			
			<div id="main-content-box" style="padding-top: 15px; padding-bottom: 0px;">
				<table class="listing" border=1 bordercolor='#EFEEEC' cellpadding=2 cellspacing=2 width=75%>
					<tr bgcolor='#EFEEEC'>
						<td><spring:message code="label.applications.listcolumnheader.positionName"/></td>
						<td><spring:message code="label.applications.listcolumnheader.endClient"/></td>
						<td><spring:message code="label.applications.listcolumnheader.appliedOn"/></td>
						<td><spring:message code="label.applications.listcolumnheader.status"/></td>
					</tr>
					<c:forEach items="${applications}" var="app">
						<tr class="listing">
							<td><a href="${pageContext.servletContext.contextPath}/showApplication.htm?id=${app.id}">${app.positionName}</a></td>
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
	<div id="right-column-narrow-box" class="col-lg-3">
		<div id="card-sized-box">
			<div id="card-title-area">
				<div class="row">
					<div class="col-sm-9">
						<span class="heading3"><spring:message code="label.applications.card_header.status_counts"/></span>
					</div>
				</div>
			</div>
			<div id="card-content-area">			
				<div id="chartDiv"></div>
			</div>
		</div>
	</div>
</div>
<%@ include file="/resources/jsp/footer.jsp" %>

