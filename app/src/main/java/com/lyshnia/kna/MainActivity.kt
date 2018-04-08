package com.lyshnia.kna

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.PaintDrawable
import android.os.Bundle
import android.os.Handler
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.text.Html
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import com.mindorks.placeholderview.PlaceHolderView
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*
import android.support.v4.widget.SwipeRefreshLayout
import com.lyshnia.kna.HomePage.Birthdays
import com.lyshnia.kna.HomePage.Bulletin
import com.lyshnia.kna.HomePage.Tweets
import com.lyshnia.kna.HomePage.Weddings


class MainActivity : AppCompatActivity() {

    private var mDrawerView: PlaceHolderView? = null
    private var mDrawer: DrawerLayout? = null
    private var mToolbar: Toolbar? = null
    private var mGalleryView: PlaceHolderView? = null

    private var EVENT_DATE_TIME = "2018-04-07 07:39:00"
    private val DATE_FORMAT = "yyyy-MM-dd HH:mm:ss"
    private var linear_layout_1: LinearLayout? = null
    private var linear_layout_2: LinearLayout? = null
    private var tv_days: TextView? = null
    private var tv_hour: TextView? = null
    private var tv_minute: TextView? = null
    private var tv_second: TextView? = null
    private var handler = Handler()
    private var runnable: Runnable? = null
    private var hasCountedBefore = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        with(getSupportActionBar()) {
            val titleText: String = title.toString()
            setTitle(Html.fromHtml("<font color='#746E66'>" + titleText + "</font>"))
        }


        mDrawer = findViewById<DrawerLayout>(R.id.drawerLayout);
        mDrawerView = findViewById<PlaceHolderView>(R.id.drawerView);
        mToolbar = findViewById<Toolbar>(R.id.toolbar);
//        mGalleryView = findViewById<PlaceHolderView>(R.id.galleryView);
        setupDrawer();

        initUI();
        val shape = PaintDrawable(Color.BLACK)
        shape.setCornerRadius(30f)

        // now find your view and add background to it
        linear_layout_2!!.background = shape
        linear_layout_1!!.background = shape
        countDownStart();

        swiperefresh.setOnRefreshListener(
                SwipeRefreshLayout.OnRefreshListener {
                    Log.i("REF", "onRefresh called from SwipeRefreshLayout")

                    // This method performs the actual data-refresh operation.
                    // The method calls setRefreshing(false) when it's finished.
                    EVENT_DATE_TIME = "2018-04-04 15:33:00"
                    this@MainActivity.countDownStart();
                    swiperefresh.setRefreshing(false)
                }
        )

    }

    fun bulletin(v: View) {
        val intent = Intent(this, Bulletin::class.java)
        startActivity(intent)
    }
    fun tweets(v: View) {
        val intent = Intent(this, Tweets::class.java)
        startActivity(intent)
    }
    fun birthdays(v: View) {
        val intent = Intent(this, Birthdays::class.java)
        startActivity(intent)
    }
    fun weddings(v: View) {
        val intent = Intent(this, Weddings::class.java)
        startActivity(intent)
    }

    private fun setupDrawer() {
        mDrawerView!!
                .addView(DrawerHeader())
                .addView(DrawerMenuItem(this, DrawerMenuItem.DRAWER_MENU_ITEM_HOME))
                .addView(DrawerMenuItem(this, DrawerMenuItem.DRAWER_MENU_ITEM_REQUESTS))
                .addView(DrawerMenuItem(this, DrawerMenuItem.DRAWER_MENU_ITEM_GROUPS))
                .addView(DrawerMenuItem(this, DrawerMenuItem.DRAWER_MENU_ITEM_NOTIFICATIONS))
                .addView(DrawerMenuItem(this, DrawerMenuItem.DRAWER_MENU_ITEM_TERMS))
                .addView(DrawerMenuItem(this, DrawerMenuItem.DRAWER_MENU_ITEM_SETTINGS))
                .addView(DrawerMenuItem(this, DrawerMenuItem.DRAWER_MENU_ITEM_ABOUT))

        val drawerToggle = object : ActionBarDrawerToggle(this, mDrawer, mToolbar, R.string.open_drawer, R.string.close_drawer) {
            override fun onDrawerOpened(drawerView: View) {
                super.onDrawerOpened(drawerView)
            }

            override fun onDrawerClosed(drawerView: View) {
                super.onDrawerClosed(drawerView)
            }
        }

        mDrawer!!.addDrawerListener(drawerToggle)
        drawerToggle.syncState()
    }

    private fun initUI() {
        linear_layout_1 = findViewById(R.id.linear_layout_1)
        linear_layout_2 = findViewById(R.id.linear_layout_2)
        tv_days = findViewById(R.id.tv_days)
        tv_hour = findViewById(R.id.tv_hour)
        tv_minute = findViewById(R.id.tv_minute)
        tv_second = findViewById(R.id.tv_second)
    }

    private fun countDownStart() {
        runnable = object : Runnable {
            override fun run() {
                try {
                    handler.postDelayed(this, 1000)
                    val dateFormat = SimpleDateFormat(DATE_FORMAT)
                    dateFormat.timeZone = TimeZone.getTimeZone("GMT+8:00")
                    val event_date = dateFormat.parse(EVENT_DATE_TIME)
                    val current_date = Date()

                    Log.i("REF", event_date.toString())


                    if (!current_date.after(event_date)) {

                        if (!hasCountedBefore) {
                            linear_layout_1!!.setVisibility(View.GONE)
                            linear_layout_2!!.setVisibility(View.VISIBLE)
                            hasCountedBefore = true
                            timerLabel.text = getString(R.string.ctd_timer);
                        }

                        val diff = event_date.getTime() - current_date.getTime()
                        val Days = diff / (24 * 60 * 60 * 1000)
                        val Hours = diff / (60 * 60 * 1000) % 24
                        val Minutes = diff / (60 * 1000) % 60
                        val Seconds = diff / 1000 % 60
                        //
                        tv_days!!.setText(String.format("%02d", Days))
                        tv_hour!!.setText(String.format("%02d", Hours))
                        tv_minute!!.setText(String.format("%02d", Minutes))
                        tv_second!!.setText(String.format("%02d", Seconds))
                    } else {
                        linear_layout_1!!.setVisibility(View.VISIBLE)
                        linear_layout_2!!.setVisibility(View.GONE)
                        timerLabel.text = getString(R.string.ctd_timer_running);
                        handler.removeCallbacks(runnable)

                        this@MainActivity.onStop()
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }

            }
        }
        handler.postDelayed(runnable, 0)
    }

    override fun onStop() {
        super.onStop()
        handler.removeCallbacks(runnable)
        hasCountedBefore = false
    }
}
