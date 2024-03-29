
<html>
    <head>
         <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
         <meta name="layout" content="main" />
         <g:javascript library="prototype" />
         <title>Benutzerkonto erstellen</title>         
    </head>
    <body>
        <div id="content_box">
           <h1><span>Neues Benutzerkonto</span></h1>
           
           <g:hasErrors bean="${account}">
                <div class="errors">
                    <g:renderErrors bean="${account}" as="list" />
                </div>
           </g:hasErrors>
           <g:form action="register" method="post" >
               <div class="dialog">
                <table>
                     <tr class='prop'><td valign='top' class='name'><label for='userid'>Benutzerkennung:&nbsp;</label></td><td valign='top' class='value ${hasErrors(bean:account,field:'userid','errors')}'><input type='text' name='userid' value='${account?.userid}' /></td></tr>
                     <tr class='prop'><td valign='top' class='name'><label for='password'>Passwort:&nbsp;</label></td><td valign='top' class='value ${hasErrors(bean:account,field:'password','errors')}'><input type="password" name='password' value='${account?.password}' /></td></tr>                       
                     <tr class='prop'><td valign='top' class='name'><label for='email'>E-Mail:&nbsp;</label></td><td valign='top' class='value ${hasErrors(bean:account,field:'email','errors')}'><input type='text' name='email' value='${account?.email}' /></td></tr>
                                 

               </table>
               </div>
               <div class="buttons">
               
                     <span class="formButton">
                        <input type="submit" value="Anlegen"></input>
                     </span>
               </div>
            </g:form>
            <script type="text/javascript">
            
            	function hideSpinner() {
            		document.getElementById("spinner").visibility = hidden;
            	}
            	
            	function showSpinner() {
	            	document.getElementById("spinner").visibility = visible;
            	}
            	
            	function appearDiv() {
            		//Element.hide('feedresults');
            		alert("Appear");
	            	new Effect.Appear('feedresults');
	            	$("spinner").display = none;
            	}
            	
            	function fadeDiv() {
            		alert("Fade");
            		new Effect.Fade('feedresults');
            	}
            
            </script>
            <div id="feedresults"></div>
            
        </div>
    </body>
</html>
            