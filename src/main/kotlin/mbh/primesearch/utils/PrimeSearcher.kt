package mbh.primesearch.utils

class PrimeSearcher(private val range: Int) {
    fun findPrimes(rangeIndex: Int): List<Int> {
        val primes = mutableListOf<Int>()

        val range = convertToRange(rangeIndex)
        for (number in range) {
            if (isPrime(number)) {
                primes.add(number)
            }
        }

        return primes
    }

    private fun convertToRange(rangeIndex: Int): IntRange {
        if (rangeIndex < 0) {
            throw IllegalArgumentException("Input must be a non-negative integer.")
        }

        val start = rangeIndex * range
        val end = start + (range - 1)

        return start..end
    }

    private fun isPrime(number: Int): Boolean {
        if (number <= 1) return false
        if (number <= 3) return true
        if (number % 2 == 0 || number % 3 == 0) return false

        var i = 5
        while (i * i <= number) {
            if (number % i == 0 || number % (i + 2) == 0) {
                return false
            }
            i += 6
        }

        return true
    }
}