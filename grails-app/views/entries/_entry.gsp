<div class="post_box">	
	<div class="header">
		<h2>
			<g:link controller="entries" action="jump" id="${entry.id}">${entry.title}</g:link>
	    </h2>
	    <div class="tag">
	        <g:link controller="blog" action="show" id="${entry.blog.id}" style="">${entry.blog.title}</g:link>
						 [ ${entry.hitCount} Klicks ] -
	        <g:dateFromNow date="${entry.dateAdded}"/>	    
	    </div>
    </div>

	<g:if test="${thumbnails}">
    <div id="pic">
      <a class="p1" href="<g:createLink controller='entries' action='jump' id='${entry.id}'/>" title="thumbnail image">
        <img src="<g:createLink controller='thumbnail' action='show' id='${entry.id}'/>" alt="Kein Bild verf&uuml;gbar" onmouseover="document.getElementById('large-${entry.id}').src = '<g:createLink controller='thumbnail' action='showLarge' id='${entry.id}'/>'" />
        <img id="large-${entry.id}" src="" alt="Loading Image..." class="large"  />
      </a>
    </div>
    </g:if>
    
    <div class="pb_right">
		<g:summariseEntry description="${entry.description}"/>
    </div>
    
    <div class="cleaner"></div>
</div>
