package com.lyshnia.kna;

import android.content.Context;
import android.content.Intent;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.lyshnia.kna.Events.Events;
import com.lyshnia.kna.Sermons.Sermons;
import com.lyshnia.kna.Settings.SettingsActivity;
import com.mindorks.placeholderview.annotations.Click;
import com.mindorks.placeholderview.annotations.Layout;
import com.mindorks.placeholderview.annotations.Resolve;
import com.mindorks.placeholderview.annotations.View;

@Layout(R.layout.drawer_item)
public class DrawerMenuItem {

    public static final int DRAWER_MENU_ITEM_HOME = 1;
    public static final int DRAWER_MENU_ITEM_EVENTS = 2;
    public static final int DRAWER_MENU_ITEM_SERMONS = 3;
    public static final int DRAWER_MENU_ITEM_NOTIFICATIONS = 4;
    public static final int DRAWER_MENU_ITEM_SETTINGS = 5;
    public static final int DRAWER_MENU_ITEM_TERMS = 6;
    public static final int DRAWER_MENU_ITEM_ABOUT = 7;

    public int mMenuPosition;
    public Context mContext;
    public DrawerCallBack mCallBack;

    @View(R.id.itemNameTxt)
    public TextView itemNameTxt;

    @View(R.id.itemIcon)
    public ImageView itemIcon;

    public DrawerMenuItem(Context context, int menuPosition) {
        mContext = context;
        mMenuPosition = menuPosition;
    }

    @Resolve
    public void onResolved() {
        switch (mMenuPosition) {
            case DRAWER_MENU_ITEM_HOME:
                itemNameTxt.setText("Home");
                itemIcon.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_home_black_18dp));
                break;
            case DRAWER_MENU_ITEM_EVENTS:
                itemNameTxt.setText("Events");
                itemIcon.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_compare_arrows_black_18dp));
                break;
            case DRAWER_MENU_ITEM_SERMONS:
                itemNameTxt.setText("Sermons");
                itemIcon.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_group_work_black_18dp));
                break;
            case DRAWER_MENU_ITEM_NOTIFICATIONS:
                itemNameTxt.setText("Notifications");
                itemIcon.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_notifications_black_18dp));
                break;
            case DRAWER_MENU_ITEM_SETTINGS:
                itemNameTxt.setText("Settings");
                itemIcon.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_settings_black_18dp));
                break;
            case DRAWER_MENU_ITEM_TERMS:
                itemIcon.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_book_black_18dp));
                itemNameTxt.setText("Downloads");
                break;
            case DRAWER_MENU_ITEM_ABOUT:
                itemIcon.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_exit_to_app_black_18dp));
                itemNameTxt.setText("About App");
                break;
        }
    }

    @Click(R.id.mainView)
    public void onMenuItemClick() {
        switch (mMenuPosition) {
            case DRAWER_MENU_ITEM_HOME:
                Intent intent = new Intent(mContext, MainActivity.class);
                mContext.startActivity(intent);
                if (mCallBack != null) mCallBack.onProfileMenuSelected();
                break;
            case DRAWER_MENU_ITEM_EVENTS:
                intent = new Intent(mContext, Events.class);
                mContext.startActivity(intent);
                if (mCallBack != null) mCallBack.onEventsMenuSelected();
                break;
            case DRAWER_MENU_ITEM_SERMONS:
                intent = new Intent(mContext, Sermons.class);
                mContext.startActivity(intent);
                if (mCallBack != null) mCallBack.onGroupsMenuSelected();
                break;
            case DRAWER_MENU_ITEM_NOTIFICATIONS:
                Toast.makeText(mContext, "No New Notifications", Toast.LENGTH_SHORT).show();
                if (mCallBack != null) mCallBack.onNotificationsMenuSelected();
                break;
            case DRAWER_MENU_ITEM_SETTINGS:
                intent = new Intent(mContext, SettingsActivity.class);
                mContext.startActivity(intent);
                if (mCallBack != null) mCallBack.onSettingsMenuSelected();
                break;
            case DRAWER_MENU_ITEM_TERMS:
                Toast.makeText(mContext, "Terms", Toast.LENGTH_SHORT).show();
                if (mCallBack != null) mCallBack.onTermsMenuSelected();
                break;
            case DRAWER_MENU_ITEM_ABOUT:
                Toast.makeText(mContext, "Logout", Toast.LENGTH_SHORT).show();
                if (mCallBack != null) mCallBack.onLogoutMenuSelected();
                break;
        }
    }

    public void setDrawerCallBack(DrawerCallBack callBack) {
        mCallBack = callBack;
    }

    public interface DrawerCallBack {
        void onProfileMenuSelected();

        void onEventsMenuSelected();

        void onGroupsMenuSelected();

        void onNotificationsMenuSelected();

        void onSettingsMenuSelected();

        void onTermsMenuSelected();

        void onLogoutMenuSelected();
    }
}