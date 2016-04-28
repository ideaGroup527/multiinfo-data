//<!-- Begin
///全选
function checkAll(name) {
	var box = document.getElementsByName(name);
	for (var j = 0; j < box.length; j++) 
	{
		if(!box[j].checked)
		{
			 box[j].checked = true;   
		}
	}
}

///全不选
function uncheckAll(name) {
	var box = document.getElementsByName(name);
	for (var j = 0; j < box.length; j++) 
	{
		if(box[j].checked)
		{
			 box[j].checked = false;   
		}
	}
}

///反选
function switchAll(name) {
	var box = document.getElementsByName(name);
	for (var j = 0; j < box.length; j++) 
	{
		box[j].checked = !box[j].checked; 

	}
}
//  End -->