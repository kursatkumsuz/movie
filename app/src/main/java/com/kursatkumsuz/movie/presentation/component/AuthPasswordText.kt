package com.kursatkumsuz.movie.presentation.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kursatkumsuz.movie.R

@Composable
fun AuthPasswordText(onInput: (String) -> Unit) {

    var passwordState by remember { mutableStateOf("") }
    var passwordVisibility by remember { mutableStateOf(false) }


    val icon = if (passwordVisibility) {
        painterResource(id = R.drawable.ic_visible)
    } else {
        painterResource(id = R.drawable.ic_invisible)
    }

    TextField(
        value = passwordState,
        onValueChange = {
            passwordState = it
            onInput(it)
        },
        label = { Text(text = "Password") },
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        trailingIcon = {
            IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
                Icon(
                    painter = icon,
                    contentDescription = "Visibility Icon",
                    tint = MaterialTheme.colorScheme.onBackground
                )
            }
        },
        visualTransformation = if (passwordVisibility)
            VisualTransformation.None
        else PasswordVisualTransformation(),
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .padding(horizontal = 30.dp),
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

@Preview(showBackground = true)
@Composable
fun PasswordTextPreview() {
    AuthPasswordText(onInput = {})
}