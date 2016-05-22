package com.xiaoshan.polly.followme.http;

import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by xiaoshan on 2016/5/10.
 * 21:34
 */
public interface API {
    @GET("users/{user}/repos")
    Call<List<Repo>> listRepos(@Path("user") String user);
}
