package com.example.android.notification;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.android.notification.Activities.SendActivity;
import com.example.android.notification.Activities.To_Items_;
import com.example.android.notification.models.Users;

import java.util.List;

/**
 * Created by Riya Khandelwal on 12-04-2018.
 */

public class UsersRecyclerAdapter extends RecyclerView.Adapter<UsersRecyclerAdapter.ViewHolder> {
    private List<Users> usersList;
    private Context context;
    public UsersRecyclerAdapter(Context context,List<Users> usersList){
    this.usersList=usersList;
    this.context=context;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.users_list_item,parent,false);
    return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final String user_name=usersList.get(position).getName();
        holder.user_name_view.setText(user_name);

        ImageView user_image_view=holder.user_image_view;
        Glide.with(context).load(usersList.get(position).getImage()).into(user_image_view);

        final String user_id=usersList.get(position).userId;

         holder.mView.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {

                 Intent sendIntent=new Intent(context,SendActivity.class);
                 sendIntent.putExtra("user_id",user_id);
                 sendIntent.putExtra("user_name",user_name);
                 context.startActivity(sendIntent);
//                 Intent sendIntent=new Intent(context,To_Items_.class);
//                 sendIntent.putExtra("user_id1",user_id);
//                 sendIntent.putExtra("user_name1",user_name);
//                 context.startActivity(sendIntent);
             }
         });
    }

    @Override
    public int getItemCount() {
        return usersList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private View mView;
        private ImageView user_image_view;
        private TextView user_name_view;

        public ViewHolder(View itemView) {
            super(itemView);
            mView=itemView;
            user_image_view=(ImageView)mView.findViewById(R.id.user_list_image);
            user_name_view=(TextView)mView.findViewById(R.id.user_list_name);
        }
    }
}
