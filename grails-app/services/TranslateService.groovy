

// A simple translation service that uses Google APIs. Read about it
// at http://blogs.bytecode.com.au/glen/2009/07/30/getting-groovy-with-google-language-translation-apis.html
class TranslateService {

	def grailsApplication
	
    static transactional = false

    def getLanguage(String text) {

        if (text.size() > 200) {
            text = text[0..<200]
        }

        String url = grailsApplication.config.translate.langUrl +
            text.encodeAsURL().toString() + "&key=" + SystemConfig.findBySettingName("translate.apikey")?.settingValue

        def translateResponse = url.toURL().text

        log.debug "Google translate responsed: ${translateResponse}"

        def response =  grails.converters.JSON.parse(translateResponse)
        return response.responseData.language

    }
}
