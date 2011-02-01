/**
 * 
 */

BASE_ADDRESS='http://localhost:8888/proxy';

function showSignUp() {
	var loginSpan = $('#login');
	var registerSpan = $('#register');
	
	if (loginSpan) {
		loginSpan.flip({
			direction:'tb',
			speed:500,
		content:registerSpan
	});
		
	}
}

function showLogin() {
	var loginSpan = $('#login');
	
	if (loginSpan) {
		loginSpan.revertFlip();
	}
}


function login(email, pw) {
	
	var loginLoader = $('#login_loader').show();
	var loginButton = $('#login_register_field button').attr('disabled', 'disabled');
	
	$.get( BASE_ADDRESS + '/login?task=getseed&email=' + email)
			.success(
					function(data, textStatus, jqXHR) {
						if (jqXHR.status == 200) {
							var seed = jqXHR.responseText;
							
							var hash = hex_md5(hex_md5(pw) + seed);
							
							$.get( BASE_ADDRESS + '/login?task=validate&email=' + email + '&hash=' + hash).
							success(function (data2, textStatus2, jqXHR2) {
								if (jqXHR.status == 200) {
									var sessionId = jqXHR2.responseText;
									$.cookie("plaritySessionId", sessionId, { path: '/' });
									$.cookie("plarityEmail", email, { path: '/' });
									window.location='index.php';
								} else {
									alert("Abfangen: " + jqXHR.status);
								}
							});
						} else {
							alert("Abfangen: " + jqXHR.status);
						}
					});	
}

function logout (email, sessionId) {
	var logoutLoader = $('#logout_loader').show();
	$.get( BASE_ADDRESS + '/logout?email=' + email + 'id=' + sessionId)
	.success(function (data, textStatus, jqXHR) {
		$.cookie("plaritySessionId", null, { path: '/' });
		$.cookie("plarityEmail", null, { path: '/' });
		window.location='index.php';
	});
}

function register(email) {
	
	$.post('./lib/proxy/proxy.php',
		{
			proxy_url: "http://localhost:8889/login", 
			email: email,
			pwHash: pw
		},
		function (d, t, x) {
			alert(d);
			debugger;
		},
		'text/plain' 
	);
	
}