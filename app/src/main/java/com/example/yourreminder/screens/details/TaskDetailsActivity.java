package com.example.yourreminder.screens.details;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.yourreminder.App;
import com.example.yourreminder.R;
import com.example.yourreminder.module.Task;

public class TaskDetailsActivity extends AppCompatActivity {

    private static final String EXTRA_TASK = "TaskDetailsActivity.EXTRA_TASK";

    private Task task;

    private EditText edittask;
    private EditText editsubtask;
    private TextView priotitylvl;
    private SeekBar seekbar;
    private TextView textdatatime;


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
        setTitle(getString(R.string.toolbar_edit));

        edittask = findViewById(R.id.editTask);
        editsubtask = findViewById(R.id.editSubTask);

        priotitylvl = findViewById(R.id.textpriority);
        seekbar=findViewById(R.id.seekBar);
        textdatatime = findViewById(R.id.textDateandTime);




        if (getIntent().hasExtra(EXTRA_TASK)){
            task = getIntent().getParcelableExtra(EXTRA_TASK);
            edittask.setText(task.texttask);
            editsubtask.setText(task.textsubtask);
            priotitylvl.setText(task.priority);
            textdatatime.setText((int) task.calendar);
        } else {
            task = new Task();
        }

    }

    private void setSupportActionBar(Toolbar toolbar) {
    }

    public void onButtonSave(View view)
    {
        if (edittask.getText().length()>0){
            task.texttask=edittask.getText().toString();
            task.textsubtask=editsubtask.getText().toString();
            task.done=false;
            task.timectreate = System.currentTimeMillis();
            task.priority = Integer.parseInt(priotitylvl.getText().toString());
            task.calendar = Integer.parseInt(textdatatime.getText().toString());
            if (getIntent().hasExtra(EXTRA_TASK)){
                App.getInstance().getTaskDao().update(task);
            } else{
                App.getInstance().getTaskDao().insertTask(task);
            }
        }
        finish();
    }

    public void onButtonDelete(View view)
    {
        if (getIntent().hasExtra(EXTRA_TASK)){
            App.getInstance().getTaskDao().delete(task);
        }
        finish();
    }
    public void onButtonDate(View view)
    {


    }
}
