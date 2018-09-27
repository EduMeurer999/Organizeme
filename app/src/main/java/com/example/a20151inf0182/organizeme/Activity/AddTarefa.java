package com.example.a20151inf0182.organizeme.Activity;


import android.content.DialogInterface;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import com.example.a20151inf0182.organizeme.DAO.ConfiguracaoFirebase;
import com.example.a20151inf0182.organizeme.Entidades.Usuarios;
import com.example.a20151inf0182.organizeme.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

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
        final AlertDialog alert;

        final ArrayList<String[]> integrantes = new ArrayList<>();
        final EditText edtNomeTarefa = (EditText) findViewById(R.id.edtNomeTarefa);
        final EditText edtMateria = (EditText) findViewById(R.id.edtMateria);
        final EditText edtFazer = (EditText) findViewById(R.id.edtFazer);
        final EditText edtTempoEntrega = (EditText) findViewById(R.id.edtTempoEntrega);
        final EditText edtTempoPrevisto = (EditText) findViewById(R.id.edtTempoPrevisto);

        final Button btnAddIntegrantes = (Button) findViewById(R.id.btnAddIntegrantes);
        btnAddIntegrantes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final TextView tvEmail = new TextView(AddTarefa.this);
                tvEmail.setText("E-mail: ");
                final EditText email = new EditText(AddTarefa.this);
                final EditText tvFazer = new EditText(AddTarefa.this);
                tvFazer.setText("O que irá fazer: ");
                final EditText fazer = new EditText(AddTarefa.this);
                final AlertDialog builder = new AlertDialog.Builder(AddTarefa.this)
                .setTitle("Integrante")

                .setView(tvEmail)

                .setView(email)

                .setView(tvFazer)

               .setView(fazer)

                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (!email.getText().toString().equals("") && !fazer.getText().toString().equals("")) {
                            integrantes.add(new String[]{email.getText().toString(), fazer.getText().toString()});
                        }
                    }
                })
                .create();
                builder.show();


            }
        });

//
//
//
//      edtIntegrantes.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                Database.child("Usuarios").addListenerForSingleValueEvent(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                        for(DataSnapshot snapshot: dataSnapshot.getChildren()){
//                            if(edtIntegrantes.getText().toString().equals(snapshot.child("email").getValue().toString())){
//                                tvSituacao.setText("Usuario existe");
//                                tvSituacao.setTextColor(Color.GREEN);
//                                break;
//                            }
//                            else{
//                                tvSituacao.setText("Usuario não encontrado");
//                                tvSituacao.setTextColor(Color.RED);
//                            }
//
//                        }
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                    }
//                });
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//            }
//        });


    }


}

