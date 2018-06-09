package com.example.xero.cardtradeapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v4.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xero.cardtradeapp.BusinessLogic.CardBusinessLogic.ILogInService;
import com.example.xero.cardtradeapp.BusinessLogic.CardBusinessLogic.LogInService;

public class LoginActivity extends AppCompatActivity {

    private EditText etEmail;
    private EditText etPassword;
    private Button btEntrar;
    private TextView tvSignUp;
    int userId=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmail = findViewById(R.id.etUsername_login);
        etPassword = findViewById(R.id.etPassword_login);
        btEntrar = findViewById(R.id.btEnter_login);
        tvSignUp = findViewById(R.id.tvSignUp_login);

        // entrar
        btEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LogInTask logInTask = new LogInTask();
                logInTask.execute(etEmail.getText().toString(),etPassword.getText().toString());
            }
        });

        // crear cuenta
        tvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),SignupActivity.class);
                startActivity(intent);
            }
        });

    }
    public void responseToValidation(Boolean isValid){
        if(isValid){
            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent);
            finish();
        }else {
            Toast.makeText(LoginActivity.this, "Credenciales invalidas",
                    Toast.LENGTH_LONG).show();
        }
    }
    class LogInTask extends AsyncTask<String,Boolean,Boolean> {

        @Override
        protected Boolean doInBackground(String... strings) {

            ILogInService iLogInService = new LogInService();
            Pair<Boolean,Integer> isValid = iLogInService.UserValid(strings[0],strings[1]);
            userId = isValid.second;


            Context context = getApplicationContext();
            SharedPreferences sharedPref = context.getSharedPreferences(
                    "User", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putInt("userId", userId);
            editor.commit();


            return isValid.first;
        }

        @Override
        protected void onPostExecute(Boolean isValid) {
            super.onPostExecute(isValid);
            responseToValidation(isValid);
        }
    }



}
