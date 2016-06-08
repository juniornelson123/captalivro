package com.example.junior.captalivrosv2;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.junior.captalivrosv2.domain.Book;
import com.example.junior.captalivrosv2.domain.User;

import java.io.ByteArrayOutputStream;

public class EditBookActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText titulo, autor, ano, serie, preco;
    private RadioGroup estadoConserv;
    private String estado;

    private Button btnSalvar;
    private ImageView fotoCadastro;
    private byte[] img;

    //Entidade que sera atualizada
    Book livro;

    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_book);

        mToolbar = (Toolbar) findViewById(R.id.toobar_details);
        mToolbar.setTitle("Editar Livro");
        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        mToolbar.setSubtitleTextColor(getResources().getColor(android.R.color.white));

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        //REcuperar id do livro

        livro = Book.findById(Book.class, getIntent().getExtras().getLong("id"));


        titulo = (EditText) findViewById(R.id.tituloLivro);
        autor = (EditText) findViewById(R.id.autorLivro);
        ano = (EditText) findViewById(R.id.anoLivro);
        serie = (EditText) findViewById(R.id.serieLivro);

        //Capturando resultado radio button
        estadoConserv = (RadioGroup) findViewById(R.id.estadoLivro);

        if (livro.getConserv() != null) {
            switch (livro.getConserv()) {
                case "Otimo":
                    estadoConserv.check(R.id.otimo);
                    break;
                case "Bom":
                    estadoConserv.check(R.id.bom);
                    break;
                case "Ruim":
                    estadoConserv.check(R.id.ruim);
                    break;
                default:
            }


        }

        preco = (EditText) findViewById(R.id.precoLivro);
        btnSalvar=(Button) findViewById(R.id.btn_atualizar);
        btnSalvar.setOnClickListener(this);
        fotoCadastro = (ImageView) findViewById(R.id.foto_cadastro);
        fotoCadastro.setOnClickListener(this);

        byte[] fotoArray;

        fotoArray = livro.getImage();

        if (fotoArray.length != 0) {
            fotoCadastro.setImageBitmap(BitmapFactory.decodeByteArray(fotoArray, 0, fotoArray.length));

        } else {

            fotoCadastro.setImageResource(R.drawable.img);

        }

        titulo.setText(livro.getTitle());
        autor.setText(livro.getAuthor());
        ano.setText(livro.getYear());
        serie.setText(livro.getSerie());
        preco.setText(String.valueOf(livro.getPrice()));


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_edit_book,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }


        if(item.getItemId() == R.id.camera){

            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent, 1);
        }
        return true;
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                fotoCadastro.setImageBitmap(bitmap);
                ByteArrayOutputStream saida = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, saida);
                img = saida.toByteArray();
                livro.setImage(img);
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "Cancelou", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Saiu", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btn_atualizar:
                Double price = Double.parseDouble(preco.getText().toString());

                int op = estadoConserv.getCheckedRadioButtonId();

                switch (op) {
                    case R.id.otimo:
                        estado = "Otimo";
                        break;
                    case R.id.bom:
                        estado = "Bom";
                        break;
                    case R.id.ruim:
                        estado = "Ruim";
                        break;

                }


                livro.setTitle(titulo.getText().toString());
                livro.setAuthor(autor.getText().toString());
                livro.setYear(ano.getText().toString());
                livro.setSerie(serie.getText().toString());
                livro.setConserv(estado);
                livro.setPrice(price);

                livro.save();

                Toast.makeText(this, livro.getTitle() + " atualizado com sucesso", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, MainActivity.class));
                finish();
                break;
        }
    }

}
