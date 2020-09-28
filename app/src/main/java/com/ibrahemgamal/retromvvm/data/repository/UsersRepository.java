package com.ibrahemgamal.retromvvm.data.repository;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.ibrahemgamal.retromvvm.data.model.User;
import com.ibrahemgamal.retromvvm.data.remote.RetroClass;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UsersRepository {
    private MutableLiveData<List<User>> userListMutableLiveData;
    private Context context;

    public UsersRepository(Context context) {
        userListMutableLiveData = new MutableLiveData<>();
        this.context=context;
    }

    public MutableLiveData<List<User>>  getUserList() {
        RetroClass.getAPIService().userListCall().enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if (response.isSuccessful()){
                    userListMutableLiveData.setValue(response.body());
                }else {
                    Toast.makeText(context,response.message(),Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Toast.makeText(context,t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
        return userListMutableLiveData;
    }
}
