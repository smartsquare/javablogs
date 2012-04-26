
<html>
    <head>
         <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
         <meta name="layout" content="main" />
         <title>Benutzerkonto erstellen</title>         
    </head>
    <body>
        <div id="content">
           <h1>Fast fertig...</h1>
           
           <g:hasErrors bean="${account}">
                <div class="errors">
                    <g:renderErrors bean="${account}" as="list" />
                </div>
           </g:hasErrors>
           <p>
           Hallo ${account.userid}, danke für deine Registrierung bei javablogs.de. 
           Eine E-Mail mit einem Aktivierungslink wurde an <b>${account.email}</b> verschickt. Sobald der Link angeklickt wurde, kannst du deinen eigenen Blog hinzufügen und mit der deutschen Java Community teilen. 
           </p>
           <p>
           Viel Spaß!
           </p>
        </div>
    </body>
</html>
            