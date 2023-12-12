package com.example.tiptime

import junit.framework.TestCase.assertEquals
import org.junit.Test
import java.text.NumberFormat

/**
 * For testing  tip calculation
 */
class TipCalculatorTests {

    //testing TipCalculation without Roundup
    @Test
    fun calculateTip_20PercentWithoutRoundUp( ) {
        val amount = 10.0
        val percentage = 20.0
        val expectedTip = NumberFormat.getCurrencyInstance().format(2)
        val actualTip = calculateTip(amount,percentage,false)
        assertEquals(expectedTip,actualTip)
    }

    @Test
    fun calculateTip_20PercentWithRoundUp(){
        val amount = 100.0
        val percentage = 18.8
        val roundUp = true
        val expectedTip = NumberFormat.getCurrencyInstance().format(19)

        val actualTip = calculateTip(
            amount = amount,
            tipPercent = percentage,
            roundUp = roundUp
            )

        assertEquals(expectedTip, actualTip)

    }

}