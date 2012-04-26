grails.mime.file.extensions = true // enables the parsing of file extensions from URLs into the request format

grails.mime.types = [ html: ['text/html','application/xhtml+xml'],
    xml: ['text/xml', 'application/xml'],
    text: 'text-plain',
    js: 'text/javascript',
    rss: 'application/rss+xml',
    atom: 'application/atom+xml',
    css: 'text/css',
    csv: 'text/csv',
    all: '*/*',
    json: ['application/json','text/json'],
    form: 'application/x-www-form-urlencoded',
    multipartForm: 'multipart/form-data'
]

// The default codec used to encode data with ${}
grails.views.default.codec="none" // none, html, base64
grails.views.gsp.encoding="UTF-8"
grails.converters.encoding="UTF-8"

// enabled native2ascii conversion of i18n properties files
grails.enable.native2ascii = true

// grails.mail.host=yourhost.com
// grails.mail.default.from=you@yourhost.com


mq {
    enabled = false
}

thumbnail {
    enabled=true
    endpointurl = "http://webthumb.bluga.net/easythumb.php"
}

pdf {
    dir=System.properties["java.io.tmpdir"]
}

cache {
    enabled = true
}

feeds {
    ignoreFeedEntriesOlderThan = 30 // days
    moderate = false
    moderator_email = "daniel.rosowski@gmxde"
}

grails.mail.host =  "localhost"
grails.mail.default.from = "daniel.rosowski@gmx.de"

http {
	/*
    useproxy=true
    host="192.168.1.7"
    port=3128
    */
    timeout=10*1000
    useragent="JavaBlogs/1.0 (http://www.javablogs.de)"
    usefeedburner=false
    maxpollsperminute=7
    feedburner_atom="http://feeds.feedburner.com/groovyblogs"
    feedburner_rss="http://feeds.feedburner.com/groovyblogs"
    feedburner_stats_url="http://feeds.feedburner.com/~fc/groovyblogs?bg=99CCFF&amp;fg=444444&amp;anim=0"
}

lists {
    groovy="http://groovy.329449.n5.nabble.com/groovy-user-f329450.xml"
    grails="http://grails.1312388.n4.nabble.com/Grails-user-f1312389.xml"
}

tweets.url = "http://feeds.groovytweets.org/latestgroovytweets"

translate {
    enabled=false
    langUrl="https://ajax.googleapis.com/ajax/services/language/detect?v=1.0&q="
    url='http://translate.google.com/translate?hl=${to}&sl=auto&tl=${to}&u=${url}'
}


// log4j configuration
log4j = {
	
	appenders {
		rollingFile name: "gb",
					file: "javablogs.log",
					maxFileSize: "10MB",
					layout: pattern(conversionPattern: '%d %p %c{2} %m%n')
					
	}

    error  gb:[ 'org.codehaus.groovy.grails.web.servlet',  //  controllers
	       'org.codehaus.groovy.grails.web.pages', //  GSP
	       'org.codehaus.groovy.grails.web.sitemesh', //  layouts
	       'org.codehaus.groovy.grails."web.mapping.filter', // URL mapping
	       'org.codehaus.groovy.grails."web.mapping', // URL mapping
	       //'org.codehaus.groovy.grails.commons', // core / classloading
	       'org.codehaus.groovy.grails.plugins', // plugins
	       'org.codehaus.groovy.grails.orm.hibernate', // hibernate integration
	       'org.springframework',
	       'org.hibernate' ]

    debug  gb: 'grails.app'

    // trace  gb: ['org.codehaus.groovy.grails.commons'] // Good for debugging bean creation issues
    
}


