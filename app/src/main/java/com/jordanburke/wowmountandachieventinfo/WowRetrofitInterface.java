package com.jordanburke.wowmountandachieventinfo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface WowRetrofitInterface {

    @GET("{realm}/{name}")
    Call<WowInformation> getWowInformation(@Path("realm") String wowRealm, @Path("name") String wowUsername, @Query("fields") String mounts, @Query("apiKey") String apiKey);

    class WowInformation {

        @SerializedName("mounts")
        private Mounts wowMounts;
        @SerializedName("achievements")
        private String wowAchievements;

        public Mounts getWowMounts() {
            return wowMounts;
        }

        public String getWowAchievements() {
            return wowAchievements;
        }

        class Mounts {

            @SerializedName("collected")
            private List<CollectedMounts> colletedMounts;

            public List<CollectedMounts> getColletedMounts() {
                return colletedMounts;
            }

            class CollectedMounts {

                @SerializedName("name")
                private String name;

                public String getName() {
                    return name;
                }

                @SerializedName("icon")

                private void listAllNames(List<CollectedMounts> collectedMountsList) {

                    for (CollectedMounts mounts :
                            collectedMountsList) {
                        mounts.getName();
                    }


                }


            }
        }
    }
}
