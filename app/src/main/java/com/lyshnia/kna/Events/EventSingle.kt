package com.lyshnia.kna.Events

import android.content.res.ColorStateList
import android.graphics.drawable.BitmapDrawable
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.CollapsingToolbarLayout
import android.support.design.widget.FloatingActionButton
import android.support.v4.view.ViewCompat
import android.support.v7.graphics.Palette
import android.support.v7.widget.Toolbar
import android.transition.Slide
import android.view.MotionEvent
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.lyshnia.kna.R
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_event_single.*
import org.json.JSONObject
import java.io.File
import java.io.FileInputStream
import java.lang.Exception

class EventSingle : AppCompatActivity() {

    private val EXTRA_IMAGE = "com.lyshnia.kna.extraImage"
    private val EXTRA_TITLE = "com.lyshnia.kna.extraTitle"
    private lateinit var collapsingToolbarLayout: CollapsingToolbarLayout
    private lateinit var jsonData: JSONObject

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initActivityTransitions()
        setContentView(R.layout.activity_event_single)

        val path = this@EventSingle.getFilesDir()
        val letDirectory = File(path, "temp")
        letDirectory.mkdirs()

        val file = File(letDirectory, "temp_evt.json")
        val inputAsString = FileInputStream(file).bufferedReader().use { it.readText() }
        jsonData = JSONObject(inputAsString)


        ViewCompat.setTransitionName(findViewById(R.id.app_bar_layout), R.drawable.events_bg.toString())
        supportPostponeEnterTransition()

        setSupportActionBar(findViewById(R.id.toolbar) as Toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        val itemTitle = jsonData.getString("title")
        collapsingToolbarLayout = findViewById(R.id.collapsing_toolbar) as CollapsingToolbarLayout
        collapsingToolbarLayout.setTitle(itemTitle)
        collapsingToolbarLayout.setExpandedTitleColor(resources.getColor(android.R.color.transparent))

        val image = findViewById(R.id.image) as ImageView

        Picasso.get().load(R.drawable.events_bg).into(image, object : Callback {


            override fun onSuccess() {
                val bitmap = (image.drawable as BitmapDrawable).bitmap
                Palette.from(bitmap).generate { palette -> applyPalette(palette) }
            }

            override fun onError(e: Exception?) {

            }
        })


        val title = findViewById(R.id.title) as TextView
        title.text = itemTitle
        findViewById<TextView>(R.id.dateAndTime).text = jsonData.getString("date")
        findViewById<TextView>(R.id.location).text = jsonData.getString("location")
        findViewById<TextView>(R.id.att).text = jsonData.getString("att")

        fab.setOnClickListener {
            Toast.makeText(this, "Hey daddy!", Toast.LENGTH_SHORT).show()
        }
    }


    override fun dispatchTouchEvent(motionEvent: MotionEvent): Boolean {
        try {
            return super.dispatchTouchEvent(motionEvent)
        } catch (e: NullPointerException) {
            return false
        }

    }

    private fun initActivityTransitions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val transition = Slide()
            transition.excludeTarget(android.R.id.statusBarBackground, true)
            window.enterTransition = transition
            window.returnTransition = transition
        }
    }

    private fun applyPalette(palette: Palette) {
        val primaryDark = resources.getColor(R.color.colorPrimaryDark)
        val primary = resources.getColor(R.color.colorPrimary)
        collapsingToolbarLayout.setContentScrimColor(palette.getMutedColor(primary))
        collapsingToolbarLayout.setStatusBarScrimColor(palette.getDarkMutedColor(primaryDark))
        updateBackground(findViewById(R.id.fab) as FloatingActionButton, palette)
        supportStartPostponedEnterTransition()
    }

    private fun updateBackground(fab: FloatingActionButton, palette: Palette) {
        val lightVibrantColor = palette.getLightVibrantColor(resources.getColor(android.R.color.white))
        val vibrantColor = palette.getVibrantColor(resources.getColor(R.color.colorAccent))

        fab.rippleColor = lightVibrantColor
        fab.backgroundTintList = ColorStateList.valueOf(vibrantColor)
    }
}
