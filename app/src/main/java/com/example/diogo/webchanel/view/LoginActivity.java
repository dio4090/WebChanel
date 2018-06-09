package com.example.diogo.webchanel.view;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.diogo.webchanel.R;
import com.android.volley.Response;
import com.example.diogo.webchanel.model.User;
import com.example.diogo.webchanel.util.LoginRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    //GLOBAL VARIABLES
    EditText edtLogin, edtPassword;
    Button btnLogin;
    TextView txtNewUser;
    TextView txtNewEnterprise;
    User user;

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

    private void login() {
        user = new User();
        String email = "", password = "";

        //Pegando os valores do login
        email = edtLogin.getText().toString();
        password = edtPassword.getText().toString();

        //Frame de login
        loginProess();

        // TODO: Implement your own authentication logic here.
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String strResponse) {
                try {
                    JSONArray jsonResponse = new JSONArray(strResponse);

                    //Verifica se o login e valido
                    //Verifica se o Json esta vazio
                    if(jsonResponse.length() > 0) {
                        for (int i = 0; i < jsonResponse.length(); i++) {
                            JSONObject jsonobject = jsonResponse.getJSONObject(i);
                            user.setId(jsonobject.getInt("id"));
                            user.setEmail(jsonobject.getString("email"));
                            user.setPassword(jsonobject.getString("password"));
                        }
                        startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                    } else {
                        startActivity(new Intent(LoginActivity.this, LoginActivity.class));
                        userNotExists();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        };

        LoginRequest loginRequest = new LoginRequest(email, password, responseListener);
        RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
        queue.add(loginRequest);
    }

    private void loginProess() {
        //Dialog
        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this,R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();

        //Wait 3 seconds
        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        onLoginSuccess();
                        progressDialog.dismiss();
                    }
                }, 2000);
    }

    private void userNotExists(){
        Toast.makeText(getBaseContext(), "Login ou senha inválidos", Toast.LENGTH_LONG).show();
        btnLogin.setEnabled(true);
    }

    public void onLoginSuccess() {
        btnLogin.setEnabled(true);
        finish();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                login();
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
