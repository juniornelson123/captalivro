package com.example.junior.captalivrosv2;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.renderscript.Double2;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.junior.captalivrosv2.dao.SessionManager;
import com.example.junior.captalivrosv2.domain.Book;
import com.example.junior.captalivrosv2.domain.Seller;
import com.example.junior.captalivrosv2.domain.User;

import java.io.ByteArrayOutputStream;

public class RegisterBookActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText titulo,autor,ano,serie,preco;
    private RadioGroup estadoConserv;
    private String estado;

    private Button btnSalvar;
    private ImageView fotoCadastro;
    private byte[] img;

    private SessionManager session;
    private User usuarioLogado;

    private Toolbar toolbarBook;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_book);
        session=new SessionManager(this);

        usuarioLogado= session.getUserDetails();

        toolbarBook=(Toolbar) findViewById(R.id.toobarBook);

        toolbarBook.setTitle("Cadastrar Livro");
        toolbarBook.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        toolbarBook.setTitleTextColor(getResources().getColor(android.R.color.white));

        setSupportActionBar(toolbarBook);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        titulo=(EditText) findViewById(R.id.tituloLivro);
        autor=(EditText) findViewById(R.id.autorLivro);
        ano=(EditText) findViewById(R.id.anoLivro);
        serie=(EditText) findViewById(R.id.serieLivro);
        estadoConserv=(RadioGroup) findViewById(R.id.estadoLivro);
        preco=(EditText) findViewById(R.id.precoLivro);

        fotoCadastro=(ImageView) findViewById(R.id.foto_cadastro);
        fotoCadastro.setOnClickListener(this);
        btnSalvar=(Button) findViewById(R.id.btn_cadastrar);
        btnSalvar.setOnClickListener(this);


        //Capturando resultado radio button


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_register_book,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.foto){
            Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent,1);
        }

        if (item.getItemId() == android.R.id.home){
            startActivity(new Intent(this,MainActivity.class));
            finish();
        }
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_cadastrar:
                Double price;
                if(preco.length() !=0 ){
                    price=Double.parseDouble(preco.getText().toString());


                }else {
                    price=0.0;
                }

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
                    default:
                        estado = "Bom";
                        break;
                }

                User user = User.findById(User.class, usuarioLogado.getId());

                Book livro = new Book(titulo.getText().toString(), autor.getText().toString(), ano.getText().toString(), serie.getText().toString(), estado, price, user, img);
                livro.save();

                Toast.makeText(RegisterBookActivity.this, "Salvo com sucesso para o id " + user.getUserId(), Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, MainActivity.class));
                finish();
                break;
            case R.id.foto_cadastro:
                Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,1);
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1){
            if(resultCode == RESULT_OK){
                Bitmap bitmap=(Bitmap) data.getExtras().get("data");
                fotoCadastro.setImageBitmap(bitmap);
                ByteArrayOutputStream saida=new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG,100,saida);
                img=saida.toByteArray();
            }else if(resultCode==RESULT_CANCELED){
                Toast.makeText(RegisterBookActivity.this, "Cancelou", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(RegisterBookActivity.this, "Saiu", Toast.LENGTH_SHORT).show();
            }
        }
    }






}

