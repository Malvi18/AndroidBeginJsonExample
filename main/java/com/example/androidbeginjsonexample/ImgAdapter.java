package com.example.androidbeginjsonexample;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * Created by Dell on 20-05-2018.
 */

public class ImgAdapter extends RecyclerView.Adapter<ImgAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<Model> models;

    public ImgAdapter(Context context, ArrayList<Model> models) {
        this.context = context;
        this.models = models;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(context).inflate(R.layout.row_layout,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        MyViewHolder myViewHolder=(MyViewHolder)holder;
        Model model=models.get(position);
        holder.country.setText("county"+model.getCountry());
        //holder.country.setText("f"+model.getCountry());
        Glide.with(context).load(model.getFlag())
                .into(myViewHolder.flag);
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
       TextView country;
       ImageView flag;

        public MyViewHolder(View itemView) {
            super(itemView);
            country=itemView.findViewById(R.id.country);
            flag=itemView.findViewById(R.id.flag);
        }
    }
}
