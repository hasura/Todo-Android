package com.example.jaison.hasura_todo_android.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jaison.hasura_todo_android.R;
import com.example.jaison.hasura_todo_android.hasura.Hasura;
import com.example.jaison.hasura_todo_android.models.AuthRequest;
import com.example.jaison.hasura_todo_android.models.AuthResponse;
import com.example.jaison.hasura_todo_android.models.MessageResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.BufferedSink;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthenticationActivity extends BaseActivity implements View.OnClickListener {

    EditText username, password;
    Button signInButton, registerButton;

    public static void startActivity(Activity startingActivity) {
        startingActivity.startActivity(new Intent(startingActivity, AuthenticationActivity.class));
        startingActivity.finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Hasura.initialise(this);
        setContentView(R.layout.activity_authentication);

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        signInButton = (Button) findViewById(R.id.signInButton);
        registerButton = (Button) findViewById(R.id.registerButton);

        signInButton.setOnClickListener(this);
        registerButton.setOnClickListener(this);

        if (Hasura.getUserSessionId() != null) {
            ToDoActivity.startActivity(this);
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.signInButton:
                handleLogin();
                break;
            case R.id.registerButton:
                handleRegistration();
                break;
        }
    }

    private void handleLogin() {
        showProgressIndicator();
        Hasura.auth.login(new AuthRequest(username.getText().toString(), password.getText().toString())).enqueue(new Callback<AuthResponse>() {
            @Override
            public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {
                hideProgressIndicator();
                if (response.isSuccessful()) {
                    Hasura.setSession(response.body());
                    ToDoActivity.startActivity(AuthenticationActivity.this);
                } else {
                    try {
                        MessageResponse messageResponse = new Gson().fromJson(response.errorBody().string(), MessageResponse.class);
                        Toast.makeText(AuthenticationActivity.this, messageResponse.getMessage(), Toast.LENGTH_LONG).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<AuthResponse> call, Throwable t) {
                hideProgressIndicator();
                Toast.makeText(AuthenticationActivity.this, "Something went wrong, please ensure that you have a working internet connection", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void handleRegistration() {
        showProgressIndicator();
        Hasura.auth.register(new AuthRequest(username.getText().toString(), password.getText().toString())).enqueue(new Callback<AuthResponse>() {
            @Override
            public void onResponse(Call<AuthResponse> call, Response<AuthResponse> response) {
                hideProgressIndicator();
                if (response.isSuccessful()) {
                    Hasura.setSession(response.body());
                    ToDoActivity.startActivity(AuthenticationActivity.this);
                } else {
                    try {
                        MessageResponse messageResponse = new Gson().fromJson(response.errorBody().string(), MessageResponse.class);
                        Toast.makeText(AuthenticationActivity.this, messageResponse.getMessage(), Toast.LENGTH_LONG).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<AuthResponse> call, Throwable t) {
                hideProgressIndicator();
                Toast.makeText(AuthenticationActivity.this, "Something went wrong, please ensure that you have a working internet connection", Toast.LENGTH_LONG).show();
            }
        });
    }
}
