package com.example.macetapp40.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_home.*


private const val ARG_PARAM1 = "email"

class HomeFragment() : Fragment() {

    private var email: String? = null
    private var param2: String? = null

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
