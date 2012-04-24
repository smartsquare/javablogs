import grails.test.mixin.*

import org.junit.Test
import org.springframework.cache.ehcache.EhCacheFactoryBean

@TestFor(EntriesService)
@Mock(BlogEntry)
class EntriesServiceTests {

	def entries = null

	@Before
	public void setUp() {
		entries = createRandomEntries(10)
		entries.each {
			assertNotNull(it.save())
		}
	}

	@Test
	public void testLimitEntries() throws Exception {
		// when
		def limitedEntries = service.limitEntries(entries)

		// then
		assertEquals(3, limitedEntries.size())
	}

	@Test
	public void testGetEndlessEntries() throws Exception {
		defineBeans { entriesCache(EhCacheFactoryBean) }

		// when
		def result = service.getEndlessEntries([:])

		// then
		assertEquals(10, result.size())

	}

	@Test
	public void testGetRecentEntries() throws Exception {
		defineBeans { entriesCache(EhCacheFactoryBean) }

		// when
		def result = service.getRecentEntries(1)

		// then
		assertEquals(3, result.size())

	}

	@Test
	public void testGetPopularEntries() throws Exception {
		defineBeans { entriesCache(EhCacheFactoryBean) }

		// when
		def result = service.getPopularEntries()

		// then
		assertEquals(2, result.size())

	}

	def createRandomEntries(int count) {
		def entries = []

		def blog = new Blog(feedUrl: "http://www.smartsquare.de/blog", title: "Some blog")
		for(int i = 0; i < count; i++) {
			def title = (i % 2 == 0 ? "Grails " : "Groovy ") + i
			def link = "http://www.smartsquare.de/blog/" + UUID.randomUUID().toString();

			def entry = new BlogEntry(blog: blog, title: title, description: title, link: link, dateAdded: aWeekAgo())
			entries.add(entry)
		}

		entries.get(2).dateAdded = new Date()
		entries.get(2).hitCount = 2
		entries.get(4).dateAdded = new Date()
		entries.get(8).dateAdded = new Date()
		entries.get(8).hitCount = 1

		return entries
	}

	def aWeekAgo() {
		def cal = Calendar.getInstance()
		cal.add(Calendar.WEEK_OF_YEAR, -1)
		return cal.getTime()
	}
}
