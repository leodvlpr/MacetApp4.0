package com.example.macetapp40

import android.graphics.Bitmap
import android.net.Uri
import com.example.macetapp40.model.Plant

sealed class ViewModelState {
    object Loading : ViewModelState()
    data class UriPicSuccess(val uriPic: Uri?) : ViewModelState()
    data class BitmapPicSuccess(val bitmapPic: Bitmap?) : ViewModelState()
    data class PlantSuccess(val plant: Plant): ViewModelState()
    object Error : ViewModelState()
}
