<div class="sidebar_box">
  <h3>Über javablogs.de</h3>
  <div class="sb_content">
    <p>javablogs.de ist ein Aggregator für die deutschsprachige Java Blogosphäre und wird bereitsgestellt von der
    <a href="http://www.smartsquare.de">Smartsquare GmbH</a>.</p>
    <br/>
    <p>Ist ihr Blog ohne ihr Wissen hier gelistet? Informieren Sie uns und wir entfernen Ihren Blog aus unserem Index.</p>
  </div>

</div>


<shiro:user>
  <div class="sidebar_box">
    <h3>User Info</h3>
    <div class="sb_content">
      <p><a href="<g:createLink controller='account' action='edit'/>">
          <shiro:principal/>
        </a></p>
      <g:link controller="auth" action="signOut">Logout</g:link>

    </div>

  </div>
</shiro:user>

<shiro:notUser>
  <div class="sidebar_box">
    <h3>Login</h3>
    <div class="sb_content">
    	<g:render template="/loginForm"/>
		<p>
		<g:link controller='login' action="forgottenPassword">Passwort vergessen?</g:link><p/>
		<g:link controller='account' action="signup">Hier registrieren!</g:link>
    </div>
  </div>
</shiro:notUser>


<div class="sidebar_box">
  <h3>Quelltext</h3>
  <div class="sb_content">
    Auf <a href="http://github.com/smartsquare/javablogs/">GitHub</a>
    findest du den kompletten Quelltext zu javablogs.de. Wir freuen uns über Erweiterungen und Patches!
  </div>
</div>





<div class="sidebar_box">
  <h3>Statistik</h3>
  <div class="sb_content">
    <g:recentStats/>
    
    <img style="margin-left: -5px; margin-top: 5px;" src="<g:createLink controller='chart' action='siteStats'/>" alt="site stats"/>
    
    </div>
</div>

<div class="sidebar_box">
  <h3>Newest Bloggers</h3>
  <div class="sb_content">
    <g:recentBloggers/>
  </div>
</div>
