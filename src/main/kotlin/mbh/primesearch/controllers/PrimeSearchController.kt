package mbh.primesearch.controllers

import mbh.primesearch.services.PrimeSearchService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/search")
class PrimeSearchController(private val primeSearchService: PrimeSearchService) {
    @PostMapping("/start", produces = ["application/json"])
    fun startSearch(@RequestParam(required = false, defaultValue = "1") threads: Int): ResponseEntity<*> {
        return try {
            primeSearchService.startSearch(threads)
            ResponseEntity.ok("Searching is started.")
        } catch (ex: Exception) {
            ResponseEntity.badRequest().body(ex.message)
        }
    }

    @GetMapping("/list", produces = ["application/json"])
    fun listPrimes(@RequestParam(required = true) min: Int, @RequestParam(required = true) max: Int): ResponseEntity<*> {
        return try {
            val primes = primeSearchService.getPrimes(min, max)
            ResponseEntity.ok(primes)
        } catch (ex: Exception) {
            ResponseEntity.badRequest().body(ex.message)
        }
    }

    @PostMapping("/stop", produces = ["application/json"])
    fun stopSearch(): ResponseEntity<String>  {
        primeSearchService.stopSearch()
        return ResponseEntity.ok("Searching is stopped.")
    }
}
