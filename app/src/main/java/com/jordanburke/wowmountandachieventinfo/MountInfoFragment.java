package com.jordanburke.wowmountandachieventinfo;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class MountInfoFragment extends Fragment {


    public MountInfoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_mount_info, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    public static MountInfoFragment newInstance() {
        
        Bundle args = new Bundle();
        
        MountInfoFragment fragment = new MountInfoFragment();
        fragment.setArguments(args);
        return fragment;
    }

}
