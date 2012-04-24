import grails.test.mixin.Mock
import grails.test.mixin.TestFor

import org.junit.Before
import org.junit.Test

@TestFor(AccountController)
@Mock(FeedService)
class AccountControllerTests {

	@Before
	public void setUp() {
		//defineBeans { feedService(FeedService) }
	}

	@Test
	public void testAddFeedInvalidFeed() throws Exception {
		// given
		params.feedUrl = "invalidUrl"

		// when
		controller.addFeed()

		// then
		assertEquals("/account/edit", response.redirectedUrl)
		assertEquals(AccountController.INVALID_FEED_URL, flash.message)
	}

	@Test
	public void testAddFeedSuccess() throws Exception {
		// given
		params.feedUrl = "invalidUrl"

		// when
		controller.addFeed()

		// then
		assertEquals("/account/edit", response.redirectedUrl)
	}
}
