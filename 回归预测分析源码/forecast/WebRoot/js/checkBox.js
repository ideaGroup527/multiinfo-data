//<!-- Begin
///ȫѡ
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

///ȫ��ѡ
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

///��ѡ
function switchAll(name) {
	var box = document.getElementsByName(name);
	for (var j = 0; j < box.length; j++) 
	{
		box[j].checked = !box[j].checked; 

	}
}
//  End -->