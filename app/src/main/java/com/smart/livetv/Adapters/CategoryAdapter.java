package com.smart.livetv.Adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.smart.livetv.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import com.smart.livetv.CategoryDetails;
import com.smart.livetv.Models.Category;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
    List<Category> categoryList;

    public CategoryAdapter(List<Category> categoryList) {
        this.categoryList = categoryList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_view,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.catTitle.setText(categoryList.get(position).getName());
        Picasso.get().load(categoryList.get(position).getImage_url()).into(holder.cateImage);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), CategoryDetails.class);
                i.putExtra("category",categoryList.get(position));
                v.getContext().startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView cateImage;
        TextView catTitle;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cateImage = itemView.findViewById(R.id.catImage);
            catTitle = itemView.findViewById(R.id.catTitle);
        }
    }
}
