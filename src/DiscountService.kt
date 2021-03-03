package com.checkout

class DiscountService {

    private data class DiscountCriteria(val noOfUnitsToBeBoughtTogetherForDiscount: Int, val discountPerSet: Int)
    private data class DiscountedItemUnitCount(val item: String, val count: Int)

    private val discountedItems = mapOf(
        "001" to DiscountCriteria(3, 100),
        "002" to DiscountCriteria(2, 40)
    )

    fun calculateDiscount(items: List<String>): Int {
        val totalDiscountableItems = items
            .filter { discountedItems.containsKey(it) }
            .groupingBy { it }
            .eachCount()
            .map { DiscountedItemUnitCount(it.key, it.value) }

        var discount = 0
        totalDiscountableItems.forEach {
            val totalNumberOfEligibleDiscountedSets = it.count / discountedItems[it.item]!!.noOfUnitsToBeBoughtTogetherForDiscount
            val discountPerSet = discountedItems[it.item]!!.discountPerSet
            discount += totalNumberOfEligibleDiscountedSets * discountPerSet
        }
        return discount
    }
}