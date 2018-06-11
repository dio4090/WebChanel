package com.example.diogo.webchanel.view.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.diogo.webchanel.R;

import java.util.ArrayList;

public class FragmentServices extends Fragment {

    public FragmentServices(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_services, container, false);

        //Rerefênciamento ao listView e criando o arrayAdapter
        ListView lista = (ListView) view.findViewById(R.id.lvServices);

        ArrayList<String> enterprises = adicionarEnterprises();

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                enterprises);

        lista.setAdapter(arrayAdapter);

        return view;

    }

    //Método para popular o listView
    private ArrayList<String> adicionarEnterprises() {

        ArrayList<String> dados =  new ArrayList<String>();
        dados.add("SERVIÇO TESTE");
        dados.add("SERVIÇO TESTE");
        dados.add("SERVIÇO TESTE");
        dados.add("SERVIÇO TESTE");
        dados.add("SERVIÇO TESTE");
        dados.add("SERVIÇO TESTE");
        dados.add("SERVIÇO TESTE");
        dados.add("SERVIÇO TESTE");
        dados.add("SERVIÇO TESTE");
        dados.add("SERVIÇO TESTE");
        dados.add("SERVIÇO TESTE");

        return dados;

    }
}
