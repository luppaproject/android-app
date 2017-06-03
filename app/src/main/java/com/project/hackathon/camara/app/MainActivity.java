package com.project.hackathon.camara.app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.project.hackathon.camara.app.adapter.SuspectedCustomAdapter;
import com.project.hackathon.camara.app.model.Suspected;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

      }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();

        ListView listViewSuspected;
        listViewSuspected = (ListView) findViewById(R.id.listSuspected);

        ArrayList<Suspected> suspecteds = new ArrayList<Suspected>();
        suspecteds.add(new Suspected(1,1,1,1,"XX","XX"));
        suspecteds.add(new Suspected(1,1,1,1,"XX","XX"));
        suspecteds.add(new Suspected(1,1,1,1,"XX","XX"));

        SuspectedCustomAdapter rankingCustomAdapter;
        rankingCustomAdapter = new SuspectedCustomAdapter(suspecteds, this);

        listViewSuspected.setAdapter(rankingCustomAdapter);
    }
}
