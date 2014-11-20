package com.gjermundbjaanes.data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Address {

    @Id
    @GeneratedValue
    private long id;

    public Address() {
    }

    public long getId() {
        return id;
    }
}
