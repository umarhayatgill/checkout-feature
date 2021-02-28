package com.checkout

object PricingService {
    private val discountedItems = mapOf(
        "001" to 100,
        "002" to 80,
        "003" to 50,
        "004" to 30
    )

    fun calculateTotalCost(items: List<String>): Int {
        return items.sumBy { discountedItems[it]!! }
    }
}