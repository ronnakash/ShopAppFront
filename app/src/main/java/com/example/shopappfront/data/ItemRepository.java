package com.example.shopappfront.data;

import com.example.shopappfront.data.models.Item;

public class ItemRepository extends RepositoryWithId<Item>{
    private static volatile ItemRepository instance;

    private ItemRepository() {
        super("/item");
    }

    public static ItemRepository getInstance() {
        if (instance == null) {
            instance = new ItemRepository();
        }
        return instance;
    }


}
