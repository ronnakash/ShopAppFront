package com.example.shopappfront.data.models;

public abstract class ApplicationModelWithId implements ApplicationModel{

    int id;

    public ApplicationModelWithId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

}
