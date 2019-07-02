package com.hhmarket.mobile.ui.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.hhmarket.mobile.R;
import com.hhmarket.mobile.ui.activity.AboutActivity;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the

 * create an instance of this fragment.
 */
public class AboutFragment extends Fragment {

    public static final String TAG = "AboutFragment";
    private ListView listView ;

    public AboutFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View viewRoot = inflater.inflate(R.layout.fragment_about, container, false);

        listView = (ListView) viewRoot.findViewById(R.id.listItemAbout);
       // list.setAdapter();
        // Defined Array values to show in ListView
        String[] values = new String[] { getString(R.string.about_terms),
                getString(R.string.about_privacy)

        };

        // Define a new Adapter
        // First parameter - Context
        // Second parameter - Layout for the row
        // Third parameter - ID of the TextView to which the data is written
        // Forth - the Array of data

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, android.R.id.text1, values);


        // Assign adapter to ListView
        listView.setAdapter(adapter);

        // ListView Item Click Listener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                // ListView Clicked item index
                int itemPosition = position;

                // ListView Clicked item value
                String  itemValue    = (String) listView.getItemAtPosition(position);
                if (position ==0 ) {

                    ((AboutActivity)getActivity()).showTermsFragment();
                } else {
                   // show privacy url
                    String url = "http://www.stackoverflow.com";
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    startActivity(i);
                }

            }

        });
        return viewRoot;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }


}
