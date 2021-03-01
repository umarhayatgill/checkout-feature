package com.checkout

import org.junit.Test
import kotlin.test.assertEquals

class DiscountServiceTest {

    @Test
    fun should_give_discount_for_three_rolex() {
        // given
        val discountService = DiscountService();

        // when
        val discount = discountService.calculateDiscount(listOf("001", "001", "001"))

        // then
        assertEquals(discount, 100)
    }

    @Test
    fun should_give_discount_for_two_michael_kors() {
        // given
        val discountService = DiscountService();

        // when
        val discount = discountService.calculateDiscount(listOf("002", "002"))

        // then
        assertEquals(discount, 40)
    }

    @Test
    fun should_give_no_discount_if_it_does_not_meet_discount_criteria() {
        // given
        val discountService = DiscountService();

        // when
        val discount = discountService.calculateDiscount(listOf("001", "001", "002"))

        // then
        assertEquals(discount, 0)
    }
}