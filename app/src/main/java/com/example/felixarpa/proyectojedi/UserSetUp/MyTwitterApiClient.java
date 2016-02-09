package com.example.felixarpa.proyectojedi.UserSetUp;

import com.twitter.sdk.android.core.TwitterApiClient;
import com.twitter.sdk.android.core.TwitterSession;

class MyTwitterApiClient extends TwitterApiClient {
    public MyTwitterApiClient(TwitterSession session) {
        super(session);
    }

    public UsersService getUsersService() {
        return getService(UsersService.class);
    }
}