
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main" />
    <link rel="stylesheet" href="${createLinkTo(dir:'css',file:'thumbs.css')}"/>
    <title>${pageTitle}</title>

      <g:if test="${request.cookies.find { cookie -> cookie.name == 'lang' }}">
        <style>
          .nonenglish {
            visibility: hidden;
            height: 0px; 
          }
        </style>
      </g:if>

  </head>
  <body>
    <div class="content_box">
      <h1>${pageTitle}</h1>
    </div>

    <g:each var="entry" in="${entries}">
      <g:render template="entry" model="[ entry: entry, thumbnails: thumbnails ]"/>
    </g:each>

  </body>
</html>
