
package com.example.diogo.webchanel.view.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.diogo.webchanel.MyApplication;
import com.example.diogo.webchanel.R;
import com.example.diogo.webchanel.dao.EnterpriseDAO;
import com.example.diogo.webchanel.model.Enterprise;
import com.example.diogo.webchanel.view.MapActivity;
import com.example.diogo.webchanel.view.NewEnterpriseActivity;

import java.io.Serializable;
import java.util.ArrayList;

public class FragmentDetails extends Fragment implements Serializable {

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
    TextView txtMap;


    public FragmentDetails() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        inf = inflater.inflate(R.layout.fragment_details, container, false);
        initializeVariables(getActivity());
        showEnterprises();
        printEnterprises();

        //Ação para chamar MapActivity
        txtMap = (TextView) inf.findViewById(R.id.txt_view_map);
        String text = "Ver no Mapa...";
        SpannableString mSpannableString = new SpannableString(text);
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View widget) {

                Intent intent = new Intent(getActivity(), MapActivity.class);
                intent.putExtra("address", txtAddress.getText()
                        + ", "+txtDistrict.getText()+ ", "+ txtUf.getText());
                startActivity(intent);

            }
        };

        mSpannableString.setSpan(clickableSpan, 0, text.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        txtMap.setText(mSpannableString);
        txtMap.setMovementMethod(LinkMovementMethod.getInstance());

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
        // Just assume that in the real app we would really ask it!
        MyApplication app = (MyApplication) getActivity().getApplication();

        txtEnterpriseName.setText(app.getEnterprise().getName());
        txtAddress.setText("Endereço: "+ app.getEnterprise().getAddress());
        txtPhone.setText("Telefone: "+app.getEnterprise().getPhone());
        txtCep.setText("CEP: "+app.getEnterprise().getCep());
        txtDistrict.setText("Bairro: "+app.getEnterprise().getDistrict());
        txtCity.setText("Cidade: "+app.getEnterprise().getCity());
        txtUf.setText("UF: "+app.getEnterprise().getUf());
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
