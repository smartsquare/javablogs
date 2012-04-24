import grails.test.mixin.*

import org.junit.Test
import org.springframework.cache.ehcache.EhCacheFactoryBean

@TestFor(FeedService)
@Mock([Blog, BlogEntry])
class FeedServiceTests {

	@Before
	public void setUp() {
		String.metaClass.encodeAsMD5 = { return delegate }
	}

	@Test
	public void testGetHtmlForUrl() throws Exception {
		// given
		String url = "http://www.google.de"

		// when
		def html = service.getHtmlForUrl(url)

		// then
		assertNotNull(html)
		assert html =~ "<html.*>"
	}

	@Test
	public void testGetFeedInfoFromHtml() throws Exception {
		// when
		def feedInfo = service.getFeedInfoFromHtml(feedStr, false)

		// then
		assertEquals('Grails New Plugins Feed', feedInfo.title)
		assertEquals('New and recently updated Grails Plugins', feedInfo.description)
		assertEquals('rss_2.0', feedInfo.type)
		assertNull(feedInfo.author)
		assertEquals('Grails Plugin jQuery mobile framework resource files', feedInfo.entries[0].title)
		assertEquals('http://grails.org/plugin/jquery-mobile', feedInfo.entries[0].link)
	}

	@Test
	public void testUpdateFeed() throws Exception {
		// given
		def feedInfo = service.getFeedInfoFromHtml(feedStr, false)
		// mock encodeAsMD5
		feedInfo.entries[0].summary.metaClass.encodeAsMD5 = { return delegate }
		def blog = new Blog(feedUrl: "http://www.smartsquare.de/blog", title: "Some blog")

		// when
		service.updateFeed(blog, feedInfo)

		// then
		assertEquals(1, blog.blogEntries.size())
	}

	@Test
	public void testUpdateLists() throws Exception {
		defineBeans { listCache(EhCacheFactoryBean) }
		
		// given
		def feedInfo = new FeedInfo(title: "Grails ML", description: "Grails ML", author: "Max Muster", type: "rss_2.0")
		def feedEntry = new FeedEntry(title: "Some grails problem", description: "Some grails problem", 
			summary: "Some grails problem", link: "http://www.smartsquare.de/ML", author: "Daniel Rosowski", 
			publishedDate: new Date())
		feedInfo.entries << feedEntry		
		service.metaClass.getFeedInfo = { String url, boolean b -> 
			return feedInfo 
		}
		
		// when
		def entries = service.updateLists()

		// then
		assertEquals(2, entries.size()) // create an entry for each list
	}

	String feedStr = '''
<?xml version="1.0" encoding="UTF-8"?>
<rss xmlns:content="http://purl.org/rss/1.0/modules/content/" xmlns:rdf="http://www.w3.org/1999/02/22-rdf-syntax-ns#" xmlns:itunes="http://www.itunes.com/dtds/podcast-1.0.dtd" xmlns:dc="http://purl.org/dc/elements/1.1/" xmlns:taxo="http://purl.org/rss/1.0/modules/taxonomy/" version="2.0">
  <channel>
    <title>Grails New Plugins Feed</title>
    <link>http://grails.org/Plugins</link>
    <description>New and recently updated Grails Plugins</description>
    <item>
      <title>Grails Plugin jQuery mobile framework resource files</title>
      <link>http://grails.org/plugin/jquery-mobile</link>
      <content:encoded>Plugin jQuery mobile framework resource files. To get started -- checkout http://jquerymobile.com/</content:encoded>
      <pubDate>Mon, 23 Apr 2012 11:56:30 GMT</pubDate>
      <guid>http://grails.org/plugin/jquery-mobile</guid>
      <dc:creator>Karol Balejko</dc:creator>
      <dc:date>2012-04-23T11:56:30Z</dc:date>
    </item>
  </channel>
</rss>	
'''
}
