package com.lyshnia.kna.HomePage

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.Toolbar
import android.view.View
import com.lyshnia.kna.DrawerHeader
import com.lyshnia.kna.DrawerMenuItem
import com.lyshnia.kna.R
import com.mindorks.placeholderview.PlaceHolderView
import kotlinx.android.synthetic.main.toolbar_home.*

class Weddings : AppCompatActivity() {

    private var mDrawerView: PlaceHolderView? = null
    private var mDrawer: DrawerLayout? = null
    private var mToolbar: Toolbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weddings)

        toolbarTitle.text = "Weddings"

        mDrawer = findViewById<DrawerLayout>(R.id.drawerLayout);
        mDrawerView = findViewById<PlaceHolderView>(R.id.drawerView);
        mToolbar = findViewById<Toolbar>(R.id.toolbar);
        setupDrawer();

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

}
