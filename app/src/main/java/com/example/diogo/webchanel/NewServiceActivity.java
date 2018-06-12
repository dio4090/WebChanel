package com.example.diogo.webchanel;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.diogo.webchanel.dao.EnterpriseDAO;
import com.example.diogo.webchanel.dao.ServiceDAO;
import com.example.diogo.webchanel.model.Enterprise;
import com.example.diogo.webchanel.model.Service;
import com.example.diogo.webchanel.view.NewEnterpriseActivity;

import java.util.ArrayList;

public class NewServiceActivity extends AppCompatActivity  implements View.OnClickListener  {

    //GLOBAL VARIABLES
    Enterprise enterprise;
    EnterpriseDAO enterpriseDAO;
    Service service;
    ServiceDAO serviceDAO;
    ArrayList<Service> servicesList;
    MyApplication app;

    TextView txtEnterpriseName;
    EditText edtNewServiceDescription;
    EditText edtNewServiceValue;

    Button btnAddService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_service);
        initializeVariables();
    }


    private void createService() {
        //Add...
    }

    private void  showServices() {
        //Add...
    }

    private void initializeVariables() {
        enterprise = new Enterprise();
        enterpriseDAO = new EnterpriseDAO(this);

        service = new Service();
        serviceDAO = new ServiceDAO(this);

        //Adicionando a empresa na sessao
        //app.setEnterprise(enterpriseDAO.findEnterpriseByUserId(app.getUser().getId()));

        txtEnterpriseName = (TextView) findViewById(R.id.txt_enterprise_name);
        edtNewServiceDescription = (EditText) findViewById(R.id.edt_new_service_description);
        edtNewServiceValue = (EditText) findViewById(R.id.edt_new_service_value);

        //Adicionando o nome da empresa
        //txtEnterpriseName.setText(app.getEnterprise().getName());
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_add_service:
                createService();
                showServices();

                startActivity(new Intent(NewServiceActivity.this, NewEnterpriseActivity.class));
        }
    }
}
