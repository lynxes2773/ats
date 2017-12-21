<%@ include file="/resources/jsp/header.jsp" %>
<!-- Tag library for formatting dates -->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!-- Scripts for loading charts -->
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.1.4/Chart.min.js"></script>
<script type="text/javascript" src="resources/scripts/charts/statuscounts.js"></script>
<script type="text/javascript">
var chartData = '${chartData}';
pieChartModule.init(chartData);
</script>

<!-- Scripts for formatting Applications list -->
<script type="text/javascript" src="//code.jquery.com/jquery-1.12.4.js"></script>
<script type="text/javascript" src="https://cdn.datatables.net/1.10.16/js/jquery.dataTables.min.js"></script>
<link rel="stylesheet" type="text/css" href="//cdn.datatables.net/1.10.16/css/jquery.dataTables.css">
<script type="text/javascript" src="resources/scripts/formatting/applicationDataTable.js"></script>
<script type="text/javascript">
applicationsTableModule.init();
</script>

<div id="invisible-placement-container">
	<div id="left-column-wide-box" class="col-lg-9">
			<div id="page-title-area">
				<table border=0 cellspacing=0 cellpadding=0>
					<tr>
						<span class="heading2"><spring:message code="label.applications.header"/></span>
					</tr>
					<tr>
						&nbsp;&nbsp;
					</tr>
					<tr>
						<a href="${pageContext.servletContext.contextPath}/addNewApplication.htm" class="free"><spring:message code="label.common.link.addnew"/></a>
					</tr>
				</table>			
			</div>
			
			<div id="main-content-box" style="padding-top: 15px; padding-bottom: 0px;">
				<table id="applicationsTable" class="listing" border=1 bordercolor='#EFEEEC' cellpadding=2 cellspacing=2 width=100%>
					<thead>
					<tr bgcolor='#EFEEEC'>
						<td><spring:message code="label.applications.listcolumnheader.positionId"/></td>
						<td><spring:message code="label.applications.listcolumnheader.positionName"/></td>
						<td><spring:message code="label.applications.listcolumnheader.endClient"/></td>
						<td><spring:message code="label.applications.listcolumnheader.appliedOn"/></td>
						<td><spring:message code="label.applications.listcolumnheader.location"/></td>
						<td><spring:message code="label.applications.listcolumnheader.positionType"/></td>
						<td><spring:message code="label.applications.listcolumnheader.status"/></td>
					</tr>
					</thead>
					<tbody>
					<c:forEach items="${applications}" var="app">
						<tr class="listing">
							<td align='center' valign='top'>${app.id}</td>
							<td valign='top'><a href="${pageContext.servletContext.contextPath}/showApplication.htm?id=${app.id}">${app.positionName}</a></td>
							<td valign='top'>${app.endClient}</td>
							<td valign='top'><fmt:formatDate value="${app.applicationDate}" pattern="MMMMM d, yyyy" /></td>
							<td valign='top'>${app.location}</td>
							<td valign='top'>${app.positionType}</td>
							<td valign='top'>${app.applicationStatus}</td>
						</tr>
					</c:forEach>
					</tbody>
				</table>
				
			</div>	
<!-- 		<div class="post-content-spacing">
				&nbsp;
			</div>
 -->
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
				<div id="pieChartDiv" width="300" height="250"></div>
			</div>
		</div>
		<div id="card-sized-box">
			<div id="card-title-area">
				<div class="row">
					<div class="col-sm-9">
						<span class="heading3"><spring:message code="label.applications.card_header.status_counts"/></span>
					</div>
				</div>
			</div>
			<div id="card-content-area">			
				<canvas id="barChart" width="275" height="250"></canvas>
			</div>
		</div>	
	</div>
</div>
<script type="text/javascript" src="resources/scripts/charts/barchart.js"></script>
<script type="text/javascript">
barChartModule.init(chartData);  
</script>

<%@ include file="/resources/jsp/footer.jsp" %>

