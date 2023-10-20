package mbh.primesearch.services

import org.springframework.stereotype.Service

@Service
class PrimeSearchService {
    private val primeNumbers = mutableListOf<Long>()

    val collectedPrimeNumbers: List<Long> get() = primeNumbers

    fun startSearch() {

    }

    fun stopSearch() {

    }
}