package mbh.primesearch.utils

class PrimeSearchManager {
    private lateinit var searcherThreads: List<PrimeSearcherThread>
    private var isSearchingStarted = false

    var minRangeIndex: Int = -1
    var maxRangeIndex: Int = -1
    var primes = sortedSetOf<Int>()

    val searchRange get() = 100
    val rangeLimit get() = (minRangeIndex * searchRange) until (maxRangeIndex * searchRange)

    fun startSearch(threadCount: Int) {
        if (isSearchingStarted) {
            throw SearchingStartedException()
        }

        setRangeIndexes()

        searcherThreads = (0 until threadCount).map {
            PrimeSearcherThread(this, ++maxRangeIndex)
        }.toList()

        for (searcher in searcherThreads) {
            searcher.start()
        }

        isSearchingStarted = true
    }

    fun stopSearch() {
        for (searcher in searcherThreads) {
            searcher.interrupt()
        }

        isSearchingStarted = false
    }

    private fun setRangeIndexes() {
        if (primes.isEmpty()) {
            minRangeIndex = -1
            maxRangeIndex = -1
        } else {
            minRangeIndex = getRangeIndex(primes.first())
            maxRangeIndex = getRangeIndex(primes.last())
        }
    }

    private fun getRangeIndex(prime: Int): Int {
        return prime.floorDiv(searchRange)
    }
}