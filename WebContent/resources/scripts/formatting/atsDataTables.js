candidateTableModule = function(){
	
	var init = $(document).ready( function () 
	{
		$.noConflict();
    	var table = $('#candidatesTable').DataTable({
			"paging": 		true,
			"pagingType":	"full_numbers",
			"searching": 	true,
			"ordering": 	true,
			"select": 		true,
			"pageLength": 	10,
			"columns": [
				{ "width": "5%" },
				{ "width": "20%" },
				{ "width": "5%" },
				{ "width": "20%" },
				{ "width": "10%" },
				{ "width": "40%" }
			]
    	});
	});
	return{init: init};
}() //candidateTableModule ends
