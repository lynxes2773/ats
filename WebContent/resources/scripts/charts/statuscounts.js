pieChartModule = function(){
	var obj;  
	
	var dormant;
	var applied;
	var interviewsScheduled;
	var contactInitiated;	 
	var rejected;
	
	var init = function(chartData){
		obj = JSON.parse(chartData);  
		
		dormant = parseInt(obj['Dormant']);
		applied = parseInt(obj['Applied']);
		interviewsScheduled = parseInt(obj['Interviews Scheduled']);
		contactInitiated = parseInt(obj['Contact Initiated']);	 
		rejected = parseInt(obj['Rejected']);

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
	     var options = {'legend':'right', 'width':275, 'height':220};
	     // Instantiate and draw our chart, passing in some options.
	     var chart = new google.visualization.PieChart(document.getElementById('pieChartDiv'));
		 
	     chart.draw(data, options);
	 }
	 
	 return{init: init};
}();
