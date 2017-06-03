package com.project.hackathon.camara.app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.project.hackathon.camara.app.handler.DatabaseHandler;
import com.project.hackathon.camara.app.model.Suspected;

/**
 * Created by matheuscatossi on 6/3/17.
 */

public class InformationSuspectedActivity extends AppCompatActivity {

    private int id;
    private DatabaseHandler db;
    private Button btn_vote;
    private Suspected suspected;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information_suspected);

        btn_vote = (Button) findViewById(R.id.btn_vote);

        Intent myIntent = getIntent();
        id = Integer.parseInt(myIntent.getStringExtra("id"));

        db = new DatabaseHandler(this);


        suspected = null;

        if (db.getSuspectedsCount() != 0) {
            suspected = db.getSuspected(id);
            Log.d("SUSPECTED",""+ suspected.getVote());
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
                    suspected = new Suspected(id, 1);
                    btn_vote.setText(R.string.text_btn_not_vote);
                    db.addSuspected(suspected);
                }
            }
        });
    }
}
