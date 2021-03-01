package com.checkout

import org.junit.Test
import org.mockito.BDDMockito.given
import org.mockito.Mockito.mock
import kotlin.test.assertEquals

class CheckoutServiceTest {

    @Test
    fun should_be_able_to_checkout() {
        // given
        val pricingService = mock(PricingService::class.java)
        val discountService = mock(DiscountService::class.java)

        given(pricingService.calculateTotalCost(listOf("001", "002"))).willReturn(100)
        given(discountService.calculateDiscount(listOf("001", "002"))).willReturn(20)

        // when
        val checkoutService = CheckoutService(discountService, pricingService)
        val price = checkoutService.checkoutWatches(listOf("001", "002"))

        // then
        assertEquals(price, 80)
    }

    @Test
    fun should_return_zero_if_no_items_provided() {
        // given
        val pricingService = mock(PricingService::class.java)
        val discountService = mock(DiscountService::class.java)

        given(pricingService.calculateTotalCost(emptyList())).willReturn(0)
        given(discountService.calculateDiscount(emptyList())).willReturn(0)

        // when
        val checkoutService = CheckoutService(discountService, pricingService)
        val price = checkoutService.checkoutWatches(emptyList())

        // then
        assertEquals(price, 0)
    }
}