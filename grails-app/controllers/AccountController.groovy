import org.apache.shiro.authc.AuthenticationException
import org.apache.shiro.authc.UsernamePasswordToken
import org.apache.shiro.SecurityUtils
import javax.servlet.http.Cookie

class AccountController  {
	
	public static final String INVALID_FEED_URL = "Die angegebene Adresse enthŠlt keinen gŸltigen RSS/Atom Feed."
	
    FeedService feedService

    private Account getCurrentUser() {
        def subject = SecurityUtils.getSubject();
        return Account.findByUserid(subject.principal)
    }

    def index = { redirect(action:'edit',params:params) }


    def edit = {
        def account = getCurrentUser() // Account.findByUserid( session.account.id )

        if(!account) {
            flash.message = "Das Benutzerkonto ${account.id} wurde nicht gefunden."
            redirect(action:'list')
        }
        else {
            return [ account : account ]
        }
    }

    def update = {
        def account = Account.get( params.id )
        if(account.id == getCurrentUser()?.id) {
            //account.properties = params
            bindData(account, params, ['id', 'password'] ) // don't bind id
            if (params.password)
            account.password = params.password.encodeAsSHA1Bytes().encodeBase64()

            if(account.save()) {
            	flash.message = "Aktualisierung erfolgreich"
                redirect(action:'edit', model:[account:account])
            }
            else {
                render(view:'edit',model:[account:account])
            }
        }
        else {
            flash.message = "Das Benutzerkonto ${params.id} wurde nicht gefunden."
            redirect(action:'edit',id:params.id)
        }
    }

    def signup = {
        def account = new Account()
        account.properties['userid', 'password', 'email'] = params
        return ['account':account]
    }

    def register = {
        def account = new Account()
        account.properties['userid', 'password', 'email'] = params
        account.registered = new Date()
        account.status = "active"
        account.password = params.password.encodeAsSHA1Bytes().encodeBase64()

        if(account.save(flush: true)) {
            
            def authToken = new UsernamePasswordToken(params.userid, params.password)

            SecurityUtils.subject.login(authToken)

            redirect(action:'edit')
            //render(view: 'addfeed', model: ['account':account ])
            return
        } else {
            account.password = params.password
            render(view:'signup',model:[account:account])
        }
    }


    def deleteFeed = {
        def blog = Blog.get( params.id )
        if(blog) {
            if (blog.account.id == getCurrentUser()?.id) {

                blog.delete()

                flash.message = "Der Blog '${blog.title}' wurde erfolgreich gel&ouml;scht."
            } else {
                flash.message = "Du hast keine Berechtigung diesen Blog zu l&ouml;schen."
            }

        } else {
            flash.message = "Der Blog ${params.id} wurde nicht gefunden."
        }
        redirect(action: 'edit')
    }

	def addFeed = {

		def feedUrl = params.feedUrl
		log.info("Adding Feed: [$feedUrl]")

		def blog = new Blog()
		blog.feedUrl = params.feedUrl

		def feedInfo = null
		try {
			feedInfo = feedService.getFeedInfo(params.feedUrl)
		}
		catch(InvalidFeedException e) {
			flash.message = INVALID_FEED_URL
		}

		if(feedInfo) {
			blog.title = feedInfo.title ? feedInfo.title : ""
			blog.title = blog.title.length() > 250 ? blog.title[0..249] : blog.title
			blog.description = feedInfo.description ? feedInfo.description : ""
			blog.description = blog.description.length() > 250 ? blog.description[0..249] : blog.description

			def account = getCurrentUser()
			blog.account = account
			if (blog.validate()) {
				blog.save()
				if (grailsApplication.config.feeds.moderate) {
					blog.status = "PENDING"
					try {
						sendMail {
							to grailsApplication.config.feeds.moderator_email
							subject "javablogs: Feed approval for ${feedInfo.title}"
							body """
                        <p>
                        Request to approve URL: ${feedInfo.title} at url <a href="${params.feedUrl}">${params.feedUrl}</a>
                        </p>
                        <p>
                        <a href="http://www.javablogs.de/account/approveFeed/${blog.id}?password=${grailsApplication.config.feeds.approval_password}">Approve</a>

                        for ${blog.account.email} or

                        <a href="http://www.javablogs.de/account/removeFeed/${blog.id}?password=${grailsApplication.config.feeds.approval_password}">Delete</a>
                        </p>

                    """
						}

					} catch (Exception e) { log.error "Could not add feed" , e }
					flash.message = "Neuer Blogfeed hinzugef&uuml;gt: ${feedInfo.title}. Der Blog muss noch durch einen Administrator genehmigt werden und ist dann sichtbar."

				} else {
					feedService.updateFeed(blog)
					blog.status = "ACTIVE"
					flash.message = "Neuer Blogfeed hinzugef&uuml;gt: ${feedInfo.title}"
				}
			} else {
				flash.message = "Fehler beim Hinzuf&uuml;gen des Blogfeeds: ${blog?.errors}"
			}
		}

		redirect(action: 'edit')
	}


    def updateFeed = {
        def blog = Blog.get(params.id)

        if (blog && blog.status == "ACTIVE") {
            log.info("Updating Feed: [${blog?.feedUrl}]")
            def feedInfo = feedService.updateFeed(blog)
            flash.message = "Blogfeed erfolgreich aktualisiert: ${blog.title}"
        } else {
            flash.message = "Der Blog wurde noch nicht genehmigt, oder der Blog wurde nicht gefunden."
        }
        redirect(action: 'edit')

    }

    def approveFeed = {
        Blog blog = Blog.get(params.id)
        if (blog) {
            blog.status = "ACTIVE"
            log.warn "Approving blog: ${blog.title} - ${blog.id}"
            render "<h1>Approval all good for ${blog.title} - ${blog.id}</h1>"
        } else {
            render "<h1>Missing blog</h1>"
        }
    }

    def removeFeed = {

        Blog blog = Blog.get(params.id)
        if (blog) {
            blog.delete()
            log.warn "Deleting blog: ${blog.title} - ${blog.id}"
            render "<h1>Delete all good for ${blog.title} - ${blog.id}</h1>"
        } else {
            render "<h1>Missing blog</h1>"
        }

    }

    def preferredLang = {

        def prefLang = params.id
        if (prefLang == "en") {
            def newCookie = new Cookie("lang", "en")
            newCookie.path = '/'
            newCookie.maxAge = 60 * 60 * 24 * 365 * 5 // 5 years is plenty
            response.addCookie(newCookie)
            flash.message = "Setting preferred language to English"
        } else {
            def langCookie = request.cookies.find { cookie -> cookie.name == "lang" }
            if (langCookie) { // remove it
                langCookie.maxAge = 0
                langCookie.path = '/'
                response.addCookie(langCookie)
                flash.message = "Removing preferred language"
            }
        }
        redirect(uri: "/")

    }



}