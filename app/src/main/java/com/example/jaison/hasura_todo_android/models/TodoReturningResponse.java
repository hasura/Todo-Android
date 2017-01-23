package com.example.jaison.hasura_todo_android.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by jaison on 23/01/17.
 */

public class TodoReturningResponse {

    @SerializedName("affected_rows")
    Integer affectedRows;

    @SerializedName("returning")
    List<TodoRecord> todoRecords;

    public Integer getAffectedRows() {
        return affectedRows;
    }

    public List<TodoRecord> getTodoRecords() {
        return todoRecords;
    }
}
