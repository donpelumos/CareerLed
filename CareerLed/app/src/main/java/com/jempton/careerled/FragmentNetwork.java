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
 * Created by BALE on 28/09/2015.
 */
public class FragmentNetwork extends Fragment {

    NetworkList [] networkListData;
    ListView networkList;

    public FragmentNetwork(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_network, container, false);
        networkList = (ListView)rootView.findViewById(R.id.network_list);
        networkListData = new NetworkList[]{
                new NetworkList("Surname + Firstname",R.drawable.ic_pages ), new NetworkList("Surname + Firstname",R.drawable.ic_pages ),
                new NetworkList("Surname + Firstname",R.drawable.ic_pages ),
                new NetworkList("Surname + Firstname",R.drawable.ic_pages ), new NetworkList("Surname + Firstname",R.drawable.ic_pages ),
                new NetworkList("Surname + Firstname",R.drawable.ic_pages ), new NetworkList("Surname + Firstname",R.drawable.ic_pages),
                new NetworkList("Surname + Firstname",R.drawable.ic_pages)};

        final NetworkListAdapter adp = new NetworkListAdapter(rootView.getContext(),R.layout.row_network, networkListData );
        networkList.setAdapter(adp);

        networkList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(rootView.getContext(), ActivityProfilePageOther.class);
                startActivity(intent);

            }
        });
        return rootView;
    }

}