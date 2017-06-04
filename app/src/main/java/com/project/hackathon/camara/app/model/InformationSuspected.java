package com.project.hackathon.camara.app.model;

import java.util.ArrayList;

/**
 * Created by matheuscatossi on 6/4/17.
 */

public class InformationSuspected {

    ArrayList<Product> products;
    String registerAt;
    String orderNumber;
    String manufacturer;
    String productAlias;
    String type;
    String name;
    String id;
    String orderType;
    String biddingUrl;
    String avaazUrl;
    String createdAt;
    float totalPrice;
    float crawlerPrice;
    int quantity;
    int score;
    int numberVotes;

    public InformationSuspected() {

    }

    public InformationSuspected(ArrayList<Product> products, String registerAt, String orderNumber, String manufacturer, String productAlias, String type, String name, String id, String orderType, String biddingUrl, String createdAt, float totalPrice, float crawlerPrice, int quantity, int score, int numberVotes, String avaazUrl){
        this.products = products;
        this.registerAt = registerAt;
        this.orderNumber = orderNumber;
        this.manufacturer = manufacturer;
        this.productAlias = productAlias;
        this.type = type;
        this.name = name;
        this.id = id;
        this.orderType = orderType;
        this.biddingUrl = biddingUrl;
        this.createdAt = createdAt;
        this.totalPrice = totalPrice;
        this.crawlerPrice = crawlerPrice;
        this.quantity = quantity;
        this.score = score;
        this.numberVotes = numberVotes;
        this.avaazUrl = avaazUrl;
    }

    public String getAvaazUrl() {
        return avaazUrl;
    }

    public void setAvaazUrl(String avaazUrl) {
        this.avaazUrl = avaazUrl;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public String getRegisterAt() {
        return registerAt;
    }

    public void setRegisterAt(String registerAt) {
        this.registerAt = registerAt;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getProductAlias() {
        return productAlias;
    }

    public void setProductAlias(String productAlias) {
        this.productAlias = productAlias;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getBiddingUrl() {
        return biddingUrl;
    }

    public void setBiddingUrl(String biddingUrl) {
        this.biddingUrl = biddingUrl;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public float getCrawlerPrice() {
        return crawlerPrice;
    }

    public void setCrawlerPrice(float crawlerPrice) {
        this.crawlerPrice = crawlerPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getNumberVotes() {
        return numberVotes;
    }

    public void setNumberVotes(int numberVotes) {
        this.numberVotes = numberVotes;
    }
}
