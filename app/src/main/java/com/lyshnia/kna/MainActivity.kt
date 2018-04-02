package com.lyshnia.kna

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.content.Intent
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import android.app.ProgressDialog




class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        with(getSupportActionBar()) {
            val titleText: String = title.toString()
            setTitle(Html.fromHtml("<font color='#746E66'>" + titleText + "</font>"))
        }


        val dialog = ProgressDialog(this)
        dialog.setMessage("Loading")
        dialog.setCancelable(false)
        dialog.setInverseBackgroundForced(false)
        dialog.show()

        dialog.hide()
    }

    fun loginActivity(v: View) {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }
}
