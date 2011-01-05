<?php
	
function printHead () {
?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML+RDFa 1.0//EN" "http://www.w3.org/MarkUp/DTD/xhtml-rdfa-1.dtd">
   <html version="XHTML+RDFa 1.0" xmlns="http://www.w3.org/1999/xhtml" xml:lang="en"> 
    <head>  
    <meta name="author" content="Sebastian Germesin" /> 
    <meta name="date" content="' . date('c') . '" />  
    <meta name="keywords" content="pledge vorsatz charity wohltaetigkeit" />  
    <meta name="description" content="This page serves..." />  
    <meta http-equiv="content-type" content="text/html; charset=iso-8859-1" />  
    <meta http-equiv="content-language" content="en" /> 
    <title>Plarity</title> 
    <script src="js/plarity.js" type="text/javascript"></script>  
    <script src="lib/jquery/jquery-1.4.4.min.js" type="text/javascript"></script>  
    <script src="lib/jqueryui/jquery-ui-1.8.7.custom.min.js" type="text/javascript"></script>
    <link rel="stylesheet" href="lib/jqueryui/css/south-street/jquery-ui-1.8.7.custom.css" type="text/css" /> 
    <link rel="stylesheet" href="lib/sexybuttons/sexybuttons.css" type="text/css" />  
    <link rel="stylesheet" href="css/main.css" type="text/css" /> 
  </head>
<?php
}
?>

<?php
function printHeader () {
?>
    <div id="header">
      <div class="wrapper">
   	  	<h1 id="title">Plarity</h1>
	    <span id="login">
		  <form>
		  	<table>
		   		<tr>
		  			<td colspan="3">Login</td>
		  		</tr>
			  	<tr>
			  		<td><input type="email" class="input_text" placeholder="your@email.com" /></td>
			  		<td><input type="password" class="input_text" placeholder="your password" /></td>
			  		<td><button type="button" class="sexybutton sexysimple sexyteal sexysmall">Login</button></td>
			  	</tr>
			  	<tr>
			  		<td><a href="#" onclick="openSignUp();">Sign up for free!</a></td>
			  		<td colspan="2"><a href="#" >Forgot your password?</a></td>
			  	</tr>
			</table>
		  </form>
		</span>
      </div>
    </div>
<?php
}
?>

<?php
function printFooter () {
?>
    <div id="footer">
      <div id="links">
        <a href="about">About</a>&nbsp;&sdot;&nbsp;<a href="blog">Blog</a>&nbsp;&sdot;&nbsp;<a href="legal">Legal</a>&nbsp;&sdot;&nbsp;<a href="impressum">Impressum</a>&nbsp;&sdot;&nbsp;<a href="help">Help</a>&nbsp;&sdot;&nbsp;<a href="jobs">Jobs</a>
      </div>
      <div class="validators">
        <a href="http://validator.w3.org/check?uri=referer">
          <img src="http://www.w3.org/Icons/valid-xhtml-rdfa" alt="Valid XHTML 1.0 Strict" style="height:20px;" />
        </a>
        <a href="http://jigsaw.w3.org/css-validator/check/referer">
          <img style="border:0; height:20px" src="http://jigsaw.w3.org/css-validator/images/vcss" alt="Valid CSS" />
        </a>
      </div>
    </div>
<?php
}
?>