import grails.test.mixin.Mock
import grails.test.mixin.TestFor

import net.sf.ehcache.Element

import org.junit.Before
import org.junit.Test

@TestFor(ThumbnailService)
@Mock(SystemConfig)
class ThumbnailServiceTests {

	def thumbnailUser = ""
	def thumbnailKey = ""

	@Before
	public void setUp() {
		mockCodec(org.codehaus.groovy.grails.plugins.codecs.MD5Codec)
		service.thumbCache = []as Set
		service.thumbCache.metaClass.put { element ->
			delegate.add(element)
		}

		if(!thumbnailUser || !thumbnailKey) {
			fail("Need to set thumbnail API user and key first!")
		}
		new SystemConfig(settingName: "thumbnail.user", settingValue: thumbnailUser).save()
		new SystemConfig(settingName: "thumbnail.key", settingValue: thumbnailKey).save()
	}

	@Test
	public void testImageFetch() {
		// when
		service.fetchThumbnailsToCache 1, "http://www.smartsquare.de"
		
		// then
		def iterator = service.thumbCache.iterator()
		Element element = iterator.next()
		assertTrue(element.getKey().toString().endsWith("-small"))
		element = iterator.next()
		assertTrue(element.getKey().toString().endsWith("-large"))
	}
}
