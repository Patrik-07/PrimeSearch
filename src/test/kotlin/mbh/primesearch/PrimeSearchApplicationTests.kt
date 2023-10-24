package mbh.primesearch

import mbh.primesearch.controllers.PrimeSearchController
import mbh.primesearch.services.PrimeSearchService
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpStatus

@SpringBootTest
class PrimeSearchApplicationTests {
	private val primeSearchService = PrimeSearchService()
	private val primeSearchController = PrimeSearchController(primeSearchService)

	@Test
	fun test_startSearch() {
		val expectedResponseMessage = "Searching is started."

		val response = primeSearchController.startSearch(3)

		val status = response.statusCode
		val responseMessage = response.body as String

		Assertions.assertEquals(expectedResponseMessage, responseMessage)
		Assertions.assertEquals(HttpStatus.OK, status)
	}

	@Test
	fun test_startSearch_twice() {
		val expectedResponseMessage = "The searching is already started."

		primeSearchController.startSearch(3)
		val response = primeSearchController.startSearch(3)

		val status = response.statusCode
		val responseMessage = response.body as String

		Assertions.assertEquals(expectedResponseMessage, responseMessage)
		Assertions.assertEquals(HttpStatus.BAD_REQUEST, status)
	}

	@Test
	fun test_startSearch_exceededThreads() {
		val expectedResponseMessage = "Too many threads. Please give correct thread count."

		val response = primeSearchController.startSearch(10)

		val status = response.statusCode
		val responseMessage = response.body as String

		Assertions.assertEquals(expectedResponseMessage, responseMessage)
		Assertions.assertEquals(HttpStatus.BAD_REQUEST, status)
	}

	@Test
	fun test_listPrimes() {
		primeSearchController.startSearch(3)
		Thread.sleep(2000)
		primeSearchController.stopSearch()

		val response = primeSearchController.listPrimes(0, 5)

		val primes = response.body as? List<Int>
		val status = response.statusCode

		Assertions.assertNotNull(primes)
		Assertions.assertEquals(primes, listOf(2, 3, 5))
		Assertions.assertEquals(HttpStatus.OK, status)
	}

	@Test
	fun test_listPrimes_invalidRange() {
		val expectedResponseMessage = "The range is invalid. Please give correct min/max values."

		primeSearchController.startSearch(3)
		Thread.sleep(2000)
		primeSearchController.stopSearch()

		val response = primeSearchController.listPrimes(10, 0)

		val responseMessage = response.body as String
		val status = response.statusCode

		Assertions.assertEquals(expectedResponseMessage, responseMessage)
		Assertions.assertEquals(HttpStatus.BAD_REQUEST, status)
	}

	@Test
	fun test_listPrimes_rangeBoundExceeded() {
		val expectedResponseMessage = "The searching doesn't reach this range yet."

		primeSearchController.startSearch(3)
		Thread.sleep(2000)
		primeSearchController.stopSearch()

		val response = primeSearchController.listPrimes(0, 800)

		val responseMessage = response.body as String
		val status = response.statusCode

		Assertions.assertEquals(expectedResponseMessage, responseMessage)
		Assertions.assertEquals(HttpStatus.BAD_REQUEST, status)
	}

	@Test
	fun test_searchStop() {
		val expectedResponseMessage = "Searching is stopped."

		primeSearchController.startSearch(3)
		val response = primeSearchController.stopSearch()

		val responseMessage = response.body as String
		val status = response.statusCode

		Assertions.assertEquals(expectedResponseMessage, responseMessage)
		Assertions.assertEquals(HttpStatus.OK, status)
	}
}
