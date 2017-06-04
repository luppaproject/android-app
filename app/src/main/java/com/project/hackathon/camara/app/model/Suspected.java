package com.project.hackathon.camara.app.model;

/**
 * Created by matheuscatossi on 6/3/17.
 */

public class Suspected {

    int id;
    int icon;
    int score;
    int icon_score;
    String type;
    String name;
    int vote;

    public Suspected(int id, int icon, int score, int icon_score, String type, String name) {
        this.id = id;
        this.icon = icon;
        this.score = score;
        this.icon_score = icon_score;
        this.type = type;
        this.name = name;
    }

    public Suspected(int icon, int score, int icon_score, String type, String name) {
        this.icon = icon;
        this.score = score;
        this.icon_score = icon_score;
        this.type = type;
        this.name = name;
    }

    public Suspected(int id, int vote) {
        this.id = id;
        this.vote = vote;
    }

    public Suspected(){

    }

    public int getVote() {
        return vote;
    }

    public void setVote(int vote) {
        this.vote = vote;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getIcon_score() {
        return icon_score;
    }

    public void setIcon_score(int icon_score) {
        this.icon_score = icon_score;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
