package com.example.junior.captalivrosv2.adapter;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.junior.captalivrosv2.R;
import com.example.junior.captalivrosv2.domain.Book;
import com.example.junior.captalivrosv2.holder.BookViewHolder;

import java.util.List;

/**
 * Created by junior on 24/05/2016.
 */
public class BookAdapter extends RecyclerView.Adapter<BookViewHolder> {
    private LayoutInflater inflater;
    private List<Book> mList;
    private Context context;
    public BookAdapter(List<Book> l, Context c){
        inflater=LayoutInflater.from(c);
        mList=l;
        context=c;
    }
    @Override
    public BookViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v=inflater.inflate(R.layout.item_list_book,parent,false);
        BookViewHolder bvh=new BookViewHolder(v,context);

        return bvh;
    }

    @Override
    public void onBindViewHolder(BookViewHolder holder, int position) {

        byte[] fotoArray;

        fotoArray=mList.get(position).getImage();
        if(fotoArray.length != 0) {
            holder.image.setImageBitmap(BitmapFactory.decodeByteArray(fotoArray, 0, fotoArray.length));

        }else{

            holder.image.setImageResource(android.R.drawable.ic_menu_camera);

        }

        holder.title.setText(mList.get(position).getTitle());
        holder.author.setText(mList.get(position).getAuthor());

        //Convertendo id para String
        String idBook=String.valueOf(mList.get(position).getId());

        holder.id.setText(idBook);


    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}
