package com.example.jaison.hasura_todo_android.hasura;

import com.example.jaison.hasura_todo_android.models.DeleteTodoQuery;
import com.example.jaison.hasura_todo_android.models.InsertTodoQuery;
import com.example.jaison.hasura_todo_android.models.SelectTodoQuery;
import com.example.jaison.hasura_todo_android.models.TodoRecord;
import com.example.jaison.hasura_todo_android.models.TodoReturningResponse;
import com.example.jaison.hasura_todo_android.models.UpdateTodoQuery;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by jaison on 23/01/17.
 */

public interface HasuraDBInterface {

    @POST(Endpoint.QUERY)
    Call<List<TodoRecord>> getTodos(@Body SelectTodoQuery query);

    @POST(Endpoint.QUERY)
    Call<TodoReturningResponse> addATodo(@Body InsertTodoQuery query);

    @POST(Endpoint.QUERY)
    Call<TodoReturningResponse> deleteATodo(@Body DeleteTodoQuery query);

    @POST(Endpoint.QUERY)
    Call<TodoReturningResponse> updateATodo(@Body UpdateTodoQuery query);

}
