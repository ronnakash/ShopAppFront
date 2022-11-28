package com.example.shopappfront.data.models;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;

public class Item extends ApplicationModelWithId {

    private String itemName;
    private String itemDescription;
    private BigDecimal itemBasePrice;
    private Integer itemDiscount;
    private BigDecimal itemFinalPrice;
    private List<Category> categories;
    private String picUrl;

    public Item(String itemName, String itemDescription, BigDecimal itemBasePrice,
                Integer itemDiscount, BigDecimal itemFinalPrice, List<Category> categories, String picUrl, int id) {
        this(itemName, itemDescription, itemBasePrice, itemDiscount, categories, picUrl, id);
        //this.itemFinalPrice = itemFinalPrice;

    }

    public Item(String itemName, String itemDescription, BigDecimal itemBasePrice,
                Integer itemDiscount, List<Category> categories, String picUrl, int id) {
        super(id);
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.itemBasePrice = itemBasePrice;
        this.itemDiscount = itemDiscount;
        this.categories = categories;
        this.picUrl = picUrl;
        this.itemFinalPrice = itemBasePrice.multiply(new BigDecimal((100-itemDiscount)/100.0,
                        new MathContext(2, RoundingMode.HALF_EVEN)))
                .setScale(2, RoundingMode.DOWN);
    }

    public String getItemName() {
        return itemName;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public BigDecimal getItemBasePrice() {
        return itemBasePrice;
    }

    public Integer getItemDiscount() {
        return itemDiscount;
    }

    public BigDecimal getItemFinalPrice() {
        return itemFinalPrice;
    }

    public List<String> getCategories() {
        return categories
                .stream()
                .map(category -> category.getCategoryName())
                .collect(Collectors.toList());
    }

    public List<Category> getCategoriesList() {
        return categories;
    }

    public String getPicUrl() {
        return picUrl;
    }

}
