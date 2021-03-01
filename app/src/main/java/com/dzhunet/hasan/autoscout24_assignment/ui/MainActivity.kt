package com.dzhunet.hasan.autoscout24_assignment.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dzhunet.hasan.autoscout24_assignment.R
import com.dzhunet.hasan.autoscout24_assignment.ui.feed.FeedsFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction()
                .add(R.id.container, FeedsFragment())
                .commitAllowingStateLoss()
    }
}
