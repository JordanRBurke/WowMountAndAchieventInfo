package com.jordanburke.wowmountandachieventinfo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface WowRetrofitInterface {

    @GET("{realm}/{name}")
    Call<WowInformation> getWowInformation(@Path("realm") String wowRealm, @Path("name") String wowUsername, @Query("fields") String fields,  @Query("apikey") String apiKey);


    }
