package com.jordanburke.wowmountandachieventinfo;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MountAdapter extends RecyclerView.Adapter<MountAdapter.MountViewHolder> implements MainActivity.MountCallback {



    private List<WowInformation.Mounts.CollectedMounts> mountList;
    
    public MountAdapter(List<WowInformation.Mounts.CollectedMounts> mountList)  {
        this.mountList = mountList;
    }

    @NonNull
    @Override
    public MountViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.mount_item_layout, parent, false);

        return new MountViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MountViewHolder holder, int position) {
        holder.bindMountList(mountList.get(position));
    }

    @Override
    public int getItemCount() {
        return mountList.size();
    }

    @Override
    public void mountClass(List<WowInformation.Mounts.CollectedMounts> collectedMountsList) {
         mountList = collectedMountsList;
    }


//    @Override
//    public MountAdapter.MessageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.mount_item_layout, parent, false);
//        return new MessageViewHolder(itemView);
//    }



    public class MountViewHolder extends RecyclerView.ViewHolder {

        public TextView mountName;


        public MountViewHolder(View itemView) {
            super(itemView);
            mountName = itemView.findViewById(R.id.mount_text_view);


        }

        public void bindMountList(List<WowInformation.Mounts.CollectedMounts> mounts) {
//            mountName.setText(mounts.getColletedMounts().get());
//            mountName.setText(mounts());

        }
    }




}
