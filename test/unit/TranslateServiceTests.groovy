import org.junit.Before
import org.junit.Ignore
import org.junit.Test

import grails.test.*
import grails.test.mixin.*

@TestFor(TranslateService)
class TranslateServiceTests {

	@Before
    public void setUp() {
        mockCodec(org.codehaus.groovy.grails.plugins.codecs.URLCodec)
    }

	// ignore for now, since google translation api is a paid service
	@Test
	@Ignore
    void testTranslate() {
      def lang = service.getLanguage("http://www.groovy.org.es/")
      assertEquals "es", lang
    }
}
