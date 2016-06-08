package com.example.junior.captalivrosv2;

import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.junior.captalivrosv2.dao.SessionManager;
import com.example.junior.captalivrosv2.domain.Book;
import com.example.junior.captalivrosv2.domain.Seller;

import org.w3c.dom.Text;

import java.util.List;

public class DetailsBookActivity extends AppCompatActivity {

    private ImageView image;
    private TextView title,author,year,serie,conserv,price,seller;
    private Toolbar mToolbar;
    private SessionManager session;

    private Book livro;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_book);
        session=new SessionManager(this);

        //Recuperando valores da intent
        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();

        //Convertendo id de String para Long
        Long idBook=Long.parseLong(bundle.getString("id"));

        //Recupera Livro selecionado
        livro=Book.findById(Book.class,idBook);


        //Recupera Vendedor do Livro
        Seller vendedor=  Seller.findById(Seller.class,session.getUserDetails().getUserId());



        mToolbar=(Toolbar) findViewById(R.id.toobar_details);


        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        setSupportActionBar(mToolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        CollapsingToolbarLayout cToolbar=(CollapsingToolbarLayout) findViewById(R.id.colapse);
        cToolbar.setTitle(livro.getTitle());

        cToolbar.setCollapsedTitleTextColor(getResources().getColor(R.color.colorPrimary));
        cToolbar.setTitle("Detalhes");
        cToolbar.setContentScrimColor(getResources().getColor(R.color.colorPrimary));
        cToolbar.setCollapsedTitleTextColor(getResources().getColor(android.R.color.white));






//Convertendo imagem para bitmap
        byte[] fotoArray;

        fotoArray=livro.getImage();

        image=(ImageView) findViewById(R.id.imagem_livro);
        title=(TextView) findViewById(R.id.titulo_valor);
        author=(TextView) findViewById(R.id.autor_valor);
        year=(TextView) findViewById(R.id.ano_valor);
        serie=(TextView) findViewById(R.id.serie_valor);
        conserv=(TextView) findViewById(R.id.conserv_valor);
        price=(TextView) findViewById(R.id.preco_valor);
        seller=(TextView) findViewById(R.id.vendedor_valor);

        if(fotoArray.length != 0 ) {
            image.setImageBitmap(BitmapFactory.decodeByteArray(fotoArray, 0, fotoArray.length));

        }else{

            image.setImageResource(R.drawable.camera);

        }

        title.setText(livro.getTitle());
        author.setText(livro.getAuthor());
        year.setText(livro.getYear());
        serie.setText(livro.getSerie());
        conserv.setText(livro.getConserv());
        price.setText(String.valueOf(livro.getPrice()));
        seller.setText(vendedor.getBoxDescription());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if(!session.getUserDetails().getTypeUser()){
            List<Book> books=Book.findWithQuery(Book.class,"Select * from Book where user=?",String.valueOf(session.getUserDetails().getUserId()));
            if(verify(books)) {
                getMenuInflater().inflate(R.menu.menu_details_book, menu);
            }
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.editar){

            Intent intent=new Intent(this,EditBookActivity.class);
            intent.putExtra("id",livro.getId());

            startActivity(intent);
        }
        if(item.getItemId() == R.id.deletar){
            Toast.makeText(DetailsBookActivity.this, "Livro "+livro.getTitle()+" deletado", Toast.LENGTH_SHORT).show();
            livro.delete();

            startActivity(new Intent(this,MainActivity.class));
        }

        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public Boolean verify(List<Book> books){

        boolean result=false;
        for (Book book: books) {
            if (book.getId() == livro.getId()){
                result=true;
            }
        }
        return result;
    }
}
