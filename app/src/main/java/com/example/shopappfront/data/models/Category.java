package com.example.shopappfront.data.models;

public class Category extends ApplicationModelWithId{

    private String categoryName;

    public Category(int id, String categoryName) {
        super(id);
        this.categoryName = categoryName;
    }

    public String getCategoryName() {
        return categoryName;
    }

}
