<%@ include file="/resources/jsp/header.jsp" %>
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<script type="text/javascript" src="resources/scripts/charts.js"></script>
<script type="text/javascript">
      google.charts.load('current', {'packages':['corechart']});

      var chartData = '${chartData}';
	  var obj = JSON.parse(chartData);
	  
	  var dormant = parseInt(obj['DORMANT']);
	  var applied = parseInt(obj['APPLIED']);
	  var interviewsScheduled = parseInt(obj['INTERVIEWS SCHEDULED']);
	  var contactInitiated = parseInt(obj['CONTACT INITIATED']);	

      data.addColumn('number', 'Position Count');
      data.addRows([
        ['Dormant', 23],
        ['Applied', 34],
        ['Interviews Scheduled', 45],
        ['Contact Initiated', 56],
      ]);         
      
      // Set chart options
      var options = {'title':'Application Status',
                   'width':500,
                   'height':400};
	  
      // Instantiate and draw our chart, passing in some options.
      var chart = new google.visualization.PieChart(document.getElementById('chart_div'));
      google.visualization.events.addListener(chart, 'select', selectHandler);
      chart.draw(data, options);
</script>
		<div id="content-area">
			<div id="page-title-area">
				<div class="row" style="padding-top: 0px; padding-bottom: 0px;">
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
			<div id="chart_div" style="width:500; height:400"></div>
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
<%@ include file="/resources/jsp/footer.jsp" %>