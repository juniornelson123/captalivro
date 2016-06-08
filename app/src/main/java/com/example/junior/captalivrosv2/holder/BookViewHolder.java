package com.example.junior.captalivrosv2.holder;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.junior.captalivrosv2.DetailsBookActivity;
import com.example.junior.captalivrosv2.EditBookActivity;
import com.example.junior.captalivrosv2.R;

/**
 * Created by junior on 24/05/2016.
 */
public class BookViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    public ImageView image;
    public TextView title,author,id;
    Context c;

    public BookViewHolder(View itemView, Context c) {
        super(itemView);
        this.c=c;
        id=(TextView) itemView.findViewById(R.id.book_id);
        image=(ImageView) itemView.findViewById(R.id.iv_book);
        title=(TextView) itemView.findViewById(R.id.tv_title);
        author=(TextView) itemView.findViewById(R.id.tv_author);

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        //Toast.makeText(c, id.getText(), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(c, DetailsBookActivity.class);

        intent.putExtra("id",id.getText());

        c.startActivity(intent);
    }
}
