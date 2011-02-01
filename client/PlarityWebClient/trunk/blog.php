<?php
   require_once("./php/util.php");
   printHead();
?>
   <div id="content">
	    <div class="wrapper">
             This is where the blog belongs!
	   </div>
    </div>
    
    <div id="dialog-form" style="display:none;" title="Create new user">
	<p class="validateTips">All form fields are required.</p>

	<form>
	<fieldset>
		<label for="email">Email</label>
		<input type="text" name="email" id="email" value="" placeholder="your@email.com" class="text ui-widget-content ui-corner-all" />
		<label for="password">Password</label>
		<input type="password" name="password" id="password" value="" class="text ui-widget-content ui-corner-all" />
	</fieldset>
	</form>
</div>
    
<?php
   printFooter();
?>
  </body>
</html>
