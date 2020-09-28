package com.ibrahemgamal.retromvvm.presentation.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.gms.tasks.Task;
import com.google.gson.Gson;
import com.ibrahemgamal.retromvvm.R;
import com.ibrahemgamal.retromvvm.data.model.User;
import com.ibrahemgamal.retromvvm.databinding.ActivityMainBinding;
import com.ibrahemgamal.retromvvm.presentation.adapter.ShowUsersAdapter;
import com.ibrahemgamal.retromvvm.presentation.viewmodel.MainViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity implements ShowUsersAdapter.onItemClickListen {
    private MainViewModel mainViewModel;
    private ActivityMainBinding activityMainBinding;
    private ShowUsersAdapter showUsersAdapter;
    public static final String PASS_USER = "user";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mainViewModel = new MainViewModel(this);
        mainViewModel.getUserList();
        mainViewModel.userListLiveData.observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                showUsersAdapter = new ShowUsersAdapter(users, MainActivity.this);
                activityMainBinding.showUsersRv.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                activityMainBinding.showUsersRv.setHasFixedSize(false);
                activityMainBinding.showUsersRv.setAdapter(showUsersAdapter);
            }
        });
        activityMainBinding.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                showUsersAdapter.getFilter().filter(s);
                return false;
            }
        });

    }

    @Override
    public void onItemClick(User user) {
        Intent intent = new Intent(MainActivity.this, ClickedUserActivity.class);
        Gson gson = new Gson();
        String userData = gson.toJson(user);
        intent.putExtra(PASS_USER, userData);
        startActivity(intent);
    }
}