package com.example.diogo.webchanel.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.diogo.webchanel.MyApplication;
import com.example.diogo.webchanel.R;
import com.example.diogo.webchanel.dao.UserDAO;
import com.example.diogo.webchanel.model.User;
import com.example.diogo.webchanel.util.AndroidUtil;

import java.util.ArrayList;

public class NewUserActivity extends AppCompatActivity implements View.OnClickListener {

    //GLOBAL VARIABLES
    User user;
    ArrayList<User> usersList;
    UserDAO userDAO;
    AndroidUtil util;
    MyApplication app;

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
        usersList = new ArrayList<User>();
        userDAO = new UserDAO(this);
        app = (MyApplication) getApplication();

        edtNewUserName = (EditText) findViewById(R.id.edt_new_user_name);
        edtNewUserAddess = (EditText) findViewById(R.id.edt_new_user_address);
        edtNewUserPhone = (EditText) findViewById(R.id.edt_new_user_phone);
        edtNewUserEmail = (EditText) findViewById(R.id.edt_new_user_email);
        edtNewUserPassword = (EditText) findViewById(R.id.edt_new_user_password);
        btnAddUser = (Button) findViewById(R.id.btn_add_user);

        btnAddUser.setOnClickListener(this);
    }

    private void createUser() {
        user = new User();

        user.setName(edtNewUserName.getText().toString());
        user.setAddress(edtNewUserAddess.getText().toString());
        user.setPhone(edtNewUserPhone.getText().toString());
        user.setEmail(edtNewUserEmail.getText().toString());
        user.setPassword(edtNewUserPassword.getText().toString());

        userDAO.insertUser(user, this);
        app.setUser(user);
    }

    private void showUsers(){
        usersList = userDAO.findAllUsers();

        for(User u : usersList) {
            System.out.println("USUARIO CADASTRADO:");
            System.out.println("ID:"+u.getId());
            System.out.println("NOME:"+u.getName());
            System.out.println("EMAIL:"+u.getEmail());
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_add_user:
                createUser();
                showUsers();

                //Verifica se nao e uma nova empresa
                if(!app.isNewEnterprise()) {
                    startActivity(new Intent(NewUserActivity.this, LoginActivity.class));
                } else {
                    startActivity(new Intent(NewUserActivity.this, NewEnterpriseActivity.class));
                }

        }
    }
}
