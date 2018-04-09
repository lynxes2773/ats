fileUploadModule = function(){
	
	var init = function(myFile, elementId)
	{
	   var file = myFile.files[0];  
	   var filename = file.name;
	   document.getElementById(elementId).value=filename;
	};
	 
	 return{init: init};
}();

removeAttachmentLinkModule = function(){
	
	var init = function(rowNumber, action)
	{
		if(action=='0')
		{
			document.getElementById('removeColumnRow'+rowNumber).style.visibility='hidden';
		}
		else
		{
			document.getElementById('removeColumnRow'+rowNumber).style.visibility='visible';
		}	
		
	};
	 
	 return{init: init};
}();



