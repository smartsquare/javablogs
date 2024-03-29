import grails.util.Environment

class BootStrap {
	
     def init = { servletContext ->

        switch (Environment.current) {

            case Environment.DEVELOPMENT:
            case Environment.PRODUCTION:
                createConfigurationIfRequired()
                createAdminUserIfRequired()
                break;

        }
     	
        
     }
	 
     def destroy = {
			 
		
		
     }

    def createConfigurationIfRequired() {

        if (SystemConfig.count() == 0) {

            def defaultConfig = [
                "thumbnail.user" : "14439",
                "thumbnail.apiKey" : "910d6823d2478f5a6b3e338faf47ecf3",

                "translate.apikey" : "yourKey",

                "twitter.enabled" : "true",
                "twitter.user" : "youruser",
                "twitter.password" : "yourpassword"
            ]

            defaultConfig.each { key, value ->
                new SystemConfig(settingName: key, settingValue: value).save()
            }


        }

    }

    def createAdminUserIfRequired() {
        if (Account.count() == 0) {
        	
        	def admin = new Account(userid: "admin", role: "admin",
        	      status: "ACTIVE", email: "daniel.rosowski@gmx.de")
        
        	def password =  "admin".encodeAsSHA1Bytes().encodeBase64()
        	println "Admin password is encoded to ${password}"
        	admin.password = password
        	      
            if (!admin.save()) {
            	println "Failed to create admin user: ${admin.errors}" 
            }
        }
    }
} 