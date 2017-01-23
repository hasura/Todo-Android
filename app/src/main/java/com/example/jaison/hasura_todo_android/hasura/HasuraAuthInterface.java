package com.example.jaison.hasura_todo_android.hasura;

import com.example.jaison.hasura_todo_android.models.AuthRequest;
import com.example.jaison.hasura_todo_android.models.AuthResponse;
import com.example.jaison.hasura_todo_android.models.MessageResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;


/**
 * Created by jaison on 23/01/17.
 */

public interface HasuraAuthInterface {

    @POST(Endpoint.LOGIN)
    Call<AuthResponse> login(@Body AuthRequest request);

    @POST(Endpoint.REGISTER)
    Call<AuthResponse> register(@Body AuthRequest request);

    @GET(Endpoint.LOGOUT)
    Call<MessageResponse> logout();

}
