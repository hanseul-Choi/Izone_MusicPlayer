package com.izone.musicplayer.network

import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.izone.musicplayer.common.MPConst
import com.izone.musicplayer.repository.storage.StorageListener

class StorageApiClient {
    fun getMusicUri(music: String, listener: StorageListener) {

        storageRef.child(music).downloadUrl.addOnCompleteListener { task ->
            if(task.isSuccessful) {
                listener.onSuccess(task.result)
            } else {
                task.exception?.let { listener.onFailed(it) }
            }

        }
    }

    companion object {
        private val storage: FirebaseStorage = FirebaseStorage.getInstance(MPConst.STORAGE_URL)
        val storageRef: StorageReference = storage.reference

        //set image list[pos].album

//        //set music
//        storageRef.child(list[pos].music).downloadUrl.addOnSuccessListener { uri ->
//            viewModel.setMusic(uri.toString())
//            viewModel.playMusic()
//        }
    }
}