package com.example.a20151inf0182.organizeme.Activity;

import android.content.Intent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;

import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;


import com.example.a20151inf0182.organizeme.DAO.ConfiguracaoFirebase;
import com.example.a20151inf0182.organizeme.Entidades.Usuarios;
import com.example.a20151inf0182.organizeme.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import com.google.firebase.database.DatabaseReference;




public class PerfilActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    private FirebaseUser usuarioConectado;
    private MainActivity login;
    private static final String TAG = "PerfilActivity";




    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

//
        mDatabase = ConfiguracaoFirebase.getDatabaseReference();
        mAuth = ConfiguracaoFirebase.getFirebaseAuth();
        usuarioConectado = mAuth.getCurrentUser();

        final TextView tvNome = (TextView) findViewById(R.id.txtNome);
        final TextView tvIdade = (TextView) findViewById(R.id.txtIdade);
        final TextView tvCurso = (TextView) findViewById(R.id.txtCurso);
        final TextView tvEmail = (TextView) findViewById(R.id.txtEmail);
        final TextView tvSerie = (TextView) findViewById(R.id.txtSerie);
        final Button btnEditar = (Button) findViewById(R.id.btnEditar);

        Intent i = getIntent();
        Usuarios usuario = (Usuarios) i.getSerializableExtra("Usuario");




        tvNome.setText("Nome: "+usuario.getNome());
        tvEmail.setText("Email: "+usuario.getEmail());

        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PerfilActivity.this, AddTarefa.class));
            }
        });
        Button btnLogout = (Button) findViewById(R.id.btnLogout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                startActivity(new Intent(PerfilActivity.this, MainActivity.class));
            }
        });



    }
//    public void btnEditarAction(){
//        btnEditar.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(PerfilActivity.this, TesteActivity.class));
//            }
//        });
//    }



}
