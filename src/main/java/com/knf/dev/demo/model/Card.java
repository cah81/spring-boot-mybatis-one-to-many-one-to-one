package com.knf.dev.demo.model;

public class Card {
    private Integer id;
    private String cardNumber;
    private String cardType;
    private String email;
    private User user;

    public Card() {
    }

    public Card(Integer id, String cardNumber, String cardType, String email, User user) {
        this.id = id;
        this.cardNumber = cardNumber;
        this.cardType = cardType;
        this.email = email;
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
