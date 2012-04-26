

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main" />
    <title>Suchergebnisse</title>
</head>
<body>
    <div class="body">
        <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
        </g:if>
    </div>


    <g:if test="${searchResult?.results}">
        <div id="searchCount">

            Es werden <b>${1 + searchResult.offset} - ${ searchResult.offset + 10}</b>
            von <b>${searchResult.total}</b> Treffern angezeigt
            

            <g:each var="result" in="${searchResult.results}">

            <div class="hit">
                <p class="hitTitle">

                <g:link controller="entries"
                        action="jump" id="${result.id}">${result.title}</g:link>

            </div>
            <p class="hitInfo">${result.dateAdded} </p>
            <p class="hitBody">${result.description} </p>
			</g:each>

        </div>

		<div id="searchCrumbs">
	        <g:paginate controller="search" action="search" params="[query:params.query]"
	                    tonext="next &gt;" total="${searchResult.total}"/>
	    </div>

    </g:if>
    <g:else>
        Die Suche lieferte keine Ergebnisse.
    </g:else>


    
    
</body>
</html>
