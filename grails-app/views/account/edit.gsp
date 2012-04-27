
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="layout" content="main" />
<title>Benutzerkonto bearbeiten</title>
<g:javascript library="scriptaculous" />

</head>
<body>

	<div class="content_box">
		<h1>
			<span>Benutzerkonto bearbeiten</span>
		</h1>

		<g:hasErrors bean="${account}">
			<div class="errors">
				<g:renderErrors bean="${account}" as="list" />
			</div>
		</g:hasErrors>
		<div id="editAccount">
			<g:form controller="account" action="update" method="post">
				<input type="hidden" name="id" value="${account?.id}" />
				<div class="dialog">
					<table>

						<tr class='prop'>
							<td valign='top' class='name'><label for='userid'>Benutzerkennung:&nbsp;</label></td>
							<td valign='top'
								class='value ${hasErrors(bean:account,field:'userid','errors')}'>
								${account.userid}
							</td>
						</tr>

						<tr class='prop'>
							<td valign='top' class='name'><label for='password'>Passwort:&nbsp;</label></td>
							<td valign='top'
								class='value ${hasErrors(bean:account,field:'password','errors')}'><input
								type="password" name='password' /></td>
						</tr>

						<tr class='prop'>
							<td valign='top' class='name'><label for='email'>E-Mail:&nbsp;</label></td>
							<td valign='top'
								class='value ${hasErrors(bean:account,field:'email','errors')}'><input
								type='text' name='email' value='${account?.email}' /></td>
						</tr>

					</table>
				</div>
				<div class="buttons">
					<span class="button"><g:actionSubmit value="Aktualisieren" /></span>
				</div>

			</g:form>
		</div>

		<br />

		<g:if test="${account.blogs.size() > 0}">
			<h1>Meine Blogs</h1>

			<div id="myBlogs">

				<g:hasErrors bean="${blog}">
					<div class="errors">
						<g:renderErrors bean="${blog}" as="list" />
					</div>
				</g:hasErrors>

				<table class="blogTable">
					<tr>
						<th>Blog</th>
						<th>Beschreibung</th>
						<th>Letztes Update</th>
						<th>Nächstes Update</th>
						<th></th>
					</tr>


					<g:each var="blog" in="${account.blogs}">


						<tr class="myBlog" id="myBlog-${blog.id}">
							<td><a href="${blog.feedUrl}"> ${blog.title}
							</a></td>
							<td>
								${blog.description}
							</td>
							<td id="lastPolled-${blog.id}"><g:dateFromNow
									date="${blog.lastPolled}" /></td>
							<td id="nextPolled-${blog.id}"><g:dateFromNow
									date="${blog.nextPoll}" /></td>

							<td><a
								href="<g:createLink action='updateFeed' id='${blog.id}'/>"><img src="${createLinkTo(file:'images/refresh.png')}" alt="Aktualisieren" title="Aktualisieren"/></a>
								<a href="<g:createLink action='deleteFeed' id='${blog.id}'/>"
								onclick="return confirm('Bist du sicher, dass du den Blog ${blog.title} löschen möchtest?')"><img src="${createLinkTo(file:'images/delete.png')}" alt="Löschen" title="Löschen"/></a>
							</td>
						</tr>


					</g:each>
				</table>

			</div>
		</g:if>

		<g:form action="addFeed" method="post">
			<div id="newFeed"
				style="margin: 1em; padding: 1em; border: 1px solid;">

				<p style="margin-bottom: 5px;">javablogs.de führt eine Liste von
					deutschsprachigen Javablogs. Als nächstes kannst du deinen eigenen
					Blog zu der Liste hinzufügen. Diese Seite ist moderiert, d.h. dein
					Blog erscheint erst auf der Startseite, wenn er von einem
					Administrator genehmigt wurde. Bitte beachte auch, dass die
					angegebene URL einen RSS Feed anbieten muss, damit javablogs.de die
					Seite aggregieren kann.</p>

				<br /> <label for='feedUrl'>Dein Blogfeed:&nbsp;</label> <input
					type="text" name='feedUrl' style="width: 400px;"
					value="${blog?.feedUrl?.encodeAsHTML()}"></input> <input
					type="submit" value="Hinzufügen" />

			</div>

		</g:form>

		<div id="feedResults">
			<!-- placeholder for Ajax content -->
		</div>

		<script language="javascript">
			function showSpinner() {
				document.all.spinner.style.display = "inline"
				// new Effect.Fade('feedResults');
			}

			function hideSpinner() {
				document.all.spinner.style.display = "none"
			}

			function appearDiv() {
				new Effect.Appear('feedResults');
			}
		</script>


	</div>
</body>
</html>
