package com.example.macetapp40

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.macetapp40.fragments.FavouriteFragment
import com.example.macetapp40.fragments.HomeFragment
import com.example.macetapp40.fragments.SettingsFragment
import com.google.android.material.bottomnavigation.BottomNavigationView



class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Fragments
        val homeFragment = HomeFragment()
        val favouriteFragment = FavouriteFragment()
        val settingsFragment = SettingsFragment()
        makeCurrentFragment(homeFragment)
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNav.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.ic_home -> makeCurrentFragment(homeFragment)
                R.id.ic_favorites -> makeCurrentFragment(favouriteFragment)
                R.id.ic_settings -> makeCurrentFragment(settingsFragment)
            }
            true
        }

        //SetUp
        val bundle : Bundle? = intent.extras
        val email = bundle?.getString("email")
        setUp(email ?: "")


        //Save data
        val prefs = getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE).edit()
        prefs.putString("email", email)
        prefs.apply()

    }

    private fun setUp(email: String) {

    }

    private fun makeCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fl_wrapper, fragment)
            commit()
        }

}