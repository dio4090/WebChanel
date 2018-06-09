
package com.example.diogo.webchanel.view.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.diogo.webchanel.R;
import com.example.diogo.webchanel.dao.EnterpriseDAO;
import com.example.diogo.webchanel.model.Enterprise;
import com.example.diogo.webchanel.view.NewEnterpriseActivity;

import java.util.ArrayList;

public class FragmentDetails extends Fragment {

    //GLOBAL VARIABLES
    Enterprise ent;
    EnterpriseDAO entDAO;
    ArrayList<Enterprise> enterpriseList;
    NewEnterpriseActivity newEnterprise;
    View inf;

    TextView txtEnterpriseName;
    TextView txtAddress;
    TextView txtPhone;
    TextView txtCep;
    TextView txtDistrict;
    TextView txtCity;
    TextView txtUf;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        inf = inflater.inflate(R.layout.fragment_details, container, false);
        initializeVariables(getActivity());
        showEnterprises();
        printEnterprises();
        return inf;
    }

    public void initializeVariables(Activity a) {
        entDAO = new EnterpriseDAO(a);
        ent = new Enterprise();

        txtEnterpriseName = (TextView) inf.findViewById(R.id.txt_enterprise_name);
        txtAddress = (TextView) inf.findViewById((R.id.txt_address));
        txtPhone = (TextView) inf.findViewById(R.id.txt_phone);
        txtCep = (TextView) inf.findViewById(R.id.txt_cep);
        txtDistrict = (TextView) inf.findViewById(R.id.txt_district);
        txtCity = (TextView) inf.findViewById(R.id.txt_city);
        txtUf = (TextView) inf.findViewById(R.id.txt_uf);
    }

    private void showEnterprises(){
        enterpriseList = entDAO.findAllEnterprises();

        for(Enterprise e : enterpriseList) {
            txtEnterpriseName.setText("Nome da Empresa: " +e.getName());
            txtAddress.setText("Endereço: "+e.getAddress());
            txtPhone.setText("Telefone: "+e.getPhone());
            txtCep.setText("CEP: "+e.getCep());
            txtDistrict.setText("Bairro: "+e.getDistrict());
            txtCity.setText("Cidade: "+e.getCity());
            txtUf.setText("UF: "+e.getUf());
        }
    }

    private void printEnterprises() {
        ent = entDAO.findEnterpriseById(1);

        System.out.println("ID: " +ent.getId());
        System.out.println("Nome da Empresa: " +ent.getName());
        System.out.println("Endereço: "+ent.getAddress());
        System.out.println("Telefone: "+ent.getPhone());
        System.out.println("CEP: "+ent.getCep());
        System.out.println("Bairro: "+ent.getDistrict());
        System.out.println("Cidade: "+ent.getCity());
        System.out.println("UF: "+ent.getUf());
    }

}
