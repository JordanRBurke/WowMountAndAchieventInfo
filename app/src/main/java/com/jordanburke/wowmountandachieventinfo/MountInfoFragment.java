package com.jordanburke.wowmountandachieventinfo;


import android.os.Bundle;
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
    private List<WowRetrofitInterface.WowInformation.Mounts.CollectedMounts> collectedMounts;
//    @BindView(R.id.mount_text_view)
//    protected TextView mountTitle;





//    private String wowName = getArguments().getString(ACCOUNT_NAME).toString();


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







    private String toastError(String error) {
        Toast.makeText(getContext(), error, Toast.LENGTH_LONG).show();

        return error;

    }





}
