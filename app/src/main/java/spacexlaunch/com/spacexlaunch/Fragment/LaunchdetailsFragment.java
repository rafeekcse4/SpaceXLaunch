package spacexlaunch.com.spacexlaunch.Fragment;


import android.app.DialogFragment;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.LinkedHashMap;
import java.util.LinkedList;

import spacexlaunch.com.spacexlaunch.Adapter.LaunchFragmentAdapter;
import spacexlaunch.com.spacexlaunch.R;

/**
 * A simple {@link } subclass.
 */
public class LaunchdetailsFragment extends DialogFragment {

    //Initiate the recyclerview id
    RecyclerView recyclerView;

    LaunchFragmentAdapter launchFragmentAdapter;

    public LaunchdetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_launchdetails, container, false);

        getDialog().setTitle("Rocket Details");

        @SuppressWarnings("unchecked") LinkedHashMap<String,String> map= (LinkedHashMap<String, String>) getArguments().getSerializable("details");
        @SuppressWarnings("unchecked") LinkedList<String > list= (LinkedList<String>) getArguments().getSerializable("listdetails");


        recyclerView=view.findViewById(R.id.id_launchdetails_recyclerview);
        launchFragmentAdapter=new LaunchFragmentAdapter(map,list);
        RecyclerView.LayoutManager mLayoutmanager=new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutmanager);

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(launchFragmentAdapter);
        return view;
    }


}
