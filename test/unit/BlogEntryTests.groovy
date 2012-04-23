
@TestFor(BlogEntry)
class BlogEntryTests {

	@Test
	public void testIsGroovyRelatedFails() throws Exception {
		domain.title = "Foobar"
		assertFalse(domain.isGroovyRelated());

		domain.description = "Foobar"
		assertFalse(domain.isGroovyRelated());
	}

	@Test
	public void testIsGroovyRelatedSucceeds() throws Exception {
		domain.title = "Grails goodies";
		assertTrue(domain.isGroovyRelated());

		domain.description = "Grails goodies";
		assertTrue(domain.isGroovyRelated());
	}
}
