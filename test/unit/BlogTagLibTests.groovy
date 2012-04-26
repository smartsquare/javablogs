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
		assertEquals "Jetzt gerade", out
	}

	void testDateFromNowOneDayAgo() {
		// given
		Calendar cal = Calendar.getInstance()
		cal.add(Calendar.DAY_OF_MONTH, -1)

		// when
		def out = applyTemplate('<g:dateFromNow date="${date}" />', [date: cal.time])

		// then
		assertEquals "Vor 1 Tag", out
	}

	void testDateFromNowTwoDaysAgo() {
		// given
		Calendar cal = Calendar.getInstance()
		cal.add(Calendar.DAY_OF_MONTH, -2)

		// when
		def out = applyTemplate('<g:dateFromNow date="${date}" />', [date: cal.time])

		// then
		assertEquals "Vor 2 Tagen", out
	}

	void testDateFromNowOneHourAgo() {
		// given
		Calendar cal = Calendar.getInstance()
		cal.add(Calendar.HOUR, -1)

		// when
		def out = applyTemplate('<g:dateFromNow date="${date}" />', [date: cal.time])

		// then
		assertEquals "Vor 1 Stunde", out
	}

	void testDateFromNowTwoHoursAgo() {
		// given
		Calendar cal = Calendar.getInstance()
		cal.add(Calendar.HOUR, -2)

		// when
		def out = applyTemplate('<g:dateFromNow date="${date}" />', [date: cal.time])

		// then
		assertEquals "Vor 2 Stunden", out
	}

	void testDateFromNowOneMinuteAgo() {
		// given
		Calendar cal = Calendar.getInstance()
		cal.add(Calendar.MINUTE, -1)

		// when
		def out = applyTemplate('<g:dateFromNow date="${date}" />', [date: cal.time])

		// then
		assertEquals "Vor 1 Minute", out
	}

	void testDateFromNowTwoMinutesAgo() {
		// given
		Calendar cal = Calendar.getInstance()
		cal.add(Calendar.MINUTE, -2)

		// when
		def out = applyTemplate('<g:dateFromNow date="${date}" />', [date: cal.time])

		// then
		assertEquals "Vor 2 Minuten", out
	}


	void testDateFromNowTwoHoursFromNow() {
		// given
		Calendar cal = Calendar.getInstance()
		cal.add(Calendar.HOUR, 1)
		cal.add(Calendar.MINUTE, 46)

		// when
		def out = applyTemplate('<g:dateFromNow date="${date}" />', [date: cal.time])

		// then
		assertEquals "In 1 Stunde 45 Minuten", out
	}
}
