package com.project.hackathon.camara.app.model;

import java.util.List;

/**
 * Created by matheuscatossi on 25/06/17.
 */

public class ListSuspected {

    private List<Suspected> biddings;
    private int pages;
    private int nextPage;

    public ListSuspected() {

    }

    public ListSuspected(List<Suspected> biddings, int pages, int nextPage) {
        this.pages = pages;
        this.nextPage = nextPage;
        this.biddings = biddings;
    }

    public List<Suspected> getBiddings() {
        return biddings;
    }

    public void setBiddings(List<Suspected> biddings) {
        this.biddings = biddings;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getNextPage() {
        return nextPage;
    }

    public void setNextPage(int nextPage) {
        this.nextPage = nextPage;
    }
}
