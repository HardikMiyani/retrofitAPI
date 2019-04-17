package com.friendship.talk.webservices;

/**
 * Created by iweenggs on 02/07/18.
 */

public interface ResponseCallback {

    public void ResponseSuccessCallBack(Object object);

    public void ResponseFailCallBack(String message);

    public void onResponseFail(String msg);
}