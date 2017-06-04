package com.project.hackathon.camara.app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.project.hackathon.camara.app.adapter.SuspectedCustomAdapter;
import com.project.hackathon.camara.app.model.Suspected;

import java.util.ArrayList;

/**
 * Created by matheuscatossi on 6/3/17.
 */

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

        RecyclerView recyclerView;
        recyclerView = (RecyclerView) findViewById(R.id.listSuspected);

        ArrayList<Suspected> suspecteds;
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(mLayoutManager);
        suspecteds = new ArrayList<>();

        suspecteds.add(new Suspected(1,R.drawable.computer,90,R.drawable.up,"Computer","Computer"));
        suspecteds.add(new Suspected(2,R.drawable.mobile,87,R.drawable.up,"Mobile","Mobile"));
        suspecteds.add(new Suspected(3,R.drawable.computer,80,R.drawable.up,"Computer","Computer"));
        suspecteds.add(new Suspected(4,R.drawable.mobile,72,R.drawable.up,"Mobile","Mobile"));
        suspecteds.add(new Suspected(5,R.drawable.pencil,65,R.drawable.up,"Pencil","Pencil"));
        suspecteds.add(new Suspected(6,R.drawable.computer,60,R.drawable.up,"Computer","Computer"));
        suspecteds.add(new Suspected(7,R.drawable.mobile,55,R.drawable.up,"Mobile","Mobile"));
        suspecteds.add(new Suspected(8,R.drawable.pencil,35,R.drawable.down,"Pencil","Pencil"));
        suspecteds.add(new Suspected(9,R.drawable.computer,22,R.drawable.down,"Computer","Computer"));

        SuspectedCustomAdapter rankingCustomAdapter;
        rankingCustomAdapter = new SuspectedCustomAdapter(this, suspecteds);

        recyclerView.setAdapter(rankingCustomAdapter);
    }
}
