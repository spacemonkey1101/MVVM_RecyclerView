package com.example.mvvm_recyclerview.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.mvvm_recyclerview.R;
import com.example.mvvm_recyclerview.models.NicePlace;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private static final String TAG = "RecyclerViewAdapter";

    private List<NicePlace> nicePlaceList ;
    private Context mContext;

    public RecyclerViewAdapter(List<NicePlace> nicePlaceList, Context mContext) {
        this.nicePlaceList = nicePlaceList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_list_item , parent , false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Log.d(TAG, "onBind called");
        //Set the name of Nice Place
        holder.imageName.setText(nicePlaceList.get(position).getTitle());

        //set the image
        RequestOptions requestOptions = new RequestOptions().error(R.drawable.ic_launcher_background);
        Glide.with(mContext)
                .setDefaultRequestOptions(requestOptions)
                .load(nicePlaceList.get(position).getImageUrl()) //resource of image
                .into(holder.circleImageView); //we load it into the image



    }

    @Override
    public int getItemCount() {
        return nicePlaceList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        // view holder holds the view in memory
        CircleImageView circleImageView;
        TextView imageName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //attach widgets to their id
            circleImageView = itemView.findViewById(R.id.circular_image);
            imageName = itemView.findViewById(R.id.image_name);
        }
    }
}
