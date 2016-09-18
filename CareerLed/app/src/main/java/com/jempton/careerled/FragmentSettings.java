package com.jempton.careerled;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

/**
 * Created by BALE on 16/09/2015.
 */
public class FragmentSettings extends Fragment {

    MenuList [] menuListData;
    ListView settingsList;

    public FragmentSettings(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_settings, container, false);
        settingsList = (ListView)rootView.findViewById(R.id.settings_list);
        menuListData = new MenuList[]{
                new MenuList("About",R.drawable.connect_icon ), new MenuList("Account",R.drawable.ic_communities ),
                new MenuList("Profile",R.drawable.ic_communities ),
                new MenuList("Feedback",R.drawable.forum_icon ), new MenuList("Help",R.drawable.guide_icon_ ),
                new MenuList("Report An Issue",R.drawable.ic_pages ), new MenuList("Terms of Service",R.drawable.settings_icon),
                new MenuList("Log Out",R.drawable.ic_launcher)};

        final SettingsAdapter adp = new SettingsAdapter(rootView.getContext(),R.layout.row_settings, menuListData );
        settingsList.setAdapter(adp);

        settingsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 3) {
                    Intent intent = new Intent(rootView.getContext(), ActivityFeedback.class);
                    startActivity(intent);
                } else if (position == 2) {
                    Intent intent = new Intent(rootView.getContext(), ActivityProfilePageUser.class);
                    startActivity(intent);
                } else if (position == 1) {
                    Intent intent = new Intent(rootView.getContext(), ActivityAccount.class);
                    startActivity(intent);
                }
                else if (position == 5) {
                    Intent intent = new Intent(rootView.getContext(), ActivityReportIssue.class);
                    startActivity(intent);
                }

            }
        });
        return rootView;
    }

}
