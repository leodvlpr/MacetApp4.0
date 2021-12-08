package com.example.macetapp40.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_home.*
import android.content.Intent
import android.graphics.Bitmap
import android.widget.Toast
import com.example.macetapp40.Login
import com.example.macetapp40.ShareDataViewModel
import com.example.macetapp40.ViewModelState
import kotlinx.android.synthetic.main.fragment_home.imgPlant
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import android.graphics.BitmapFactory
import android.util.Base64
import com.example.macetapp40.R
import kotlinx.android.synthetic.main.fragment_favourite.*
import java.io.ByteArrayOutputStream


private const val ARG_PARAM1 = "email"
private const val ARG_PARAM2 = "plantName"
private const val ARG_PARAM3 = "plantSensor"
private const val ARG_PARAM4 = "userId"

class HomeFragment() : Fragment() {

    private val shareDataViewModelViewModel : ShareDataViewModel by sharedViewModel()
    private var email: String? = null
    private var plantName: String? = null
    private var plantSensor: String? = null
    private var userId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            email = it.getString(ARG_PARAM1)
            plantName = it.getString(ARG_PARAM2)
            plantSensor = it.getString(ARG_PARAM3)
            userId =  it.getString(ARG_PARAM4)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (view.findViewById(com.example.macetapp40.R.id.emailTextView) as TextView).text = email
        (view.findViewById(com.example.macetapp40.R.id.tv_plantName) as TextView).text = plantName
        (view.findViewById(com.example.macetapp40.R.id.tv_status) as TextView).text = plantSensor
        logOutBtn.setOnClickListener {
            val prefs = activity?.getSharedPreferences(getString(com.example.macetapp40.R.string.prefs_file), Context.MODE_PRIVATE)
                ?.edit()
            prefs?.clear()
            prefs?.apply()
            val intent = Intent(activity, Login::class.java)
            requireActivity().startActivity(intent)
            activity?.finishAffinity()
        }
        observerResponse()
        shareDataViewModelViewModel.getUriPhoto()
        shareDataViewModelViewModel.getBitmapPhoto()
        userId?.let { shareDataViewModelViewModel.getPlantByUserId(it) }
    }

    private fun observerResponse () {
        shareDataViewModelViewModel.getViewModelState.observe(viewLifecycleOwner) {
                state ->
                when (state) {
                    is ViewModelState.Loading -> {

                    }
                    is ViewModelState.UriPicSuccess -> {
                        if (state.uriPic != null) {
                            imgPlant.setImageURI(state.uriPic)
                        }
                    }
                    is ViewModelState.BitmapPicSuccess -> {
                        if (state.bitmapPic != null) {
                            imgPlant.setImageBitmap(state.bitmapPic)
                        }
                    }
                    is ViewModelState.PlantSuccess -> {
                        tv_plantName.text = state.plant.name
                        if (state.plant.watering == "si") {
                            tv_status.text = "OK"
                        } else {
                            tv_status.text = "--"
                            tv_plantName.text = "No plant yet!"
                            Toast.makeText(context, "No assigned plant yet! Please register your product in our website. www.macetapp.com", Toast.LENGTH_SHORT).show()
                        }
                    }
                    else -> {
                        Toast.makeText(context, "", Toast.LENGTH_SHORT).show()
                        val intent = Intent(activity, Login::class.java)
                        requireActivity().startActivity(intent)
                        activity?.finishAffinity()
                    }
                }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(com.example.macetapp40.R.layout.fragment_home, container, false)
    }

}
