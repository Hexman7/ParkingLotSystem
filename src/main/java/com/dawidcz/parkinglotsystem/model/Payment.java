package com.dawidcz.parkinglotsystem.model;

import java.math.BigDecimal;

public class Payment {
    private int id;
    private int ticketId;
    private int chargerTickedId;
    private BigDecimal amount;
    private String paymentMethod;
    private String status;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public int getChargerTickedId() {
        return chargerTickedId;
    }

    public void setChargerTickedId(int chargerTickedId) {
        this.chargerTickedId = chargerTickedId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
