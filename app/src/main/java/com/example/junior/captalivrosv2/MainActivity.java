package com.example.junior.captalivrosv2;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.junior.captalivrosv2.adapter.TabsAdapter;
import com.example.junior.captalivrosv2.dao.SessionManager;
import com.example.junior.captalivrosv2.domain.Seller;
import com.example.junior.captalivrosv2.domain.User;
import com.example.junior.captalivrosv2.fragments.ListBookFragment;
import com.example.junior.captalivrosv2.fragments.ListBookSellerFragment;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

public class MainActivity extends AppCompatActivity implements ListBookFragment.OnFragmentInteractionListener,
        ListBookSellerFragment.OnFragmentInteractionListener{
    //Toolbar
    private Toolbar mToolbar;

    //NavigationDrawerLeft
    private Drawer navLeft;

    //Tabs
    private TabLayout tab;
    private ViewPager viewPager;

    //AccountHeader
    AccountHeader header;

    //Sessao
    SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        session=new SessionManager(this);
       /* if(session.isLoggedIn() != true){
            startActivity(new Intent(this,LoginActivity.class));
        }*/


        //User user=session.getUserDetails().getId();
        Seller seller=Seller.findById(Seller.class,session.getUserDetails().getUserId());

        mToolbar=(Toolbar) findViewById(R.id.toobarMain);
        mToolbar.setNavigationIcon(R.drawable.ic_devices_white_24dp);
        mToolbar.setTitle("CaptaLivros");
        mToolbar.setSubtitle("Id: "+String.valueOf(session.getUserDetails().getId())+" Nome: "+seller.getName() + " Id: "+seller.getId());
        setSupportActionBar(mToolbar);


        viewPager=(ViewPager) findViewById(R.id.view_pager);
        viewPager.setAdapter(new TabsAdapter(getSupportFragmentManager(),this));


        tab=(TabLayout) findViewById(R.id.tabLayout);
        tab.setupWithViewPager(viewPager);

        Seller box=Seller.findById(Seller.class,session.getUserDetails().getUserId());




        
        //Account header


        header=new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.color.primary)
                .addProfiles(
                        new ProfileDrawerItem().withName(box.getName()).withEmail(box.getEmail()).withIcon(android.R.drawable.sym_def_app_icon)
                )
                .build();

        //NavDrawer
        navLeft= new DrawerBuilder()
                .withActivity(this)
                .withToolbar(mToolbar)
                .withDisplayBelowStatusBar(true)
                .withActionBarDrawerToggleAnimated(true)
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {

                         switch (position){

                            case 1:
                                viewPager.setCurrentItem(0);
                                break;
                            case 2:
                                viewPager.setCurrentItem(1);
                                break;
                            case 3:
                                startActivity(new Intent(MainActivity.this,RegisterBookActivity.class));
                                break;
                            case 5:
                                Toast.makeText(MainActivity.this, "About us", Toast.LENGTH_SHORT).show();

                                break;
                            case 6:
                                session.logoutUser();
                                startActivity(new Intent(MainActivity.this,LoginActivity.class));
                                finish();
                                break;
                        }

                        return false;
                    }
                })
                .withOnDrawerItemLongClickListener(new Drawer.OnDrawerItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(View view, int position, IDrawerItem drawerItem) {
                        return false;
                    }
                })
                .withAccountHeader(header)
                .build();






        navLeft.addItem(new PrimaryDrawerItem().withName("LISTA LIVROS"));
        navLeft.addItem(new PrimaryDrawerItem().withName("MEUS LIVROS"));
        navLeft.addItem(new PrimaryDrawerItem().withName("CADASTRAR lIVRO"));
        navLeft.addItem(new DividerDrawerItem());
        navLeft.addItem(new PrimaryDrawerItem().withName("About Us"));
        navLeft.addItem(new PrimaryDrawerItem().withName("Sair"));




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.adicionar){

            startActivity(new Intent(MainActivity.this,RegisterBookActivity.class));

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
