package com.lyshnia.kna;

import android.widget.ImageView;
import android.widget.TextView;

import com.mindorks.placeholderview.annotations.Layout;
import com.mindorks.placeholderview.annotations.NonReusable;
import com.mindorks.placeholderview.annotations.Resolve;
import com.mindorks.placeholderview.annotations.View;

@NonReusable
@Layout(R.layout.drawer_header)
public class DrawerHeader {

/*    @View(R.id.profileImageView)
    public ImageView profileImage;*/

    @View(R.id.nameTxt)
    public TextView nameTxt;

    /*@View(R.id.emailTxt)
    public TextView emailTxt;*/

    @Resolve
    public void onResolved() {
        nameTxt.setText("ENI Koinonia");
//        emailTxt.setText("janishar.ali@gmail.com");
    }
}