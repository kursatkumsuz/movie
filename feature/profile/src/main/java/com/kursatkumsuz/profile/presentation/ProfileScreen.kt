package com.kursatkumsuz.profile.presentation


import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import com.kursatkumsuz.messagebar.BarType
import com.kursatkumsuz.messagebar.MessageBarContent
import com.kursatkumsuz.messagebar.rememberAnimatedBarState
import com.kursatkumsuz.util.Constants.EMPTY_IMAGE
import kotlinx.coroutines.delay


@Composable
fun ProfileScreen(onNavigateAuthentication: () -> Unit) {
    val barState = rememberAnimatedBarState()
    val viewModel: ProfileViewModel = hiltViewModel()
    val state = viewModel.state.value
    val image = viewModel.imageState.value
    val isUploading = viewModel.uploadState.value

    var signedOutState by remember {
        mutableStateOf(false)
    }
    if (signedOutState) {
        LaunchedEffect(key1 = Unit) {
            delay(600)
            onNavigateAuthentication()
        }
    }
    MessageBarContent(barState = barState) {
        ProfileContent(
            user = state.user,
            profileImage = image ?: EMPTY_IMAGE,
            isPhotoUploading = isUploading,
            onSaveProfileImage = { uri ->
                if (uri != null) {
                    viewModel.uploadProfileImage(image = uri,
                        onSuccess = {
                            barState.AnimatedMessageBar(
                                message = "Profile Photo Successfully Uploaded!",
                                type = BarType.SUCCESS
                            )
                        },
                        onError = { msg ->
                            barState.AnimatedMessageBar(
                                message = msg,
                                type = BarType.ERROR
                            )
                        })
                }
            },
            onSignOutClick = {
                viewModel.signOut(
                    onSuccess = {
                        barState.AnimatedMessageBar(
                            message = "Successfully Signed Out!",
                            type = BarType.SUCCESS
                        )
                    },
                    onError = { msg ->
                        barState.AnimatedMessageBar(
                            message = msg,
                            type = BarType.ERROR
                        )
                    }
                )
                signedOutState = true
            }
        )
    }

}


