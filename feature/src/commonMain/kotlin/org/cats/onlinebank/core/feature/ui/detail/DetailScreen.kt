package org.cats.onlinebank.core.feature.ui.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import onlinebank.feature.generated.resources.Res
import onlinebank.feature.generated.resources.baseline_arrow_back_ios_24
import onlinebank.feature.generated.resources.feature_amount_value
import onlinebank.feature.generated.resources.feature_error_label
import onlinebank.feature.generated.resources.feature_loading_label
import onlinebank.feature.generated.resources.feature_my_accounts_label
import org.cats.onlinebank.core.common.model.OBResult
import org.cats.onlinebank.core.common.utils.Utils.roundTo
import org.cats.onlinebank.core.feature.ui.home.model.UiAccountModel
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun DetailScreen(
  viewModel: DetailViewModel = koinViewModel(),
  onBackClicked: () -> Unit
) {
  val uiState by viewModel.uiState.collectAsState()
  when (uiState) {
    OBResult.Loading -> {
      Text(
        modifier = Modifier.fillMaxSize(),
        text = stringResource(Res.string.feature_loading_label),
        style = TextStyle(
          fontSize = 60.sp,
          fontWeight = FontWeight.Medium,
          color = Color.Black,
          textAlign = TextAlign.Center
        )
      )
    }
    is OBResult.Failure -> {
      Text(
        modifier = Modifier.fillMaxSize(),
        text = stringResource(Res.string.feature_error_label),
        style = TextStyle(
          fontSize = 60.sp,
          fontWeight = FontWeight.Medium,
          color = Color.Red,
          textAlign = TextAlign.Center
        )
      )
    }
    is OBResult.Success -> {
      DetailContent(
        uiState = (uiState as OBResult.Success<UiAccountModel>).value,
        onBackClicked = onBackClicked
      )
    }
  }
}

@Composable
fun DetailContent(
  onBackClicked: () -> Unit,
  uiState: UiAccountModel
) {
    Column(modifier = Modifier.fillMaxWidth().background(Color.LightGray.copy(0.2f))) {
      Row(
        modifier = Modifier
          .fillMaxWidth()
          .clickable { onBackClicked.invoke() }
          .padding(
            top = 50.dp,
            bottom = 10.dp
          )
      ) {
        Icon(
          modifier = Modifier
            .padding(start = 4.dp),
          painter = painterResource(Res.drawable.baseline_arrow_back_ios_24),
          contentDescription = null,
          tint = Color.Blue.copy(0.5f)
        )
        Text(
          modifier = Modifier,
          text = stringResource(Res.string.feature_my_accounts_label),
          style = TextStyle(
            fontWeight = FontWeight.Medium,
            color = Color.Blue.copy(0.5f),
            textAlign = TextAlign.Start
          )
        )
      }

      Text(
        modifier = Modifier.fillMaxWidth(),
        text = stringResource(Res.string.feature_amount_value, uiState.balance.roundTo(2)),
        style = TextStyle(
          fontSize = 60.sp,
          fontWeight = FontWeight.Medium,
          color = Color.Black,
          textAlign = TextAlign.Center
        )
      )
      Text(
        modifier = Modifier
          .fillMaxWidth()
          .padding(top = 20.dp, bottom = 30.dp),
        text = uiState.label,
        style = TextStyle(
          fontSize = 28.sp,
          color = Color.Black,
          textAlign = TextAlign.Center
        )
      )

    LazyColumn(modifier = Modifier.fillMaxSize().background(Color.White)) {
        items(uiState.operations){ item ->
          Row(
            modifier = Modifier
              .weight(1f)
              .padding(10.dp)
              .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
          ) {
            Column(
              modifier = Modifier
                .weight(0.6f)
                .padding(10.dp)
                .fillMaxWidth(),
              horizontalAlignment = Alignment.Start,
              verticalArrangement = Arrangement.Center
            )  {
              Text(
                modifier = Modifier,
                text = item.title,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = TextStyle(
                  fontWeight = FontWeight.Medium,
                  fontSize = 16.sp,
                  color = Color.Black,
                  textAlign = TextAlign.Center
                )
              )
              Text(
                modifier = Modifier,
                text = item.date,
                style = TextStyle(
                  fontSize = 16.sp,
                  color = Color.Black,
                  textAlign = TextAlign.Center
                )
              )
            }
            Text(
              modifier = Modifier.weight(0.3f),
              text =  item.amount.plus(" €"),
              style = TextStyle(
                fontSize = 16.sp,
                color = Color.LightGray,
                textAlign = TextAlign.End
              )
            )
          }

          HorizontalDivider(
            modifier = Modifier
              .fillMaxWidth(),
            color = Color.LightGray,
            thickness = 1.dp
          )
        }
      }
    }

}