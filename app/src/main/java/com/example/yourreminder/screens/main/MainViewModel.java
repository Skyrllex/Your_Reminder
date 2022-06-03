package com.example.yourreminder.screens.main;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.yourreminder.App;
import com.example.yourreminder.module.Task;

import java.util.List;

public class MainViewModel extends ViewModel {

    private final LiveData<List<Task>> taskLiveData = App.getInstance().getTaskDao().getAllLiveData();

    public LiveData<List<Task>> getTaskLiveData(){
        return taskLiveData;

    }
}
