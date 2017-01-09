package com.jempton.careerled;

import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created by BALE on 27/09/2015.
 */
public class FragmentForums extends Fragment implements DialogForumOptions.NoticeDialogListener{
    MenuList [] menuListData;
    ListView settingsList;
    int longClickOnly = 0;
    DialogForumOptions newFragment;
    FragmentManager fm;
    public static final String TAG = "tag1";
    public FragmentForums(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_forums, container, false);
        settingsList = (ListView)rootView.findViewById(R.id.forums_list);
        menuListData = new MenuList[]{
                new MenuList("Forum 1",R.drawable.icon ), new MenuList("Forum 2",R.drawable.icon ),
                new MenuList("Forum 3",R.drawable.icon ),
                new MenuList("Forum 4",R.drawable.icon ), new MenuList("Forum 5",R.drawable.icon),
                new MenuList("Forum 6",R.drawable.icon ), new MenuList("Forum 7",R.drawable.icon),
                new MenuList("Forum 8",R.drawable.icon), new MenuList("Forum 9",R.drawable.icon),
                new MenuList("Forum 10",R.drawable.icon), new MenuList("Forum 11",R.drawable.icon),
                new MenuList("Forum 12",R.drawable.icon)};

        final SettingsAdapter adp = new SettingsAdapter(rootView.getContext(),R.layout.row_forums, menuListData );
        settingsList.setAdapter(adp);
        fm = getFragmentManager();
        settingsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (longClickOnly == 0) {
                    Intent intent = new Intent(rootView.getContext(), ActivityForumChat.class);
                    startActivity(intent);
                }

            }
        });
        settingsList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                longClickOnly = 1;
                DialogForumOptions newFragment = new DialogForumOptions();
                newFragment.show(fm, "missless");
                Bundle extras = new Bundle();
                extras.putInt("network", 1);
                newFragment.setArguments(extras);
                newFragment.setTargetFragment(FragmentForums.this, 0);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        longClickOnly = 0;
                    }
                }, 2000);
                return false;

            }
        });

        return rootView;
    }
    public void doit(){

    }

    @Override
    public void onSelected(DialogFragment dialog, String value) {
        DialogForumOptions df = (DialogForumOptions)fm.findFragmentByTag("missless");
        df.dismiss();
        longClickOnly = 0;
    }
}
