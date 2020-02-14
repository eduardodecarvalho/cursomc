package com.eduardo.cursomc.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

@Entity
public class Order implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private Date instant;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "order")
    private Payment payment;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "delivery_address")
    private Address deliveryAddress;

    @OneToMany(mappedBy = "id.order")
    private Set<OrderedItem> items = new HashSet<>();

    public Order() {
    }

    public Order(Integer id, Date instant, Client client, Address deliveryAddress) {
        super();
        this.id = id;
        this.instant = instant;
        this.client = client;
        this.deliveryAddress = deliveryAddress;
    }

    public double getValorPedido() {
        double soma = 0.0;
        for (OrderedItem ip : items) {
            soma = soma + ip.getSubTotal();
        }
        return soma;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getInstant() {
        return instant;
    }

    public void setInstant(Date instant) {
        this.instant = instant;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Address getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(Address deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public Set<OrderedItem> getItems() {
        return items;
    }

    public void setItems(Set<OrderedItem> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        StringBuilder builder = new StringBuilder();
        builder.append("Order number: ");
        builder.append(getId());
        builder.append(", instant: ");
        builder.append(sdf.format(getInstant()));
        builder.append(", client: ");
        builder.append(getClient().getName());
        builder.append(", payment status: ");
        builder.append(getPayment().getState().getDescription());
        builder.append("\nDetails:\n");
        for (OrderedItem ip : getItems()) {
            builder.append(ip.toString());
        }
        builder.append("Price: ");
        builder.append(nf.format(getValorPedido()));
        return builder.toString();
    }
}
