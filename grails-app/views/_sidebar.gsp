<div class="niceBox">
  <div class="niceBoxHd">Über javablogs.de</div>
  <div class="niceBoxBody">
    javablogs.org wird bereitsgestellt von der
    <a href="http://www.smartsquare.de">Smartsquare GmbH</a>.<br/>
    javablogs.de läuft in der Version
    <g:meta name="app.version"/> und verwendet
    <a href="http://grails.org">Grails</a> <g:meta name="app.grails.version"/>
  </div>

</div>


<div class="niceBox">
  <div class="niceBoxHd">Feeds</div>
  <div class="niceBoxBody">
    <ul>
      <li>
        <a href="<g:createLink controller='feed' action='atom'/>" class="feedLink">
          <img src="${createLinkTo(dir:'images',file:'feed-icon-16x16.jpg')}" alt="Atom"/>
          Atom </a>
      </li>
      <li>
        <a href="<g:createLink controller='feed' action='rss'/>" class="feedLink">
          <img src="${createLinkTo(dir:'images',file:'feed-icon-16x16.jpg')}" alt="RSS"/>
          RSS </a>
      </li>
    </ul>
    <g:feedburner/>
  </div>
</div>


<shiro:user>
  <div class="niceBox">
    <div class="niceBoxHd">User Info</div>
    <div class="niceBoxBody">
      <p><a href="<g:createLink controller='account' action='edit'/>">
          <shiro:principal/>
        </a></p>
      <g:link controller="auth" action="signOut">Logout</g:link>

    </div>

  </div>
</shiro:user>

<shiro:notUser>
  <div class="niceBox">
    <div class="niceBoxHd">Login</div>
    <div class="niceBoxBody">
      <g:form controller="auth" action="signIn" method="post" >
        <b>Benutzerkennung:&nbsp;</b>
        <input type='text' name='username'/>
        <b>Passwort:&nbsp;</b>
        <input type="password" name='password'/>
        <b>Login merken?:&nbsp;</b>
        <g:checkBox name="rememberMe" />

        <span class="formButton">
          <input type="submit" value="Login"></input>
        </span>
      </g:form>
      <p>
      <g:link controller='login' action="forgottenPassword">Passwort vergessen?</g:link><p/>
      <g:link controller='account' action="signup">Hier registrieren!</g:link>
    </div>
  </div>
</shiro:notUser>


<div class="niceBox">
  <div class="niceBoxHd">Quelltext</div>
  <div class="niceBoxBody">
    Unter <a href="http://github.com/smartsquare/javablogs/">GitHub</a>
    findest du den kompletten Quelltext zu javablogs.de. Wir freuen uns über Erweiterungen und Patches!
  </div>
</div>





<div class="niceBox">
  <div class="niceBoxHd">Statistik</div>
  <div class="niceBoxBody">
    <g:recentStats/>
    
    <img style="margin-left: -5px; margin-top: 5px;" src="<g:createLink controller='chart' action='siteStats'/>" alt="site stats"/>
    
    </div>
</div>

<div class="niceBox">
  <div class="niceBoxHd">Newest Bloggers</div>
  <div class="niceBoxBody">
    <g:recentBloggers/>
  </div>
</div>
