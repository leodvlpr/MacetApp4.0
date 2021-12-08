package com.example.macetapp40

import android.graphics.Bitmap
import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.macetapp40.repository.MacetappRepository
import com.example.macetapp40.repository.MacetappResult
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class ShareDataViewModel(
    private val macetappRepository: MacetappRepository,
    private val _viewModelState: MutableLiveData<ViewModelState>,
) : ViewModel() {

    val getViewModelState: LiveData<ViewModelState>
    get() = _viewModelState
    private var uriPic: Uri? = null
    private var bitmapPic: Bitmap? = null


    //Get plant//
    fun getPlantByUserId(userId: String) {
        viewModelScope.launch {
            _viewModelState.value = when (val result = macetappRepository.getPlantByUser(userId)) {
                MacetappResult.Loading -> ViewModelState.Loading
                is MacetappResult.Error -> ViewModelState.Error
                is MacetappResult.Success -> ViewModelState.PlantSuccess(result.data)
            }
        }
    }

    //UriPhoto//
    fun setUriPhoto (uriPhoto : Uri?) {
        uriPic = uriPhoto
    }

    fun getUriPhoto () {
        viewModelScope.launch {
            getUriPic().onStart {
                _viewModelState.value = ViewModelState.Loading
            }
            _viewModelState.value = ViewModelState.UriPicSuccess(uriPic)
        }
    }

    private fun getUriPic() = flow <Uri>{ uriPic }


    //Bitmap//
    fun setBitmapPhoto (bitmap: Bitmap?) {
        bitmapPic = bitmap
    }

    fun getBitmapPhoto () {
        viewModelScope.launch {
            getBitmapPic().onStart {
                _viewModelState.value = ViewModelState.Loading
            }
            _viewModelState.value = ViewModelState.BitmapPicSuccess(bitmapPic)
        }
    }

    private fun getBitmapPic() = flow <Bitmap>{ bitmapPic }

}