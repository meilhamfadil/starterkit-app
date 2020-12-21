package id.kudzoza.feature.home.screen.home

import android.os.Bundle
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import id.kudzoza.feature.home.R

/**
 * Created by Kudzoza
 * on 18/12/2020
 **/

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val homeFragment =
            supportFragmentManager.findFragmentById(R.id.homeFragment) as HomeFragment
        homeFragment.arguments = intent.extras
    }
}