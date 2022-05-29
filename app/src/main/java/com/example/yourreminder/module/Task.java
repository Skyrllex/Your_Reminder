package com.example.yourreminder.module;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Objects;

@Entity
public class Task implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    public int uid; //id

    @ColumnInfo(name = "texttask" )
    public String texttask; //TaskText

    @ColumnInfo(name = "textsubtask" )
    public String textsubtask; //SubTaskText

    @ColumnInfo(name = "timectreate" )
    public long timectreate; //date on create

    @ColumnInfo(name = "calendar" )
    public long calendar; //date and time

    @ColumnInfo(name = "priority" )
    public int priority; //priority

    @ColumnInfo(name = "done" )
    public boolean done; //chek do or not


    public Task()
    {

    }

    @Override
    public boolean equals (Object o){
        if (this == o) return true;
        if (o == null || getClass()!= o.getClass()) return false;

        Task task = (Task) o;

        if (uid != task.uid) return false;
        if (timectreate != task.timectreate) return false;
        if (calendar != task.calendar) return false;
        if (priority != task.priority) return false;
        if (!textsubtask.equals(task.textsubtask)) return false;
        return Objects.equals(texttask, task.texttask);

    }

    @Override
    public int hashCode() {
        int result = uid;

        result = (31 * result) + ((texttask != null) ? texttask.hashCode() : 0);
        result = (31 * result) + ((textsubtask != null) ? textsubtask.hashCode() : 0);
        result = (31 * result) + (int) (timectreate ^ (timectreate>>>32));
        result = (31 * result) + (int) (calendar ^ (calendar>>>32));
        //result = (31 * result) + (priority ^ (priority>>>32));
        result = (31 * result) + (done ? 1 : 0);

        return result;
    }

    protected Task(Parcel in){
        uid = in.readInt();
        texttask = in.readString();
        textsubtask = in.readString();
        timectreate = in.readLong();
        calendar = in.readLong();
        priority = in.readInt();
        done = in.readByte() != 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flag) {

        dest.writeInt(uid);
        dest.writeString(texttask);
        dest.writeString(textsubtask);
        dest.writeLong(timectreate);
        dest.writeLong(calendar);
        dest.writeInt(priority);
        dest.writeByte((byte) (done ? 1 : 0));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator <Task> CREATOR = new Creator<Task>() {
        @Override
        public Task createFromParcel(Parcel in) {
            return new Task(in);
        }

        @Override
        public Task[] newArray(int size) {
            return new Task[size];
        }
    };

}
