package com.example.yourreminder.screens.main;

import android.app.Activity;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SortedList;

import com.example.yourreminder.App;
import com.example.yourreminder.R;
import com.example.yourreminder.module.Task;
import com.example.yourreminder.screens.details.TaskDetailsActivity;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.TaskViewHolder> {

    private SortedList<Task> sortedList;
    public Adapter(){
        sortedList = new SortedList<>(Task.class, new SortedList.Callback<Task>() {
            @Override
            public int compare(Task o1, Task o2) {
                if (!o2.done && o1.done){
                    return 1;
                }
                if (o2.done && !o1.done){
                    return -1;
                }
                if (o2.timectreate > o1.timectreate)
                {
                    return 1;
                }

                if (o2.priority > o1.priority){
                    return 1;
                }
                return (int) (o1.calendar - o2.calendar);
            }

            @Override
            public void onChanged(int position, int count) {
                notifyItemRangeChanged(position, count);
            }

            @Override
            public boolean areContentsTheSame(Task oldItem, Task newItem) {
                return oldItem.equals(newItem);
            }

            @Override
            public boolean areItemsTheSame(Task item1, Task item2) {
                return item1.uid==item2.uid;
            }

            @Override
            public void onInserted(int position, int count) {
                notifyItemRangeInserted(position, count);
            }

            @Override
            public void onRemoved(int position, int count) {
                notifyItemRangeRemoved(position, count);
            }

            @Override
            public void onMoved(int fromPosition, int toPosition) {
            notifyItemMoved(fromPosition, toPosition);
            }
        });
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TaskViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_task_list,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
    holder.bind(sortedList.get(position));
    }

    @Override
    public int getItemCount() {
        return sortedList.size();
    }

    public void setItems(List<Task> tasks){
        sortedList.replaceAll(tasks);
    }

    static class TaskViewHolder extends RecyclerView.ViewHolder {

        TextView itemTask;
        CheckBox itemDone;
        View itemDelete;

        Task task;

        boolean silentUpdate;

        public TaskViewHolder(@NonNull View itemView)
        {
            super(itemView);

            itemTask=itemView.findViewById(R.id.taskItem);
            itemDone=itemView.findViewById(R.id.doneItem);
            itemDelete=itemView.findViewById(R.id.deleteItem);

            itemView.setOnClickListener(view -> TaskDetailsActivity.start((Activity) itemView.getContext(), task));

            itemDone.setOnCheckedChangeListener((compoundButton, checked) -> {
                if (!silentUpdate){
                    task.done = checked;
                    App.getInstance().getTaskDao().update(task);
                }
                updateStrokeOut();
            });

            itemDelete.setOnClickListener(view -> App.getInstance().getTaskDao().delete(task));

        }

        public void bind(Task task){
            this.task = task;

            itemTask.setText(task.texttask);
            updateStrokeOut();
            silentUpdate = true;
            itemDone.setChecked(task.done);
            silentUpdate = false;
        }

        private void updateStrokeOut(){
            if (task.done){
                itemTask.setPaintFlags(itemTask.getPaintFlags()| Paint.STRIKE_THRU_TEXT_FLAG);
            }
            else{
                itemTask.setPaintFlags(itemTask.getPaintFlags() & ~Paint.STRIKE_THRU_TEXT_FLAG);
            }

        }
    }
}
