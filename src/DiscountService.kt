package com.checkout

object DiscountService {
    private data class DiscountedItem(val discountPerItems: Int, val discount: Int)

    private val discountedItems = mapOf(
        "001" to DiscountedItem(3, 100),
        "002" to DiscountedItem(2, 40)
    )

    fun calculateDiscount(items: List<String>): Int {
        val totalDiscountableItems = items
            .filter { discountedItems.containsKey(it) }
            .groupingBy { it }
            .eachCount()

        var discount = 0
        totalDiscountableItems.entries.forEach {
            discount += (it.value / discountedItems[it.key]!!.discountPerItems) * discountedItems[it.key]!!.discount
        }
        return discount
    }
}