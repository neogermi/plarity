<?php
   require_once("./php/util.php");
   printHead();
?>
  <body>
<?php
   printHeader();
?>
   <div id="content">
	    <div class="wrapper">
	      	<table class="pledge">
	      		<tr>
	      			<td class="left" >01 Jan 2011</td>
				    <td class="right">
				      		<span class="text">
				      			I will 
				      			<span class="resolution">not smoke</span>
				      			until
				      			<span class="end_date">24 Nov 2011</span>
				      			or I will donate
				      			<span class="donation">1 Euro</span>
				      			to
				      			<span class="charity">Kinder in Not</span>
				      		</span>
				     </td>
	      	</table>
	      	<table class="pledge even">
	      		<tr>
	      			<td class="left" >15 Dec 2010</td>
				    <td class="right">
				      		<span class="text">
				      			If I not 
				      			<span class="resolution">quit smoking</span>,
				      			I will donate
				      			<span class="donation">1 Euro</span>
				      			to
				      			<span class="charity">Kinder in Not</span>
				      		</span>
				     </td>
	      	</table>
	      	<table class="pledge">
	      		<tr>
	      			<td class="left" >12 Nov 2010</td>
				    <td class="right">
				      		<span class="text">
				      			I will 
				      			<span class="resolution">stop smoking</span>
				      			before the
				      			<span class="end_date">24 Nov 2010</span>
				      			or I will donate
				      			<span class="donation">1 Euro</span>
				      			to
				      			<span class="charity">Kinder in Not</span>
				      		</span>
				     </td>
	      	</table>
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
