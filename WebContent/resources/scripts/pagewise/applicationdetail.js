fileUploadModule = function(){
	
	var init = function(myFile, elementId)
	{
	   var file = myFile.files[0];  
	   var filename = file.name;
	   document.getElementById(elementId).value=filename;
	};
	 
	 return{init: init};
}();