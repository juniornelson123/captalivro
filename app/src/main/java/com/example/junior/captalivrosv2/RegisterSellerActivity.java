package com.example.junior.captalivrosv2;

import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.junior.captalivrosv2.domain.Seller;
import com.example.junior.captalivrosv2.domain.User;

import java.io.ByteArrayOutputStream;
import java.util.List;

public class RegisterSellerActivity extends AppCompatActivity {
    private Toolbar toolbar_cadastro;
    private EditText descricao, cnpj, nomeProprietario, senha, email, fone;
    private Button cadastrar;
    private ImageView fotoPerfil;

    private byte[] img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_seller);

        toolbar_cadastro = (Toolbar) findViewById(R.id.toolbar_cadastro);
        toolbar_cadastro.setTitle("Cadastro Vendedor");
        toolbar_cadastro.setTitleTextColor(getResources().getColor(android.R.color.white));
        toolbar_cadastro.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        setSupportActionBar(toolbar_cadastro);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        descricao = (EditText) findViewById(R.id.numero_box);
        cnpj = (EditText) findViewById(R.id.cnpj);
        nomeProprietario = (EditText) findViewById(R.id.nome);
        senha = (EditText) findViewById(R.id.senha_boxe);
        email = (EditText) findViewById(R.id.email);
        fone = (EditText) findViewById(R.id.fone);

        fotoPerfil = (ImageView) findViewById(R.id.image_perfil);
        fotoPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,1);
            }
        });

        cadastrar = (Button) findViewById(R.id.btn_cadastrar);
        cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(!verifySeller()){
                    Toast.makeText(RegisterSellerActivity.this, "Email informado ja esta cadastrado", Toast.LENGTH_SHORT).show();
                }else{

                    Seller banca = new Seller(descricao.getText().toString(), nomeProprietario.getText().toString(), cnpj.getText().toString(), fone.getText().toString(), email.getText().toString(),img);
                    banca.save();
                    User user = new User(descricao.getText().toString(), senha.getText().toString(), false, banca.getId());
                    user.save();
                    Toast.makeText(RegisterSellerActivity.this, "Boxe cadastrado com sucesso", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(RegisterSellerActivity.this, LoginActivity.class));
                    finish();
                }
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1){
            if(resultCode == RESULT_OK){
                Bitmap bitmap=(Bitmap) data.getExtras().get("data");
                fotoPerfil.setImageBitmap(bitmap);
                ByteArrayOutputStream saida=new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG,100,saida);
                img=saida.toByteArray();
            }else if(resultCode==RESULT_CANCELED){
                Toast.makeText(this, "Cancelou", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "Saiu", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public Boolean verifySeller(){
        boolean result=true;

        List<Seller> vendedor=Seller.listAll(Seller.class);

        for (Seller seller:vendedor) {
            if(seller.getBoxDescription().equals(descricao.getText())){

                result=false;
            }
        }


        return result;
    }

}
