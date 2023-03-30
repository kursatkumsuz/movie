package com.kursatkumsuz.movie.presentation.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun AuthInputText(
    labelText: String,
    onInput: (String) -> Unit
) {
    var textState by remember { mutableStateOf("") }

    TextField(
        value = textState.trim(),
        onValueChange = {
            textState = it
            onInput(it)
        },
        label = { Text(text = labelText) },
        singleLine = true,
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .padding(horizontal = 30.dp),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        colors = TextFieldDefaults.textFieldColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            textColor = Color(0xFFCAAFAF)
        ),
        shape = RoundedCornerShape(15.dp)
    )
}