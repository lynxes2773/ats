var obj;  

var dormant;
var applied;
var interviewsScheduled;
var contactInitiated;	 
var rejected;

chartDataModule = function()
{
	
	var init = function(chartData)
	{
		obj = JSON.parse(chartData);  

		dormant = parseInt(obj['DORMANT']);
		applied = parseInt(obj['APPLIED']);
		interviewsScheduled = parseInt(obj['INTERVIEWS SCHEDULED']);
		contactInitiated = parseInt(obj['CONTACT INITIATED']);	 
		rejected = parseInt(obj['REJECTED']);
	};
	
	
}();