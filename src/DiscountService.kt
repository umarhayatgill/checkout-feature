package com.checkout

class DiscountService {

    private data class DiscountedItem(val discountPerItems: Int, val discount: Int)
    private data class DiscountedItemCount(val item: String, val count: Int)

    private val discountedItems = mapOf(
        "001" to DiscountedItem(3, 100),
        "002" to DiscountedItem(2, 40)
    )

    fun calculateDiscount(items: List<String>): Int {
        val totalDiscountableItems = items
            .filter { discountedItems.containsKey(it) }
            .groupingBy { it }
            .eachCount()
            .map { DiscountedItemCount(it.key, it.value) }

        var discount = 0
        totalDiscountableItems.forEach {
            val totalNumberOfEligibleDiscountedSets = it.count / discountedItems[it.item]!!.discountPerItems
            val discountPerSet = discountedItems[it.item]!!.discount
            discount += totalNumberOfEligibleDiscountedSets * discountPerSet
        }
        return discount
    }
}