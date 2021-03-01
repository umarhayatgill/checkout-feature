package com.checkout

class PricingService {
    private val itemToPricePairs = mapOf(
        "001" to 100,
        "002" to 80,
        "003" to 50,
        "004" to 30
    )

    fun calculateTotalCost(items: List<String>): Int {
        return items.sumBy { itemToPricePairs[it]!! }
    }
}