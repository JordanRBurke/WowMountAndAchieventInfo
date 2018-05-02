package com.jordanburke.wowmountandachieventinfo;

import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.Toast;

import java.net.CookieHandler;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    public final static String ACCOUNT_NAME = "account_name";
    public final static String REALM_NAME = "realm_name";

    @BindView(R.id.submit_button_main)
    protected Button submitButton;
    @BindView(R.id.character_name_edit)
    protected TextInputEditText characterName;
    @BindView(R.id.realm_name_edit)
    protected TextInputEditText realmName;
    private MountInfoFragment mountInfoFragment;
    private Retrofit retrofit;
    private String baseUrl = "https://us.api.battle.net/wow/character/";
    private WowRetrofitInterface wowRetrofitInterface;
    private MountAdapter adapter;
    private RecyclerView recyclerView;
    private List<WowRetrofitInterface.WowInformation.Mounts.CollectedMounts> collectedMounts;
    private MountCallback mountCallback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }



    @OnClick(R.id.submit_button_main)
    protected void submitButtonClicked() {
        mountInfoFragment = MountInfoFragment.newInstance();
//        Bundle bundle = new Bundle();
//        bundle.putString(ACCOUNT_NAME, characterName.getText().toString());
//        bundle.putString(REALM_NAME, realmName.getText().toString());
//        mountInfoFragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout_main, mountInfoFragment).commit();




    }

    public void makeApiCall(String user, String realm, String mounts, String key) {
        wowRetrofitInterface.getWowInformation(user, realm, mounts, key).enqueue(new Callback<WowRetrofitInterface.WowInformation>() {
            @Override
            public void onResponse(Call<WowRetrofitInterface.WowInformation> call, Response<WowRetrofitInterface.WowInformation> response) {
                if (response.isSuccessful()) {
//                    .setText(response.body().getWowMounts().toString());
//                    mountTitle.setText(response.body().getWowMounts().toString());

                    mountCallback.mountClass(response.body().getWowMounts().getColletedMounts());




                } else {
                    Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<WowRetrofitInterface.WowInformation> call, Throwable t) {

            }
        });
    }



    public interface MountCallback {

        void mountClass(List<WowRetrofitInterface.WowInformation.Mounts.CollectedMounts> collectedMountsList);


    }

    private void buildRetrofit() {
        retrofit = new Retrofit.Builder().baseUrl(baseUrl).addConverterFactory
                (GsonConverterFactory.create()).build();
        wowRetrofitInterface = retrofit.create(WowRetrofitInterface.class);
    }
    @Override
    public void onStart() {
//        try {
//        private String usernameWow;
//        private String realmWow;
        super.onStart();
        buildRetrofit();
        String usernameWow = characterName.getText().toString();
        String realmWow = realmName.getText().toString();
//        usernameWow = getArguments().get(ACCOUNT_NAME).toString();
//        realmWow = getArguments().get(REALM_NAME).toString();
//        String user = usernameEdit.getText().toString();
//        String realm = realmNameEdit.getText().toString();

        makeApiCall(usernameWow, realmWow, "mounts", getString(R.string.blizzard_api_key));
        setAdapter();

//        } catch (Exception e) {
//            Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
//            toastError("Error");
//        }
    }

    private void setAdapter() {
        adapter = new MountAdapter(collectedMounts);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
//        linearLayoutManager.
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }





}
