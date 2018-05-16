package com.example.diogo.webchanel.view;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.diogo.webchanel.R;
import com.example.diogo.webchanel.dao.UserDAO;
import com.example.diogo.webchanel.model.User;
import com.example.diogo.webchanel.util.AndroidUtil;

public class NewUserActivity extends AppCompatActivity implements View.OnClickListener {

    //GLOBAL VARIABLES
    User user;
    UserDAO userDAO;
    AndroidUtil util;

    EditText edtNewUserName;
    EditText edtNewUserAddess;
    EditText edtNewUserPhone;
    EditText edtNewUserEmail;
    EditText edtNewUserPassword;
    Button btnAddUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);

        initializeVariables();
    }

    private void initializeVariables() {
        user = new User();
        userDAO = new UserDAO(this);

        edtNewUserName = (EditText) findViewById(R.id.edt_new_user_name);
        edtNewUserAddess = (EditText) findViewById(R.id.edt_new_user_address);
        edtNewUserPhone = (EditText) findViewById(R.id.edt_new_user_phone);
        edtNewUserEmail = (EditText) findViewById(R.id.edt_new_user_email);
        edtNewUserPassword = (EditText) findViewById(R.id.edt_new_user_password);
        btnAddUser = (Button) findViewById(R.id.btn_add_user);

        btnAddUser.setOnClickListener(this);
    }

    private void createUser() {
        try {
            user = new User();

            user.setName(edtNewUserName.getText().toString());
            user.setAddress(edtNewUserAddess.getText().toString());
            user.setPhone(edtNewUserPhone.getText().toString());
            user.setEmail(edtNewUserEmail.getText().toString());
            user.setPassword(edtNewUserPassword.getText().toString());

            userDAO.insertUser(user);
        } catch (Exception e) {
            System.out.println("Error on createUser(): " + e.getMessage());
            Toast.makeText(this, "Erro ao realizar o cadastro", Toast.LENGTH_LONG).show();
        }
    }

    private void showUsers(){
        user = new User();
        user = userDAO.findUserById(1);
        System.out.println("ENPRESA CADASTRADA:");
        System.out.println("ID:"+user.getId());
        System.out.println("NOME:"+user.getName());
        System.out.println("EMAIL:"+user.getEmail());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_add_user:
                createUser();
                showUsers();
                startActivity(new Intent(NewUserActivity.this, LoginActivity.class));

        }
    }
}
