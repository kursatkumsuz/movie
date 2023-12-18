package com.kursatkumsuz.profile.presentation

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.kursatkumsuz.domain.model.User
import com.kursatkumsuz.profile.presentation.component.ProfileList

@Composable
fun ProfileContent(
    user: User,
    profileImage: String,
    isPhotoUploading: Boolean,
    onSignOutClick: () -> Unit,
    onSaveProfileImage: (Uri?) -> Unit,
) {
    val context = LocalContext.current
    val versionName = context.packageManager.getPackageInfo(context.packageName, 0).versionName
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ProfileImage(
            profileImage = profileImage,
            onSaveProfileImage = onSaveProfileImage,
            isPhotoUploading = isPhotoUploading
        )

        user.name?.let { Text(text = it, color = MaterialTheme.colorScheme.onBackground) }
        user.email?.let { Text(text = it, color = MaterialTheme.colorScheme.onBackground) }
        Spacer(modifier = Modifier.height(20.dp))
        ProfileList()
        Spacer(modifier = Modifier.height(30.dp))
        Button(
            modifier = Modifier.size(width = 250.dp, height = 40.dp),
            onClick = onSignOutClick,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(color = 0xFF1B1B1A),
                contentColor = Color.White
            )
        ) {
            Text(text = "Sign Out")
        }
        Spacer(modifier = Modifier.height(20.dp))
        Text(text = "Ver. $versionName", color = MaterialTheme.colorScheme.onBackground)
    }
}

@Composable
fun ProfileImage(
    isPhotoUploading: Boolean,
    profileImage: String,
    onSaveProfileImage: (Uri?) -> Unit,
) {
    var uri by remember {
        mutableStateOf<Uri?>(null)
    }
    val image = if (uri != null) uri.toString() else profileImage
    val photoPicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = {
            uri = it
            onSaveProfileImage(it)
        }
    )
    Column {
        Box(modifier = Modifier.size(150.dp)) {
            AsyncImage(
                modifier = Modifier
                    .size(120.dp)
                    .clip(CircleShape)
                    .align(Alignment.Center),
                model = image, contentDescription = "Profile Image",
                contentScale = ContentScale.Crop
            )

            if (isPhotoUploading) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .size(28.dp),
                    color = MaterialTheme.colorScheme.onBackground,
                    strokeWidth = 3.dp
                )
            } else {
                IconButton(
                    modifier = Modifier.align(
                        Alignment.BottomEnd
                    ),
                    onClick = {
                        photoPicker.launch(
                            PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                        )
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = "Edit Icon",
                        tint = MaterialTheme.colorScheme.onBackground
                    )
                }

            }
        }
    }
}