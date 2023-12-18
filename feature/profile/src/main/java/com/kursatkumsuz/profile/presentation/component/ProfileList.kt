package com.kursatkumsuz.profile.presentation.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.kursatkumsuz.profile.R


@Composable
fun ProfileList() {
    val list = listOf(
        Item(
            text = "Appearance",
            icon = R.drawable.ic_appearance
        ),
        Item(
            text = "Notification",
            icon = R.drawable.ic_notification
        ),
        Item(
            text = "Privacy & Security",
            icon = R.drawable.ic_privacy
        ),
        Item(
            text = "Help & Support",
            icon = R.drawable.ic_help
        ),
        Item(
            text = "About",
            icon = R.drawable.ic_question_mark
        ),
    )
    LazyColumn {
        items(list.size) { index ->
            ProfileListItem(item = list[index])
        }
    }
}

@Composable
fun ProfileListItem(item: Item) {
    Row(
        modifier = Modifier
            .padding(vertical = 10.dp)
            .fillMaxWidth()
    ) {
        Icon(
            painter = painterResource(id = item.icon),
            contentDescription = "Icon",
            tint = MaterialTheme.colorScheme.onBackground
        )
        Spacer(modifier = Modifier.width(10.dp))
        Text(text = item.text, color = MaterialTheme.colorScheme.onBackground)
        Spacer(modifier = Modifier.weight(1f))
        IconButton(
            onClick = { /*TODO*/ }) {
            Icon(
                imageVector = Icons.Default.KeyboardArrowRight,
                contentDescription = "Button Icon",
                tint = MaterialTheme.colorScheme.onBackground
            )
        }
    }
}


data class Item(
    val text: String,
    @DrawableRes val icon: Int
)