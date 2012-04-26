<html>
	<head>
		<title>javablogs.de - <g:layoutTitle default="Willkommen" /></title>
		<link rel="stylesheet" href="${createLinkTo(dir:'css',file:'reset-fonts-grids.css')}"/>
		<link rel="stylesheet" href="${createLinkTo(dir:'css',file:'javablogs.css')}"/>
		<link rel="shortcut icon" href="${createLinkTo(file:'favicon.ico')}" />
		<g:layoutHead />
		<g:javascript library="application" />
		<meta name="description" content="javablogs.de is a german java blog aggregator" />
		<meta name="keywords" content="java,javaee,jee,javafx,jsf,jvm" />
		<meta name="robots" content="index,follow" />					
	</head>
	
	<body>
	<div id="doc3" class="yui-t4"> 
	    <div id="hd">
			<h1 id="title">javablogs.de</h1>
			<h2 id="subtitle">Frisch gemahlen und heiß gebrüht</h2>
			
			  <div id="tabs">
			    <ul>
			      <!-- <li class="${request.forwardURI =~ /home/ ? 'current' : 'notcurrent'}"><a href="<g:createLinkTo dir=''/>" >home</a></li> -->
			      <li id="${request.forwardURI =~ /entries\/recent/ ? 'current' : 'notcurrent'}"><a href="<g:createLink controller='entries' action='recent'/>">Neu</a></li>
     			  <li id="${request.forwardURI =~ /entries\/popular/ ? 'current' : 'notcurrent'}"><a href="<g:createLink controller='entries' action='popular'/>">Beliebt</a></li>
     			  <li id="${request.forwardURI =~ /entries\/lists/ ? 'current' : 'notcurrent'}"><a href="<g:createLink controller='entries' action='lists'/>">Mailinglisten</a></li>
     			  <!-- <li id="${request.forwardURI =~ /entries\/tweets/ ? 'current' : 'notcurrent'}"><a href="<g:createLink controller='entries' action='tweets'/>">Tweets</a></li> -->
				<shiro:hasRole name="admin">
     			  <li id="${request.forwardURI =~ /blog\/list/ ? 'current' : 'notcurrent'}"><a href="<g:createLink controller='blog' action='list'/>">Alle Blogs</a></li>
				</shiro:hasRole>

                          <li id="${request.forwardURI =~ /account/ ? 'current' : 'notcurrent'}"><a href="<g:createLink controller='account' action='edit'/>">Meine Blogs</a></li>

                <shiro:user>
			      	<li id="${request.forwardURI =~ /login/ ? 'current' : 'notcurrent'}"><a href="<g:createLink controller='auth' action='signOut'/>">Logout</a></li>
			  	</shiro:user>

				<shiro:notUser>
			      <li id="${request.forwardURI =~ /login/ ? 'current' : 'notcurrent'}"><a href="<g:createLink controller='auth' action='login'/>">Login</a></li>
			  	</shiro:notUser>
			      <g:if test="${request.forwardURI =~ /search/}">
				      <li id="current"><a href="<g:createLink controller='search' action='search'/>">Suche</a></li>
			      </g:if>

			      
			      
			    </ul>
			    <g:searchBox noCombo="true" query="${params.query}" fields="title,description" controller="search" action="search" />
			  </div>
			
		</div>  
	   <div id="bd"> <!-- start body -->
	   
	  		<div id="yui-main"> 
	        	<div class="yui-b">
	        		<g:if test="${flash.message}">
	        			<div id="flash">
	        				${flash.message}
	        			</div>
	        		</g:if>
	        	
		        	<g:layoutBody />		
	        	</div> 
	      	</div> 
	      	<div class="yui-b">
             
	      		<g:render template="/sidebar"/>
	      	
	      	</div> 
	   


	   </div>  <!-- end body -->
	   <div id="ft">
	   		Das Copyright liegt bei den jeweiligen Autoren der Artikel. javablogs.de by <a href="http://www.smartsquare.de">Smartsquare GmbH</a> based on <a href="http://www.groovyblogs.org">groovyblogs.org</a> by Glen Smith.
	   </div>  
	</div> 
		
	</body>	

</html>