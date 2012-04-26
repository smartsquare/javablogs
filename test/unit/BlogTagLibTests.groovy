@TestFor(BlogTagLib)
class BlogTagLibTests extends GroovyTestCase {

	void testNiceDate() {
		// given
		Date now = new Date()

		// when
		def out = applyTemplate('<g:niceDate date="${date}" />', [date: now])

		// then
		assertEquals(new java.text.SimpleDateFormat("EEE, d MMM yyyy HH:mm").format(now), out)

	}

	void testDateFromNowRightNow() {
		// given
		Calendar cal = Calendar.getInstance()

		// when
		def out = applyTemplate('<g:dateFromNow date="${date}" />', [date: cal.time])

		// then
		assertEquals "Right now", out
	}

	void testDateFromNowOneHourAgo() {
		// given
		Calendar cal = Calendar.getInstance()
		cal.add(Calendar.HOUR, -1)

		// when
		def out = applyTemplate('<g:dateFromNow date="${date}" />', [date: cal.time])

		// then
		assertEquals "1 hour ago", out
	}

	void testDateFromNowOneHourFromNow() {
		// given
		Calendar cal = Calendar.getInstance()
		cal.add(Calendar.HOUR, 1)
		cal.add(Calendar.MINUTE, 1)

		// when
		def out = applyTemplate('<g:dateFromNow date="${date}" />', [date: cal.time])

		// then
		assertEquals "1 hour from now", out
	}
}
