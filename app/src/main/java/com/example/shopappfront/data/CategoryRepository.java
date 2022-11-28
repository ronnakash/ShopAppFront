package com.example.shopappfront.data;

import com.example.shopappfront.data.models.Category;

public class CategoryRepository extends RepositoryWithId<Category>{
    private static volatile CategoryRepository instance;

    private CategoryRepository() {
        super("/category");
    }

    public static CategoryRepository getInstance() {
        if (instance == null) {
            instance = new CategoryRepository();
        }
        return instance;
    }


}
