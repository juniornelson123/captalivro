package com.example.junior.captalivrosv2;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.junior.captalivrosv2.dao.SessionManager;
import com.example.junior.captalivrosv2.domain.Seller;
import com.example.junior.captalivrosv2.domain.User;

import java.util.List;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText usuario, password;
    private Button entrar;
    private TextView cadastro;
    private Toolbar mToolbar;
    private SessionManager session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        session=new SessionManager(this);
       if(session.isLoggedIn()) {
           if(!session.getUserDetails().getTypeUser()) {
               startActivity(new Intent(this, MainActivity.class));
               finish();

           }
        }


        mToolbar=(Toolbar) findViewById(R.id.toolbar_login);
          mToolbar.setTitle("CaptaLivros");
        mToolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
        mToolbar.setNavigationIcon(R.drawable.ic_storage_white_24dp);


        usuario = (EditText) findViewById(R.id.usuario);
        password = (EditText) findViewById(R.id.password);

        entrar = (Button) findViewById(R.id.btn_entrar);
        entrar.setOnClickListener(this);
        cadastro = (TextView) findViewById(R.id.txt_login);
        cadastro.setOnClickListener(this);

    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_entrar:
                if(!usuario.getText().equals("") && !password.getText().equals("")){
                   List<User> usuarios = User.findWithQuery(User.class, "Select * from User where username=? and password=?", usuario.getText().toString(), password.getText().toString());
                    if (!usuarios.isEmpty()) {
                        session.createLoginSession(usuarios.get(0));
                        Toast.makeText(LoginActivity.this, "Usuario logado " + session.getUserDetails().getUsername(), Toast.LENGTH_SHORT).show();
                       if(!usuarios.get(0).getTypeUser()){
                            startActivity(new Intent(LoginActivity.this, MainActivity.class));
                       }
                        finish();

                    }
                   }else if(usuario.getText().equals("") && password.getText().equals("")){
                    Toast.makeText(LoginActivity.this, "Por favor preencher todos os campos corretamente", Toast.LENGTH_SHORT).show();
                    }

                break;

           case R.id.txt_login:
                AlertDialog.Builder builder=new AlertDialog.Builder(LoginActivity.this);
                builder.setMessage("Vamos iniciar seu cadastro!!!!")
                        .setPositiveButton("Sou Cliente", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                startActivity(new Intent(LoginActivity.this,RegisterUserActivity.class));
                            }
                        })
                        .setNegativeButton("Sou vendedor", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                startActivity(new Intent(LoginActivity.this, RegisterSellerActivity.class));
                            }
                        }
                        );

                AlertDialog dialog=builder.create();
                dialog.show();
                //SelectDialogFragment dialog=new SelectDialogFragment();

                //startActivity(new Intent(this,RegisterSellerActivity.class));
                // Toast.makeText(LoginActivity.this, "Efetuar Login", Toast.LENGTH_SHORT).show();
                break;

        }
    }
}
