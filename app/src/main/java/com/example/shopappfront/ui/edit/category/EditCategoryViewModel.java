package com.example.shopappfront.ui.edit.category;

import com.example.shopappfront.data.models.Category;
import com.example.shopappfront.ui.edit.EditModelViewModel;

public class EditCategoryViewModel extends EditModelViewModel<Category> {

    public EditCategoryViewModel() {
        super();
    }


    public void dataChanged(String categoryName){
        super.editModelFormState.setValue(new EditCategoryFormState(categoryName));
    }

}
