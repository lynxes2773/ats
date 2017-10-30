barChartModule = function(){

	var init = function(param)
	{
		window.alert('inside init(): '+param);
		var obj = JSON.parse(param);
		var dormant = parseInt(obj['Dormant']);
		var applied = parseInt(obj['Applied']);
		var interviewsScheduled = parseInt(obj['Interviews Scheduled']);
		var contactInitiated = parseInt(obj['Contact Initiated']);	 
		var rejected = parseInt(obj['Rejected']);

		window.alert('dormant: '+dormant);
		window.alert('applied: '+applied);
		window.alert('interviewsScheduled: '+interviewsScheduled);
		window.alert('contactInitiated: '+contactInitiated);
		window.alert('rejected: '+rejected);
		
		
		var ctx = document.getElementById('barChart').getContext('2d');

		var statusCountChart = new Chart(ctx, 
		{
			  type: 'bar',
			  data: 
			  {
			    labels: ['Dormant', 'Applied', 'Scheduled', 'Initiated', 'Rejected'],
			    datasets: [{
			      label: 'Application Status',
			      backgroundColor: ["#3e95cd", "#8e5ea2","#3cba9f","#e8c3b9","#c45850"],
			      data: [dormant, applied, interviewsScheduled, contactInitiated, rejected]
			    }]
			  },
			  options: 
			  {
		        legend: { display: true },
		        title: 
		        {
		          display: true,
		          text: 'Application Status Counts'
		        }
		      }
		});
	};
	
	return{init: init};
	
}(); // barChartModule ends
