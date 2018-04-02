package com.lyshnia.kna

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.widget.LinearLayout
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.PaintDrawable
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        getSupportActionBar()!!.hide(); //<< this
        setContentView(R.layout.activity_login)

        val shape = PaintDrawable(Color.WHITE)
        shape.setCornerRadius(25f)

        // now find your view and add background to it
        linearGuy.background = shape

    }
}
