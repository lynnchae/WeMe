package com.daoke.mobileserver.test;

import javax.xml.bind.annotation.*;
import java.util.Set;

/**
 * User: chenlong
 * Date: 2015/1/6
 * Time: 14:00
 */
@XmlAccessorOrder(XmlAccessOrder.ALPHABETICAL)
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "shop", propOrder = { "name", "number", "describer", "address","orders" })
@XmlRootElement(name = "CHMart")
public class Shop {

    private String name;

    private String number;

    private String describer;

    private Set<Order> orders;

    private Address address;


    public Shop() {
    }

    public Shop(String name, String number, String describer, Address address) {
        this.name = name;
        this.number = number;
        this.describer = describer;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDescriber() {
        return describer;
    }

    public void setDescriber(String describer) {
        this.describer = describer;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Shop{" +
                "name='" + name + '\'' +
                ", number='" + number + '\'' +
                ", describer='" + describer + '\'' +
                ", orders=" + orders +
                ", address=" + address +
                '}';
    }
}
