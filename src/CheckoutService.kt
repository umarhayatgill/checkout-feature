package com.checkout

object CheckoutService {

    fun checkoutWatches(items: List<String>): Int {
        val totalCost = PricingService.calculateTotalCost(items);
        val discount = DiscountService.calculateDiscount(items)
        return totalCost - discount;
    }
}