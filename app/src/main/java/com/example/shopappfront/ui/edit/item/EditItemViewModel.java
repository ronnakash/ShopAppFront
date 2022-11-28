package com.example.shopappfront.ui.edit.item;

import com.example.shopappfront.data.models.Item;
import com.example.shopappfront.ui.edit.EditModelViewModel;

public class EditItemViewModel extends EditModelViewModel<Item> {


    public EditItemViewModel() {
        super();
    }

    public void dataChanged(String itemName, String itemDescription, String itemBasePrice,
                            String itemDiscount, String itemPicUrl){
        super.editModelFormState.setValue(new EditItemFormState(itemName, itemDescription,
                itemBasePrice, itemDiscount, itemPicUrl));
    }

}
