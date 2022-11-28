package com.example.shopappfront.ui.edit.order;

import com.example.shopappfront.data.models.Order;
import com.example.shopappfront.ui.edit.EditModelViewModel;

public class EditOrderViewModel extends EditModelViewModel<Order> {

    public EditOrderViewModel() {
        super();
    }

    public void dataChanged(){
        super.editModelFormState.setValue(new EditOrderFormState());
    }

}
