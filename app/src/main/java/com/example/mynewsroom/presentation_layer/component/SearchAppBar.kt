package com.example.mynewsroom.presentation_layer.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.sp
import com.example.mynewsroom.ui.theme.RoyalBlue

@Composable
fun SearchAppBar(
    modifier: Modifier = Modifier,
    value: String,
    onInputValueChange: (String) -> Unit,
    onCloseIconClicked: () -> Unit,
    onSearchIconClicked: () -> Unit,
) {
    TextField(
        modifier = modifier
            .fillMaxWidth(),
        value = value,
        onValueChange = onInputValueChange,
        textStyle = TextStyle(color = Color.White, fontSize = 16.sp),
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.Search, contentDescription = null,
                tint = Color.White.copy(0.7f)
            )
        },
        placeholder = {
            Text(text = "Search....", color = Color.White.copy(alpha = 0.7f))
        },
        trailingIcon = {
            IconButton(onClick = {
                if (value.isNotEmpty()) onInputValueChange("")
                else onCloseIconClicked()
            }) {
                Icon(
                    imageVector = Icons.Filled.Close,
                    contentDescription = null,
                    tint = Color.White
                )
            }
        },
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
        keyboardActions = KeyboardActions(
            onSearch = { onSearchIconClicked() }
        ),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = RoyalBlue,
            unfocusedContainerColor = RoyalBlue,
            cursorColor = Color.White,
            focusedIndicatorColor = Color.White
        )
    )
}
