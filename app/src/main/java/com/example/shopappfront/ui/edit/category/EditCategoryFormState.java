package com.example.shopappfront.ui.edit.category;

import com.example.shopappfront.data.models.Category;
import com.example.shopappfront.ui.edit.EditModelFormState;

public class EditCategoryFormState extends EditModelFormState<Category> {

    String categoryNameError;

    public EditCategoryFormState(String categoryName) {
        categoryNameError = (categoryName== null || categoryName.equals(""))?
                "Category name can't be empty" : null;
    }

    @Override
    public boolean isDataValid() {
        return categoryNameError == null;
    }
}
