package org.cats.onlinebank.feature

import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.cats.onlinebank.core.common.ResourcesProvider
import org.cats.onlinebank.core.common.model.OBError
import org.cats.onlinebank.core.common.model.OBResult
import org.cats.onlinebank.feature.ui.home.HomeContent
import org.cats.onlinebank.feature.ui.home.model.toUiBankListModel
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HomeScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()
    val appContext = InstrumentationRegistry.getInstrumentation().targetContext

    private val resProvider =  ResourcesProvider(context = appContext)

    @Test
    fun test_loading_state() {
        composeTestRule.setContent {
            HomeContent(
                uiState = OBResult.Loading,
                modifier = Modifier,
                onDetailClick = { _, _ -> }
            )
        }
        composeTestRule.onNodeWithText("Loading...").assertIsDisplayed()
    }

    @Test
    fun test_error_state() {
        composeTestRule.setContent {
            HomeContent(
                uiState = OBResult.Failure(OBError("Error message")),
                modifier = Modifier,
                onDetailClick = { _, _ -> }
            )
        }

        composeTestRule.onNodeWithText("Error").assertIsDisplayed()
    }

    @Test
    fun test_success_state() {
        val mockData = FAKE_DATA.fakeBanksResult.toUiBankListModel(resProvider)

        composeTestRule.setContent {
            HomeContent(
                uiState = OBResult.Success(mockData),
                modifier = Modifier,
                onDetailClick = { _, _ ->

                }
            )
        }

        composeTestRule.onNodeWithText("CA Languedoc").assertIsDisplayed()
    }
}