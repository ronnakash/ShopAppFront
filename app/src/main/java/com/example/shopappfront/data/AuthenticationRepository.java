package com.example.shopappfront.data;

import android.content.Context;

import com.example.shopappfront.data.models.AuthenticationApplicationModel;
import com.example.shopappfront.data.models.LoginModel;
import com.example.shopappfront.data.models.User;
import com.example.shopappfront.data.requests.RestRequestBuilder;
import com.example.shopappfront.data.requests.RestRequestWithBody;
import com.loopj.android.http.TextHttpResponseHandler;

/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 */
public class AuthenticationRepository<T extends AuthenticationApplicationModel> extends Repository<T>{

    private static volatile AuthenticationRepository instance;
    private User loggedInUser;

    private AuthenticationRepository() {
    }

    public static AuthenticationRepository getInstance() {
        if (instance == null) {
            instance = new AuthenticationRepository<>();
        }
        return instance;
    }

    public boolean isLoggedIn() {
        return loggedInUser != null;
    }

    public void logout() {
        loggedInUser = null;
    }

    public void setLoggedInUser(User user) {
        loggedInUser = user;
    }

    public User getLoggedInUser() {
        return loggedInUser;
    }



    public void login(String username, String password, Context context, TextHttpResponseHandler responseHandler) {
        super.post((RestRequestWithBody) new RestRequestBuilder<LoginModel>()
                .setUrl("/auth/login")
                .setContext(context)
                .setResponseHandler(responseHandler)
                .setBody(new LoginModel(username, password))
                .build());
    }


    public void register(User user, Context context, TextHttpResponseHandler responseHandler) {
        super.put((RestRequestWithBody) new RestRequestBuilder<User>()
                .setUrl("/auth/register")
                .setContext(context)
                .setResponseHandler(responseHandler)
                .setBody(user)
                .build());
    }

    public String getUserToken(){
        if (isLoggedIn())
            return "Bearer " + loggedInUser.getToken();
        else return null;
    }

    public Integer getUserId(){
        if (isLoggedIn())
            return loggedInUser.getId();
        else return -1;

    }

}