package com.jordanburke.wowmountandachieventinfo;

import com.google.gson.annotations.SerializedName;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface WowRetrofitInterface {
    @GET("{name}/{realm}")
    Call<WowInformation> getWowInformation(@Path("name") String wowUsername);

    class WowInformation {
        @SerializedName("mounts")
        private String wowMounts;
        @SerializedName("achievements")
        private String wowAchievements;




    }
}
