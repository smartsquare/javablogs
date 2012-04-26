import net.sf.ehcache.Element

class BlogTagLib {

    def recentBlogsCache
    def recentStatsCache
	def grailsApplication

    def summariseEntry = { attrs ->

        def description = attrs.description

        // strip html for the summary, then truncate
        def summary = FeedEntry.summarize(description)
        out << summary

    }


    public static String getNiceDate(Date date) {

        def now = new Date()

        def diff = Math.abs(now.getTime() - date.getTime())

        long second = 1000
        long minute = 1000 * 60
        long hour = minute * 60
        long day = hour * 24

        def niceTime = ""

        long calc = 0L;

        calc = Math.floor(diff / day)
        if (calc > 0) {
            niceTime += calc + " Tag" + (calc > 1 ? "en" : "")
            diff = diff % day
        }

        calc = Math.floor(diff / hour)
        if (calc > 0) {
            niceTime += calc + " Stunde" + (calc > 1 ? "n" : "")
            diff = diff % hour
        }

        calc = Math.floor(diff / minute)
        if (calc > 0) {
            niceTime += calc + " Minute" + (calc > 1 ? "n" : "")
            diff = diff % minute
        }

        if (niceTime.length() == 0) {
            niceTime = "Jetzt gerade"
        } else {
            niceTime = (date.getTime() > now.getTime() ? "In " : "Vor ") + niceTime 
        }

        return niceTime

    }


    def dateFromNow = { attrs ->

        def date = attrs.date

        out << getNiceDate(date)

    }

    def niceDate = { attrs ->

        def date = attrs.date
        def sdf = new java.text.SimpleDateFormat("EEE, d MMM yyyy HH:mm", Locale.GERMAN)
        out << sdf.format(date)

    }

    def translate = { attrs ->

        if (grailsApplication.config.translate.enabled) {

            def entry = attrs.entry

            if (entry.language && !(entry.language.startsWith('en'))) {

                out << "<span class='translateLink'>"
                out << "[ <a href='"
                out << "jumpTranslate/$entry.id?lang=" + entry.language
                out << "'>Translate</a> ]"
                out << "</span>"


            }

        }


    }

    // Creates a list of recent bloggers
    def recentBloggers = { attrs ->

        def cacheValue = recentBlogsCache.get("recentBloggers")?.value
        if (cacheValue) {

            out << cacheValue

        } else {

            def maxEntries = attrs.max ? attrs.max : 5
            def recentBlogs = Blog.listOrderByRegistered(max:5, order:"desc")

            def sw = new StringWriter()
            def outs = new PrintWriter(sw)

            outs << "<ul id='recentBloggers'>"
            recentBlogs.each { blog ->

                outs << "<li>"
                // createLink(controller: 'blog', action: 'show', id: blog.id)
                outs << "<a href='../blog/show/" + blog.id + "'>"
                outs << blog.title
                outs << "</a>"

                outs << "</li>"
                // <a href="<g:createLink controller='blog' action='show' id='blog.id'>">${blog.title}</a></li>
            }
            outs << "</ul>"

            def recentStr = sw.toString()
            out << recentStr
            // println "List is: ${recentStr}"
            recentBlogsCache.put(new Element("recentBloggers", recentStr))

        }

    }

    def recentStats = { attrs ->

        def cacheStats = recentStatsCache.get("recentStats")?.value
        if (cacheStats) {
            out << cacheStats
        } else {
            def newStats = """
                <p>${Blog.count()} Blogs Aggregated</p>
                <p>${BlogEntry.count()} Entries Indexed</p>
                <p>${BlogEntry.findAllByDateAddedGreaterThan(new Date().minus(1)).size()} Entries Last 24 hours</p>
			"""
            out << newStats
            recentStatsCache.put(new Element("recentStats", newStats))
        }

    }

    def recentChart =  { attrs ->

        def entryCount = recentStatsCache.get("recentChart")?.value
        if (!entryCount) {

            entryCount = new TreeMap()

            def lastWeek = new Date().minus(6)

            // Normalise all post dates to midnight on the day they were posted for charting
            BlogEntry.findAllByDateAddedGreaterThan(lastWeek).each { entry ->

                //def key = fmt.format(entry.dateAdded)
                def entryKey = Calendar.getInstance()
                entryKey.set(1900 + entry.dateAdded.year, entry.dateAdded.month, entry.dateAdded.date, 0, 0, 0)
                entryKey.set(Calendar.MILLISECOND, 0)
                def key = entryKey.getTimeInMillis()
                entryCount[key] = entryCount[key] ? entryCount[key] + 1 : 1

            }
            recentStatsCache.put(new Element("recentChart", entryCount))


        }
        def fmt = new java.text.SimpleDateFormat("EEE")

        out << g.barChart(
                    title: "Entries Last 7 Days",
                    titleAttrs: ["000000", "12"],
                    type: "bvs",
                    size: [170,150],
                    axes: "x,y",
                    gridLines: "100,20",
                    colors: ['df8a8a'],
                    fill: "bg,s,ccffbf|c,s,c0c0c0",
                    dataType: "simple",
                    axesLabels: [
                        0: entryCount.keySet().collect { fmt.format(it) } ,
                        1: [ 0, entryCount.values().max() ]
                    ],
                    data: entryCount.values().asList()
                 )



    }



    def feedburner = { attr ->

        if (grailsApplication.config.http.usefeedburner) {
            out << """
			<p style='margin-top: 5px'>
					<img src="${grailsApplication.config.http.feedburner_stats_url}" height="26" width="88" style="border:0" alt="Feedburner Stats" />
			</p>
			"""
        }


    }


}