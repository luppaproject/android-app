package com.project.hackathon.camara.app.model;

/**
 * Created by matheuscatossi on 6/4/17.
 */

public class Voted {

    boolean voted;

    public Voted(){

    }

    public Voted(boolean voted){
        this.voted = voted;
    }

    public boolean isVoted() {
        return voted;
    }

    public void setVoted(boolean voted) {
        this.voted = voted;
    }
}
