package com.example.macetapp40.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.macetapp40.R
import com.example.macetapp40.ShareDataViewModel
import com.example.macetapp40.ViewModelState
import kotlinx.android.synthetic.main.fragment_favourite.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class FavouriteFragment : Fragment() {

    private val shareDataViewModelViewModel : ShareDataViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favourite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observerResponse()
        shareDataViewModelViewModel.getUriPhoto()
        shareDataViewModelViewModel.getBitmapPhoto()
    }

    private fun observerResponse () {
        shareDataViewModelViewModel.getViewModelState.observe(viewLifecycleOwner) {
            state ->
            when (state) {
                is ViewModelState.Loading -> {}
                is ViewModelState.Success -> {
                    if (state.uriPic != null && state.bitmapPic == null) {
                        imgPlant.setImageURI(state.uriPic)
                    }
                    if (state.uriPic == null && state.bitmapPic != null) {
                        imgPlant.setImageBitmap(state.bitmapPic)
                    }
                }
            }
        }
    }

}