package com.itonemm.blogprojectonline;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsHolder> {

    ArrayList<NewsModel> myModels=new ArrayList<>();
    Context context;
    Activity activity;

    @NonNull
    @Override
    public NewsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.newsrow,parent,false);
        return new NewsHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsHolder holder, final int position) {
        NewsModel temp=myModels.get(position);
        holder.newsDate.setText(temp.publishedDate);
        holder.newsTitle.setText(temp.title);
        holder.newsAduthor.setText(temp.author);
        Glide.with(context)
                .load(temp.imageUrl)
                .into(holder.newsImage);

        holder.newsImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,DetailsActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("url",myModels.get(position).newsUrl);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return myModels.size();
    }

    public NewsAdapter(ArrayList<NewsModel> myModels, Context context, Activity activity) {
        this.myModels = myModels;
        this.context = context;
        this.activity = activity;
    }

    public class NewsHolder extends RecyclerView.ViewHolder{
        ImageView newsImage;
        TextView newsTitle,newsAduthor,newsDate;

        public NewsHolder(@NonNull View itemView) {
            super(itemView);
            newsImage=itemView.findViewById(R.id.newsimage);
            newsTitle=itemView.findViewById(R.id.newstitle);
            newsAduthor=itemView.findViewById(R.id.newsauthor);
            newsDate=itemView.findViewById(R.id.newsdate);
        }
    }
}
