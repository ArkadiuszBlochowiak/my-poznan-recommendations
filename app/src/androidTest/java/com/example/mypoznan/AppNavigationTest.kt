package com.example.mypoznan

import androidx.activity.ComponentActivity
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.v2.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import com.example.mypoznan.ui.MyPoznanApp
import org.junit.Rule
import org.junit.Test

class AppNavigationTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun compactView_verifyUsingBottomNavigation() {
        composeTestRule.setContent {
            MyPoznanApp(WindowWidthSizeClass.Compact)
        }

        composeTestRule.onNodeWithStringTag(R.string.bottom_navigation).assertIsDisplayed()
    }

    @Test
    fun mediumView_verifyUsingNavigationRail() {
        composeTestRule.setContent {
            MyPoznanApp(WindowWidthSizeClass.Medium)
        }

        composeTestRule.onNodeWithStringTag(R.string.navigation_rail).assertIsDisplayed()
    }

    @Test
    fun expandedView_verifyUsingNavigationDrawer() {
        composeTestRule.setContent {
            MyPoznanApp(WindowWidthSizeClass.Expanded)
        }

        composeTestRule.onNodeWithStringTag(R.string.navigation_drawer).assertIsDisplayed()
    }
}