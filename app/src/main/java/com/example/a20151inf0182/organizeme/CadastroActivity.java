package com.example.a20151inf0182.organizeme;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;



public class CadastroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        EditText edtNome = (EditText) findViewById(R.id.edtNome);
        EditText edtEmail = (EditText) findViewById(R.id.edtEmail);
        EditText edtSenha = (EditText) findViewById(R.id.edtSenha);
        EditText edtSenhaC = (EditText) findViewById(R.id.edtSenhaC);
        String nome = edtNome.getText().toString();
        String Email = edtEmail.getText().toString();
        Database db = new Database(getBaseContext(), "Usuarios.db", "create table if not exists usuarios ('id INTEGER PRIMARY KEY AUTOINCREMENT, nome VARCHAR)'");

    }
}
