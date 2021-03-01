package com.checkout

class CheckoutService(private val discountService: DiscountService,
                      private val pricingService: PricingService ) {

    fun checkoutWatches(items: List<String>): Int {
        val totalCost = pricingService.calculateTotalCost(items);
        val discount = discountService.calculateDiscount(items)
        return totalCost - discount
    }
}