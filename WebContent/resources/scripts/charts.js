pieChartModule = function(){
	var obj;  
	
	var dormant;
	var applied;
	var interviewsScheduled;
	var contactInitiated;	 
	var rejected;
	
	var init = function(chartData){
		obj = JSON.parse(chartData);  
		
		dormant = parseInt(obj['DORMANT']);
		applied = parseInt(obj['APPLIED']);
		interviewsScheduled = parseInt(obj['INTERVIEWS SCHEDULED']);
		contactInitiated = parseInt(obj['CONTACT INITIATED']);	 
		rejected = parseInt(obj['REJECTED']);

		google.charts.load('current', {'packages':['corechart']});
		google.charts.setOnLoadCallback(drawChart);
	};
	
	
	 function drawChart() 
	 { 
		 var data = google.visualization.arrayToDataTable([
		 ['Status', 'Positions'],
		 ['Dormant', dormant],
		 ['Applied', applied],
		 ['Interviews Scheduled', interviewsScheduled],
		 ['Contact Initiated', contactInitiated],    
		 ['Rejected', rejected]
		]);

		 // Set chart options
	     var options = {'legend':'right', 'width':325, 'height':250};
	     // Instantiate and draw our chart, passing in some options.
	     var chart = new google.visualization.PieChart(document.getElementById('chartDiv'));
		 
	     chart.draw(data, options);
	 }
	 
	 return{init: init};
}();
