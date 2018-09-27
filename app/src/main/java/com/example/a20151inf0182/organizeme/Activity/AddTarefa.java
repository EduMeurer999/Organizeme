package com.example.a20151inf0182.organizeme.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.SearchView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.example.a20151inf0182.organizeme.DAO.ConfiguracaoFirebase;
import com.example.a20151inf0182.organizeme.Entidades.Usuarios;
import com.example.a20151inf0182.organizeme.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;


public class AddTarefa extends AppCompatActivity {



    private FirebaseAuth Auth;
    private FirebaseUser usuarioConectado;
    private DatabaseReference Database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_tarefa);

        Database = ConfiguracaoFirebase.getDatabaseReference();
        Auth = ConfiguracaoFirebase.getFirebaseAuth();
        final Usuarios usuario = new Usuarios();


        final EditText edtNomeTarefa = (EditText) findViewById(R.id.edtNomeTarefa);
        final EditText edtMateria = (EditText) findViewById(R.id.edtMateria);
        final EditText edtFazer = (EditText) findViewById(R.id.edtFazer);
        final EditText edtTempoEntrega = (EditText) findViewById(R.id.edtTempoEntrega);
        final EditText edtTempoPrevisto = (EditText) findViewById(R.id.edtTempoPrevisto);
        final EditText edtIntegrantes = (EditText) findViewById(R.id.edtIntegrantes);
        final TextView tvSituacao = (TextView) findViewById(R.id.tvSituacao);

        edtIntegrantes.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Database.child("Usuarios").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for(DataSnapshot snapshot: dataSnapshot.getChildren()){
                            if(edtIntegrantes.getText().toString().equals(snapshot.child("email").getValue().toString())){
                                tvSituacao.setText("Usuario existe");
                                tvSituacao.setTextColor(Color.GREEN);
                                break;
                            }
                            else{
                                tvSituacao.setText("Usuario não encontrado");
                                tvSituacao.setTextColor(Color.RED);
                            }

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });




        final Button btnCadastrar = (Button) findViewById(R.id.btnCadastrar);
        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<EditText> edts = new ArrayList<EditText>();
                edts.add(edtFazer);
                edts.add(edtNomeTarefa);
                edts.add(edtMateria);
                edts.add(edtTempoEntrega);
                edts.add(edtTempoPrevisto);
                edts.add(edtIntegrantes);
                boolean b = false;
                for(EditText edtAtual: edts){
                    b = edtAtual.equals("");
                    if (b){
                        break;
                    }
                    else{
                        b= false;
                    }
                }
                if(!b){
                    
                }
            }
        });

    }


}
