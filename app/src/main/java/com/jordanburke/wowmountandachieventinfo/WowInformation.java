package com.jordanburke.wowmountandachieventinfo;

import android.os.Parcel;
import android.os.Parcelable;

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

        public class CollectedMounts implements Parcelable{

            @SerializedName("name")
            private String name;

            protected CollectedMounts(Parcel in) {
                name = in.readString();
            }

            public  final Creator<CollectedMounts> CREATOR = new Creator<CollectedMounts>() {
                @Override
                public CollectedMounts createFromParcel(Parcel in) {
                    return new CollectedMounts(in);
                }

                @Override
                public CollectedMounts[] newArray(int size) {
                    return new CollectedMounts[size];
                }
            };

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


            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(name);
            }
        }
    }
}