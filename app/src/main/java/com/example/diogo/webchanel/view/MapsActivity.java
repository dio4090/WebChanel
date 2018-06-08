package com.example.diogo.webchanel.view;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.diogo.webchanel.R;
import com.example.diogo.webchanel.view.fragments.MapsFragments;

public class MapsActivity extends AppCompatActivity {

    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Recuperando o FragmentManager
        fragmentManager = getSupportFragmentManager();

        //Para iniciar a transação do banco de dados
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        //Adicionando o fragment ao activity
        transaction.add(R.id.container2, new MapsFragments(),"MapsFragments");

        //Confirmando transação
        transaction.commitAllowingStateLoss();
    }

}