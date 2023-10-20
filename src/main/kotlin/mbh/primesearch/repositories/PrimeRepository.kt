package mbh.primesearch.repositories

import org.springframework.stereotype.Repository

@Repository
class PrimeRepository {
    private val primeNumbers = mutableListOf<Long>()

    val collectedPrimeNumbers: List<Long> get() = primeNumbers
}