var chartData = '${chartData}';
var obj = JSON.parse(chartData);  

var dormant = parseInt(obj['DORMANT']);
var applied = parseInt(obj['APPLIED']);
var interviewsScheduled = parseInt(obj['INTERVIEWS SCHEDULED']);
var contactInitiated = parseInt(obj['CONTACT INITIATED']);	 

	google.charts.load('current', {'packages':['corechart']});
	google.charts.setOnLoadCallback(drawChart);
	 
	 function drawChart() 
	 { 
		 var data = google.visualization.arrayToDataTable([
		 ['Status', 'Positions'],
		 ['Dormant', dormant],
		 ['Applied', applied],
		 ['Interviews Scheduled', interviewsScheduled],
		 ['Contact Initiated', contactInitiated]    
	]);
	     // Set chart options
	     var options = {'title':'Application Status', 'width':400, 'height':300};
		  
	     // Instantiate and draw our chart, passing in some options.
	     var chart = new google.visualization.PieChart(document.getElementById('chartDiv'));
	     chart.draw(data, options);
	 }
