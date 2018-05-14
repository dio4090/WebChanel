package com.example.diogo.webchanel.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.diogo.webchanel.R;
import com.example.diogo.webchanel.dao.EnterpriseDAO;
import com.example.diogo.webchanel.model.Enterprise;

public class NewEnterpriseActivity extends AppCompatActivity implements View.OnClickListener {

    //GLOBAL VARIABLES
    Enterprise enterprise;
    EnterpriseDAO enterpriseDAO;

    EditText edtNewEnterpriseName;
    EditText edtNewEnterpriseSocialName;
    EditText edtNewEnterpriseAddress;
    EditText edtNewEnterprisePhone;
    EditText edtNewEnterpriseCep;
    EditText edtNewEnterpriseDistrict;
    Spinner edtNewEnterpriseCity;
    EditText edtNewEnterpriseUf;
    EditText edtNewEnterpriseCnpj;
    RadioButton rbtnEdtNewEnterpriseSalon;
    RadioButton rbtnEdtNewEnterpriseBarber;
    Button btnAddNewEnterprise;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_enterprise);
        initializeVariables();
    }

    private void initializeVariables() {
        enterprise = new Enterprise();
        enterpriseDAO = new EnterpriseDAO(this);

        edtNewEnterpriseName = (EditText) findViewById(R.id.edt_new_enterprise_name);
        edtNewEnterpriseSocialName = (EditText) findViewById(R.id.edt_new_enterprise_social_name);
        edtNewEnterpriseAddress = (EditText) findViewById(R.id.edt_new_enterprise_address);
        edtNewEnterprisePhone = (EditText) findViewById(R.id.edt_new_enterprise_phone);
        edtNewEnterpriseCep = (EditText) findViewById(R.id.edt_new_enterprise_cep);
        edtNewEnterpriseDistrict = (EditText) findViewById(R.id.edt_new_enterprise_district);
        edtNewEnterpriseCity = (Spinner) findViewById(R.id.spi_new_enterprise_city);
        edtNewEnterpriseUf = (EditText) findViewById(R.id.edt_new_enterprise_uf);
        edtNewEnterpriseCnpj = (EditText) findViewById(R.id.edt_new_enterprise_cnpj);
        rbtnEdtNewEnterpriseSalon = (RadioButton) findViewById(R.id.rbt_new_enterprise_salon);
        rbtnEdtNewEnterpriseBarber = (RadioButton) findViewById(R.id.rbt_new_enterprise_barber);
        btnAddNewEnterprise = (Button) findViewById(R.id.btn_add_enterprise);

        btnAddNewEnterprise.setOnClickListener(this);
    }

    private void createEnterprise() {
        try {
            enterprise = new Enterprise();

            enterprise.setName(edtNewEnterpriseName.getText().toString());
            enterprise.setSocialName(edtNewEnterpriseSocialName.getText().toString());
            enterprise.setAddress(edtNewEnterpriseAddress.getText().toString());
            enterprise.setCep(Integer.parseInt(edtNewEnterpriseCep.getText().toString()));
            enterprise.setDistrict(edtNewEnterpriseDistrict.getText().toString());
            //Spinner edtNewEnterpriseCity;
            enterprise.setUf(edtNewEnterpriseUf.getText().toString());
            enterprise.setCnpj(edtNewEnterpriseCnpj.getText().toString());
            //RadioButton rbtnEdtNewEnterpriseSalon;
            //RadioButton rbtnEdtNewEnterpriseBarber;

            enterpriseDAO.insertEnterprise(enterprise);
        } catch (Exception e) {
            System.out.println("Error on createEnterprise(): " + e.getMessage());
            Toast.makeText(this, "Erro ao realizar o cadastro", Toast.LENGTH_LONG).show();
        }
    }

    private void showEnterprises(){
        enterprise = new Enterprise();
        enterprise = enterpriseDAO.findEnterpriseById(1);
        System.out.println("ENPRESA CADASTRADA:");
        System.out.println("ID:"+enterprise.getId());
        System.out.println("NOME:"+enterprise.getName());
        System.out.println("CNPJ:"+enterprise.getCnpj());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_add_enterprise:
                createEnterprise();
                showEnterprises();
                //startActivity(new Intent(NewEnterpriseActivity.this, LoginActivity.class));

        }
    }
}
