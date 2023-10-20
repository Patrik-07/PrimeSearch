package mbh.primesearch.controllers

import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/search")
private class PrimeSearchController {
    @PostMapping("/start")
    fun startSearch(@RequestParam(required = false, defaultValue = "1") threadsCount: Number) {

    }

    @GetMapping("/list")
    fun listPrimes(): List<Number> {
        return listOf(1, 2, 3)
    }

    @GetMapping("/stop")
    fun stopSearch() {

    }
}
