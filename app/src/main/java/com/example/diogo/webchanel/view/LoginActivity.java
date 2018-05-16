package com.example.diogo.webchanel.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.diogo.webchanel.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    //GLOBAL VARIABLES
    EditText edtLogin, edtPassword;
    Button btnLogin;
    TextView txtNewUser;
    TextView txtNewEnterprise;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initializeVariables();
    }

    private void initializeVariables() {
        edtLogin = (EditText) findViewById(R.id.edt_login);
        edtPassword = (EditText) findViewById(R.id.edt_password);
        btnLogin = (Button) findViewById(R.id.btn_login);
        txtNewUser = (TextView) findViewById(R.id.txt_new_user);
        txtNewEnterprise = (TextView) findViewById(R.id.txt_new_enterprise);

        btnLogin.setOnClickListener(this);
        txtNewUser.setOnClickListener(this);
        txtNewEnterprise.setOnClickListener(this);
    }

    private void validateLogin(String login, String pass) {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                break;

            case R.id.txt_new_user:
                startActivity(new Intent(LoginActivity.this, NewUserActivity.class));
                break;

            case R.id.txt_new_enterprise:
                startActivity(new Intent(LoginActivity.this, NewEnterpriseActivity.class));
                break;
        }
    }
}
