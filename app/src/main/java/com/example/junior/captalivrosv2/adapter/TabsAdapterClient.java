package com.example.junior.captalivrosv2.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.junior.captalivrosv2.fragments.ListBookFragment;

/**
 * Created by junior on 24/05/2016.
 */
public class TabsAdapterClient extends FragmentPagerAdapter {
    private Context context;
    private String[] titles={"Lista de Livros"};

    public TabsAdapterClient(FragmentManager fm, Context c){
        super(fm);

        context=c;


    }
    @Override
    public Fragment getItem(int position) {
        Fragment frag=null;

        if(position == 0){
            frag=new ListBookFragment();
        }

        Bundle bundle=new Bundle();
        bundle.putInt("position",position);

        frag.setArguments(bundle);

        return frag;
    }

    @Override
    public int getCount() {
        return titles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
