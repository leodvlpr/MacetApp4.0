package com.example.macetapp40

import android.graphics.Bitmap
import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class ShareDataViewModel( private val _viewModelState: MutableLiveData<ViewModelState>) : ViewModel() {

    val getViewModelState: LiveData<ViewModelState>
    get() = _viewModelState
    private var uriPic: Uri? = null
    private var bitmapPic: Bitmap? = null

    fun setUriPhoto (uriPhoto : Uri?) {
        uriPic = uriPhoto
    }

    fun getUriPhoto () {
        viewModelScope.launch {
            getUriPic().onStart {
                _viewModelState.value = ViewModelState.Loading
            }
            _viewModelState.value = ViewModelState.Success(uriPic, null)
        }
    }

    private fun getUriPic() = flow <Uri>{ uriPic }

    //Bitmap//

    fun setBitmapPhoto (bitmap: Bitmap) {
        bitmapPic = bitmap
    }

    fun getBitmapPhoto () {
        viewModelScope.launch {
            getBitmapPic().onStart {
                _viewModelState.value = ViewModelState.Loading
            }
            _viewModelState.value = ViewModelState.Success(null, bitmapPic)
        }
    }

    private fun getBitmapPic() = flow <Bitmap>{ bitmapPic }

}