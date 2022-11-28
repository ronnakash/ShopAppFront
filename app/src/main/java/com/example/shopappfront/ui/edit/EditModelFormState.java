package com.example.shopappfront.ui.edit;

import android.webkit.URLUtil;

import com.example.shopappfront.data.models.ApplicationModel;

public abstract class EditModelFormState<T extends ApplicationModel> {

    public abstract boolean isDataValid();

    protected static String validateUrl(String url){
        if (url == null || url.equals(""))
            return null;
        if(URLUtil.isHttpsUrl(url) || URLUtil.isHttpUrl(url))
            return null;
        else
            return "Url is invalid!";
    }

}
