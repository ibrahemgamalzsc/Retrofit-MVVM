package com.ibrahemgamal.retromvvm.presentation.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.gson.Gson;
import com.ibrahemgamal.retromvvm.R;
import com.ibrahemgamal.retromvvm.data.model.User;
import com.ibrahemgamal.retromvvm.databinding.ActivityClickedUserBinding;

import static com.ibrahemgamal.retromvvm.presentation.view.MainActivity.PASS_USER;
import static com.ibrahemgamal.retromvvm.presentation.view.MapsFragment.LAT;
import static com.ibrahemgamal.retromvvm.presentation.view.MapsFragment.LNG;

public class ClickedUserActivity extends AppCompatActivity{
    private User user;
    private ActivityClickedUserBinding activityClickedUserBinding;
    private MapsFragment mapsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityClickedUserBinding = DataBindingUtil.setContentView(this, R.layout.activity_clicked_user);
        getPassedUserData();
        activityClickedUserBinding.setUser(user);
    }

    private void getPassedUserData() {
        Intent intent = getIntent();
        String userData = intent.getStringExtra(PASS_USER);
        Gson gson = new Gson();
        user = gson.fromJson(userData, User.class);
        sendDataToMapFragment(user);
    }

    private void sendDataToMapFragment(User user) {

        mapsFragment=new MapsFragment();
        Bundle bundle=new Bundle();
        bundle.putString(LAT,user.getAddress().getGeo().getLat());
        bundle.putString(LNG,user.getAddress().getGeo().getLng());
        mapsFragment.setArguments(bundle);
        FragmentManager manager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=manager.beginTransaction();
        fragmentTransaction.add(R.id.fragment_container,mapsFragment);
        fragmentTransaction.commit();
    }
}