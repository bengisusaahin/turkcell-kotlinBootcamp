package com.bengisusahin.days_13

import android.os.Bundle
import android.widget.FrameLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.bengisusahin.days_13.ui.HomeFragment
import com.bengisusahin.days_13.ui.LikeFragment
import com.bengisusahin.days_13.ui.MessageFragment
import com.bengisusahin.days_13.ui.ProfileFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private var navContent: FrameLayout? = null
    private val navigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener {
        when (it.itemId) {
            R.id.nav_home -> {
                val fragment = HomeFragment.newInstance("","")
                addFragment(fragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.nav_like -> {
                val fragment = LikeFragment.newInstance("","")
                addFragment(fragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.nav_message -> {
                val fragment = MessageFragment.newInstance("","")
                addFragment(fragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.nav_profile -> {
                val fragment = ProfileFragment.newInstance("","")
                addFragment(fragment)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        navContent = findViewById(R.id.nav_content)
        val navigation = findViewById<BottomNavigationView>(R.id.nav_navigation)

        navigation.setOnNavigationItemSelectedListener(navigationItemSelectedListener)

        val fragment = HomeFragment.newInstance("","")

        addFragment(fragment)
    }

    private fun addFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .setCustomAnimations(
                com.google.android.material.R.anim.design_bottom_sheet_slide_in,
                com.google.android.material.R.anim.design_bottom_sheet_slide_out
            )
            .replace(R.id.nav_content, fragment, fragment.javaClass.simpleName)
            .commit()
    }
}