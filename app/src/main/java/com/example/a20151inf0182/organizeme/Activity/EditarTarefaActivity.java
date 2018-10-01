package com.example.a20151inf0182.organizeme.Activity;

import android.content.Intent;
import android.os.Bundle;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.a20151inf0182.organizeme.DAO.ConfiguracaoFirebase;
import com.example.a20151inf0182.organizeme.Entidades.Tarefas;
import com.example.a20151inf0182.organizeme.Entidades.Usuarios;
import com.example.a20151inf0182.organizeme.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class EditarTarefaActivity extends AppCompatActivity {

    private Tarefas tarefa;
    private Usuarios usuario;
    private DatabaseReference Database;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editar_tarefa);
        Database = ConfiguracaoFirebase.getDatabaseReference();
        tarefa = (Tarefas) getIntent().getSerializableExtra("tarefa");
        usuario = (Usuarios) getIntent().getSerializableExtra("usuario");
        final EditText edtSubProp = findViewById(R.id.edtTarefaProp);
        final EditText edtSubInt = findViewById(R.id.edtTarefaIntegrante);
        String idIntegrante = "";
        String integranteStr = "";
        String fazerIntegrante = "";
        boolean isIntegrante = false;

        if (usuario.getEmail().equals(tarefa.getProprietario())) {
            findViewById(R.id.layoutIntegrante).setVisibility(View.INVISIBLE);
            edtSubProp.setText(tarefa.getSubTarefaProp());
        } else {
            for (String[] integrante : tarefa.getIntegrantes()) {
                if (integrante[0].equals(usuario.getEmail())) {
                    integranteStr = integrante[0];
                    fazerIntegrante = integrante[1];
                    idIntegrante = integrante[2];
                    break;
                }
            }
            findViewById(R.id.layoutProp).setVisibility(View.INVISIBLE);
            edtSubInt.setText(fazerIntegrante);
            findViewById(R.id.layoutIntegrantes).setVisibility(View.INVISIBLE);
            isIntegrante = true;

        }


        final EditText edtNomeTarefa = (EditText) findViewById(R.id.edtNomeTarefa);
        final EditText edtMateria = (EditText) findViewById(R.id.edtMateria);
        final EditText edtFazer = (EditText) findViewById(R.id.edtFazer);
        final EditText edtTempoEntrega = (EditText) findViewById(R.id.edtTempoEntrega);

        final Button btnEditar = (Button) findViewById(R.id.btnEditarTarefa);

        edtNomeTarefa.setText(tarefa.getNomeTarefa());
        edtMateria.setText(tarefa.getMateria());
        edtFazer.setText(tarefa.getaFazer());
        edtTempoEntrega.setText(tarefa.getTempoEntrega());



        final String finalIdIntegrante = idIntegrante;
        final String finalNomeIntegrante = integranteStr;
        final boolean finalIsIntegrante = isIntegrante;

        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String idTarefa = tarefa.getIdTarefa();
                String fazer = edtFazer.getText().toString();
                String nomeTarefa = edtNomeTarefa.getText().toString();
                String dataEntrega = edtTempoEntrega.getText().toString();


                String materia = edtMateria.getText().toString();

                DatabaseReference tarefaBD = Database.child("Tarefas").child(tarefa.getIdTarefa());
                tarefaBD.child("nome").setValue(nomeTarefa);
                tarefaBD.child("fazer").setValue(fazer);
                tarefaBD.child("tempoEntrega").setValue(dataEntrega);

                tarefaBD.child("materia").setValue(materia);
                if (finalIsIntegrante) {
                    String subTarefa = edtSubInt.getText().toString();
                    tarefaBD.child("integrantes").child(finalIdIntegrante).child("fazer").setValue(subTarefa);
                } else {
                    String subTarefa = edtSubProp.getText().toString();
                    tarefaBD.child("SubTarefa Proprietario").setValue(subTarefa);
                }
                Toast.makeText(EditarTarefaActivity.this, "Tarefa editada com sucesso!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), TarefasActivity.class));
            }
        });

    }
}
