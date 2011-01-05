/**
 * 
 */

function openSignUp() {
	var dialogForm = $("#dialog-form");
	
	if (dialogForm) {
		dialogForm.dialog({
			autoOpen: true,
			height: 300,
			width: 350,
			modal: true,
			buttons: {
				"Sign up!": function() {
					//TODO!
				},
				"Cancel": function() {
					$( this ).dialog( "close" );
				}
			},
			close: function() {
				//TODO!
			}
		});
	}
}