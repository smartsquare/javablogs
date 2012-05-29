<html>
<head>
<title>javablogs.de - <g:layoutTitle default="Willkommen" /></title>
<link rel="stylesheet"
	href="${createLinkTo(dir:'css',file:'javablogs.css')}" />
<link rel="shortcut icon" href="${createLinkTo(file:'favicon.ico')}" />
<g:layoutHead />
<g:javascript library="application" />
<meta name="description"
	content="javablogs.de is a german java blog aggregator" />
<meta name="keywords" content="java,javaee,jee,javafx,jsf,jvm" />
<meta name="robots" content="index,follow" />
</head>

<body>
	<div id="templatemo_header">

		<div id="site_title">
			<a href="http://www.javablogs.de">Frisch gebrüht und heiß
				serviert</a>
		</div>

		<div id="header_right">
			<%-- <a href="#"><img src="${createLinkTo(file:'images/templatemo_twitter.png')}" alt="twitter" /></a> --%>
			<a href="<g:createLink controller='feed' action='rss'/>"><img src="${createLinkTo(file:'images/templatemo_rss.png')}" alt="rss" /></a>

			<div id="search_box">
				<g:searchBox noCombo="true" query="${params.query}"
					fields="title,description" controller="search" action="search" />
			</div>
		</div>

		<div class="cleaner"></div>
	</div>
	<!-- end of header -->

	<div id="templatemo_menu_wrapper">

		<div id="templatemo_menu">
			<ul>
				<!-- <li class="${request.forwardURI =~ /home/ ? 'current' : 'notcurrent'}"><a href="<g:createLinkTo dir=''/>" >home</a></li> -->
				<li
					class="${request.forwardURI =~ /entries\/recent/ ? 'current' : 'notcurrent'}"><a
					href="<g:createLink controller='entries' action='recent'/>">Neu</a></li>
				<li
					class="${request.forwardURI =~ /entries\/popular/ ? 'current' : 'notcurrent'}"><a
					href="<g:createLink controller='entries' action='popular'/>">Beliebt</a></li>
				<shiro:hasRole name="admin">
					<li
						class="${request.forwardURI =~ /blog\/list/ ? 'current' : 'notcurrent'}"><a
						href="<g:createLink controller='blog' action='list'/>">Alle
							Blogs</a></li>
				</shiro:hasRole>
				<li
					class="${request.forwardURI =~ /account/ ? 'current' : 'notcurrent'}"><a
					href="<g:createLink controller='account' action='edit'/>">Meine
						Blogs</a></li>
				<shiro:user>
					<li
						class="${request.forwardURI =~ /login/ ? 'current' : 'notcurrent'}"><a
						href="<g:createLink controller='auth' action='signOut'/>">Logout</a></li>
				</shiro:user>
				<shiro:notUser>
					<li
						class="${request.forwardURI =~ /login/ ? 'current' : 'notcurrent'}"><a
						href="<g:createLink controller='auth' action='login'/>">Login</a></li>
				</shiro:notUser>
				<g:if test="${request.forwardURI =~ /search/}">
					<li class="current"><a
						href="<g:createLink controller='search' action='search'/>">Suche</a></li>
				</g:if>
			</ul>
		</div>
	</div>
	<!-- end of templatemo_menu -->

	<div id="templatemo_main">
		<span class="tm_bottom"></span>

		<div id="templatemo_content">

			<g:if test="${flash.message}">
				<div id="flash">
					${flash.message}
				</div>
			</g:if>

			<g:layoutBody />
		</div>

		<div id="templatemo_sidebar">
			<g:render template="/sidebar" />
		</div>
	
		<div class="cleaner"></div>
	</div>



	<div id="templatemo_footer">
		<p>Das Copyright liegt bei den jeweiligen Autoren der Artikel |
		javablogs.de by <a href="http://www.smartsquare.de">Smartsquare
			GmbH</a> based on <a href="http://www.groovyblogs.org">groovyblogs.org</a>
		by Glen Smith | <g:link controller="static" action="impressum">Impressum</g:link></p>
		<p>javablogs.de läuft in der Version
    <g:meta name="app.version"/> und verwendet
    <a href="http://grails.org">Grails</a> <g:meta name="app.grails.version"/></p>
	</div>

</body>

</html>