package mbh.primesearch.controllers

import mbh.primesearch.services.PrimeSearchService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/search")
private class PrimeSearchController(private val primeSearchService: PrimeSearchService) {
    @PostMapping("/start")
    fun startSearch(@RequestParam(required = false, defaultValue = "1") threadsCount: Number) {
        primeSearchService.startSearch()
    }

    @GetMapping("/list")
    fun listPrimes(): List<Long> {
        return primeSearchService.collectedPrimeNumbers
    }

    @GetMapping("/stop")
    fun stopSearch() {
        primeSearchService.stopSearch()
    }
}
