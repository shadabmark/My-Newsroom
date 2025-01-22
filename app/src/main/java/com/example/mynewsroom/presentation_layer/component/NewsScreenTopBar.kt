package com.example.mynewsroom.presentation_layer.component

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.mynewsroom.ui.theme.RoyalBlue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsScreenTopBar(
    scrollBehavior: TopAppBarScrollBehavior,
    onSearchIconClicked: () -> Unit
) {
    TopAppBar(
        scrollBehavior = scrollBehavior,
        title = { Text(text = "Newsroom", fontWeight = FontWeight.Bold) },
        actions = {
            IconButton(onClick = onSearchIconClicked) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search",
                    modifier = Modifier.size(26.dp)
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = RoyalBlue,
            titleContentColor = Color.White,
            actionIconContentColor = Color.White
        )
    )
}