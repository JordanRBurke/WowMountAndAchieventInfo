package com.jordanburke.wowmountandachieventinfo;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * A simple {@link Fragment} subclass.
 */
public class MountInfoFragment extends Fragment {




    public final static String ACCOUNT_NAME = "account_name";
    public final static String REALM_NAME = "realm_name";


    private Retrofit retrofit;
    private String baseUrl = "https://us.api.battle.net/wow/character/";
    private WowRetrofitInterface wowRetrofitInterface;
    private MountInfoFragment mountInfoFragment;
    private MountAdapter adapter;
    @BindView(R.id.mount_recycler_view)
    protected RecyclerView recyclerView;
    private List<WowInformation.Mounts.CollectedMounts> collectedMounts;
    private Bundle bundle;
    private String bundleInformation;


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

    @Override
    public void onStart() {
        super.onStart();
        List<WowInformation.Mounts.CollectedMounts> collectedMounts = getArguments().getParcelableArrayList(MainActivity.MOUNT_LIST);
        setAdapter(collectedMounts);
    }



    private void setAdapter(List<WowInformation.Mounts.CollectedMounts> mountList) {

//        mountInfoFragment.getArguments().toString();

        adapter = new MountAdapter(mountList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
//       linearLayoutManager.
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();



    }

}
