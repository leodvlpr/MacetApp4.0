package com.example.macetapp40.fragments


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import com.example.macetapp40.MainActivity
import kotlinx.android.synthetic.main.fragment_home.*
import android.content.Intent
import android.widget.ImageView
import com.example.macetapp40.Login
import com.example.macetapp40.ShareDataViewModel
import com.example.macetapp40.ViewModelState
import kotlinx.android.synthetic.main.fragment_favourite.*
import kotlinx.android.synthetic.main.fragment_home.imgPlant
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


private const val ARG_PARAM1 = "email"

class HomeFragment() : Fragment() {

    private val shareDataViewModelViewModel : ShareDataViewModel by sharedViewModel()
    private var email: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            email = it.getString(ARG_PARAM1)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (view.findViewById(com.example.macetapp40.R.id.emailTextView) as TextView).text = email
        logOutBtn.setOnClickListener {
            val prefs = activity?.getSharedPreferences(getString(com.example.macetapp40.R.string.prefs_file), Context.MODE_PRIVATE)
                ?.edit()
            prefs?.clear()
            prefs?.apply()
            val intent = Intent(activity, Login::class.java)
            requireActivity().startActivity(intent)
        }
        observerResponse()
        shareDataViewModelViewModel.getUriPhoto()
        shareDataViewModelViewModel.getBitmapPhoto()
    }

    private fun observerResponse () {
        shareDataViewModelViewModel.getViewModelState.observe(viewLifecycleOwner) {
                state ->
            when (state) {
                is ViewModelState.Loading -> {

                }
                is ViewModelState.Success -> {
                    if (state.uriPic != null && state.bitmapPic == null) {
                        imgPlant.setImageURI(state.uriPic)
                    }
                    if (state.bitmapPic != null && state.uriPic == null) {
                        imgPlant.setImageBitmap(state.bitmapPic)
                    }
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
