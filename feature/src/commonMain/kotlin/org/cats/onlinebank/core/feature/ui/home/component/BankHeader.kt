package org.cats.onlinebank.core.feature.ui.home.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.cacts.onlinebank.feature.resources.Res
import org.cacts.onlinebank.feature.resources.baseline_expand_less_24
import org.cacts.onlinebank.feature.resources.expand_more
import org.cacts.onlinebank.feature.resources.feature_amount_value
import org.cats.onlinebank.core.common.utils.Utils.roundTo
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun BankHeader(
    modifier: Modifier = Modifier,
    label: String,
    amount: Double? = null,
    isExpanded: Boolean? = null,
    onExpandClick: () -> Unit = {},
    headerExpandableItem: HeaderExpandableItem = HeaderExpandableItem.LEVEL_1,
) {
    Row (
        modifier = Modifier
            .height(60.dp)
            .padding(horizontal = 16.dp)
            .then(
                when (headerExpandableItem) {
                    HeaderExpandableItem.LEVEL_1 ->
                        modifier

                    HeaderExpandableItem.LEVEL_2 ->
                        modifier
                            .padding(start = 8.dp)
                }
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        val textstyle = when(headerExpandableItem) {
            HeaderExpandableItem.LEVEL_1 -> TextStyle(
                fontWeight = FontWeight.Medium,
                fontSize = 24.sp,
                letterSpacing = 0.5.sp,
                color = Color.LightGray
            )
            HeaderExpandableItem.LEVEL_2 -> TextStyle(
                fontWeight = FontWeight.Normal,
                fontSize = 18.sp,
                letterSpacing = 0.5.sp,
                color = Color.Black
            )
        }
        Text(
            modifier = Modifier.weight(6f),
            text = label,
            style = textstyle
        )

        if (amount != null) {
            Text(
                modifier = Modifier.weight(4f),
                text = stringResource(Res.string.feature_amount_value, amount.roundTo(2)),
                maxLines = 1,
                style = TextStyle(
                    fontSize = 18.sp,
                    color = Color.LightGray,
                    textAlign = TextAlign.End
                )
            )
        }

        if (isExpanded != null) {
            Icon(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 1.dp)
                    .clickable { onExpandClick.invoke() },
                painter = painterResource(
                    if (isExpanded == true)
                        Res.drawable.expand_more
                    else Res.drawable.baseline_expand_less_24
                ),
                contentDescription = null,
            )
        }
    }
}