package com.jempton.careerled;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


public class MenuListAdapter extends ArrayAdapter<MenuList> {
    Context context;
    MenuList[] data;
    int layoutResourceId;
    int selectedItem;


    public MenuListAdapter(final Context context, final int layoutResourceId, final MenuList[] data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    public View getView(final int n, final View view, final ViewGroup viewGroup) {
        View inflate = view;
        ImageView menuImage = null;
        TextView menuText = null;
        if (inflate == null) {
            inflate = LayoutInflater.from(getContext()).inflate(this.layoutResourceId, null);
            //Log.v("DONE","Done");
        }
            menuImage = (ImageView)inflate.findViewById(R.id.menu_icon);
            menuText = (TextView)inflate.findViewById(R.id.menu_text);
            //inflate.setTag((Object)tag);


        final MenuList list = this.data[n];
        menuText.setText((CharSequence)list.menuName);
        menuImage.setImageResource(list.menuImage);


        return inflate;
    }



    static class listholder
    {
        ImageView imgIcon;
        TextView txtTitle;
    }

}
