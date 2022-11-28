package com.example.shopappfront.ui.edit.item;

import com.example.shopappfront.data.models.Item;
import com.example.shopappfront.ui.edit.EditModelFormState;

import java.math.BigDecimal;

public class EditItemFormState extends EditModelFormState<Item> {

    private String itemNameError;

    private String itemDescriptionError;

    private String itemBasePriceError;

    private String itemDiscountError;

    private String itemPicUrlError;

    public EditItemFormState( String itemName, String itemDescription,
                              String itemBasePrice, String itemDiscount,
                              String itemPicUrl) {
        this.itemNameError = validateName(itemName);
        this.itemDescriptionError = validateDescription(itemDescription);
        this.itemBasePriceError = validatePrice(itemBasePrice);
        this.itemDiscountError = validateDiscount(itemDiscount);
        this.itemPicUrlError = validateUrl(itemPicUrl);
    }

    @Override
    public boolean isDataValid() {
        return (itemBasePriceError == null && itemNameError == null && itemDescriptionError == null &&
                itemDiscountError == null && itemPicUrlError == null);
    }

    private static String validateName(String name){
        if (name == null|| name.equals(""))
            return "Name can't be empty!";
        return null;
    }

    private static String validateDescription(String itemDescription){
        if (itemDescription == null || itemDescription.equals(""))
            return "Description can't be empty!";
        return null;
    }

    private static String validatePrice(String price){
        if (price.equals(""))
            return "Price can't be empty";
        try {        BigDecimal priceDecimal = new BigDecimal(price);
            if(priceDecimal.scale() > 2)
                return "Price format is incorrect!";
            if (priceDecimal.compareTo(BigDecimal.ZERO) != 1)
                return "Price can't be zero!";
            return null;
        }
        catch (Exception e){
            return "Price format is incorrect!";
        }
    }

    private static String validateDiscount(String discount){
        try {
            if (discount.equals(""))
                return "Discount can't be empty (use 0)";
            int discountNum = Integer.valueOf(discount);
            if (discountNum>99 || discountNum< 0)
                return "Discount is out of range!";
            return null;
        } catch (Exception e) {
            return "Discount format is incorrect!";
        }
    }



    public String getItemNameError() {
        return itemNameError;
    }

    public String getItemDescriptionError() {
        return itemDescriptionError;
    }

    public String getItemBasePriceError() {
        return itemBasePriceError;
    }

    public String getItemDiscountError() {
        return itemDiscountError;
    }

    public String getItemPicUrlError() {
        return itemPicUrlError;
    }
}
