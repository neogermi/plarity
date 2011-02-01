<?php
	
function printHead () {
?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML+RDFa 1.0//EN" "http://www.w3.org/MarkUp/DTD/xhtml-rdfa-1.dtd">
   <html version="XHTML+RDFa 1.0" xmlns="http://www.w3.org/1999/xhtml" xml:lang="en"> 
    <head>  
    <meta name="author" content="Sebastian Germesin, Jochen Frey" /> 
    <meta name="date" content="' . date('c') . '" />  
    <meta name="keywords" content="pledge vorsatz charity wohltaetigkeit" />  
    <meta name="description" content="This page serves..." />  
    <meta http-equiv="content-type" content="text/html; charset=iso-8859-1" />  
    <meta http-equiv="content-language" content="en" /> 
    <title>Pledge 4 Charity</title> 
    
    
    
    <script src="js/plarity.js" type="text/javascript"></script>
    <link rel="stylesheet" href="css/plarity.css" type="text/css" /> 
  
	<script src="lib/md5-min.js" type="text/javascript"></script>
    
    <script src="lib/jquery/jquery-1.5.min.js" type="text/javascript"></script>  
    <script src="lib/jqueryui/jquery-ui-1.8.7.custom.min.js" type="text/javascript"></script>
    <script src="lib/flip.js" type="text/javascript"></script>
    <script src="lib/jquery.cookie.js" type="text/javascript"></script>
    <link rel="stylesheet" href="lib/jqueryui/css/south-street/jquery-ui-1.8.7.custom.css" type="text/css" /> 
    <link rel="stylesheet" href="lib/sexybuttons/sexybuttons.css" type="text/css" />  
    </head>
  <body>
    <div id="header">
      <div class="wrapper">
   	  	<h1 id="title" onclick="location.href='index.php';">Plarity</h1>
   	  	<span id="login_register_field">
   	  	<?php
   	  	  if (!isset($_COOKIE["plaritySessionId"])) {
   	  	  ?>
   	  	 <span id="login">
   	  	  <form>
		  	<table>
		   		<tr>
		  			<td colspan="4">Login</td>
		  		</tr>
			  	<tr>
			  		<td><input id="login_email" type="email" class="input_text" placeholder="your@email.com" /></td>
			  		<td><input id="login_pw" type="password" class="input_text" placeholder="your password" /></td>
			  		<td><button type="button" onclick="login($('#login_email').val(), $('#login_pw').val());" class="sexybutton sexysimple sexygreen sexysmall">Login</button></td>
			  		<td><img id="login_loader" style="display:none;" src="pics/ajax-loader.gif" /></td>
			  	</tr>
			  	<tr>
			  		<td><a href="#" onclick="showSignUp();">Sign up for free!</a></td>
			  		<td colspan="3"><a href="#" onclick="openForgotPW();" >Forgot your password?</a></td>
			  	</tr>
			</table>
		  </form>
   	  	 </span>
   	  	 <?php
   	  	  }
   	  	  else {
   	  	  ?>
   	  	  <span id="logout">
   	  	  <form>
		  	<table>
		  		<tr>
		  			<td colspan="4">&nbsp;</td>
		  		</tr>
		   		<tr>
		  			<td colspan="4">&nbsp;</td>
		  		</tr>
			  	<tr>
			  		<td><span id="logout_email"><i><?php echo($_COOKIE["plarityEmail"]); ?></i></span></td>
			  		<td><input id="session_id" type="hidden" value="<?php echo($_COOKIE["plaritySessionId"]); ?>" /></td>
			  		<td><button type="button" onclick="logout($('#logout_email').html(), $('#session_id').val());" class="sexybutton sexysimple sexygreen sexysmall">Logout</button></td>
			  		<td><img id="logout_loader" style="display:none;" src="pics/ajax-loader.gif" /></td>
			  	
			  	</tr>
			</table>
		  </form>
   	  	 </span>
   	  	 <?php
   	  	  }
   	  	  ?>
   	  	</span>
		<span id="register" style="display:none;">
   	  	  <form>
		  	<table>
		   		<tr>
		  			<td colspan="3">Register</td>
		  		</tr>
			  	<tr>
			  		<td><input id="register_email" type="email" class="input_text" placeholder="your@email.com" /></td>
			  		<td><button type="button" onclick="register($('#register_email').val());" class="sexybutton sexysimple sexygreen sexysmall">Register</button></td>
			  	</tr>
			  	<tr>
			  		<td><a href="#" onclick="showLogin();">Go back to Login!</a></td>
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
        <a href="about.php">About</a>&nbsp;&sdot;&nbsp;
        <a href="blog.php">Blog</a>&nbsp;&sdot;&nbsp;
        <a href="impressum.php">Impressum</a>&nbsp;&sdot;&nbsp;
        <a href="jobs.php">Jobs</a>
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