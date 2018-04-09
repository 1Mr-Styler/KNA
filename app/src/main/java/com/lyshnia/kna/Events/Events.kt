package com.lyshnia.kna.Events


import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.View
import com.lyshnia.kna.DrawerHeader
import com.lyshnia.kna.DrawerMenuItem
import com.lyshnia.kna.R
import com.mindorks.placeholderview.PlaceHolderView
import kotlinx.android.synthetic.main.toolbar_home.*
import org.json.JSONObject
import java.io.File

class Events : AppCompatActivity(), RecyclerAdapter.ItemClickListener {

    private var mDrawerView: PlaceHolderView? = null
    private var mDrawer: DrawerLayout? = null
    private var mToolbar: Toolbar? = null

    var adapter: RecyclerAdapter? = null
    private lateinit var buls: ArrayList<HashMap<String, String>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_events)

        toolbarTitle.text = "Events"

        mDrawer = findViewById<DrawerLayout>(R.id.drawerLayout);
        mDrawerView = findViewById<PlaceHolderView>(R.id.drawerView);
        mToolbar = findViewById<Toolbar>(R.id.toolbar);
        setupDrawer();


        // data to populate the RecyclerView with
        var listItems = ArrayList<HashMap<String, String>>();

        // Simulate number of bulettins
        buls = arrayListOf<HashMap<String, String>>(
                hashMapOf<String, String>(
                        "title" to "Kingdom Economics II",
                        "date" to "Fri 12, Jun 2018 at 8pm",
                        "dateD" to "12",
                        "att" to "670 people attending",
                        "location" to "Leather Research Road, Zaria, Nigeria"
                ),
                hashMapOf<String, String>(
                        "title" to "The Power of Genuine Brokenness",
                        "date" to "Fri 2, Jul 2018 at 9pm",
                        "dateD" to "2",
                        "att" to "718 people attending",
                        "location" to "CGC, Opp. 2nd Ecwa Church, Samaru, Zaria."
                ),
                hashMapOf<String, String>(
                        "title" to "The Mystery of Strongholds",
                        "date" to "Mon 17, Aug 2018 at 6.30pm",
                        "dateD" to "17",
                        "att" to "598 people attending",
                        "location" to "Leather Research Road, Zaria, Nigeria"
                ),
                hashMapOf<String, String>(
                        "title" to "September 2018 Miracle Service",
                        "date" to "Fri 28, Sep 2018 at 3pm",
                        "dateD" to "8",
                        "att" to "1,252 people attending",
                        "location" to "CGC, Opp. 2nd Ecwa Church, Samaru, Zaria."
                )
        )


        for (bul in buls) {
            Log.i("GFD", bul.toString())
            listItems.add(bul);
        }

        Log.i("REF", listItems.toString())

        // set up the RecyclerView
        var recyclerView = findViewById<RecyclerView>(R.id.recyclerView);
        recyclerView.setLayoutManager(LinearLayoutManager(this));
        adapter = RecyclerAdapter(this, listItems);
        adapter!!.setClickListener(this);
        recyclerView.setAdapter(adapter);


        val itemDecorator = DividerItemDecoration(recyclerView.context, DividerItemDecoration.VERTICAL)
        itemDecorator.setDrawable(ContextCompat.getDrawable(recyclerView.context, R.drawable.divider)!!)

        recyclerView.addItemDecoration(itemDecorator)
    }

    public override fun onItemClick(view: View, position: Int) {
        val path = this@Events.getFilesDir()
        val letDirectory = File(path, "temp")
        letDirectory.mkdirs()

        val file = File(letDirectory, "temp_evt.json")
        file.writeText(JSONObject(buls.get(position)).toString())

//        val intent = Intent(this, EventSingle::class.java)
//        startActivity(intent)

        EventSingle.navigate(this, view.findViewById(R.id.imageView6))

    }

    private fun setupDrawer() {
        mDrawerView!!
                .addView(DrawerHeader())
                .addView(DrawerMenuItem(this, DrawerMenuItem.DRAWER_MENU_ITEM_HOME))
                .addView(DrawerMenuItem(this, DrawerMenuItem.DRAWER_MENU_ITEM_EVENTS))
                .addView(DrawerMenuItem(this, DrawerMenuItem.DRAWER_MENU_ITEM_SERMONS))
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
