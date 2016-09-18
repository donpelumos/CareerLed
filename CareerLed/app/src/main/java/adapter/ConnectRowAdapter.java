package adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.jempton.careerled.MenuList;
import com.jempton.careerled.R;


public class ConnectRowAdapter extends ArrayAdapter<ConnectRow> {
    Context context;
    ConnectRow[] data;
    int layoutResourceId;
    int selectedItem;
    View inflate;


    public ConnectRowAdapter(final Context context, final int layoutResourceId, final ConnectRow[] data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    public View getView(final int n, final View view, final ViewGroup viewGroup) {
        inflate = view;
        ImageView menuImage = null;
        TextView menuText = null;
        //listholder tag;
        if (inflate == null) {
            inflate = LayoutInflater.from(getContext()).inflate(this.layoutResourceId, null);
            Log.v("DONE", "Done Connect Row");
            //tag = new listholder();
        }
        menuImage = (ImageView)inflate.findViewById(R.id.menu_icon);
        menuText = (TextView)inflate.findViewById(R.id.fullname);
        //inflate.setTag((Object)tag);


        final ConnectRow list = this.data[n];
        menuText.setText((CharSequence)list.connectName);
        menuImage.setImageResource(list.connectImage);

        inflate.setPressed(true);

        return inflate;
    }





    static class listholder
    {
        ImageView imgIcon;
        TextView txtTitle;
    }

}