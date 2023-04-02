package edu.sabanciuniv.myapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CommentsAdapter extends RecyclerView.Adapter<CommentsAdapter.CommentViewHolder>
{

    private Context ctx;
    private List<Comments> data;

    public CommentsAdapter(Context ctx, List<Comments> data){
        this.ctx = ctx;
        this.data = data;
    }


    @NonNull
    @Override
    public CommentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View root= LayoutInflater.from(ctx).inflate(R.layout.comment_row_layout,parent,false);

        CommentViewHolder holder = new CommentViewHolder(root);
        holder.setIsRecyclable(false);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CommentViewHolder holder, int position) {

        holder.ListName.setText(data.get(holder.getAdapterPosition()).getName());
        holder.ListMessage.setText(data.get(holder.getAdapterPosition()).getMtext());


    }

    @Override
    public int getItemCount() {
        return data.size();
    }



    class CommentViewHolder extends RecyclerView.ViewHolder
    {

        TextView ListName;
        TextView ListMessage;
        ConstraintLayout row;

        public CommentViewHolder(@NonNull View itemView)
        {
            super(itemView);

            ListName = itemView.findViewById(R.id.ListName);
            ListMessage = itemView.findViewById(R.id.ListMessage);
            row = itemView.findViewById(R.id.row_comment);

        }

    }

}


