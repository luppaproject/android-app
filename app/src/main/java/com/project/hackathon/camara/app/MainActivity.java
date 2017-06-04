package com.project.hackathon.camara.app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.project.hackathon.camara.app.adapter.SuspectedCustomAdapter;
import com.project.hackathon.camara.app.model.Suspected;
import com.project.hackathon.camara.app.webservice.APIClient;
import com.project.hackathon.camara.app.webservice.APIInterface;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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


    RecyclerView recyclerView;
    ArrayList<Suspected> suspecteds;
    APIInterface apiService;
    Call<List<Suspected>> callSuspected;
    static final ScheduledThreadPoolExecutor EXECUTOR = (ScheduledThreadPoolExecutor) Executors.newScheduledThreadPool(2);
    static ScheduledFuture<?> sSuspected;

    @Override
    protected void onResume() {
        super.onResume();

        recyclerView = (RecyclerView) findViewById(R.id.listSuspected);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(mLayoutManager);
        suspecteds = new ArrayList<>();

        apiService = APIClient.getService().create(APIInterface.class);
        callSuspected = apiService.getAllSuspected();
        suspecteds = new ArrayList<>();

        sSuspected = EXECUTOR.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                callSuspected.enqueue(new Callback<List<Suspected>>() {
                    @Override
                    public void onResponse(Call<List<Suspected>> call, Response<List<Suspected>> response) {
                        if (response.raw().code() == 200) {

                            for (Suspected suspected : response.body()) {
                                suspecteds.add(new Suspected(suspected.getId(), suspected.getName(), suspected.getType(), suspected.getScore()));
                            }

                            SuspectedCustomAdapter rankingCustomAdapter;
                            rankingCustomAdapter = new SuspectedCustomAdapter(MainActivity.this, suspecteds);

                            recyclerView.setAdapter(rankingCustomAdapter);
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Suspected>> call, Throwable t) {
                        Log.e("GETALLBIDDING", t.toString());
                    }
                });
            }
        }, 0, 600, TimeUnit.SECONDS);
    }
}
