package com.example.diogo.webchanel.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.diogo.webchanel.MyApplication;
import com.example.diogo.webchanel.R;
import com.example.diogo.webchanel.dao.EnterpriseDAO;
import com.example.diogo.webchanel.model.Enterprise;
import com.example.diogo.webchanel.util.AndroidUtil;
import com.example.diogo.webchanel.util.EnterpriseAdapter;

import java.io.Serializable;
import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, Serializable {

    //GLOBAL VARIABLES
    AndroidUtil util;
    Enterprise enterprise;
    ArrayList<Enterprise> enterpriseList;
    EnterpriseDAO enterpriseDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //Rerefênciamento ao listView e criando o arrayAdapter
        final ListView lista = (ListView) findViewById(R.id.lvEnterprises);
        ArrayList<Enterprise> enterprises = adicionarEnterprises();
        final ArrayAdapter adapter = new EnterpriseAdapter(this, enterprises);
        lista.setAdapter(adapter);

        //Adicionando evento ao click da lista
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                // Classe global
                MyApplication app = (MyApplication) getApplication();
                //Passando o enterprise que foi selecionado
                app.setEnterprise ((Enterprise) lista.getAdapter().getItem(position));
                startActivity(new Intent(HomeActivity.this, DetailsEnterpriseActivity.class));
            }
        });
    }

    //Método para popular o listView
    private ArrayList<Enterprise> adicionarEnterprises() {
        enterpriseList = new ArrayList<>();
        enterpriseDAO = new EnterpriseDAO(this);

        enterpriseList = enterpriseDAO.findAllEnterprises();
        util = new AndroidUtil();

        //PEGANDO UMA IMAGEM RANDOMICAMENTE
        for(Enterprise e : enterpriseList) {
            int imageNum = util.getRandomInterger(0, 5);
            switch (imageNum) {
                case 1:
                    e.setImage(R.drawable.barbearia1);
                    break;
                case 2:
                    e.setImage(R.drawable.barbearia2);
                    break;
                case 3:
                    e.setImage(R.drawable.barbearia3);
                    break;
                case 4:
                    e.setImage(R.drawable.barbearia4);
                    break;
                default:
                    e.setImage(R.drawable.barbearia1);
                    break;
            }
        }
        return enterpriseList;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.provider) {
            // Handle the camera action

            startActivity(new Intent(HomeActivity.this, MapsActivity.class));

        } else if (id == R.id.detalhe_empresa) {

            startActivity(new Intent(HomeActivity.this, DetailsEnterpriseActivity.class));

        } else if (id == R.id.nav_video) {

            startActivity(new Intent(HomeActivity.this, VideoActivity.class));

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
