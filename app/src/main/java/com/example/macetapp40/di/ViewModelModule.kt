package com.example.macetapp40.di

import androidx.lifecycle.MutableLiveData
import com.example.macetapp40.ShareDataViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel{
        ShareDataViewModel(_viewModelState = MutableLiveData())
    }
}