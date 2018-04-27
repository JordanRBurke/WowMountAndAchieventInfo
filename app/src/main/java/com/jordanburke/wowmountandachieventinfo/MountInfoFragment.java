package com.jordanburke.wowmountandachieventinfo;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

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

    private Retrofit retrofit;
    private String baseUrl = "https://us.api.battle.net/wow/character/Thrall/Amabo?fields=mounts&locale=en_US&apikey=5r2rskc5qp9x2mh7dtabxjznz4vf2dc5";
    private WowRetrofitInterface wowRetrofitInterface;


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

    private void buildRetrofit() {
        retrofit = new Retrofit.Builder().baseUrl(baseUrl).addConverterFactory
                (GsonConverterFactory.create()).build();
        wowRetrofitInterface = retrofit.create(WowRetrofitInterface.class);
    }

    @Override
    public void onStart() {
        super.onStart();
        buildRetrofit();
    }

    public void makeApiCall(String user, String realm) {
        wowRetrofitInterface.getWowInformation(user, realm).enqueue(new Callback<WowRetrofitInterface.WowInformation>() {
            @Override
            public void onResponse(Call<WowRetrofitInterface.WowInformation> call, Response<WowRetrofitInterface.WowInformation> response) {
                if (response.isSuccessful()) {

                } else {
                    Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<WowRetrofitInterface.WowInformation> call, Throwable t) {

            }
        });
    }
}
