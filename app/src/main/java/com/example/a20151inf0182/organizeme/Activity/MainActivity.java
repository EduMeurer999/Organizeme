package com.example.a20151inf0182.organizeme.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.a20151inf0182.organizeme.R;

public class MainActivity extends AppCompatActivity {

    private EditText etdEmail;
    private EditText etdSenha;
    private Button btnLogar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView txtCadastro = findViewById(R.id.txtCadastro);
        txtCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent telaCadastro = new Intent(MainActivity.this, CadastroActivity.class);
                startActivity(telaCadastro);
            }
        });
        //fazer a tela de login video aula 3
        // edtEmail = (EditText) findViewById(R.id.ed)
    }
}
