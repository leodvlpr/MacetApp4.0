package com.example.macetapp40

import android.graphics.Bitmap
import android.net.Uri

sealed class ViewModelState {
    object Loading : ViewModelState()
    class Success(val uriPic: Uri?,val bitmapPic: Bitmap?) : ViewModelState()
}
