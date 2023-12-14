package com.kursatkumsuz.profile.data.repository

import android.net.Uri
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.UploadTask
import com.kursatkumsuz.profile.domain.repository.ImageRepository
import com.kursatkumsuz.util.Constants

class ImageRepositoryImpl(
    private val firebaseFirestore: FirebaseFirestore,
    private val firebaseStorage: FirebaseStorage,
    private val firebaseAuth: FirebaseAuth
) : ImageRepository {

    override suspend fun getUserUid(): String {
        var uid = ""
        firebaseAuth.currentUser?.uid?.let {
            uid = it
        }
        return uid
    }

    override suspend fun saveProfileImage(imageUrl: String): Task<Void?> {
        val data = mapOf(
            "image" to imageUrl
        )
        val userUid = getUserUid()
        return firebaseFirestore.collection(Constants.IMAGE_COLLECTION).document(userUid).set(data)
    }

    override suspend fun uploadProfileImage(image: Uri): UploadTask {
        val userUid = getUserUid()
        val imageName = "$userUid.jpg"
        return firebaseStorage.reference.child("images").child(imageName).putFile(image)
    }
}