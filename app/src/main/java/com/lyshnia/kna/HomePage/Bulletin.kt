package com.lyshnia.kna.HomePage

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.lyshnia.kna.DrawerHeader
import com.lyshnia.kna.DrawerMenuItem
import com.lyshnia.kna.R
import com.mindorks.placeholderview.PlaceHolderView
import kotlinx.android.synthetic.main.activity_bulletin.*
import com.lyshnia.kna.R.id.recyclerView
import android.support.v7.widget.DividerItemDecoration
import android.util.Log
import com.lyshnia.kna.R.attr.layoutManager
import kotlinx.android.synthetic.main.toolbar_home.*
import android.content.Intent
import android.net.Uri


class Bulletin : AppCompatActivity(), RecyclerAdapter.ItemClickListener {

    private var mDrawerView: PlaceHolderView? = null
    private var mDrawer: DrawerLayout? = null
    private var mToolbar: Toolbar? = null

    var adapter: RecyclerAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bulletin)

        toolbarTitle.text = "Bulletins"

        mDrawer = findViewById<DrawerLayout>(R.id.drawerLayout);
        mDrawerView = findViewById<PlaceHolderView>(R.id.drawerView);
        mToolbar = findViewById<Toolbar>(R.id.toolbar);
        setupDrawer();

        // data to populate the RecyclerView with
        var listItems = ArrayList<HashMap<String, String>>();

        // Simulate number of bulettins
        val buls = arrayListOf<HashMap<String, String>>(
                hashMapOf<String, String>(
                        "title" to "Chronological Catalog of Koinonia Sermons",
                        "dateM" to "MAR",
                        "dateD" to "6",
                        "author" to "by ENI Media",
                        "url" to "http://koinoniasermons.org/assets/bg/CHRONOLOGICAL%20ARRANGEMENT%20FOR%20KN%20SITE%20-9b29c998fbff4c2c396ae8fd135a176c.pdf"
                ),
                hashMapOf<String, String>(
                        "title" to "TOPICAL CATALOG OF KOINONIA SERMONS",
                        "dateM" to "MAR",
                        "dateD" to "8",
                        "author" to "by ENI Media",
                        "url" to "http://koinoniasermons.org/assets/bg/TPOICAL%20CATALOGUE%20FOR%20KN%20SITE%20-1c25b7825ce9a761d7076b8435e3cfbe.pdf"
                ),
                hashMapOf<String, String>(
                        "title" to "What is the purpose of life",
                        "dateM" to "AUG",
                        "dateD" to "12",
                        "author" to "by ENI Media",
                        "url" to "http://koinoniasermons.org/assets/bg/CHRONOLOGICAL%20ARRANGEMENT%20FOR%20KN%20SITE%20-9b29c998fbff4c2c396ae8fd135a176c.pdf"
                )
        )


        for (bul in buls) {
            listItems.add(bul);
        }

        Log.i("REF", listItems.toString())

        // set up the RecyclerView
        var recyclerView = findViewById<RecyclerView>(R.id.recyclerView);
        recyclerView.setLayoutManager(LinearLayoutManager(this));
        adapter = RecyclerAdapter(this, listItems);
        adapter!!.setClickListener(this);
        recyclerView.setAdapter(adapter);

        val dividerItemDecoration = DividerItemDecoration(recyclerView.context,
                1)
        recyclerView.addItemDecoration(dividerItemDecoration)
    }

    public override fun onItemClick(view: View, position: Int) {
//        Toast.makeText(this, "You clicked " + adapter!!.getItem(position) + " on row number " + position, Toast.LENGTH_SHORT).show();

        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(adapter!!.getURL(position)))
        startActivity(browserIntent)
    }

    private fun setupDrawer() {
        mDrawerView!!
                .addView(DrawerHeader())
                .addView(DrawerMenuItem(this.applicationContext, DrawerMenuItem.DRAWER_MENU_ITEM_HOME))
                .addView(DrawerMenuItem(this.applicationContext, DrawerMenuItem.DRAWER_MENU_ITEM_REQUESTS))
                .addView(DrawerMenuItem(this.applicationContext, DrawerMenuItem.DRAWER_MENU_ITEM_GROUPS))
                .addView(DrawerMenuItem(this.applicationContext, DrawerMenuItem.DRAWER_MENU_ITEM_NOTIFICATIONS))
                .addView(DrawerMenuItem(this.applicationContext, DrawerMenuItem.DRAWER_MENU_ITEM_TERMS))
                .addView(DrawerMenuItem(this.applicationContext, DrawerMenuItem.DRAWER_MENU_ITEM_SETTINGS))
                .addView(DrawerMenuItem(this.applicationContext, DrawerMenuItem.DRAWER_MENU_ITEM_ABOUT))

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
