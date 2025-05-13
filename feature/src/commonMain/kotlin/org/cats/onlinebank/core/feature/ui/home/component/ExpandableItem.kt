package org.cats.onlinebank.core.feature.ui.home.component

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.cats.onlinebank.core.feature.ui.home.model.UiBankModel

@Composable
fun ExpandableItem(
    modifier: Modifier = Modifier,
    item: UiBankModel,
    headerExpandableItem: HeaderExpandableItem = HeaderExpandableItem.LEVEL_1,
    isExpanded: Boolean,
    onExpandClick: () -> Unit = {},
    onDetailClick: (String, String) -> Unit = { _, _ -> },
) {
    Column(modifier = modifier
        .animateContentSize()
        .background(Color.White)
        .then(
            when (headerExpandableItem) {
                HeaderExpandableItem.LEVEL_1 ->
                    modifier

                HeaderExpandableItem.LEVEL_2 ->
                    modifier
                        .padding(start = 16.dp)
            }
        ),
    ) {
        BankHeader(
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 10.dp),
            label = item.name,
            amount = item.capital,
            isExpanded = isExpanded,
            onExpandClick = onExpandClick,
            headerExpandableItem = headerExpandableItem
        )

        if (isExpanded) {
            Column(Modifier.padding(start = 16.dp)) {
                item.accounts.forEach { account ->
                    ValueItem(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { onDetailClick(item.name, account.id) },
                        item = account,
                    )
                }
            }
        }
    }
}
enum class HeaderExpandableItem{
    LEVEL_1,
    LEVEL_2
}