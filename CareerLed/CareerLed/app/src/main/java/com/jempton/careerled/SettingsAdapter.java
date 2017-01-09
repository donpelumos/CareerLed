package com.jempton.careerled;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by BALE on 16/09/2015.
 */
public class SettingsAdapter  extends ArrayAdapter<MenuList> {
    Context context;
    MenuList[] data;
    int layoutResourceId;
    int selectedItem;


    public SettingsAdapter(final Context context, final int layoutResourceId, final MenuList[] data) {
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
        menuImage = (ImageView)inflate.findViewById(R.id.settings_icon);
        menuText = (TextView)inflate.findViewById(R.id.settings_text);
        //inflate.setTag((Object)tag);


        final MenuList list = this.data[n];
        menuText.setText((CharSequence)list.menuName);
        menuImage.setImageResource(list.menuImage);


        return inflate;
    }


}
