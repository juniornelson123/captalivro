package com.example.junior.captalivrosv2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.junior.captalivrosv2.domain.Client;
import com.example.junior.captalivrosv2.domain.User;

public class RegisterUserActivity extends AppCompatActivity {
    private Toolbar toolbar_cadastro;
    private EditText nome, senha, email;
    private Button cadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        toolbar_cadastro = (Toolbar) findViewById(R.id.toolbar_cadastro);
        toolbar_cadastro.setTitle("Cadastro Usuário");
        toolbar_cadastro.setNavigationIcon(R.drawable.ic_arrow_left);
        setSupportActionBar(toolbar_cadastro);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        nome = (EditText)findViewById(R.id.cad_nome_user);
        senha = (EditText) findViewById(R.id.cad_senha_user);
        email = (EditText) findViewById(R.id.cad_email_user);

        cadastrar = (Button) findViewById(R.id.btn_cadastrar);
        cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!nome.getText().toString().equals("") && !senha.getText().toString().equals("") && !email.getText().toString().equals("")){
                    Client cliente = new Client(nome.getText().toString(), email.getText().toString());
                    cliente.save();
                    User user = new User(nome.getText().toString(), senha.getText().toString(), true, cliente.getId());
                    user.save();
                    Toast.makeText(RegisterUserActivity.this, "Cliente cadastrado com sucesso", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(RegisterUserActivity.this, LoginActivity.class));
                    finish();
                }else {
                    if (nome.getText().toString().equals("")) {
                        nome.setError("Campo vazio");
                    }
                    if (senha.getText().toString().equals("")) {
                        senha.setError("Campo vazio");
                    }
                    if (email.getText().toString().equals("")) {
                        email.setError("Campo vazio");
                    }
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

}

