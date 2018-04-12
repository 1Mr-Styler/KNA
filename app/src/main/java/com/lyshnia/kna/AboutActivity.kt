package com.lyshnia.kna

import android.content.res.Configuration
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AppCompatDelegate
import android.view.Gravity
import android.view.View
import android.widget.Toast
import mehdi.sakout.aboutpage.AboutPage
import mehdi.sakout.aboutpage.Element
import java.util.*

class AboutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        simulateDayNight(/* DAY */1)

        val aboutPage = AboutPage(this)
                .isRTL(false)
                .setImage(R.drawable.about_bg)
                .setDescription(getString(R.string.about_desc))
                .addItem(Element().setTitle("Version 1.0 Wireframe"))
                .addGroup("Connect with us")
                .addEmail("support@koinoniasermons.org")
                .addWebsite("http://koinoniasermons.org/")
                .addFacebook("koinoniaeni")
                .addTwitter("koinonia_eni")
                .addPlayStore("com.lyshnia.android.kna")
                .addItem(getCopyRightsElement())
                .create()

        setContentView(aboutPage)
    }


    internal fun getCopyRightsElement(): Element {
        val copyRightsElement = Element()
        val copyrights = String.format(getString(R.string.copy_right), Calendar.getInstance().get(Calendar.YEAR))
        copyRightsElement.setTitle(copyrights)
        copyRightsElement.setIconDrawable(R.drawable.about_icon_copy_right)
        copyRightsElement.setIconTint(mehdi.sakout.aboutpage.R.color.about_item_icon_color)
        copyRightsElement.iconNightTint = android.R.color.white
        copyRightsElement.gravity = Gravity.CENTER
        copyRightsElement.onClickListener = View.OnClickListener { Toast.makeText(this@AboutActivity, copyrights, Toast.LENGTH_SHORT).show() }
        return copyRightsElement
    }

    internal fun simulateDayNight(currentSetting: Int) {
        val DAY = 0
        val NIGHT = 1
        val FOLLOW_SYSTEM = 3

        val currentNightMode = resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
        if (currentSetting == DAY && currentNightMode != Configuration.UI_MODE_NIGHT_NO) {
            AppCompatDelegate.setDefaultNightMode(
                    AppCompatDelegate.MODE_NIGHT_NO)
        } else if (currentSetting == NIGHT && currentNightMode != Configuration.UI_MODE_NIGHT_YES) {
            AppCompatDelegate.setDefaultNightMode(
                    AppCompatDelegate.MODE_NIGHT_YES)
        } else if (currentSetting == FOLLOW_SYSTEM) {
            AppCompatDelegate.setDefaultNightMode(
                    AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
        }
    }
}
