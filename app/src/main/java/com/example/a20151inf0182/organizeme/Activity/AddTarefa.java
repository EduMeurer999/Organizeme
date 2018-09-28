package com.example.a20151inf0182.organizeme.Activity;


import android.app.Dialog;
import android.content.DialogInterface;

import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.example.a20151inf0182.organizeme.DAO.ConfiguracaoFirebase;
import com.example.a20151inf0182.organizeme.Entidades.Tarefas;
import com.example.a20151inf0182.organizeme.Entidades.Usuarios;
import com.example.a20151inf0182.organizeme.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;


public class AddTarefa extends AppCompatActivity {


    private FirebaseAuth Auth;
    private FirebaseUser usuarioConectado;
    private DatabaseReference Database;
    private Usuarios usuario;
    private Tarefas tarefa;
    EditText input;
    EditText input2;
    TextView tvIntegrantes;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_tarefa);
        Database = ConfiguracaoFirebase.getDatabaseReference();
        Auth = ConfiguracaoFirebase.getFirebaseAuth();

        usuario = (Usuarios) getIntent().getSerializableExtra("Usuario");
        tarefa = new Tarefas();

        final ArrayList<ArrayList<String>> integrantes = new ArrayList<>();
        final EditText edtNomeTarefa = (EditText) findViewById(R.id.edtNomeTarefa);
        final EditText edtMateria = (EditText) findViewById(R.id.edtMateria);
        final EditText edtFazer = (EditText) findViewById(R.id.edtFazer);
        final EditText edtTempoEntrega = (EditText) findViewById(R.id.edtTempoEntrega);
        final EditText edtTempoPrevisto = (EditText) findViewById(R.id.edtTempoPrevisto);
        tvIntegrantes = (TextView) findViewById(R.id.tvIntegrantes);

        LayoutInflater li = getLayoutInflater();
        View view = li.inflate(R.layout.alert_dialog, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setTitle("Integrantes");
        builder.setView(R.layout.alert_dialog);


        //SET posistive Button
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                final EditText edtEmail = (EditText) ((Dialog) dialog).findViewById(R.id.email);
                final EditText fazer = (EditText) ((Dialog) dialog).findViewById(R.id.fazer);
                if (
                        !edtEmail.getText().toString().equals("") &&
                                !edtEmail.getText().toString().equals("")) {

                    Database.child("Usuarios")
                            .addListenerForSingleValueEvent(
                                    new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                            for (DataSnapshot snapshot :
                                                    dataSnapshot.getChildren()) {
                                                if (snapshot.child("email").getValue().toString()
                                                        .equals(edtEmail.getText().toString())) {
                                                    integrantes.add(new ArrayList<String>(Arrays.asList(
                                                            edtEmail.getText().toString(),
                                                            fazer.getText().toString())));

                                                    atualizarTXT(integrantes);
                                                    break;
                                                }
                                            }
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError databaseError) {

                                        }
                                    }

                            );

                } else {
                    Toast.makeText(getApplicationContext(), "Nenhum campo deve estar em branco", Toast.LENGTH_SHORT).show();
                }
            }
        });


        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });


        final AlertDialog ad = builder.create();
        Button btnAddIntegrantes = (Button) findViewById(R.id.btnAddIntegrantes);
        btnAddIntegrantes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ad.show();


            }
        });

        Button removerIntegrante = (Button) findViewById(R.id.btnDropIntegrante);
        removerIntegrante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                integrantes.remove(integrantes.size() - 1);
                atualizarTXT(integrantes);
            }
        });
    }

    public void atualizarTXT(ArrayList<ArrayList<String>> arr) {
        for (ArrayList<String> tmpArr : arr) {
            tvIntegrantes.setText("" + tmpArr.get(0).toString() + "\n");
        }
    }
}





