package com.izone.musicplayer.network

import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.izone.musicplayer.MPConst
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

    fun getAlbumUri(album: String, listener: StorageListener) {
//        var albumUri: Uri? = null

        // 비동기를 동기로 바꿔야 null이 return이 안될거 같은데??
        // 코루틴을 감싸서 처리? 아니면 viewModel에 직접접근?
        storageRef.child(album).downloadUrl.addOnCompleteListener { task ->
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

//        storageRef.child(list[pos].album).downloadUrl.addOnCompleteListener { task ->
//            if(task.isSuccessful) {
//                Glide.with(this).load(task.result).into(fMbinding.fmIvAlbum)
//            }
//        }
//
//        //set music
//        storageRef.child(list[pos].music).downloadUrl.addOnSuccessListener { uri ->
//            viewModel.setMusic(uri.toString())
//            viewModel.playMusic()
//        }
    }
}