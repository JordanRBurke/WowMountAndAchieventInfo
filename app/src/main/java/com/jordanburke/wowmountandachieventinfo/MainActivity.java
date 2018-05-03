package com.jordanburke.wowmountandachieventinfo;

import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.Toast;

import java.net.CookieHandler;
import java.util.ArrayList;
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
    private List<WowInformation.Mounts.CollectedMounts> collectedMounts;
    private MountCallback mountCallback;
    private String bundleInformation;

    private Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }



    @OnClick(R.id.submit_button_main)
    protected void submitButtonClicked() {

//        Bundle bundle = new Bundle();
//        bundle.putString(ACCOUNT_NAME, characterName.getText().toString());
//        bundle.putString(REALM_NAME, realmName.getText().toString());
//        mountInfoFragment.setArguments(bundle);
        String usernameWow = characterName.getText().toString();
        String realmWow = realmName.getText().toString();


        mountInfoFragment = MountInfoFragment.newInstance();

        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout_main, mountInfoFragment).commit();


            makeApiCall(usernameWow, realmWow, "mounts", "en_US", getString(R.string.blizzard_api_key));
            toastError("AdapterFound");





    }

    public void makeApiCall(final String user, final String realm, final String mounts, final String locale, final String key) {
        wowRetrofitInterface.getWowInformation(user, realm, mounts, locale, key ).enqueue(new Callback<WowInformation>() {
            @Override
            public void onResponse(Call<WowInformation> call, Response<WowInformation> response) {
                if (response.isSuccessful()) {
                    //TODO Get the info you get back here to the Fragment after you create the adaper and then notify dataset has changed
//                    .setText(response.body().getWowMounts().toString());
//                    mountTitle.setText(response.body().getWowMounts().toString());


                    bundleInformation = response.body().getWowMounts().toString();
                    bundle.putString("WOW_API_INFO", bundleInformation);
                    bundle.putString("WOW__CALL", user);
                    bundle.putString("WOW_REALM_CALL", realm);
                    bundle.putString("WOW_MOUNTS_CALL", mounts);
                    bundle.putString("WOW_LOCALE", locale);
                    bundle.putInt("WOW_API_KEY", R.string.blizzard_api_key);
                    mountInfoFragment.setArguments(bundle);

                } else {
                    Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<WowInformation> call, Throwable t) {

            }
        });
    }



    public interface MountCallback {

        void mountClass(List<WowInformation.Mounts.CollectedMounts> collectedMountsList);


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


//        usernameWow = getArguments().get(ACCOUNT_NAME).toString();
//        realmWow = getArguments().get(REALM_NAME).toString();
//        String user = usernameEdit.getText().toString();
//        String realm = realmNameEdit.getText().toString();



//        } catch (Exception e) {
//            Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
//            toastError("Error");
//        }

    }
    private String toastError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show();

        return error;

    }






}
