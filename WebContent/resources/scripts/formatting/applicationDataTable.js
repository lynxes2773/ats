/**
 * Script for formatting Applications list 
 */
applicationsTableModule = function(){
	
	var init = $(document).ready( function () 
	{
		$.noConflict();
    	var table = $('#applicationsTable').DataTable({
			"paging": 		true,
			"pagingType":	"full_numbers",
			"searching": 	true,
			"ordering": 	true,
			"select": 		true,
			"pageLength": 	10,
			"columns": [
				{ "width": "10%" },
				{ "width": "15%" },
				{ "width": "20%" },
				{ "width": "15%" },
				{ "width": "15%" },
				{ "width": "10%" },
				{ "width": "15%" }
			]
    	});
	});
	return{init: init};
}() //applicationsTableModule ends
