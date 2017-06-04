package com.project.hackathon.camara.app.model;

/**
 * Created by matheuscatossi on 6/4/17.
 */

public class Product {

    String crawlerUrl;
    String productName;
    String manufacturer;
    int score;
    int quantity;
    float totalCrawlerPrice;
    float totalPrice;
    float crawlerPrice;
    float price;

    public Product() {

    }

    public Product(String crawlerUrl, String productName, String manufacturer, int score, int totalCrawlerPrice, int quantity, float totalPrice, float crawlerPrice, float price){
        this.crawlerUrl = crawlerUrl;
        this.productName = productName;
        this.manufacturer = manufacturer;
        this.score = score;
        this.totalCrawlerPrice = totalCrawlerPrice;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.crawlerPrice = crawlerPrice;
        this.price = price;
    }

    public String getCrawlerUrl() {
        return crawlerUrl;
    }

    public void setCrawlerUrl(String crawlerUrl) {
        this.crawlerUrl = crawlerUrl;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public float getTotalCrawlerPrice() {
        return totalCrawlerPrice;
    }

    public void setTotalCrawlerPrice(float totalCrawlerPrice) {
        this.totalCrawlerPrice = totalCrawlerPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
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

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
