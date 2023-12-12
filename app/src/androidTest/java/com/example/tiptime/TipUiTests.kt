package com.example.tiptime

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onSibling
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import com.example.tiptime.ui.theme.TipTimeTheme
import org.junit.Rule
import org.junit.Test
import java.text.NumberFormat

class TipUiTests {

    /**
     * The createComposeRule() function sets up a testing environment that enables you to
     * interact with and test Compose-based UI components within your Android app.
     * It initializes a testing rule object, which provides the necessary functionality
     * to launch Compose content for testing purposes, interact with the UI elements,
     * and perform assertions.
     */
    @get:Rule
    var composeTestRule = createComposeRule()

    @Test
    fun calculate_20_percent_tip_without_Roundup(){

        //setting the ui of TipTimeLayout composable under the TipTimeTheme theme for testing
        composeTestRule.setContent {
            TipTimeTheme {
                TipTimeLayout()
            }
        }

        //finding the composable which have specified text and then giving input based on that
        composeTestRule.onNodeWithText("Bill Amount")
            .performTextInput("10")
        composeTestRule.onNodeWithText("Tip Percentage")
            .performTextInput("20")

        //asserting that is there text on the screen which shows the expected result if not giving the error msg
        composeTestRule.onNodeWithText("Tip Amount: ${NumberFormat.getCurrencyInstance().format(2)}")
            .assertExists("No node with this text was found.")

    }

    @Test
    fun calculateTip_with_RoundUp(){

        //setting the content for ui testing
        composeTestRule.setContent {
            TipTimeTheme {
                TipTimeLayout()
            }
        }

        //testing the ui
        composeTestRule.onNodeWithText("Bill Amount")
            .performTextInput("100")

        composeTestRule.onNodeWithText("Tip Percentage")
            .performTextInput("18.8")

        composeTestRule.onNodeWithTag("RoundUpSwitch").performClick()

        val expectedTip = NumberFormat.getCurrencyInstance().format(19)

        composeTestRule.onNodeWithText("Tip Amount: $expectedTip").assertExists("not text found matching with $expectedTip")
    }
}