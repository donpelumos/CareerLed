package com.jempton.careerled;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by BALE on 16/10/2015.
 */
public class NotificationListAdapter extends ArrayAdapter<NotificationList> {
    Context context;
    NotificationList[] data;
    int layoutResourceId;
    int selectedItem;


    public NotificationListAdapter(final Context context, final int layoutResourceId, final NotificationList[] data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    public View getView(final int n, final View view, final ViewGroup viewGroup) {
        View inflate = view;
        ImageView menuImage = null;
        TextView menuText = null;
        TextView menudateTime = null;
        if (inflate == null) {
            inflate = LayoutInflater.from(getContext()).inflate(this.layoutResourceId, null);

        }
        //menuImage = (ImageView) inflate.findViewById(R.id.person_icon);
        //menuText = (TextView) inflate.findViewById(R.id.person_name);



        //final NetworkList list = this.data[n];
        //menuText.setText((CharSequence) list.personName);
        //menuImage.setImageResource(list.personImage);


        return inflate;
    }
}
