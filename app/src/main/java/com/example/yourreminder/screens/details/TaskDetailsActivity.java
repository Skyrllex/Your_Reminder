package com.example.yourreminder.screens.details;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toolbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.yourreminder.R;
import com.example.yourreminder.databinding.ActivityMainBinding;
import com.example.yourreminder.module.Task;

public class TaskDetailsActivity extends AppCompatActivity {

    private static final String EXTRA_TASK = "TaskDetailsActivity .EXTRA_TASK";

    private Task task;

    private EditText edittask;
    private EditText editsubtask;

    public static void start(Activity caller, Task task){
        Intent intent = new Intent (caller, TaskDetailsActivity.class);
        if (task!=null){
            intent.putExtra(EXTRA_TASK, task);
        }
        caller.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_task_details);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

}
