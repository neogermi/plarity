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
    <title>PledgeMe</title> 
    <script src="js/jquery/jquery-1.4.4.min.js" type="text/javascript"></script>  
    <script src="js/jqueryui/jquery-ui-1.8.7.custom.min.js" type="text/javascript"></script>  
    <link rel="stylesheet" href="js/jqueryui/css/south-street/jquery-ui-1.8.7.custom.css" type="text/css" />  
    <link rel="stylesheet" href="css/main.css" type="text/css" /> 
  </head>
<?php
}
?>

<?php
function insertLoginDiv () {
?>
  <span class="login">
    <form>
      Login<br />
      <input type="email" class="input_text" placeholder="your@email.com"></input>
      <input type="password" class="input_text" placeholder="your password"></input>
      <input type="button" class="login_button" value="Login" />
    </form>
    <span class="sign_up">Click <span class="link">here</span> to sign up for free!</span>
 </span>
<?php
}
?>

<?php
function printFooter () {
?>
    <div class="footer">
      <div>
        <a href="blog">Blog</a>&nbsp;&sdot;&nbsp;<a href="legal">Legal</a>&nbsp;&sdot;&nbsp;<a href="impressum">Impressum</a>&nbsp;&sdot;&nbsp;<a href="help">Hilfe</a>&nbsp;&sdot;&nbsp;<a href="jobs">Jobs</a>
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