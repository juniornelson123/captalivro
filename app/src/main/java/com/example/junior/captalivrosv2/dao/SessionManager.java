package com.example.junior.captalivrosv2.dao;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.example.junior.captalivrosv2.LoginActivity;
import com.example.junior.captalivrosv2.domain.Book;
import com.example.junior.captalivrosv2.domain.User;


/**
 * Created by junior on 18/05/2016.
 */
public class SessionManager {
    SharedPreferences shared;
    SharedPreferences.Editor editor;
    Context _context;
    int PRIVATE_MODE = 0;
    private static final String PREF_NAME = "CaptaSessionTeste2LivrosPref";
    private static final String IS_LOGIN = "IsLoggedIn";
    public static final String KEY_NAME = "name";
    public static final String TIPO_USUARIO = "tipo_usuario";
    public static final String PASSWORD="password";
    public static final String KEY_ID = "id";
    public static final String KEY_USER_ID = "UserId";
    public static final String KEY_ID_GCM_USER = "NameUser";

    public SessionManager(Context context) {
        this._context = context;
        shared = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = shared.edit();

    }

    public void createLoginSession(User user) {

        editor.putBoolean(IS_LOGIN,true);
        editor.putLong(KEY_ID,user.getId());
        editor.putString(KEY_NAME, user.getUsername());
        editor.putBoolean(TIPO_USUARIO, user.getTypeUser());
        editor.putString(PASSWORD,user.getPassword());
        editor.putLong(KEY_USER_ID,user.getUserId());

        editor.commit();
    }

    public void checkLogin() {
        // Check login status
        if (!this.isLoggedIn()) {
            Toast.makeText(_context, "Por favor, faça login novamente.",
                    Toast.LENGTH_SHORT).show();
            // user is not logged in redirect him to Login Activity
            Intent i = new Intent(_context, LoginActivity.class);
            // Closing all the Activities
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            // Add new Flag to start new Activity
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            // Staring Login Activity
            _context.startActivity(i);
        }

    }

    public boolean isLoggedIn() {
        return shared.getBoolean(IS_LOGIN, false);
    }

    public void logoutUser() {
        editor.putBoolean(IS_LOGIN,false);
        editor.clear();
        editor.commit();

        Intent i = new Intent(_context, LoginActivity.class);

        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        _context.startActivity(i);

    }

    public User getUserDetails() {
        User usuario = new User();

        usuario.setId(shared.getLong(KEY_ID,0));
        usuario.setUsername(shared.getString(KEY_NAME, "Usuário não conectado."));
        usuario.setTypeUser(shared.getBoolean(TIPO_USUARIO,false));
        usuario.setPassword(shared.getString(PASSWORD,"Senha nao encontrada"));
        usuario.setUser_id(shared.getLong(KEY_USER_ID,0));

        return usuario;
    }


}
