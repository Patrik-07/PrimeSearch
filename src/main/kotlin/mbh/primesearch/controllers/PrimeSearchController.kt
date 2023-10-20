package mbh.primesearch.controllers

import mbh.primesearch.services.PrimeSearchService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/search")
private class PrimeSearchController(private val primeSearchService: PrimeSearchService) {
    @PostMapping("/start")
    fun startSearch(@RequestParam(required = false, defaultValue = "1") threadsCount: Int) {
        primeSearchService.startSearch()
    }

    // @RequestParam(required = true) min: Int, @RequestParam(required = true) max: Int
    @GetMapping("/list")
    fun listPrimes(): Long {
        return primeSearchService.findNthPrime(1001, 7919)
    }

    @GetMapping("/stop")
    fun stopSearch() {
        primeSearchService.stopSearch()
    }
}
