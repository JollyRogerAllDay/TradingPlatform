function mouseOverButton(button)
{
	document.getElementById(button.id).style.background = "#b8c9f9";
}

function mouseOutButton(button)
{
	document.getElementById(button.id).style.background = "#7193f3";
}

function highlight(field)
{
	field.focus();
	field.select();
}