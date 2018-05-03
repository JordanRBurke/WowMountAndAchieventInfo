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
public class MountInfoFragment extends Fragment implements Parcelable{




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
//    @BindView(R.id.mount_text_view)
//    protected TextView mountTitle;





//    private String wowName = getArguments().getString(ACCOUNT_NAME).toString();


    public MountInfoFragment() {
        // Required empty public constructor
    }


    @SuppressLint("ValidFragment")
    protected MountInfoFragment(Parcel in) {
        baseUrl = in.readString();
        mountInfoFragment = in.readParcelable(MountInfoFragment.class.getClassLoader());

    }

    public static final Creator<MountInfoFragment> CREATOR = new Creator<MountInfoFragment>() {
        @Override
        public MountInfoFragment createFromParcel(Parcel in) {
            return new MountInfoFragment(in);
        }

        @Override
        public MountInfoFragment[] newArray(int size) {
            return new MountInfoFragment[size];
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_mount_info, container, false);
        ButterKnife.bind(this, view);
        adapterOkayCheck();
        return view;
    }

    private void adapterOkayCheck() {
        if (adapter == null) {
            toastError("Adapter Is Empty");
        } else {
            toastError("Adapter Is Filled");
        }
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
        setAdapter();
    }

    private String toastError(String error) {
        Toast.makeText(getContext(), error, Toast.LENGTH_LONG).show();

        return error;

    }
    public void makeApiCall(final String user, final String realm, final String mounts, final String locale, final String key) {
        wowRetrofitInterface.getWowInformation(user, realm, mounts, locale, key ).enqueue(new Callback<WowInformation>() {
            @Override
            public void onResponse(Call<WowInformation> call, Response<WowInformation> response) {
                if (response.isSuccessful()) {
                    //TODO Get the info you get back here to the Fragment after you create the adaper and then notify dataset has changed
//                    .setText(response.body().getWowMounts().toString());
                    response.body().getWowMounts().toString();
                    toastError("Success");

//                    mountInfoFragment.getArguments().getString("WOW_CALL");
//                    bundleInformation = response.body().getWowMounts().toString();
//                    bundle.putString("WOW_API_INFO", bundleInformation);
//                    bundle.putString("WOW__CALL", user);
//                    bundle.putString("WOW_REALM_CALL", realm);
//                    bundle.putString("WOW_MOUNTS_CALL", mounts);
//                    bundle.putString("WOW_LOCALE", locale);
//                    bundle.putInt("WOW_API_KEY", R.string.blizzard_api_key);
//                    mountInfoFragment.setArguments(bundle);

                } else {
                    Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<WowInformation> call, Throwable t) {
                toastError("Failure");
            }
        });
    }

    private void setAdapter() {

//        mountInfoFragment.getArguments().toString();

        adapter = new MountAdapter(collectedMounts);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
//       linearLayoutManager.
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();



    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(baseUrl);
        dest.writeParcelable(mountInfoFragment, flags);
    }
}
