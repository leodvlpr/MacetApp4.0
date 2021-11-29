package com.example.macetapp40.fragments

import android.app.Activity.RESULT_OK
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.macetapp40.R
import kotlinx.android.synthetic.main.fragment_settings.*
import android.provider.MediaStore
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import com.example.macetapp40.ShareDataViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class SettingsFragment : Fragment() {
    private val PICK_IMAGE = 100
    private val REQUEST_IMAGE_CAPTURE = 1
    private var imageUri: Uri? = null
    private val shareDataViewModelViewModel : ShareDataViewModel by sharedViewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        imgFolder.setOnClickListener {
            openGallery()
        }
        imgCam.setOnClickListener {
            openCamera()
        }

        saveBtn.setOnClickListener {

        }
    }

    private fun openGallery() {
        val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
        startActivityForResult(gallery, PICK_IMAGE)
    }

    private fun openCamera() {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE) {
            imageUri = data?.data
            shareDataViewModelViewModel.setUriPhoto(imageUri)
            imgSelected.setImageURI(imageUri)
        }

        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            val imageBitmap = data?.extras?.get("data") as Bitmap
            shareDataViewModelViewModel.setBitmapPhoto(imageBitmap)
            imgSelected.setImageBitmap(imageBitmap)
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

}