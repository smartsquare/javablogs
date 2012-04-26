
<html>
    <head>
         <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
         <meta name="layout" content="main" />
         <title>Passwort vergessen?</title>         
    </head>
    <body>
        <div id="content">
           <h1>Passwort vergessen?</h1>
           
           
           <p style="margin: 8px;">
           Falls du dein Passwort vergessen hast, können wir ein neues Passwort generieren und es an die E-Mail Adresse
           senden, die du bei der Registrierung angegeben hast.
           Alles was wir dazu benötigen ist deine javablogs.de Benutzerkennung.
           </p>
 
           <g:form action="forgottenPassword" method="post" >
               <div class="dialog">
                <table>
                	<tr class='prop'><td valign='top' class='name'><label for='userid'>Benutzerkennung:&nbsp;</label></td><td valign='top' class='value ${hasErrors(bean:account,field:'userid','errors')}'><input type='text' name='userid' value='${account?.userid}' /></td></tr>
               </table>
               </div>
               <div class="buttons">
                     <span class="formButton">
                        <input type="submit" value="Passwort zurücksetzen"></input>
                     </span>
               </div>
            </g:form>
        </div>
    </body>
</html>
            