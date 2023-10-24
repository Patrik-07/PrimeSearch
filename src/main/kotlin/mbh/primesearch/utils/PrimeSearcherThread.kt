package mbh.primesearch.utils

class PrimeSearcherThread(private val searcherManager: PrimeSearchManager, private var rangeIndex: Int) : Thread() {
    private val primeSearcher = PrimeSearcher(searcherManager.searchRange)
    private val sleepTime = 1000L

    override fun run() {
        super.run()

        while (!interrupted()) {
            sleep(sleepTime)

            val primes = primeSearcher.findPrimes(rangeIndex)

            synchronized(searcherManager) {
                searcherManager.primes.addAll(primes)
                updateRangeIndex()
            }
        }
    }

    private fun updateRangeIndex() {
        if (rangeIndex < searcherManager.minRangeIndex) {
            searcherManager.minRangeIndex = rangeIndex
        } else if (searcherManager.minRangeIndex == -1) {
            searcherManager.minRangeIndex = 0
        }
        rangeIndex = searcherManager.maxRangeIndex++
    }
}