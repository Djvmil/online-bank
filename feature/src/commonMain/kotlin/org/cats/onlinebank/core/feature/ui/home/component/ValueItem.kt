package org.cats.onlinebank.core.feature.ui.home.component

import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import onlinebank.feature.generated.resources.Res
import onlinebank.feature.generated.resources.baseline_arrow_forward_ios_24
import onlinebank.feature.generated.resources.feature_amount_value
import org.cats.onlinebank.core.common.utils.Utils.roundTo
import org.cats.onlinebank.core.feature.ui.home.model.UiAccountModel
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun ValueItem(
    item: UiAccountModel,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        Row(
            modifier = Modifier
                .height(50.dp)
                .padding(start = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.weight(2f),
                text = item.label,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                modifier = Modifier.weight(1.5f),
                text = stringResource(Res.string.feature_amount_value, item.balance.roundTo(2)),
                maxLines = 1,
                style = TextStyle(
                    color = Color.LightGray,
                    textAlign = TextAlign.End
                )
            )

            Icon(
                modifier = Modifier
                    .weight(0.5f).
                height(14.dp),
                painter = painterResource(Res.drawable.baseline_arrow_forward_ios_24),
                contentDescription = null,
            )
        }
    }
}