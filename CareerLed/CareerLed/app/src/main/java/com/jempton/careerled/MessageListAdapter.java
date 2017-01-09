package com.jempton.careerled;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by BALE on 28/09/2015.
 */
public class MessageListAdapter extends ArrayAdapter<MessageList> {
    Context context;
    MessageList[] data;
    int layoutResourceId;
    int selectedItem;


    public MessageListAdapter(final Context context, final int layoutResourceId, final MessageList[] data) {
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
            //Log.v("DONE","Done");
        }
        menuImage = (ImageView)inflate.findViewById(R.id.person_icon);
        menuText = (TextView)inflate.findViewById(R.id.surname);
        //menudateTime = (TextView)inflate.findViewById(R.id.date_time);
        //inflate.setTag((Object)tag);


        final MessageList list = this.data[n];
        menuText.setText((CharSequence)list.personName);
        menuImage.setImageResource(list.personImage);
        //menudateTime.setText((CharSequence)list.dateTime);


        return inflate;
    }

}