package com.example.a20151inf0182.organizeme.Activity;


import android.app.Dialog;
import android.content.DialogInterface;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
    private Usuarios usuario;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_tarefa);
        Database = ConfiguracaoFirebase.getDatabaseReference();
        Auth = ConfiguracaoFirebase.getFirebaseAuth();
        final AlertDialog alert;
        usuario = (Usuarios) getIntent().getSerializableExtra("Usuario");

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
                onCreateDialog(savedInstanceState);




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
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(AddTarefa.this);
        // Get the layout inflater
        LayoutInflater inflater = AddTarefa.this.getLayoutInflater();

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(inflater.inflate(R.layout.alert_dialog, null))
                // Add action buttons
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        // sign in the user ...
                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });
        return builder.create();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.opcPerfil:
                Intent i  = new Intent(this, PerfilActivity.class);
                i.putExtra("Usuario", usuario);
                startActivity(i);
                break;
            case R.id.opcAddTarefa:
                i = new Intent(this, AddTarefa.class);
                i.putExtra("Usuario", usuario);
                startActivity(i);
                break;
            case R.id.opcNotas:
                i = new Intent(this, NotasActivity.class);
                i.putExtra("Usuario", usuario);
                startActivity(i);
                break;
            case R.id.opcVerTarefas:
                i = new Intent(this, TarefasActivity.class);
                i.putExtra("Usuario", usuario);
                startActivity(i);
                break;
            case R.id.opcAjuda:
                i = new Intent(this, AjudaActivity.class);
                i.putExtra("Usuario", usuario);
                startActivity(i);
                break;
            case R.id.opcSair:
                mAuth.signOut();
                startActivity(new Intent(this, MainActivity.class));
                break;

        }
        return super.onOptionsItemSelected(item);



    }

}

