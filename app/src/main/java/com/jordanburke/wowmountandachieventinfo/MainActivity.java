package com.jordanburke.wowmountandachieventinfo;

import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    public final static String ACCOUNT_NAME = "account_name";
    public final static String REALM_NAME = "realm_name";

    @BindView(R.id.submit_button_main)
    protected String submitButton;
    @BindView(R.id.character_name_edit)
    protected TextInputEditText characterName;
    @BindView(R.id.realm_name_edit)
    protected TextInputEditText realmName;
    private MountInfoFragment mountInfoFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.submit_button_main)
    protected void submitButtonClicked() {
        mountInfoFragment = MountInfoFragment.newInstance();
        Bundle bundle = new Bundle();
        bundle.putString(ACCOUNT_NAME, characterName.getText().toString());
        bundle.putString(REALM_NAME, realmName.getText().toString());
        mountInfoFragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout_main, mountInfoFragment).commit();



    }

}
