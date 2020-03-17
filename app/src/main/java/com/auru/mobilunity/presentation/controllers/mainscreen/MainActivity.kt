package com.auru.mobilunity.presentation.controllers.mainscreen

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.auru.mobilunity.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container,
                        MainFragment.newInstance()
                    )
                    .commitNow()
        }
    }
}
