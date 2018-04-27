package com.jordanburke.wowmountandachieventinfo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface WowRetrofitInterface {
    @GET("{name}/{realm}")
    Call<WowInformation> getWowInformation(@Path("name") String wowUsername, @Path("realm") String wowRealm);

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
