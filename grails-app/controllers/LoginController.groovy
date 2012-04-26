import org.apache.shiro.authc.AuthenticationException
import org.apache.shiro.authc.UsernamePasswordToken
import org.apache.shiro.SecurityUtils

class LoginController {
	
    def index = {
        render(view:'login')
    }
	
    def login = {
        if (params.userid && params.password) {
            def user = Account.findByUseridAndStatus(params.userid, "active")
	    		
            String calcPassword = params.password.encodeAsSHA1Bytes().encodeBase64()
            if (user != null && user.password == calcPassword)  {
                session.account = user
                user.lastLogin = new Date()
                user.save()
                flash.message = "Hallo und willkommen ${user.userid}"
                if (session.returnController) {
                    redirect(controller:session.returnController, action:session.returnAction)
                } else {
                    redirect(controller:'entries', action:'recent')
                }
            } else {
                flash.message = "Unbekannte Benutzerkennung oder falsches Passwort."
            }
        }
    }
	
    def forgottenPassword = {
	
        if (params.userid) {
			
            def account = Account.findByUserid(params.userid)
            if (account && account.email) {
    			
                def PW_POOL = "23456789ABCDEFGHJKLMNPQRSTUVWXYZ"
                def genPw = ""
                8.times {

                    genPw += PW_POOL[new Random().nextInt(PW_POOL.size() -1)]

                }
                account.password = genPw.encodeAsSHA1Bytes().encodeBase64()
                def msg = """
		        	<h1>javablogs.de Passwort zurückgesetzt</h1>
		        	<p>
		            Hallo ${account.userid}, dein Passwort wurde zurückgesetzt: <b>${genPw}</b>.
					Die Eingabe muss in GROSSBUCHSTABEN erfolgen.
					Nach dem Login, kannst du dein Passwort unter "Meine Blogs" neu setzen.
		            </p>
					<p>Viele Grüße von javablogs.de!</p>
		            """
                sendMail {
                    to account.email
                    subject "javablogs.de Passwort zurückgesetzt"
                    body msg
                }
                flash.message = "Ein neues Passwort wurde generiert und an deine E-Mail Adresse gesendet."
                redirect(controller: 'entries')
            } else {
                flash.message = "Unter dieser Kennung wurde kein Benutzerkonto gefunden."
            }
			
        }
			
    }
	
    
    def logout = {
    		
        session.account = null
        flash.message = "You have successfully logged out"
        redirect(controller: 'entries', action:'recent')
    		
    }
}

