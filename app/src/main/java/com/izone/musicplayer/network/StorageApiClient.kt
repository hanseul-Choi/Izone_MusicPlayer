package com.izone.musicplayer.network

import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.izone.musicplayer.common.MPConst
import com.izone.musicplayer.repository.storage.StorageListener
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StorageApiClient @Inject constructor() {
    fun getMusicUri(music: String, listener: StorageListener) {

        storageRef.child(music).downloadUrl.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                listener.onSuccess(task.result)
            } else {
                task.exception?.let { listener.onFailed(it) }
            }

        }
    }

    companion object {
        private val storage: FirebaseStorage = FirebaseStorage.getInstance(MPConst.STORAGE_URL)
        val storageRef: StorageReference = storage.reference
    }
}