package com.jordanburke.wowmountandachieventinfo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WowInformation {

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

    public class Mounts {

        @SerializedName("collected")
        private List<CollectedMounts> colletedMounts;

        public List<CollectedMounts> getColletedMounts() {
            return colletedMounts;
        }

        public class CollectedMounts {

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