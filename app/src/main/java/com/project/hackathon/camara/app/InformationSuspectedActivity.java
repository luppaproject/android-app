package com.project.hackathon.camara.app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.project.hackathon.camara.app.adapter.ProductSuspectedCustomAdapter;
import com.project.hackathon.camara.app.adapter.SuspectedCustomAdapter;
import com.project.hackathon.camara.app.handler.DatabaseHandler;
import com.project.hackathon.camara.app.model.Suspected;

import java.util.ArrayList;

/**
 * Created by matheuscatossi on 6/3/17.
 */

public class InformationSuspectedActivity extends AppCompatActivity {

    private String id;
    private DatabaseHandler db;
    private Button btn_vote;
    private Suspected suspected;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information_suspected);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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

        RecyclerView recyclerView;
        recyclerView = (RecyclerView) findViewById(R.id.listProductSuspected);

        ArrayList<Suspected> suspecteds;
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(mLayoutManager);
        suspecteds = new ArrayList<>();

        suspecteds.add(new Suspected("Computer", "R$2.000,00", "R$5.000,00"));
        suspecteds.add(new Suspected("Computer", "R$2.000,00", "R$5.000,00"));
        suspecteds.add(new Suspected("Computer", "R$2.000,00", "R$5.000,00"));
        suspecteds.add(new Suspected("Computer", "R$2.000,00", "R$5.000,00"));
        suspecteds.add(new Suspected("Computer", "R$2.000,00", "R$5.000,00"));
        suspecteds.add(new Suspected("Computer", "R$2.000,00", "R$5.000,00"));
        suspecteds.add(new Suspected("Computer", "R$2.000,00", "R$5.000,00"));
        suspecteds.add(new Suspected("Computer", "R$2.000,00", "R$5.000,00"));
        suspecteds.add(new Suspected("Computer", "R$2.000,00", "R$5.000,00"));


        ProductSuspectedCustomAdapter rankingCustomAdapter;
        rankingCustomAdapter = new ProductSuspectedCustomAdapter(this, suspecteds);

        recyclerView.setAdapter(rankingCustomAdapter);


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
            }
        });
    }
}
