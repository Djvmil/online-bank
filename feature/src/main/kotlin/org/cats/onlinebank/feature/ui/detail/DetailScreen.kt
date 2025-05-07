package org.cats.onlinebank.feature.ui.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.cacts.onlinebank.feature.R
import org.cats.onlinebank.core.common.model.OBResult
import org.cats.onlinebank.core.data.source.remote.model.AccountApiModel
import org.koin.androidx.compose.koinViewModel
import java.util.Locale

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
        text = "Loading...",
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
        text = "Failure",
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
        uiState = (uiState as OBResult.Success<AccountApiModel>).value,
        onBackClicked = onBackClicked
      )
    }
  }
}

@Composable
fun DetailContent(
  onBackClicked: () -> Unit,
  uiState: AccountApiModel
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
          painter = painterResource(R.drawable.baseline_arrow_back_ios_24),
          contentDescription = null,
          tint = Color.Blue.copy(0.5f)
        )
        Text(
          modifier = Modifier,
          text = "Mes Comptes",
          style = TextStyle(
            fontWeight = FontWeight.Medium,
            color = Color.Blue.copy(0.5f),
            textAlign = TextAlign.Start
          )
        )
      }

      Text(
        modifier = Modifier.fillMaxWidth(),
        text =  String.format(Locale.getDefault(), "%.2f €", uiState.balance),
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
        itemsIndexed(uiState.operations,  { _, it -> it.hashCode() }){ id, item ->
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
                .weight(0.5f)
                .padding(10.dp)
                .fillMaxWidth(),
              horizontalAlignment = Alignment.Start,
              verticalArrangement = Arrangement.Center
            )  {
              Text(
                modifier = Modifier,
                text = item.title,
                style = TextStyle(
                  fontWeight = FontWeight.Medium,
                  fontSize = 16.sp,
                  color = Color.Black,
                  textAlign = TextAlign.Center
                )
              )
              Text(
                modifier = Modifier,
                //text = item.date.toDateHuman(),
                text = item.date,
                style = TextStyle(
                  fontSize = 16.sp,
                  color = Color.Black,
                  textAlign = TextAlign.Center
                )
              )
            }
            Text(
              modifier = Modifier.weight(0.5f),
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


@Preview
@Composable
fun DetailScreenPreview() {
  DetailContent(onBackClicked = {}, AccountApiModel(
    id = "1",
    balance = 1000.0,
    label = "label",
    holder = "holder",
    operations = emptyList(),
    contractNumber = "contract_number",
    productCode = "product_code",
    order = 1,
    role = 1
  ))
}