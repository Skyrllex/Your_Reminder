package com.example.yourreminder.screens.details;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.yourreminder.App;
import com.example.yourreminder.R;
import com.example.yourreminder.module.Task;

import java.util.Objects;

public class TaskDetailsActivity extends AppCompatActivity {

    private static final String EXTRA_TASK = "TaskDetailsActivity.EXTRA_TASK";

    private Task task;

    private EditText edittask;
    private EditText editsubtask;
    private TextView priotitylvl;
    private SeekBar seekbar;
    private TextView textdatatime;
    private Button bdate;
    private Button bdelete;
    private Button bsave;


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
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        setTitle(getString(R.string.toolbar_edit));

        edittask = findViewById(R.id.editTask);
        editsubtask = findViewById(R.id.editSubTask);

        priotitylvl = findViewById(R.id.textpriority);
        seekbar=findViewById(R.id.seekBar);
        textdatatime = findViewById(R.id.textDateandTime);


        bdate = findViewById(R.id.bdata);
        bsave = findViewById(R.id.bSave);
        bdelete = findViewById(R.id.bDelete);

        bdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonDate();
            }
        });

        bsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonSave();
            }
        });

        bdelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonDelete();
            }
        });



        if (getIntent().hasExtra(EXTRA_TASK)){
            task = getIntent().getParcelableExtra(EXTRA_TASK);
            edittask.setText(task.texttask);
            editsubtask.setText(task.textsubtask);
          //  priotitylvl.setText((int)task.priority);
            //textdatatime.setText((int) task.calendar);
        } else {
            task = new Task();
        }

    }

    public void onButtonSave()
    {
        if (edittask.getText().length()>0){
            task.texttask=edittask.getText().toString();
            task.textsubtask=editsubtask.getText().toString();
            task.done=false;
            task.timectreate = System.currentTimeMillis();
            task.priority = 8;//Integer.parseInt(priotitylvl.getText().toString());
            task.calendar = System.currentTimeMillis();//Integer.parseInt(textdatatime.getText().toString());
            if (getIntent().hasExtra(EXTRA_TASK)) {
                App.getInstance().getTaskDao().update(task);
            } else {
                App.getInstance().getTaskDao().insertTask(task);
            }
        }
        finish();
    }

    public void onButtonDelete( )
    {
        if (getIntent().hasExtra(EXTRA_TASK)){
            App.getInstance().getTaskDao().delete(task);
        }
        finish();
    }
    public void onButtonDate( )
    {


    }
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

}
