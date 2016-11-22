package com.banking.bank;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table
@XmlRootElement
public class Transaction implements Serializable{

    enum Type {
        DEBIT("debit"),
        CREDIT("credit");

        private String type;

        Type(String type) {
            this.type = type;
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @ManyToOne
    @JoinColumn(name = "accountId", referencedColumnName = "id")
    private Account account;
    private Type type;
    private String timestamp;
    private double amount;
    private double postBalance;

    public Transaction() {}

    public Transaction(Type type, double amount, Account account) {
        this.type = type;
        this.amount = amount;
        this.account = account;

        setTimestamp();
    }

    public void setType(String type) {
        this.type = Type.valueOf(type);
    }

    public Type getType() {
        return type;
    }

    public void setPostBalance(double postBalance) {
        this.postBalance = postBalance;
    }

    public double getPostBalance() {
        return postBalance;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }

    public Account getAccount() {
        return account;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setTimestamp() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.timestamp = sdf.format(date);
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getTimestamp() {
        return timestamp;
    }
}

