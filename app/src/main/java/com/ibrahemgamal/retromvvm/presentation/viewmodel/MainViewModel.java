package com.ibrahemgamal.retromvvm.presentation.viewmodel;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.ibrahemgamal.retromvvm.data.model.User;
import com.ibrahemgamal.retromvvm.data.repository.UsersRepository;

import java.util.List;

public class MainViewModel extends ViewModel {
    public LiveData<List<User>> userListLiveData;
    private UsersRepository usersRepository;
    private Context context;

    public MainViewModel(Context context) {
        usersRepository=new UsersRepository(context);
        this.context = context;
    }
    public void getUserList(){
        userListLiveData=usersRepository.getUserList();
    }
}
