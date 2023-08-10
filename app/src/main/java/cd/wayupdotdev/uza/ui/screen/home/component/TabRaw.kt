package cd.wayupdotdev.uza.ui.screen.home.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChipTab(
    itemsList: List<String>,
    selectedItem: String,
    onTabClick: (String) -> Unit
) {
    LazyRow(modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 16.dp)){
        items(itemsList) {item ->
            FilterChip(
                modifier = Modifier.padding(horizontal = 6.dp),
                selected = (item == selectedItem),
                onClick = { onTabClick(item) },
                label = { Text(text = item) }
            )
        }
    }
}