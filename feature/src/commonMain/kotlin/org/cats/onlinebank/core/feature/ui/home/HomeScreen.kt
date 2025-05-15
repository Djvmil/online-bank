package org.cats.onlinebank.core.feature.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.cacts.onlinebank.feature.resources.Res
import org.cacts.onlinebank.feature.resources.feature_error_label
import org.cacts.onlinebank.feature.resources.feature_loading_label
import org.cacts.onlinebank.feature.resources.feature_my_accounts_label
import org.cats.onlinebank.core.common.model.OBError
import org.cats.onlinebank.core.common.model.OBResult
import org.cats.onlinebank.core.feature.ui.home.component.BankHeader
import org.cats.onlinebank.core.feature.ui.home.component.ExpandableItem
import org.cats.onlinebank.core.feature.ui.home.component.HeaderExpandableItem
import org.cats.onlinebank.core.feature.ui.home.model.UiBankListModel
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = koinViewModel(),
    onDetailClick: (String, String) -> Unit = { _, _ -> }) {
    val uiState by viewModel.uiBanks.collectAsState()
    HomeContent(uiState = uiState, modifier = Modifier, onDetailClick = onDetailClick)
}

@Composable
fun HomeContent(
    uiState: OBResult<List<UiBankListModel>, OBError>,
    modifier: Modifier,
    onDetailClick: (String, String) -> Unit = { _, _ -> },
) {

    Column(modifier = modifier.background(Color.LightGray.copy(0.1f)).fillMaxWidth()) {
        Row (
              modifier = Modifier
                  .fillMaxWidth()
                  .padding(
                      top = 30.dp,
                      bottom = 10.dp)
        ){
            Text(
                modifier = Modifier
                    .padding(top = 30.dp, start = 16.dp),
                text = stringResource(Res.string.feature_my_accounts_label),
                style = TextStyle(
                    fontSize = 40.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    textAlign = TextAlign.Start
                )
            )
        }
         when (uiState) {
            OBResult.Loading -> {
                Text(
                    modifier = Modifier
                          .padding(top = 100.dp)
                        .fillMaxSize(),
                    text = stringResource(Res.string.feature_loading_label),
                    style = TextStyle(
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.Black,
                        textAlign = TextAlign.Center
                    )
                )
            }

            is OBResult.Failure -> {
                Text(
                    modifier = Modifier
                        .padding(top = 100.dp)
                        .fillMaxSize(),
                    text = stringResource(Res.string.feature_error_label),
                    style = TextStyle(
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.Red,
                        textAlign = TextAlign.Center
                    )
                )
            }

            is OBResult.Success -> {
                val values = uiState.value
                values.forEach { item ->
                    BankHeader(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        label = item.label
                    )

                    val expandStates = remember(item.banks.size) {
                        List(item.banks.size) { mutableStateOf(false) }
                    }
                    LazyColumn {
                        itemsIndexed(item.banks) { idx, bank ->
                            ExpandableItem(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                item = bank,
                                headerExpandableItem = HeaderExpandableItem.LEVEL_2,
                                isExpanded = expandStates[idx].value,
                                onExpandClick = { expandStates[idx].value = !expandStates[idx].value },
                                onDetailClick = onDetailClick
                            )
                        }
                    }
                }
            }
        }
    }
}
