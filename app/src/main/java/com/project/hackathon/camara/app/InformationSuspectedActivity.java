package com.project.hackathon.camara.app;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.project.hackathon.camara.app.adapter.ProductSuspectedCustomAdapter;
import com.project.hackathon.camara.app.adapter.SuspectedCustomAdapter;
import com.project.hackathon.camara.app.handler.DatabaseHandler;
import com.project.hackathon.camara.app.model.InformationSuspected;
import com.project.hackathon.camara.app.model.Product;
import com.project.hackathon.camara.app.model.RequisitionVoted;
import com.project.hackathon.camara.app.model.Suspected;
import com.project.hackathon.camara.app.model.Voted;
import com.project.hackathon.camara.app.webservice.APIClient;
import com.project.hackathon.camara.app.webservice.APIInterface;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by matheuscatossi on 6/3/17.
 */

public class InformationSuspectedActivity extends AppCompatActivity {

    private String id;
    private DatabaseHandler db;
    private Button btn_vote;
    private Suspected suspected;

    private RecyclerView recyclerView;
    private ArrayList<InformationSuspected> informationSuspecteds;
    ArrayList<Suspected> productSuspecteds;
    private APIInterface apiService;
    private Call<InformationSuspected> callInformationSuspected;
    private InformationSuspected informationSuspected;

    private TextView tv_name;
    private TextView tv_desc;
    private TextView tv_link_avaaz;
    private TextView tv_count;
    private TextView tv_bidding_url;

    private Call<Voted> callVoted;

    private ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information_suspected);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btn_vote = (Button) findViewById(R.id.btn_vote);

        Intent myIntent = getIntent();
        id = myIntent.getStringExtra("id");

        db = new DatabaseHandler(this);

        suspected = null;

        if (db.getSuspectedsCount() != 0) {
            suspected = db.getSuspected(id);

            if (suspected == null) {
                btn_vote.setText(R.string.text_btn_vote_default);
            } else {
                if (suspected.getVote() == 0) {
                    btn_vote.setText(R.string.text_btn_vote_default);
                } else {
                    btn_vote.setText(R.string.text_btn_not_vote);
                }
            }
        }

        tv_name = (TextView) findViewById(R.id.tv_name);
        tv_desc = (TextView) findViewById(R.id.tv_desc);
        tv_link_avaaz = (TextView) findViewById(R.id.tv_link_avaaz);
        tv_count = (TextView) findViewById(R.id.tv_count);
        tv_bidding_url = (TextView) findViewById(R.id.tv_bidding_url);

        recyclerView = (RecyclerView) findViewById(R.id.listProductSuspected);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(mLayoutManager);
        productSuspecteds = new ArrayList<>();

        apiService = APIClient.getService().create(APIInterface.class);
        callInformationSuspected = apiService.getBiddingById(id);
        informationSuspecteds = new ArrayList<>();

        callInformationSuspected.enqueue(new Callback<InformationSuspected>() {
            @Override
            public void onResponse(Call<InformationSuspected> call, Response<InformationSuspected> response) {
                if (response.raw().code() == 200) {
                    informationSuspected = response.body();


                    for (Product product : informationSuspected.getProducts()) {
                        productSuspecteds.add(new Suspected(product.getProductName(), "" + product.getTotalCrawlerPrice(), "" +product.getTotalPrice()));
                    }

                    ProductSuspectedCustomAdapter rankingCustomAdapter;
                    rankingCustomAdapter = new ProductSuspectedCustomAdapter(InformationSuspectedActivity.this, productSuspecteds);

                    recyclerView.setAdapter(rankingCustomAdapter);

                    tv_name.setText("" + informationSuspected.getProductAlias());
                    tv_desc.setText("" + informationSuspected.getManufacturer());
                    tv_link_avaaz.setText("" + informationSuspected.getAvaazUrl());
                    tv_bidding_url.setText("" + informationSuspected.getBiddingUrl());
                    tv_count.setText("Quantidade de votos: " + informationSuspected.getNumberVotes());
                }
            }

            @Override
            public void onFailure(Call<InformationSuspected> call, Throwable t) {
                Log.e("GETBIDDINGBYID", t.toString());
            }
        });

        tv_bidding_url.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent intent = new Intent(Intent.ACTION_VIEW).setData(Uri.parse(tv_bidding_url.getText().toString()));
                startActivity(intent);
            }
        });

        tv_link_avaaz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent intent = new Intent(Intent.ACTION_VIEW).setData(Uri.parse(tv_link_avaaz.getText().toString()));
                startActivity(intent);
            }
        });

        btn_vote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(suspected != null) {
                    if (suspected.getVote() == 0) {
                        suspected.setVote(1);
                        btn_vote.setText(R.string.text_btn_not_vote);
                    } else {
                        suspected.setVote(0);
                        btn_vote.setText(R.string.text_btn_vote_default);
                    }

                    db.updateSuspected(suspected);
                } else {
                    suspected = new Suspected(""+ id, 1);
                    btn_vote.setText(R.string.text_btn_not_vote);
                    db.addSuspected(suspected);
                }

                progress = ProgressDialog.show(InformationSuspectedActivity.this, "Carregando", "Enviando informações", true);

                APIInterface apiService = APIClient.getService().create(APIInterface.class);
                callVoted = apiService.postVoted(id, new RequisitionVoted("Matheus Catossi", "matheuscatossi@gmail.com", "08465120", "BR"));

                callVoted.enqueue(new Callback<Voted>() {

                    @Override
                    public void onResponse(Call<Voted> call, Response<Voted> response) {
                        if (response.raw().code() == 200) {
                            Voted t = response.body();

                            Toast.makeText(InformationSuspectedActivity.this.getBaseContext(), "Votação realizado com sucesso", Toast.LENGTH_SHORT).show();
                            progress.dismiss();
                            finish();
                        }
                    }

                    @Override
                    public void onFailure(Call<Voted> call, Throwable t) {
                        Log.e("MENSAGEM", t.toString());
                        progress.dismiss();
                    }

                });
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(InformationSuspectedActivity.this,MainActivity.class);
        startActivity(i);
        finish();
        super.onBackPressed();
    }
}
