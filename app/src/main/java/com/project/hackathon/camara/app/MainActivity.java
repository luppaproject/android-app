package com.project.hackathon.camara.app;

import android.app.ProgressDialog;
import android.support.constraint.solver.Goal;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.project.hackathon.camara.app.adapter.SuspectedCustomAdapter;
import com.project.hackathon.camara.app.model.ListSuspected;
import com.project.hackathon.camara.app.model.Suspected;
import com.project.hackathon.camara.app.utils.Constants;
import com.project.hackathon.camara.app.utils.Globals;
import com.project.hackathon.camara.app.webservice.APIClient;
import com.project.hackathon.camara.app.webservice.APIInterface;

import java.util.ArrayList;

import javax.microedition.khronos.opengles.GL;

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

        // Filters

        if (id == R.id.filter_1) {
            return true;
        } else if (id == R.id.filter_2) {
            return true;
        } else if (id == R.id.filter_3) {
            return true;
        } else if (id == R.id.filter_4) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    // RecyclerView objects and Variables
    private RecyclerView recyclerView;
    private ArrayList<Suspected> suspecteds;
    private APIInterface apiService;
    private Call<ListSuspected> callSuspected;
    private LinearLayoutManager mLayoutManager;

    // Variables for control scrollpage
    private int previousTotal = 0;
    private boolean loading = true;
    private int visibleThreshold = 5;
    int firstVisibleItem, visibleItemCount, totalItemCount;

    // ProgressDialog for loading items list
    private ProgressDialog progress;

    @Override
    protected void onResume() {
        super.onResume();

        // Page inital for pagination
        Globals.nextPage = 1;

        // Instance RecyclerView for list biddings
        recyclerView = (RecyclerView) findViewById(R.id.listSuspected);
        recyclerView.setLayoutManager(mLayoutManager);
        mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);

        // New ArrayList type suspected
        suspecteds = new ArrayList<>();

        // ScrollView event scroll
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                visibleItemCount = recyclerView.getChildCount();
                totalItemCount = mLayoutManager.getItemCount();
                firstVisibleItem = mLayoutManager.findFirstVisibleItemPosition();

                if (loading) {
                    if (totalItemCount > previousTotal) {
                        loading = false;
                        previousTotal = totalItemCount;
                    }
                }
                if (!loading && (totalItemCount - visibleItemCount)
                        <= (firstVisibleItem + visibleThreshold)) {
                    loading = true;

                    // get items for API (Endless)
                    populateList();
                }
            }
        });

        populateList();
    }


    // method for get items API
    public void populateList(){

        // Checking variable nextPage for not request the API no need
        if(Globals.nextPage == 0)
            return;

        apiService = APIClient.getService().create(APIInterface.class);

        progress = ProgressDialog.show(MainActivity.this, "Carregando", "Buscando informações...", true);

        callSuspected = apiService.getAllSuspected(String.valueOf(Globals.nextPage), String.valueOf(Constants.TOTAL_BIDDING_FOR_PAGE));
        
        callSuspected.enqueue(new Callback<ListSuspected>() {
            @Override
            public void onResponse(Call<ListSuspected> call, Response<ListSuspected> response) {
                if (response.raw().code() == 200) {

                    ListSuspected listSuspected = response.body();

                    for (Suspected suspected : listSuspected.getBiddings()) {
                        suspecteds.add(new Suspected(suspected.getId(), suspected.getName(), suspected.getType(), suspected.getScore()));
                    }

                    Globals.nextPage = (int) listSuspected.getNextPage();

                    SuspectedCustomAdapter rankingCustomAdapter;
                    rankingCustomAdapter = new SuspectedCustomAdapter(MainActivity.this, suspecteds);

                    recyclerView.setAdapter(rankingCustomAdapter);

                    recyclerView.scrollToPosition(firstVisibleItem);
                    progress.dismiss();

                }
            }

            @Override
            public void onFailure(Call<ListSuspected> call, Throwable t) {
                Log.e("GETALLBIDDING", t.toString());
            }
        });
    }

}
