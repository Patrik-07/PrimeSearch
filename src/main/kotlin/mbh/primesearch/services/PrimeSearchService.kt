package mbh.primesearch.services

import mbh.primesearch.repositories.PrimeRepository
import org.springframework.stereotype.Service
import java.lang.Exception
import kotlin.math.sqrt

@Service
class PrimeSearchService(private val primeRepository: PrimeRepository) {
    val collectedPrimeNumbers: List<Long> get() = primeRepository.collectedPrimeNumbers
    private val primeNumbers = mutableListOf<Long>()

    fun getPrimeNumbers(min: Int, max: Int): List<Long> {
        if (max - min > 1000) {
            throw Exception("To big interval")
        }

        return primeNumbers.subList(min, max)
    }

    fun startSearch() {

    }

    fun stopSearch() {

    }

    fun isPrime(number: Long): Boolean {
        if (number <= 1) return false
        if (number <= 3) return true
        if (number % 2 == 0L || number % 3 == 0L) return false

        var i = 5L
        while (i * i <= number) {
            if (number % i == 0L || number % (i + 2) == 0L) {
                return false
            }
            i += 6
        }

        return true
    }

    fun findNthPrime(n: Long, startFrom: Long): Long {
        if (n <= 0) {
            throw IllegalArgumentException("N must be a positive integer.")
        }

        var number = startFrom + 1

        while (true) {
            if (isPrime(number)) {
                return number
            }
            number++
        }
    }
}