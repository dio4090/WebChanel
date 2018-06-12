package com.example.diogo.webchanel.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.diogo.webchanel.MyApplication;
import com.example.diogo.webchanel.R;
import com.example.diogo.webchanel.dao.EnterpriseDAO;
import com.example.diogo.webchanel.model.Enterprise;

import java.util.ArrayList;
import java.util.List;

public class NewEnterpriseActivity extends AppCompatActivity implements View.OnClickListener {

    //GLOBAL VARIABLES
    Enterprise enterprise;
    ArrayList<Enterprise> enterpriseList;
    EnterpriseDAO enterpriseDAO;
    MyApplication app;

    EditText edtNewEnterpriseName;
    EditText edtNewEnterpriseSocialName;
    EditText edtNewEnterpriseAddress;
    EditText edtNewEnterprisePhone;
    EditText edtNewEnterpriseCep;
    EditText edtNewEnterpriseDistrict;
    EditText edtNewEnterpriseCity;
    Spinner edtNewEnterpriseUf;
    EditText edtNewEnterpriseCnpj;
    Spinner spiEdtNewEnterpriseType;
    Button btnAddNewEnterprise;
    Spinner sItems, sItemsType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_enterprise);
        initializeVariables();
    }

    private void initializeVariables() {
        enterprise = new Enterprise();
        enterpriseDAO = new EnterpriseDAO(this);
        app = (MyApplication) getApplication();

        // you need to have a list of data that you want the spinner to display
        List<String> spinnerArray =  new ArrayList<String>();
        spinnerArray.add("AC");
        spinnerArray.add("AL");
        spinnerArray.add("AP");
        spinnerArray.add("AM");
        spinnerArray.add("BA");
        spinnerArray.add("CE");
        spinnerArray.add("DF");
        spinnerArray.add("ES");
        spinnerArray.add("GO");
        spinnerArray.add("MA");
        spinnerArray.add("MT");
        spinnerArray.add("MS");
        spinnerArray.add("MG");
        spinnerArray.add("PA");
        spinnerArray.add("PB");
        spinnerArray.add("PR");
        spinnerArray.add("PE");
        spinnerArray.add("PI");
        spinnerArray.add("RJ");
        spinnerArray.add("RN");
        spinnerArray.add("RS");
        spinnerArray.add("RO");
        spinnerArray.add("RR");
        spinnerArray.add("SC");
        spinnerArray.add("SP");
        spinnerArray.add("SE");
        spinnerArray.add("TO");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinnerArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sItems = (Spinner) findViewById(R.id.spi_new_enterprise_uf);
        sItems.setAdapter(adapter);


        // you need to have a list of data that you want the spinner to display
        List<String> spinnerArrayType =  new ArrayList<String>();
        spinnerArrayType.add("Barbearia");
        spinnerArrayType.add("Barbearia e Shop");
        spinnerArrayType.add("Salão");

        ArrayAdapter<String> adapterType = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinnerArrayType);
        adapterType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sItemsType = (Spinner) findViewById(R.id.spi_new_enterprise_type);
        sItemsType.setAdapter(adapterType);

        edtNewEnterpriseName = (EditText) findViewById(R.id.edt_new_enterprise_name);
        edtNewEnterpriseSocialName = (EditText) findViewById(R.id.edt_new_enterprise_social_name);
        edtNewEnterpriseAddress = (EditText) findViewById(R.id.edt_new_enterprise_address);
        edtNewEnterprisePhone = (EditText) findViewById(R.id.edt_new_enterprise_phone);
        edtNewEnterpriseCep = (EditText) findViewById(R.id.edt_new_enterprise_cep);
        edtNewEnterpriseDistrict = (EditText) findViewById(R.id.edt_new_enterprise_district);
        edtNewEnterpriseCity = (EditText) findViewById(R.id.edt_new_enterprise_city);
        edtNewEnterpriseUf = (Spinner) findViewById(R.id.spi_new_enterprise_uf);
        edtNewEnterpriseCnpj = (EditText) findViewById(R.id.edt_new_enterprise_cnpj);
        spiEdtNewEnterpriseType = (Spinner) findViewById(R.id.spi_new_enterprise_type);
        btnAddNewEnterprise = (Button) findViewById(R.id.btn_add_enterprise);

        //Preenchendo valores ja adicionados
        edtNewEnterpriseAddress.setText(app.getUser().getAddress());
        edtNewEnterprisePhone.setText(app.getUser().getPhone());

        btnAddNewEnterprise.setOnClickListener(this);
    }

    private void createEnterprise() {
        try {
            enterprise = new Enterprise();

            enterprise.setName(edtNewEnterpriseName.getText().toString());
            enterprise.setSocialName(edtNewEnterpriseSocialName.getText().toString());
            enterprise.setAddress(edtNewEnterpriseAddress.getText().toString());
            enterprise.setPhone(edtNewEnterprisePhone.getText().toString());
            enterprise.setCep(Integer.parseInt(edtNewEnterpriseCep.getText().toString()));
            enterprise.setDistrict(edtNewEnterpriseDistrict.getText().toString());
            enterprise.setCity(edtNewEnterpriseCity.getText().toString());
            enterprise.setUf(sItems.getSelectedItem().toString());
            enterprise.setCnpj(edtNewEnterpriseCnpj.getText().toString());
            enterprise.setType(sItemsType.getSelectedItem().toString());
            enterprise.setUserId(app.getUser().getId());
            enterpriseDAO.insertEnterprise(enterprise);
        } catch (Exception e) {
            System.out.println("Error on createEnterprise(): " + e.getMessage());
            Toast.makeText(this, "Erro ao realizar o cadastro", Toast.LENGTH_LONG).show();
        }
    }

    private void showEnterprises(){
        enterprise = new Enterprise();
        enterpriseList = enterpriseDAO.findAllEnterprises();

        for(Enterprise e : enterpriseList) {
            System.out.println("ENPRESA CADASTRADA:");
            System.out.println("ID:"+e.getId());
            System.out.println("NOME:"+e.getName());
            System.out.println("CNPJ:"+e.getCnpj());
            System.out.println("USER_ID:"+e.getUserId());
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_add_enterprise:
                createEnterprise();
                showEnterprises();

                startActivity(new Intent(NewEnterpriseActivity.this, LoginActivity.class));

        }
    }
}
