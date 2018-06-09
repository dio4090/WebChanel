package com.example.diogo.webchanel.view;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.diogo.webchanel.R;
import com.example.diogo.webchanel.model.EnterpriseTest;
import com.example.diogo.webchanel.util.EnterpriseAdapter;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


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
        ListView lista = (ListView) findViewById(R.id.lvEnterprises);
        ArrayList<EnterpriseTest> enterprises = adicionarEnterprises();
        ArrayAdapter adapter = new EnterpriseAdapter(this, enterprises);
        lista.setAdapter(adapter);
    }

    //Método para popular o listView
    private ArrayList<EnterpriseTest> adicionarEnterprises() {
        ArrayList<EnterpriseTest> enterprises = new ArrayList<EnterpriseTest>();
        EnterpriseTest e = new EnterpriseTest("Barbearia 01",
                "Rua Pacatuba S/N", R.drawable.barbearia1);
        enterprises.add(e);
        e = new EnterpriseTest("Barbearia 02",
                "Rua Sergipe S/N", R.drawable.barbearia2);
        enterprises.add(e);
        e = new EnterpriseTest("Barbearia 03",
                "Av. Otoniel Dórea", R.drawable.barbearia3);
        enterprises.add(e);
        e = new EnterpriseTest("Barbearia 04",
                "R. Franklin Campos, 1675", R.drawable.barbearia4);
        e = new EnterpriseTest("Barbearia 02",
                "Rua Sergipe S/N", R.drawable.barbearia2);
        enterprises.add(e);
        e = new EnterpriseTest("Barbearia 03",
                "Av. Otoniel Dórea", R.drawable.barbearia3);
        enterprises.add(e);
        e = new EnterpriseTest("Barbearia 04",
                "R. Franklin Campos, 1675", R.drawable.barbearia4);
        e = new EnterpriseTest("Barbearia 02",
                "Rua Sergipe S/N", R.drawable.barbearia2);
        enterprises.add(e);
        e = new EnterpriseTest("Barbearia 03",
                "Av. Otoniel Dórea", R.drawable.barbearia3);
        enterprises.add(e);
        e = new EnterpriseTest("Barbearia 04",
                "R. Franklin Campos, 1675", R.drawable.barbearia4);
        e = new EnterpriseTest("Barbearia 02",
                "Rua Sergipe S/N", R.drawable.barbearia2);
        enterprises.add(e);
        e = new EnterpriseTest("Barbearia 03",
                "Av. Otoniel Dórea", R.drawable.barbearia3);
        enterprises.add(e);
        e = new EnterpriseTest("Barbearia 04",
                "R. Franklin Campos, 1675", R.drawable.barbearia4);
        enterprises.add(e);

        return enterprises;
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
