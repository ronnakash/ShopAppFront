package com.example.shopappfront.data;

import android.content.Context;

import com.example.shopappfront.data.models.Item;
import com.example.shopappfront.data.models.Order;
import com.example.shopappfront.data.models.OrderedItems;
import com.example.shopappfront.data.requests.RestRequestBuilder;
import com.example.shopappfront.data.requests.RestRequestWithBody;
import com.example.shopappfront.data.requests.response.RestResponse;
import com.example.shopappfront.data.requests.response.TextHttpResponseHandlerWithDefault;
import com.loopj.android.http.TextHttpResponseHandler;

import java.util.List;

public class OrderRepository extends RepositoryWithId<Order>{
    private static volatile OrderRepository instance;
    private Order shoppingCartOrder;
    List<Order> usersOrders;

    private OrderRepository() {
        super("/order");
        shoppingCartOrder = new Order();
    }

    public static OrderRepository getInstance() {
        if (instance == null) {
            instance = new OrderRepository();
        }
        return instance;
    }

    public void setUsersOrders(List<Order> usersOrders) {
        this.usersOrders = usersOrders;
    }

    public Order getUsersOrderById(int id) {
        for (Order order : usersOrders){
            if (order.getId() == id)
                return order;
        }
        throw new RuntimeException();
    }

    public void getUsersOrders(TextHttpResponseHandler responseHandler, Context context){
        super.get(new RestRequestBuilder<Order>()
                .setUrl(baseUrl + "/my")
                .setContext(context)
                .setResponseHandler(responseHandler)
                .build());
    }

    public void updateOrderStatus(Order order, TextHttpResponseHandler responseHandler, Context context){
        super.post((RestRequestWithBody<RestResponse<Order>>) new RestRequestBuilder<Order>()
                .setUrl(baseUrl + "/status")
                .setContext(context)
                .setBody(order)
                .setResponseHandler(responseHandler)
                .build());
    }


    public void addItemsToCartOrder(Item item){
        OrderedItems orderedItems = shoppingCartOrder.getOrderedItemsByItemId(item.getId());
        if (orderedItems == null) {
            orderedItems = new OrderedItems(item, 1, shoppingCartOrder.getId());
            shoppingCartOrder.getOrderedItems().add(orderedItems);
        } else
            orderedItems.setQuantity(orderedItems.getQuantity()+1);
    }

    public Order getShoppingCartOrder() {
        return shoppingCartOrder;
    }

    public void resetCart() {
        shoppingCartOrder = new Order();
    }


    public void getAll(TextHttpResponseHandlerWithDefault<List<Order>> responseHandler, Context context){
        get(getAllRequest(responseHandler, context));
    }

}
