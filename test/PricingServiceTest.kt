package com.checkout

import org.junit.Test
import kotlin.test.assertEquals

class PricingServiceTest {

    @Test
    fun should_calculate_cost_for_three_rolex_watches() {
        // given
        val pricingService = PricingService();

        // when
        val discount = pricingService.calculateTotalCost(listOf("001", "001", "001"))

        // then
        assertEquals(discount, 300)
    }

    @Test
    fun should_calculate_cost_for_two_rolex_and_one_casio_watches() {
        // given
        val pricingService = PricingService();

        // when
        val discount = pricingService.calculateTotalCost(listOf("001", "001", "004"))

        // then
        assertEquals(discount, 230)
    }
}