package mbh.primesearch.services

import mbh.primesearch.utils.*
import org.springframework.stereotype.Service
import java.util.SortedSet

@Service
class PrimeSearchService {
    private val searcherManager = PrimeSearchManager()
    private val minimumPrimeCount = 1000
    private val maximumThreadCount = 5

    fun startSearch(threadCount: Int) {
        if (threadCount > maximumThreadCount) {
            throw ThreadCountExceededException()
        }
        searcherManager.startSearch(threadCount)
    }

    fun getPrimes(min: Int, max: Int): List<Int> {
        synchronized(searcherManager) {
            checkValidRange(min, max)
            checkRangeBound(min, max)

            val primes: List<Int> = filterSetByRange(searcherManager.primes, min, max)

            checkPrimeCount(primes)

            return primes
        }
    }

    fun stopSearch() {
        searcherManager.stopSearch()
    }

    private fun checkValidRange(min: Int, max: Int) {
        if (min > max) {
            throw InvalidRangeException()
        }
    }

    private fun checkRangeBound(min: Int, max: Int) {
        val isMinInRange = min in searcherManager.rangeLimit
        val isMaxInRange = max in searcherManager.rangeLimit

        if (!isMinInRange || !isMaxInRange) {
            throw RangeBoundExceededException()
        }
    }

    private fun checkPrimeCount(primes: List<Int>) {
        if (primes.size > minimumPrimeCount) {
            throw PrimeCountExceededException()
        }
    }

    private fun filterSetByRange(allPrimes: SortedSet<Int>, min: Int, max: Int): List<Int> {
        val primes = mutableListOf<Int>()

        for (prime in allPrimes) {
            if (prime in min .. max) {
                primes.add(prime)
            }
        }

        return primes
    }
}