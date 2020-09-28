package com.ibrahemgamal.retromvvm.data.remote;

import com.ibrahemgamal.retromvvm.data.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIService {

    @GET("users")
    Call<List<User>> userListCall();
}
