package com.example.macetapp40

import android.graphics.Bitmap
import android.net.Uri
import com.example.macetapp40.model.Plant
import com.example.macetapp40.model.User

sealed class ViewModelState {
    object Loading : ViewModelState()
    data class UriPicSuccess(val uriPic: Uri?) : ViewModelState()
    data class BitmapPicSuccess(val bitmapPic: Bitmap?) : ViewModelState()
    data class PlantSuccess(val plant: Plant): ViewModelState()
    data class UserSuccess(val user: User): ViewModelState()

    object Error : ViewModelState()
}
